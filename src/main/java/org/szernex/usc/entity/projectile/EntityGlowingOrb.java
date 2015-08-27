package org.szernex.usc.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.szernex.usc.init.ModBlocks;

public class EntityGlowingOrb extends EntityThrowable
{
	public EntityGlowingOrb(World world)
	{
		super(world);
	}

	public EntityGlowingOrb(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	public EntityGlowingOrb(World world, EntityLivingBase entity)
	{
		super(world, entity);
	}

	@Override
	public AxisAlignedBB getBoundingBox()
	{
		return null;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (!worldObj.isRemote)
		{
			int x = mop.blockX;
			int y = mop.blockY;
			int z = mop.blockZ;

			switch (mop.sideHit)
			{
				case 0:
					--y;
					break;
				case 1:
					++y;
					break;
				case 2:
					--z;
					break;
				case 3:
					++z;
					break;
				case 4:
					--x;
					break;
				case 5:
					++x;
					break;
			}

			Block block = worldObj.getBlock(x, y, z);

			if (block.getMaterial() == Material.air
					|| block.getMaterial() == Material.water
					|| block.getMaterial().isReplaceable())
			{
				worldObj.setBlock(x, y, z, ModBlocks.blockGlowingOrb);
				ModBlocks.blockGlowingOrb.createNewTileEntity(worldObj, 0);
			}

			//worldObj.spawnEntityInWorld(new EntityGlowingOrb(worldObj, x + 0.5, y + 0.5, z + 0.5));

			setDead();
		}
	}


}
