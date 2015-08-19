package org.szernex.usc.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import org.szernex.usc.handler.ConfigHandler;
import org.szernex.usc.util.LogHelper;

import java.util.HashMap;

public class HealthManager
{
	public static final int DEFAULT_REGEN_RATE =    80;

	private static final HealthManager instance = new HealthManager();

	private HashMap<EntityPlayer, Integer> playerRegenTicks = new HashMap<>();
	private int minHungerForRegen;

	public static HealthManager getInstance()
	{
		return instance;
	}

	public void init()
	{
		LogHelper.debug("Initializing HealthManager");

		if (ConfigHandler.alterRegeneration)
		{
			LogHelper.info("Turning off natural health regeneration");
			MinecraftServer.getServer().worldServerForDimension(0).getGameRules().setOrCreateGameRule("naturalRegeneration", "false");
		}

		minHungerForRegen = ConfigHandler.minHungerForRegen;

		LogHelper.info("HealthManager initialized");
	}

	/**
	 * Adds the given player to the tick watch list.
	 *
	 * @param player The EntityPlayer to add.
	 */
	public void addPlayer(EntityPlayer player)
	{
		USCExtendedPlayer extended_player = USCExtendedPlayer.get(player);
		int regen_rate = DEFAULT_REGEN_RATE;

		if (extended_player != null)
			regen_rate = extended_player.getRegenRate();

		LogHelper.info("Adding player %s with regeneration rate %d", player.getCommandSenderName(), regen_rate);
		playerRegenTicks.put(player, regen_rate);
	}

	/**
	 * Updates the given player for health regeneration.
	 *
	 * @param player The EntityPlayer to update.
	 */
	public void updatePlayer(EntityPlayer player)
	{
		if (ConfigHandler.alterRegeneration)
		{
			// Healing the player currently does not consume hunger!!!!!!!!!!!!
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
	}
}
