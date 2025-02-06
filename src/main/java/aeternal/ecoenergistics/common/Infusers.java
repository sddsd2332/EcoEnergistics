package aeternal.ecoenergistics.common;

import aeternal.ecoenergistics.common.config.EcoConfig;
import aeternal.ecoenergistics.common.enums.AvaritiaTiers;
import aeternal.ecoenergistics.common.enums.Compressed;
import aeternal.ecoenergistics.common.enums.Dust;
import aeternal.ecoenergistics.common.enums.MoreDust;

import mekanism.api.infuse.InfuseObject;
import mekanism.api.infuse.InfuseRegistry;
import mekanism.api.infuse.InfuseType;
import mekanism.common.MekanismItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class Infusers {

    public static InfuseType gold;
    public static InfuseType glowstone;
    public static InfuseType steel;
    public static InfuseType lapis;
    public static InfuseType emerald;
    public static InfuseType titanium;
    public static InfuseType uranium;
    public static InfuseType iridium;

    public static InfuseType crystal;
    public static InfuseType neutronium;
    public static InfuseType infinity;


    public static void registerInfuseType() {
        gold = new InfuseType("GOLD", new ResourceLocation(EcoEnergistics.MOD_ID, "blocks/infuse/InfuseGold")).setTranslationKey("gold");
        InfuseRegistry.registerInfuseType(gold);
        glowstone = new InfuseType("GLOWSTONE", new ResourceLocation(EcoEnergistics.MOD_ID, "blocks/infuse/InfuseGlowstone")).setTranslationKey("glowstone");
        InfuseRegistry.registerInfuseType(glowstone);
        steel = new InfuseType("STEEL", new ResourceLocation(EcoEnergistics.MOD_ID, "blocks/infuse/InfuseSteel")).setTranslationKey("steel");
        InfuseRegistry.registerInfuseType(steel);
        lapis = new InfuseType("LAPIS", new ResourceLocation(EcoEnergistics.MOD_ID, "blocks/infuse/InfuseLapis")).setTranslationKey("lapis");
        InfuseRegistry.registerInfuseType(lapis);
        emerald = new InfuseType("EMERALD", new ResourceLocation(EcoEnergistics.MOD_ID, "blocks/infuse/InfuseEmerald")).setTranslationKey("emerald");
        InfuseRegistry.registerInfuseType(emerald);
        titanium = new InfuseType("TITANIUM", new ResourceLocation(EcoEnergistics.MOD_ID, "blocks/infuse/InfuseTitanium")).setTranslationKey("titanium");
        InfuseRegistry.registerInfuseType(titanium);
        uranium = new InfuseType("URANIUM", new ResourceLocation(EcoEnergistics.MOD_ID, "blocks/infuse/InfuseUranium")).setTranslationKey("uranium");
        InfuseRegistry.registerInfuseType(uranium);
        iridium = new InfuseType("IRIDIUM", new ResourceLocation(EcoEnergistics.MOD_ID, "blocks/infuse/InfuseIridium")).setTranslationKey("iridium");
        InfuseRegistry.registerInfuseType(iridium);

        if (EcoEnergistics.hooks.AvaritiaLoaded && EcoConfig.current().integration.AvaritiaEnable.val()){
            crystal = new InfuseType("CRYSTAL", new ResourceLocation(EcoEnergistics.MOD_ID, "blocks/infuse/InfuseCrystal")).setTranslationKey("crystal");
            InfuseRegistry.registerInfuseType(crystal);
            neutronium = new InfuseType("NEUTRONIUM", new ResourceLocation(EcoEnergistics.MOD_ID, "blocks/infuse/InfuseNeutronium")).setTranslationKey("neutronium");
            InfuseRegistry.registerInfuseType(neutronium);
            infinity = new InfuseType("INFINITY", new ResourceLocation(EcoEnergistics.MOD_ID, "blocks/infuse/InfuseInfinity")).setTranslationKey("infinity");
            InfuseRegistry.registerInfuseType(infinity);
        }
    }

    public static void registerInfuseObject() {
        InfuseType gold = InfuseRegistry.get("GOLD");
        InfuseType glowstone = InfuseRegistry.get("GLOWSTONE");
        InfuseType steel = InfuseRegistry.get("STEEL");
        InfuseType lapis = InfuseRegistry.get("LAPIS");
        InfuseType emerald = InfuseRegistry.get("EMERALD");
        InfuseType titanium = InfuseRegistry.get("TITANIUM");
        InfuseType uranium = InfuseRegistry.get("URANIUM");
        InfuseType iridium = InfuseRegistry.get("IRIDIUM");
        if (gold != null) {
            ItemStack goldDust = new ItemStack(MekanismItems.Dust, 1, 1);
            ItemStack goldCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, Compressed.GOLD.ordinal());
            InfuseRegistry.registerInfuseObject(goldDust, new InfuseObject(gold, 10));
            InfuseRegistry.registerInfuseObject(goldCompressed, new InfuseObject(gold, 80));
        }
        if (glowstone != null) {
            ItemStack activeGlowstoneIngot = new ItemStack(EcoEnergisticsItems.MoreDust, 1, MoreDust.ACTIVATEDGLOWSTONE.ordinal());
            ItemStack activeGlowstoneCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, Compressed.GLOWSTONE.ordinal());
            InfuseRegistry.registerInfuseObject(activeGlowstoneIngot, new InfuseObject(glowstone, 10));
            InfuseRegistry.registerInfuseObject(activeGlowstoneCompressed, new InfuseObject(glowstone, 80));
        }
        if (steel != null) {
            ItemStack steelDust = new ItemStack(MekanismItems.OtherDust, 1, 1);
            InfuseRegistry.registerInfuseObject(steelDust, new InfuseObject(steel, 10));
        }
        if (lapis != null) {
            ItemStack lapisDust = new ItemStack(EcoEnergisticsItems.MoreDust, 1, MoreDust.LAPIS.ordinal());
            ItemStack lapisCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, Compressed.LAPIS.ordinal());
            InfuseRegistry.registerInfuseObject(lapisDust, new InfuseObject(lapis, 10));
            InfuseRegistry.registerInfuseObject(lapisCompressed, new InfuseObject(lapis, 80));
        }
        if (emerald != null) {
            ItemStack emeraldDust = new ItemStack(EcoEnergisticsItems.MoreDust, 1, MoreDust.EMERALD.ordinal());
            ItemStack emeraldCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, Compressed.EMERALD.ordinal());
            InfuseRegistry.registerInfuseObject(emeraldDust, new InfuseObject(emerald, 10));
            InfuseRegistry.registerInfuseObject(emeraldCompressed, new InfuseObject(emerald, 80));
        }
        if (titanium!= null) {
            ItemStack titaniumDust = new ItemStack(EcoEnergisticsItems.Dust, 1, Dust.TITANIUM.ordinal());
            ItemStack titaniumCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, Compressed.TITANIUM.ordinal());
            InfuseRegistry.registerInfuseObject(titaniumDust, new InfuseObject(titanium, 10));
            InfuseRegistry.registerInfuseObject(titaniumCompressed, new InfuseObject(titanium, 80));
        }
        if (uranium!= null) {
            ItemStack uraniumDust = new ItemStack(EcoEnergisticsItems.Dust, 1, Dust.URANIUM.ordinal());
            ItemStack uraniumCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, Compressed.URANIUM.ordinal());
            InfuseRegistry.registerInfuseObject(uraniumDust, new InfuseObject(uranium, 10));
            InfuseRegistry.registerInfuseObject(uraniumCompressed, new InfuseObject(uranium, 80));
        }
        if (iridium!= null) {
            ItemStack iridiumDust = new ItemStack(EcoEnergisticsItems.Dust, 1, Dust.IRIDIUM.ordinal());
            ItemStack iridiumCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, Compressed.IRIDIUM.ordinal());
            InfuseRegistry.registerInfuseObject(iridiumDust, new InfuseObject(iridium, 10));
            InfuseRegistry.registerInfuseObject(iridiumCompressed, new InfuseObject(iridium, 80));
        }

        if (EcoEnergistics.hooks.AvaritiaLoaded && EcoConfig.current().integration.AvaritiaEnable.val()){
            InfuseType crystal = InfuseRegistry.get("CRYSTAL");
            InfuseType neutronium = InfuseRegistry.get("NEUTRONIUM");
            InfuseType infinity = InfuseRegistry.get("INFINITY");

            if (crystal != null) {
                ItemStack crystalDust = new ItemStack(EcoEnergisticsItems.DustAvaritia, 1, 0);
                ItemStack crystalCompressed = new ItemStack(EcoEnergisticsItems.CompressedAvaritia, 1, AvaritiaTiers.CRYSTALMATRIX.ordinal());
                InfuseRegistry.registerInfuseObject(crystalDust, new InfuseObject(crystal, 10));
                InfuseRegistry.registerInfuseObject(crystalCompressed, new InfuseObject(crystal, 80));
            }
            if (neutronium != null) {
                ItemStack neutroniumDust = new ItemStack(EcoEnergisticsItems.DustAvaritia, 1, 1);
                ItemStack neutroniumCompressed = new ItemStack(EcoEnergisticsItems.CompressedAvaritia, 1, 1);
                InfuseRegistry.registerInfuseObject(neutroniumDust, new InfuseObject(neutronium, 10));
                InfuseRegistry.registerInfuseObject(neutroniumCompressed, new InfuseObject(neutronium, 80));
            }
            if (infinity != null) {
                ItemStack infinityDust = new ItemStack(EcoEnergisticsItems.DustAvaritia, 1, 2);
                ItemStack infinityCompressed = new ItemStack(EcoEnergisticsItems.CompressedAvaritia, 1, 2);
                InfuseRegistry.registerInfuseObject(infinityDust, new InfuseObject(infinity, 10));
                InfuseRegistry.registerInfuseObject(infinityCompressed, new InfuseObject(infinity, 80));
            }
        }

    }

}

