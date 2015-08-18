package org.szernex.usc.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import org.szernex.usc.util.LogHelper;

public class USCExtendedPlayer implements IExtendedEntityProperties
{
	public static final String EXT_PROP_NAME = "USCExtendedPlayer";
	// replace all with config settings
	public static final int BASE_REGEN_RATE = 80;
	public static final float REGEN_MODIFIER_RATE = 1.1F;

	private static final String PROP_REGEN_MODIFIER = "RegenModifier";

	private final EntityPlayer player;

	private float regenModifier;

	public USCExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
		regenModifier = 1.0F;
	}

	/**
	 * Registers a new USCExtendedPlayer IEEP for the given player.
	 *
	 * @param player
	 */
	public static void register(EntityPlayer player)
	{
		player.registerExtendedProperties(EXT_PROP_NAME, new USCExtendedPlayer(player));
	}

	/**
	 * Returns the USCExtendedPlayer IEEP associated with the given player.
	 *
	 * @param player
	 * @return The USCExtendedPlayer IEEP associated with the given player or null.
	 */
	public static USCExtendedPlayer get(Entity player)
	{
		return (USCExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void init(Entity entity, World world)
	{

	}

	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = new NBTTagCompound();

		properties.setFloat(PROP_REGEN_MODIFIER, regenModifier);

		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);

		if (properties == null)
			return;

		regenModifier = properties.getFloat(PROP_REGEN_MODIFIER);

		LogHelper.info("%s: regen modifier: %f", player.getCommandSenderName(), regenModifier);
	}


	/**
	 * Returns the current regeneration modifier.
	 *
	 * @return The current regeneration modifier.
	 */
	public float getRegenModifier()
	{
		return regenModifier;
	}

	/**
	 * Increases the current regeneration modifier.
	 *
	 * @return The new regeneration modifier.
	 */
	public float increaseRegenModifier()
	{
		regenModifier *= REGEN_MODIFIER_RATE;

		return regenModifier;
	}

	/**
	 * Resets the regeneration modifier to 1.0.
	 */
	public void resetRegenModifier()
	{
		regenModifier = 1.0F;
	}

	/**
	 * Returns the regeneration rate for this player, in ticks.
	 *
	 * @return Rate in ticks when to regenerate health.
	 */
	public int getRegenRate()
	{
		return (int) Math.ceil(BASE_REGEN_RATE * regenModifier);
	}

	/**
	 * Returns the player associated to this IEEP.
	 *
	 * @return The player associated to this IEEP.
	 */
	public EntityPlayer getPlayer()
	{
		return player;
	}
}
