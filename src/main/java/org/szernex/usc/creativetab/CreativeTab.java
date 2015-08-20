package org.szernex.usc.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.szernex.usc.init.ModItems;
import org.szernex.usc.reference.Reference;

public class CreativeTab
{
	public static final CreativeTabs USC_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
	{
		@Override
		public Item getTabIconItem()
		{
			return ModItems.fruitOfRejuvenation;
		}
	};
}
