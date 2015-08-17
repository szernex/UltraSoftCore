package org.szernex.usc.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import org.szernex.usc.util.LogHelper;

import java.util.HashMap;

public class HealthManager
{
	private static final HealthManager instance = new HealthManager();

	private HashMap<EntityPlayer, Integer> playerMap = new HashMap<>();

	public static HealthManager getInstance()
	{
		return instance;
	}

	public void init()
	{
		LogHelper.debug("Initializing HealthManager");

		LogHelper.info("Turning off natural health regeneration");
		MinecraftServer.getServer().worldServerForDimension(0).getGameRules().setOrCreateGameRule("naturalRegeneration", "false");

		LogHelper.info("HealthManager initialized");
	}

	public void addPlayer(EntityPlayer player)
	{
		LogHelper.info("Adding player %s", player.getCommandSenderName());
		playerMap.put(player, 160);
	}

	public void updatePlayer(EntityPlayer player)
	{
		if (!playerMap.containsKey(player))
			return;

		int tick_count = playerMap.get(player);

		if (tick_count > 0)
			playerMap.put(player, tick_count - 1);
		else
		{
			playerMap.put(player, 160);
			if (player.shouldHeal())
			{
				LogHelper.info("Healing player %s", player.getCommandSenderName());
				player.heal(1.0F);
			}
		}
	}
}
