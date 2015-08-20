package org.szernex.usc.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.szernex.usc.core.HealthManager;
import org.szernex.usc.reference.Names;

public class ItemFruitOfRejuvenation extends ItemFoodUSC
{
	public ItemFruitOfRejuvenation()
	{
		super();

		maxStackSize = 1;
		setUnlocalizedName(Names.Items.FRUIT_OF_REJUVENATION);
	}

	@Override
	public ItemStack onEaten(ItemStack item_stack, World world, EntityPlayer player)
	{
		--item_stack.stackSize;
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

		if (!world.isRemote)
			HealthManager.getInstance().resetPlayerHealthRegen(player);

		return item_stack;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack item_stack)
	{
		return EnumAction.eat;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack item_stack, World world, EntityPlayer player)
	{
		player.setItemInUse(item_stack, 32);

		return item_stack;
	}
}
