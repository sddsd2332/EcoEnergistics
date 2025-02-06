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

public class Enrichment {
    public static void InitCustomEnrichmentRecipes() {
        if (MekanismConfig.current().general.machinesManager.isEnabled(BlockStateMachine.MachineType.ENRICHMENT_CHAMBER)) {
            ItemStack titaniumOre = new ItemStack(EcoEnergisticsBlocks.EcoOreBlock, 1, 0);
            ItemStack uraniummOre = new ItemStack(EcoEnergisticsBlocks.EcoOreBlock, 1, 1);
            ItemStack iridiumOre = new ItemStack(EcoEnergisticsBlocks.EcoOreBlock, 1, 2);

            ItemStack titaniumClump = new ItemStack(EcoEnergisticsItems.MoreClump, 1, 0);
            ItemStack uraniumClump = new ItemStack(EcoEnergisticsItems.MoreClump, 1, 1);
            ItemStack iridiumClump = new ItemStack(EcoEnergisticsItems.MoreClump, 1, 2);

            ItemStack titaniumDirty = new ItemStack(EcoEnergisticsItems.MoreDirtyDust, 1, 0);
            ItemStack uraniumDirty = new ItemStack(EcoEnergisticsItems.MoreDirtyDust, 1, 1);
            ItemStack iridiumDirty = new ItemStack(EcoEnergisticsItems.MoreDirtyDust, 1, 2);

            ItemStack titaniumCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 4);
            ItemStack uraniumCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 5);
            ItemStack iridiumCompressed = new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 6);

            ItemStack titaniumDust = new ItemStack(EcoEnergisticsItems.Dust, 1, 0);
            ItemStack titaniumDust2 = titaniumDust.copy();
            titaniumDust2.setCount(titaniumDust2.getCount() * 2);
            ItemStack uraniumDust = new ItemStack(EcoEnergisticsItems.Dust, 1, 1);
            ItemStack uraniumDust2 = uraniumDust.copy();
            uraniumDust2.setCount(uraniumDust2.getCount() * 2);
            ItemStack iridiumDust = new ItemStack(EcoEnergisticsItems.Dust, 1, 2);
            ItemStack iridiumDust2 = iridiumDust.copy();
            iridiumDust2.setCount(iridiumDust2.getCount() * 2);

            RecipeHandler.addEnrichmentChamberRecipe(new ItemStack(EcoEnergisticsItems.MoreDust, 1, MoreDust.LAPIS.ordinal()), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 0));
            RecipeHandler.addEnrichmentChamberRecipe(new ItemStack(EcoEnergisticsItems.MoreDust, 1, MoreDust.EMERALD.ordinal()), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 1));
            RecipeHandler.addEnrichmentChamberRecipe(new ItemStack(EcoEnergisticsItems.MoreDust, 1, MoreDust.ACTIVATEDGLOWSTONE.ordinal()), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 2));
            RecipeHandler.addEnrichmentChamberRecipe(new ItemStack(MekanismItems.Dust, 1, Resource.GOLD.ordinal()), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 3));


            RecipeHandler.addEnrichmentChamberRecipe(titaniumOre, titaniumDust2);
            RecipeHandler.addEnrichmentChamberRecipe(uraniummOre, uraniumDust2);
            RecipeHandler.addEnrichmentChamberRecipe(iridiumOre, iridiumDust2);

            RecipeHandler.addEnrichmentChamberRecipe(titaniumDirty, titaniumDust);
            RecipeHandler.addEnrichmentChamberRecipe(uraniumDirty, uraniumDust);
            RecipeHandler.addEnrichmentChamberRecipe(iridiumDirty, iridiumDust);

            RecipeHandler.addEnrichmentChamberRecipe(titaniumDust, titaniumCompressed);
            RecipeHandler.addEnrichmentChamberRecipe(uraniumDust, uraniumCompressed);
            RecipeHandler.addEnrichmentChamberRecipe(iridiumDust, iridiumCompressed);

            if (EcoEnergistics.hooks.AvaritiaLoaded && EcoConfig.current().integration.AvaritiaEnable.val()){

                ItemStack dustCrystal = new ItemStack(EcoEnergisticsItems.DustAvaritia,1, AvaritiaTiers.CRYSTALMATRIX.ordinal());
                ItemStack dustNeutronium = new ItemStack(EcoEnergisticsItems.DustAvaritia,1, AvaritiaTiers.NEUTRONIUM.ordinal());
                ItemStack dustInfinity = new ItemStack(EcoEnergisticsItems.DustAvaritia,1, AvaritiaTiers.INFINITY.ordinal());

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
