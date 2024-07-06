package aeternal.ecoenergistics.common.recipes;


import aeternal.ecoenergistics.common.EcoEnergisticsItems;
import mekanism.common.block.states.BlockStateMachine;
import mekanism.common.config.MekanismConfig;
import mekanism.common.recipe.RecipeHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import static aeternal.ecoenergistics.common.Infusers.emeraldDusts;
import static aeternal.ecoenergistics.common.Infusers.lapisDusts;

public class Compressor {
    public static void InitCustomCompressorRecipes() {
        if (MekanismConfig.current().general.machinesManager.isEnabled(BlockStateMachine.MachineType.OSMIUM_COMPRESSOR)) {
            RecipeHandler.addOsmiumCompressorRecipe(lapisDusts.get(0).copy(), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 0));
            RecipeHandler.addOsmiumCompressorRecipe(emeraldDusts.get(0).copy(), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 1));
        }
    }

}
