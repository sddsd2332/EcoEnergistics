package aeternal.ecoenergistics.common.recipes;


import aeternal.ecoenergistics.common.EcoEnergistics;
import aeternal.ecoenergistics.common.EcoEnergisticsBlocks;
import aeternal.ecoenergistics.common.EcoEnergisticsItems;
import aeternal.ecoenergistics.common.config.EcoConfig;
import aeternal.ecoenergistics.common.enums.AvaritiaTiers;
import aeternal.ecoenergistics.common.enums.MoreDust;
import mekanism.common.MekanismItems;
import mekanism.common.Resource;
import mekanism.common.block.states.BlockStateMachine;
import mekanism.common.config.MekanismConfig;
import mekanism.common.recipe.RecipeHandler;
import net.minecraft.item.ItemStack;

import static aeternal.ecoenergistics.common.Infusers.*;
import static aeternal.ecoenergistics.common.Infusers.goldDusts;

public class Enrichment {
    public static void InitCustomEnrichmentRecipes() {
        if (MekanismConfig.current().general.machinesManager.isEnabled(BlockStateMachine.MachineType.ENRICHMENT_CHAMBER)) {
            ItemStack titaniumOre = new ItemStack(EcoEnergisticsBlocks.EcoOreBlock,1,0);
            ItemStack uraniummOre = new ItemStack(EcoEnergisticsBlocks.EcoOreBlock,1,1);
            ItemStack iridiumOre = new ItemStack(EcoEnergisticsBlocks.EcoOreBlock,1,2);

            ItemStack titaniumClump = new ItemStack(EcoEnergisticsItems.MoreClump,1,0);
            ItemStack uraniumClump = new ItemStack(EcoEnergisticsItems.MoreClump,1,1);
            ItemStack iridiumClump = new ItemStack(EcoEnergisticsItems.MoreClump,1,2);

            ItemStack titaniumDirty = new ItemStack(EcoEnergisticsItems.MoreDirtyDust,1,0);
            ItemStack uraniumDirty = new ItemStack(EcoEnergisticsItems.MoreDirtyDust,1,1);
            ItemStack iridiumDirty = new ItemStack(EcoEnergisticsItems.MoreDirtyDust,1,2);

            ItemStack titaniumCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed,1,4);
            ItemStack uraniumCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed,1,5);
            ItemStack iridiumCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed,1,6);

            ItemStack titaniumDust = titaniumDusts.get(0).copy();
            ItemStack titaniumDust2 = titaniumDust.copy();
            titaniumDust2.setCount(titaniumDust2.getCount() *2);
            ItemStack uraniumDust = uraniumDusts.get(0).copy();
            ItemStack uraniumDust2 = uraniumDust.copy();
            uraniumDust2.setCount(uraniumDust2.getCount() *2);
            ItemStack iridiumDust = iridiumDusts.get(0).copy();
            ItemStack iridiumDust2 = iridiumDust.copy();
            iridiumDust2.setCount(iridiumDust2.getCount() *2);
            //conflicting with existing Mek recipes
            /*RecipeHandler.addEnrichmentChamberRecipe(lapisDusts.get(0).copy(), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 0));
            RecipeHandler.addEnrichmentChamberRecipe(emeraldDusts.get(0).copy(), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 1));*/
            RecipeHandler.addEnrichmentChamberRecipe(new ItemStack(EcoEnergisticsItems.MoreDust, 1, MoreDust.ACTIVATEDGLOWSTONE.ordinal()), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 2));
            RecipeHandler.addEnrichmentChamberRecipe(goldDusts.get(0).copy(), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 3));


            RecipeHandler.addEnrichmentChamberRecipe(titaniumOre,titaniumDust2);
            RecipeHandler.addEnrichmentChamberRecipe(uraniummOre,uraniumDust2);
            RecipeHandler.addEnrichmentChamberRecipe(iridiumOre,iridiumDust2);

            RecipeHandler.addEnrichmentChamberRecipe(titaniumDirty,titaniumDust);
            RecipeHandler.addEnrichmentChamberRecipe(uraniumDirty,uraniumDust);
            RecipeHandler.addEnrichmentChamberRecipe(iridiumDirty,iridiumDust);

            RecipeHandler.addEnrichmentChamberRecipe(titaniumDust,titaniumCompressed);
            RecipeHandler.addEnrichmentChamberRecipe(uraniumDust,uraniumCompressed);
            RecipeHandler.addEnrichmentChamberRecipe(iridiumDust,iridiumCompressed);

            if (EcoEnergistics.hooks.AvaritiaLoaded && EcoConfig.current().integration.AvaritiaEnable.val()){
                ItemStack dustCrystal = CrystalMatrixDusts.get(0).copy();
                ItemStack dustNeutronium = NeutroniumDusts.get(0).copy();
                ItemStack dustInfinity = InfinityDusts.get(0).copy();


                ItemStack compressedCrystal = new ItemStack(EcoEnergisticsItems.CompressedAvaritia,1, AvaritiaTiers.CRYSTALMATRIX.ordinal());
                ItemStack compressedNeutronium = new ItemStack(EcoEnergisticsItems.CompressedAvaritia,1, AvaritiaTiers.NEUTRONIUM.ordinal());
                ItemStack compressedInfinity = new ItemStack(EcoEnergisticsItems.CompressedAvaritia,1, AvaritiaTiers.INFINITY.ordinal());


                RecipeHandler.addEnrichmentChamberRecipe(dustCrystal,compressedCrystal);
                RecipeHandler.addEnrichmentChamberRecipe(dustNeutronium,compressedNeutronium);
                RecipeHandler.addEnrichmentChamberRecipe(dustInfinity,compressedInfinity);
            }
            
        }
    }
}
