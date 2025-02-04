package aeternal.ecoenergistics.common.recipes;


import aeternal.ecoenergistics.common.EcoEnergistics;
import aeternal.ecoenergistics.common.EcoEnergisticsItems;
import aeternal.ecoenergistics.common.config.EcoConfig;
import aeternal.ecoenergistics.common.enums.Ingot;
import mekanism.api.infuse.InfuseRegistry;
import mekanism.api.infuse.InfuseType;
import mekanism.common.MekanismItems;
import mekanism.common.block.states.BlockStateMachine;
import mekanism.common.config.MekanismConfig;
import mekanism.common.recipe.RecipeHandler;
import net.minecraft.item.ItemStack;

import static aeternal.ecoenergistics.common.Infusers.*;

public class Infuser {
    public static void InitCustomInfuserRecipes() {
        if (MekanismConfig.current().general.machinesManager.isEnabled(BlockStateMachine.MachineType.METALLURGIC_INFUSER)) {
            InfuseType carbon = InfuseRegistry.get("CARBON");
            InfuseType diamond = InfuseRegistry.get("DIAMOND");
            InfuseType obsidian = InfuseRegistry.get("OBSIDIAN");
            InfuseType redstone = InfuseRegistry.get("REDSTONE");
            /*            InfuseType steel = InfuseRegistry.get("STEEL");*/


            ItemStack advancedAlloy = new ItemStack(EcoEnergisticsItems.MoreAlloy, 1, 0);
            ItemStack hybridAlloy = new ItemStack(EcoEnergisticsItems.MoreAlloy, 1, 1);
            ItemStack perfecthybridAlloy = new ItemStack(EcoEnergisticsItems.MoreAlloy, 1, 2);
            ItemStack quantumAlloy = new ItemStack(EcoEnergisticsItems.MoreAlloy, 1, 3);
            ItemStack spectralAlloy = new ItemStack(EcoEnergisticsItems.MoreAlloy, 1, 4);
            ItemStack protonicAlloy = new ItemStack(EcoEnergisticsItems.MoreAlloy, 1, 5);
            ItemStack singularAlloy = new ItemStack(EcoEnergisticsItems.MoreAlloy, 1, 6);
            ItemStack diffractiveAlloy = new ItemStack(EcoEnergisticsItems.MoreAlloy, 1, 7);
            ItemStack photonicAlloy = new ItemStack(EcoEnergisticsItems.MoreAlloy, 1, 8);
            ItemStack neutronAlloy = new ItemStack(EcoEnergisticsItems.MoreAlloy, 1, 9);
            ItemStack activeGlowstoneIngot = new ItemStack(EcoEnergisticsItems.MoreIngot, 1, Ingot.ACTIVATEDGLOWSTONE.ordinal());
            ItemStack GlowstoneIngot = new ItemStack(MekanismItems.Ingot, 1, 3);

            ItemStack lithiumDust = new ItemStack(MekanismItems.OtherDust, 1, 4);
            ItemStack refinedlithiumDust = new ItemStack(EcoEnergisticsItems.MoreDust, 1, 3);

            ItemStack hdperod = new ItemStack(MekanismItems.HDPE_ROD, 1, 0);
            ItemStack steelrod = new ItemStack(EcoEnergisticsItems.MoreRod, 1, 0);

            // Metallurgic Infuser Recipes
            //Alloys
            RecipeHandler.addMetallurgicInfuserRecipe(glowstone, 20, new ItemStack(MekanismItems.ReinforcedAlloy), advancedAlloy);
            RecipeHandler.addMetallurgicInfuserRecipe(diamond, 20, advancedAlloy, hybridAlloy);
            RecipeHandler.addMetallurgicInfuserRecipe(lapis, 20, hybridAlloy, perfecthybridAlloy);
            RecipeHandler.addMetallurgicInfuserRecipe(emerald, 20, perfecthybridAlloy, quantumAlloy);
            RecipeHandler.addMetallurgicInfuserRecipe(gold, 30, quantumAlloy, spectralAlloy);
            RecipeHandler.addMetallurgicInfuserRecipe(obsidian, 20, spectralAlloy, protonicAlloy);
            RecipeHandler.addMetallurgicInfuserRecipe(glowstone, 20, protonicAlloy, singularAlloy);
            RecipeHandler.addMetallurgicInfuserRecipe(redstone, 50, singularAlloy, diffractiveAlloy);
            RecipeHandler.addMetallurgicInfuserRecipe(emerald, 50, diffractiveAlloy, photonicAlloy);
            RecipeHandler.addMetallurgicInfuserRecipe(obsidian, 50, photonicAlloy, neutronAlloy);

            //ROD
            RecipeHandler.addMetallurgicInfuserRecipe(steel, 50, hdperod, steelrod);

            //Ingots
            RecipeHandler.addMetallurgicInfuserRecipe(redstone, 15, GlowstoneIngot, activeGlowstoneIngot);

            //Dusts
            RecipeHandler.addMetallurgicInfuserRecipe(iridium, 20, lithiumDust, refinedlithiumDust);

            if (EcoEnergistics.hooks.AvaritiaLoaded && EcoConfig.current().integration.AvaritiaEnable.val()){
                ItemStack crystalAlloy = new ItemStack(EcoEnergisticsItems.AlloyAvaritia, 1, 0);
                ItemStack neutroniumAlloy = new ItemStack(EcoEnergisticsItems.AlloyAvaritia, 1, 1);
                ItemStack infinityAlloy = new ItemStack(EcoEnergisticsItems.AlloyAvaritia, 1, 2);

                // Metallurgic Infuser Recipes
                //Alloys
                RecipeHandler.addMetallurgicInfuserRecipe(crystal, 80, neutronAlloy, crystalAlloy);
                RecipeHandler.addMetallurgicInfuserRecipe(neutronium, 160, crystalAlloy, neutroniumAlloy);
                RecipeHandler.addMetallurgicInfuserRecipe(infinity, 10, neutroniumAlloy, infinityAlloy);
            }

        }
    }
}
