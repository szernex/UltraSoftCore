package org.szernex.usc.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import org.szernex.usc.handler.ConfigHandler;
import org.szernex.usc.reference.Names;
import org.szernex.usc.util.LogHelper;

public class USCExtendedPlayer implements IExtendedEntityProperties
{
	private static final String PROP_REGEN_MODIFIER = "RegenModifier";

	private final EntityPlayer player;

	private int baseRegenRate;
	private float regenModifierRate;
	private float regenModifier;

	public USCExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
		baseRegenRate = ConfigHandler.baseRegenRate;
		regenModifierRate = ConfigHandler.regenModifierRate;
		regenModifier = 1.0F;
	}

	/**
	 * Registers a new USCExtendedPlayer IEEP for the given player.
	 *
	 * @param player The EntityPlayer to register.
	 */
	public static void register(EntityPlayer player)
	{
		player.registerExtendedProperties(Names.NBT.EXTENDED_PLAYER, new USCExtendedPlayer(player));
	}

	/**
	 * Returns the USCExtendedPlayer IEEP associated with the given player.
	 *
	 * @param player The EntityPlayer to retrieve the IEEP for.
	 * @return The USCExtendedPlayer IEEP associated with the given player or null.
	 */
	public static USCExtendedPlayer get(Entity player)
	{
		if (player == null)
			return null;

		return (USCExtendedPlayer) player.getExtendedProperties(Names.NBT.EXTENDED_PLAYER);
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

		compound.setTag(Names.NBT.EXTENDED_PLAYER, properties);
		LogHelper.debug("Saved NBT data for %s", player.getCommandSenderName());
	}

	@Override
	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(Names.NBT.EXTENDED_PLAYER);

		if (properties == null)
			return;

		regenModifier = properties.getFloat(PROP_REGEN_MODIFIER);
		LogHelper.debug("Loaded NBT data for %s", player.getCommandSenderName());
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
		regenModifier *= regenModifierRate;

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
		return (int) Math.ceil(baseRegenRate * regenModifier);
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

	public int getBaseRegenRate()
	{
		return baseRegenRate;
	}
}
