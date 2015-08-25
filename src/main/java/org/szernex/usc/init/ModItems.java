package org.szernex.usc.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.szernex.usc.item.ItemFoodUSC;
import org.szernex.usc.item.ItemFruitOfRejuvenation;
import org.szernex.usc.item.ItemGlowingOrb;
import org.szernex.usc.reference.Names;
import org.szernex.usc.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
	public static final ItemFoodUSC fruitOfRejuvenation = new ItemFruitOfRejuvenation();
	public static final ItemGlowingOrb glowingOrb = new ItemGlowingOrb();

	public static void init()
	{
		GameRegistry.registerItem(fruitOfRejuvenation, Names.Items.FRUIT_OF_REJUVENATION);
		GameRegistry.registerItem(glowingOrb, Names.Items.GLOWING_ORB);
	}
}
