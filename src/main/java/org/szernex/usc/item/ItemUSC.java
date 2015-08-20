/**
 * Credit goes to Pahimar for providing this framework for a Item wrapper class.
 */

package org.szernex.usc.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.szernex.usc.creativetab.CreativeTab;
import org.szernex.usc.reference.Textures;

public class ItemUSC extends Item
{
	public ItemUSC()
	{
		super();

		maxStackSize = 1;
		setCreativeTab(CreativeTab.USC_TAB);
		setNoRepair();
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		return getUnlocalizedName();
	}

	@Override
	public String getUnlocalizedName()
	{
		return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon(getUnlocalizedName().substring(getUnlocalizedName().indexOf(".") + 1));
	}

	protected String getUnwrappedUnlocalizedName(String unlocalized_name)
	{
		return unlocalized_name.substring(unlocalized_name.indexOf(".") + 1);
	}
}
