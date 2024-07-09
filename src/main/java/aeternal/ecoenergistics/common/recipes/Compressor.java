package aeternal.ecoenergistics.common.recipes;


import aeternal.ecoenergistics.common.EcoEnergisticsItems;
import mekanism.common.block.states.BlockStateMachine;
import mekanism.common.config.MekanismConfig;
import mekanism.common.recipe.RecipeHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import java.util.List;



public class Compressor {
    public static List<ItemStack> lapisDusts = OreDictionary.getOres("dustLapis");
    public static List<ItemStack> emeraldDusts = OreDictionary.getOres("dustEmerald");

    public static void InitCustomCompressorRecipes() {
        if (MekanismConfig.current().general.machinesManager.isEnabled(BlockStateMachine.MachineType.OSMIUM_COMPRESSOR)) {
            if (!lapisDusts.isEmpty()) {
                RecipeHandler.addOsmiumCompressorRecipe(lapisDusts.get(0).copy(), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 0));
            }
            if (!emeraldDusts.isEmpty()) {
                RecipeHandler.addOsmiumCompressorRecipe(emeraldDusts.get(0).copy(), new ItemStack(EcoEnergisticsItems.MoreCompressed, 1, 1));
            }
        }
    }

}
