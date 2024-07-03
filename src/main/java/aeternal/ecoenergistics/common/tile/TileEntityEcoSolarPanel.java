package aeternal.ecoenergistics.common.tile;

import io.netty.buffer.ByteBuf;
import mekanism.api.TileNetworkList;
import mekanism.common.util.ChargeUtils;
import mekanism.common.util.MekanismUtils;
import mekanism.common.util.NonNullListSynchronized;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nonnull;

public abstract class TileEntityEcoSolarPanel extends TileEntityEcoGenerator {

    private static final String[] methods = new String[]{"getEnergy", "getOutput", "getMaxEnergy", "getEnergyNeeded", "getSeesSun", "getSeesMoon"};

    private boolean seesSun;
    private boolean seesMoon;
    private boolean needsRainCheck = true;
    private float peakOutput;


    public TileEntityEcoSolarPanel(String name, double maxEnergy, double output) {
        super("solar", name, maxEnergy, output);
        inventory = NonNullListSynchronized.withSize(1, ItemStack.EMPTY);
    }

    public boolean canSeeSun() {
        return seesSun;
    }

    public boolean canSeeMoon() {
        return seesMoon;
    }

    @Nonnull
    @Override
    public int[] getSlotsForFace(@Nonnull EnumFacing side) {
        return new int[]{0};
    }

    @Override
    public boolean canSetFacing(@Nonnull EnumFacing facing) {
        return super.canSetFacing(facing);
    }

    @Override
    public void validate() {
        super.validate();
        Biome b = world.provider.getBiomeForCoords(getPos());

        // Consider the best temperature to be 0.8; biomes that are higher than that
        // will suffer an efficiency loss (semiconductors don't like heat); biomes that are cooler
        // get a boost. We scale the efficiency to around 30% so that it doesn't totally dominate
        float tempEff = 0.3f * (0.8f - b.getTemperature(getPos()));

        // Treat rainfall as a proxy for humidity; any humidity works as a drag on overall efficiency.
        // As with temperature, we scale it so that it doesn't overwhelm production. Note the signedness
        // on the scaling factor. Also note that we only use rainfall as a proxy if it CAN rain; some dimensions
        // (like the End) have rainfall set, but can't actually support rain.
        float humidityEff = -0.3f * (b.canRain() ? b.getRainfall() : 0.0f);

        peakOutput = getConfiguredMax() * (1.0f + tempEff + humidityEff);
        needsRainCheck = b.canRain();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!world.isRemote) {
            ChargeUtils.charge(0, this);
            // Sort out if the generator can see the sun; we no longer check if it's raining here,
            // since under the new rules, we can still generate power when it's raining, albeit at a
            // significant penalty.
            if (canSeeSky() && !world.provider.isNether()) {
                seesSun = world.isDaytime();
                seesMoon = !world.isDaytime();
            }
            if (canOperate()) {
                setActive(true);
                if (canSeeSun()) {
                    setEnergy(getEnergy() + getProduction());
                } else if (canSeeMoon()) {
                    setEnergy(getEnergy() + getProductionNightTime());
                }
            } else {
                setActive(false);
            }
        }
    }

    protected boolean canSeeSky() {
        return world.canSeeSky(getPos());
    }

    @Override
    public boolean canExtractItem(int slotID, @Nonnull ItemStack itemstack, @Nonnull EnumFacing side) {
        if (slotID == 0) {
            return ChargeUtils.canBeOutputted(itemstack, true);
        }
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int slotID, @Nonnull ItemStack itemstack) {
        if (slotID == 0) {
            return ChargeUtils.canBeCharged(itemstack);
        }
        return true;
    }

    @Override
    public boolean canOperate() {
        return getEnergy() < getMaxEnergy() && MekanismUtils.canFunction(this);
    }

    public double getProduction() {
        // Get the brightness of the sun; note that there are some implementations that depend on the base
        // brightness function which doesn't take into account the fact that rain can't occur in some biomes.
        float brightness = world.getSunBrightnessFactor(1.0f);
        if (MekanismUtils.existsAndInstance(world.provider, "micdoodle8.mods.galacticraft.api.world.ISolarLevel")) {
            brightness *= ((ISolarLevel) world.provider).getSolarEnergyMultiplier();
        }

        // Production is a function of the peak possible output in this biome and sun's current brightness
        float production = peakOutput * brightness;

        // If the generator is in a biome where it can rain and it's raining penalize production by 80%
        if (needsRainCheck && (world.isRaining() || world.isThundering())) {
            production *= 0.2;
        }
        return production;
    }

    public double getProductionNightTime() {
        // Here we get production without brightness, as there is no brightness at night
        float production = peakOutput;
        if (seesMoon) {
            production *= 0.55;
        }
        // If it's nightime and if the generator is in a biome where it can rain and it's raining penalize production by 80%
        if (needsRainCheck && (world.isRaining() || world.isThundering())) {
            // If it's raining at night, apply an additional 30% penalty (80% total)
            production *= 0.245;
        }
        return production;
    }

    @Override
    public String[] getMethods() {
        return methods;
    }

    @Override
    public Object[] invoke(int method, Object[] arguments) throws NoSuchMethodException {
        return switch (method) {
            case 0 -> new Object[]{getEnergy()};
            case 1 -> new Object[]{output};
            case 2 -> new Object[]{BASE_MAX_ENERGY};
            case 3 -> new Object[]{BASE_MAX_ENERGY - getEnergy()};
            case 4 -> new Object[]{seesSun};
            case 5 -> new Object[]{seesMoon};
            default -> throw new NoSuchMethodException();
        };
    }

    @Override
    public void handlePacketData(ByteBuf dataStream) {
        super.handlePacketData(dataStream);
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            seesSun = dataStream.readBoolean();
            seesMoon = dataStream.readBoolean();
        }
    }

    @Override
    public TileNetworkList getNetworkedData(TileNetworkList data) {
        super.getNetworkedData(data);
        data.add(seesSun);
        data.add(seesMoon);
        return data;
    }

    public abstract float getConfiguredMax();


    @Override
    public double getMaxOutput() {
        return peakOutput;
    }
}
