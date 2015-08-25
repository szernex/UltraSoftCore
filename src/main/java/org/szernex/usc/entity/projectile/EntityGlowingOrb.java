package org.szernex.usc.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

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
	protected void onImpact(MovingObjectPosition position)
	{
		// spawn glowing orb TE

		if (!worldObj.isRemote)
			setDead();
	}
}
