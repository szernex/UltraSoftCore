package org.szernex.usc.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.szernex.usc.handler.ConfigHandler;
import org.szernex.usc.util.LogHelper;

public class Recipes
{
	public static void init()
	{
		// Fruit of Rejuvenation recipe

		if (ConfigHandler.recipeFruitOfRejuvenation)
		{
			LogHelper.info("Adding recipe for %s", ModItems.fruitOfRejuvenation.getUnlocalizedName());
			ItemStack goldenApple = new ItemStack((Item) Item.itemRegistry.getObject("golden_apple"));
			ItemStack potionRegen = new ItemStack((Item) Item.itemRegistry.getObject("potion"), 1, 8193);

			GameRegistry.addShapedRecipe(new ItemStack(ModItems.fruitOfRejuvenation),
			                             " a ",
			                             "apa",
			                             " a ",
			                             'a', goldenApple,
			                             'p', potionRegen);
		}

		LogHelper.info("Adding recipe for %s", ModItems.glowingOrb.getUnlocalizedName());
		Block glassPane = Blocks.glass_pane;
		Block torch = Blocks.torch;

		GameRegistry.addShapedRecipe(new ItemStack(ModItems.glowingOrb, 2),
		                             " g ",
		                             "gtg",
		                             " g ",
		                             'g', glassPane,
		                             't', torch);
	}
}
