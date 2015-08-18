package org.szernex.usc.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import org.szernex.usc.util.LogHelper;

import java.util.HashMap;

public class HealthManager
{
	private static final HealthManager instance = new HealthManager();

	private HashMap<EntityPlayer, Integer> playerRegenTicks = new HashMap<>();
	private HashMap<EntityPlayer, NBTTagCompound> playerData = new HashMap<>();
	private int minHungerForRegen;

	public static HealthManager getInstance()
	{
		return instance;
	}

	public void init()
	{
		LogHelper.debug("Initializing HealthManager");

		LogHelper.info("Turning off natural health regeneration");
		MinecraftServer.getServer().worldServerForDimension(0).getGameRules().setOrCreateGameRule("naturalRegeneration", "false");

		minHungerForRegen = 16; // replace with config setting

		LogHelper.info("HealthManager initialized");
	}

	/**
	 * Adds the given player to the tick watch list.
	 *
	 * @param player
	 */
	public void addPlayer(EntityPlayer player)
	{
		USCExtendedPlayer extended_player = USCExtendedPlayer.get(player);
		int regen_rate = USCExtendedPlayer.BASE_REGEN_RATE;

		if (extended_player != null)
			regen_rate = extended_player.getRegenRate();

		LogHelper.info("Adding player %s with regeneration rate %d", player.getCommandSenderName(), regen_rate);
		playerRegenTicks.put(player, regen_rate);
	}

	/**
	 * Updates the given player for health regeneration.
	 *
	 * @param player
	 */
	public void updatePlayer(EntityPlayer player)
	{
		if (!playerRegenTicks.containsKey(player))
			return;

		int tick_count = playerRegenTicks.get(player);

		if (tick_count > 0)
			playerRegenTicks.put(player, tick_count - 1);
		else
		{
			playerRegenTicks.put(player, USCExtendedPlayer.get(player).getRegenRate());

			if (player.shouldHeal() && player.getFoodStats().getFoodLevel() >= minHungerForRegen)
			{
				LogHelper.info("Healing player %s", player.getCommandSenderName());
				player.heal(1.0F);
			}
		}
	}

	public void storePlayerData(EntityPlayer player, NBTTagCompound data)
	{
		playerData.put(player, data);
	}

	public NBTTagCompound retrievePlayerData(EntityPlayer player)
	{
		return playerData.remove(player);
	}
}
