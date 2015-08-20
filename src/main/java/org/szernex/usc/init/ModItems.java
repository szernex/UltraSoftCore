package org.szernex.usc.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.szernex.usc.item.ItemFoodUSC;
import org.szernex.usc.item.ItemFruitOfRejuvenation;
import org.szernex.usc.reference.Names;
import org.szernex.usc.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
	public static final ItemFoodUSC fruitOfRejuvenation = new ItemFruitOfRejuvenation();

	public static void init()
	{
		GameRegistry.registerItem(fruitOfRejuvenation, Names.Items.FRUIT_OF_REJUVENATION);
	}
}
