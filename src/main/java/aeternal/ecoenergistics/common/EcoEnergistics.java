package aeternal.ecoenergistics.common;

import aeternal.ecoenergistics.common.block.states.BlockStateEcoGenerator.EcoGeneratorType;
import aeternal.ecoenergistics.common.capabilities.EcoCapabilities;
import aeternal.ecoenergistics.common.config.EcoConfig;
import aeternal.ecoenergistics.common.enums.AvaritiaTiers;
import aeternal.ecoenergistics.common.enums.Ore;
import aeternal.ecoenergistics.common.integration.EcoHooks;
import aeternal.ecoenergistics.common.world.GenHandler;
import io.netty.buffer.ByteBuf;
import mekanism.api.MekanismAPI;
import mekanism.common.Mekanism;
import mekanism.common.Version;
import mekanism.common.base.IModule;
import mekanism.common.config.MekanismConfig;
import mekanism.common.network.PacketSimpleGui;
import mekanism.generators.common.MekanismGenerators;
import morph.avaritia.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.CompoundDataFixer;
import net.minecraftforge.common.util.ModFixs;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

import static aeternal.ecoenergistics.common.EcoEnergisticsBlocks.EcoOreBlock;
import static aeternal.ecoenergistics.common.EcoEnergisticsItems.Dust;
import static aeternal.ecoenergistics.common.Infusers.registerInfuseObject;
import static aeternal.ecoenergistics.common.Infusers.registerInfuseType;
import static aeternal.ecoenergistics.common.recipes.Crusher.InitCustomCrusherRecipes;
import static aeternal.ecoenergistics.common.recipes.Enrichment.InitCustomEnrichmentRecipes;
import static aeternal.ecoenergistics.common.recipes.Infuser.InitCustomInfuserRecipes;
import static aeternal.ecoenergistics.common.recipes.Injection.InitCustomInjectionRecipes;
import static aeternal.ecoenergistics.common.recipes.Purification.InitCustomPurificationRecipes;

@Mod(modid = EcoEnergistics.MOD_ID, useMetadata = true, guiFactory = "aeternal.ecoenergistics.client.gui.EcoEnergisticsGuiFactory")
@Mod.EventBusSubscriber()
public class EcoEnergistics implements IModule {

    public static final String MOD_ID = "mekanismecoenergistics";
    public static final int DATA_VERSION = 1;
    @SidedProxy(clientSide = "aeternal.ecoenergistics.client.EcoEnergisticsClientProxy", serverSide = "aeternal.ecoenergistics.common.EcoEnergisticsCommonProxy")
    public static EcoEnergisticsCommonProxy proxy;
    @Mod.Instance(EcoEnergistics.MOD_ID)
    public static EcoEnergistics instance;
    public static Version versionNumber = new Version(999, 999, 999);
    public static EcoEnergisticsCreativeTab tabEcoEnergistics = new EcoEnergisticsCreativeTab();
    public static Configuration configuration;
    public static Configuration configurationIntegration;
    public static GenHandler genHandler = new GenHandler();
    public static CommonWorldTickHandler worldTickHandler = new CommonWorldTickHandler();
    public static EcoHooks hooks = new EcoHooks();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        EcoEnergisticsBlocks.registerBlocks(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        EcoEnergisticsItems.registerItems(event.getRegistry());
        EcoEnergisticsBlocks.registerItemBlocks(event.getRegistry());
        EcoEnergisticsOreDict.registerOreDict();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        proxy.registerBlockRenders();
        proxy.registerItemRenders();
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        InitCustomInfuserRecipes();
        InitCustomEnrichmentRecipes();
        InitCustomPurificationRecipes();
        InitCustomCrusherRecipes();
        InitCustomInjectionRecipes();
        InitSmeltingRecipes();
        registerInfuseObject();
    }

    public static void InitSmeltingRecipes() {
        GameRegistry.addSmelting(new ItemStack(EcoEnergisticsItems.MoreDust, 1, aeternal.ecoenergistics.common.enums.MoreDust.ACTIVATEDGLOWSTONE.ordinal()), new ItemStack(EcoEnergisticsItems.MoreIngot, 1, 0), 0.0F);
        GameRegistry.addSmelting(new ItemStack(Dust, 1, aeternal.ecoenergistics.common.enums.Dust.TITANIUM.ordinal()), new ItemStack(EcoEnergisticsItems.MoreIngot, 1, 1), 0.0F);
        GameRegistry.addSmelting(new ItemStack(Dust, 1, aeternal.ecoenergistics.common.enums.Dust.URANIUM.ordinal()), new ItemStack(EcoEnergisticsItems.MoreIngot, 1, 2), 0.0F);
        GameRegistry.addSmelting(new ItemStack(Dust, 1, aeternal.ecoenergistics.common.enums.Dust.IRIDIUM.ordinal()), new ItemStack(EcoEnergisticsItems.MoreIngot, 1, 3), 0.0F);
        GameRegistry.addSmelting(new ItemStack(EcoOreBlock, 1, Ore.TITANIUM.ordinal()), new ItemStack(EcoEnergisticsItems.MoreIngot, 1, 1), 0.0F);
        GameRegistry.addSmelting(new ItemStack(EcoOreBlock, 1, Ore.URANIUM.ordinal()), new ItemStack(EcoEnergisticsItems.MoreIngot, 1, 2), 0.0F);
        GameRegistry.addSmelting(new ItemStack(EcoOreBlock, 1, Ore.IRIDIUM.ordinal()), new ItemStack(EcoEnergisticsItems.MoreIngot, 1, 3), 0.0F);
        if (EcoEnergistics.hooks.AvaritiaLoaded &&  EcoConfig.current().integration.AvaritiaEnable.val()) {
            ItemStack crystalIngot = ModItems.crystal_matrix_ingot;
            ItemStack neutroniumIngot = ModItems.neutronium_ingot;
            ItemStack infinityIngot = ModItems.infinity_ingot;
            GameRegistry.addSmelting(new ItemStack(EcoEnergisticsItems.DustAvaritia, 1, AvaritiaTiers.CRYSTALMATRIX.ordinal()), new ItemStack(crystalIngot.getItem(), 1,1), 0.0F);
            GameRegistry.addSmelting(new ItemStack(EcoEnergisticsItems.DustAvaritia, 1, AvaritiaTiers.NEUTRONIUM.ordinal()), new ItemStack(neutroniumIngot.getItem(), 1,4), 0.0F);
            GameRegistry.addSmelting(new ItemStack(EcoEnergisticsItems.DustAvaritia, 1, AvaritiaTiers.INFINITY.ordinal()), new ItemStack(infinityIngot.getItem(), 1,6), 0.0F);
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        configuration = new Configuration(new File("config/EcoEnergistics/EcoEnergistics.cfg"));
        configurationIntegration = new Configuration(new File("config/EcoEnergistics/EcoEnergisticsIntegration.cfg"));
        proxy.preInit();
        proxy.loadConfiguration();
        EcoCapabilities.registerCapabilities();
        hooks.hookPreInit();
        registerInfuseType();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(genHandler, 1);
        Mekanism.modulesLoaded.add(this);
        PacketSimpleGui.handlers.add(proxy);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new EcoEnergisticsGuiHandler());
        MinecraftForge.EVENT_BUS.register(this);
        proxy.registerTileEntities();
        proxy.registerTESRs();
        CompoundDataFixer fixer = FMLCommonHandler.instance().getDataFixer();
        ModFixs fixes = fixer.init(MOD_ID, DATA_VERSION);

        Mekanism.logger.info("Loaded Mekanism Eco Energistics module.");
    }

    @Override
    public Version getVersion() {
        return versionNumber;
    }

    @Override
    public String getName() {
        return "EcoEnergistics";
    }

    @Override
    public void writeConfig(ByteBuf dataStream, MekanismConfig config) {
        config.generators.write(dataStream);
    }

    @Override
    public void readConfig(ByteBuf dataStream, MekanismConfig config) {
        config.generators.read(dataStream);
    }

    @Override
    public void resetClient() {

    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(EcoEnergistics.MOD_ID) || event.getModID().equals(Mekanism.MODID) || event.getModID().equals(MekanismGenerators.MODID)) {
            proxy.loadConfiguration();
        }
    }

    @Mod.EventHandler
    public void serverStopping(FMLServerStoppingEvent event) {
        worldTickHandler.resetRegenChunks();
    }

    @SubscribeEvent
    public synchronized void onChunkDataLoad(ChunkDataEvent.Load event) {
        if (!event.getWorld().isRemote) {
            if (MekanismConfig.current().general.enableWorldRegeneration.val()) {
                NBTTagCompound loadData = event.getData();
                if (loadData.getInteger("MekanismWorldGen") == Mekanism.baseWorldGenVersion &&
                        loadData.getInteger("MekanismUserWorldGen") == MekanismConfig.current().general.userWorldGenVersion.val()) {
                    return;
                }
                ChunkPos coordPair = event.getChunk().getPos();
                worldTickHandler.addRegenChunk(event.getWorld().provider.getDimension(), coordPair);
            }
        }
    }

    @SubscribeEvent
    public void onBlacklistUpdate(MekanismAPI.BoxBlacklistEvent event) {
        for (EcoGeneratorType type : EcoGeneratorType.values()) {
            event.blacklist(EcoEnergisticsBlocks.EcoGeneratorAdd, type.meta);
            if (type.isStation) {
                event.blacklist(EcoEnergisticsBlocks.AvaritiaGenerator, type.meta);
            }
        }
    }

}
