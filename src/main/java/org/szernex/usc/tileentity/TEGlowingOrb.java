package org.szernex.usc.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import org.szernex.usc.block.BlockGlowingOrb;
import org.szernex.usc.handler.ConfigHandler;
import org.szernex.usc.util.LogHelper;

public class TEGlowingOrb extends TileEntity
{
	public static final String NBT_TIME_TO_LIVE = "TimeToLive";

	private int timeToLive;

	public TEGlowingOrb()
	{
		timeToLive = ConfigHandler.glowingOrbTimeToLive * 20;
	}

	public int getTimeToLive()
	{
		return timeToLive;
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		compound.setInteger(NBT_TIME_TO_LIVE, timeToLive);

		LogHelper.debug("NBT saved: timeToLive: %d", timeToLive);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		if (compound.hasKey(NBT_TIME_TO_LIVE))
			timeToLive = compound.getInteger(NBT_TIME_TO_LIVE);

		LogHelper.debug("NBT read: timeToLive: %d", timeToLive);
	}

	@Override
	public void updateEntity()
	{
		if (worldObj.isRemote)
			return;

		if (timeToLive > 0)
			--timeToLive;

		if (timeToLive == 0)
		{
			if (worldObj.getBlock(xCoord, yCoord, zCoord) instanceof BlockGlowingOrb)
			{
				LogHelper.debug("Removing Glowing Orb at %d %d %d", xCoord, yCoord, zCoord);
				worldObj.setBlockToAir(xCoord, yCoord, zCoord);
			}

			worldObj.removeTileEntity(xCoord, yCoord, zCoord);
		}
	}
}
