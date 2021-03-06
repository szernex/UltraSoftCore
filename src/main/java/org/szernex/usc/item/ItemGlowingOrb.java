package org.szernex.usc.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.szernex.usc.entity.projectile.EntityGlowingOrb;
import org.szernex.usc.reference.Names;

public class ItemGlowingOrb extends ItemUSC
{
	public ItemGlowingOrb()
	{
		super();

		maxStackSize = 16;
		setUnlocalizedName(Names.Items.GLOWING_ORB);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (!player.capabilities.isCreativeMode)
			--stack.stackSize;

		world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

		if (!world.isRemote)
			world.spawnEntityInWorld(new EntityGlowingOrb(world, player));

		return stack;
	}
}
