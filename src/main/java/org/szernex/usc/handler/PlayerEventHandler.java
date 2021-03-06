package org.szernex.usc.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.szernex.usc.core.HealthManager;
import org.szernex.usc.core.PlayerDataManager;
import org.szernex.usc.core.USCExtendedPlayer;
import org.szernex.usc.util.ChatHelper;
import org.szernex.usc.util.LogHelper;

public class PlayerEventHandler
{
	private HealthManager healthManager = HealthManager.getInstance();

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if (event.phase == TickEvent.Phase.START || event.player.worldObj.isRemote)
			return;

		healthManager.updatePlayer(event.player);
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
	{
		healthManager.addPlayer(event.player);
	}

	@SubscribeEvent
	public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event)
	{

	}

	/**
	 * Only handles deaths of EntityPlayer's.
	 *
	 * @param event The LivingDeathEvent.
	 */
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event)
	{
		if (event.entity.worldObj.isRemote || !(event.entity instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer) event.entity;

		LogHelper.info("Player %s died, saving NBT data", player.getCommandSenderName());
		PlayerDataManager.savePlayerNBT(player);
		ChatHelper.sendLocalizedUserChatMsg(player, "usc.health.regen_increase"); // move somewhere else, preferably after respawning
	}

	/**
	 * Only handles spawning of EntityPlayer's.
	 *
	 * @param event The EntityJoinWorldEvent.
	 */
	@SubscribeEvent
	public void onPlayerRevive(EntityJoinWorldEvent event)
	{
		if (event.entity.worldObj.isRemote || !(event.entity instanceof EntityPlayer))
			return;

		EntityPlayer player = (EntityPlayer) event.entity;

		LogHelper.info("Player %s revived, loading NBT data", player.getCommandSenderName());
		PlayerDataManager.loadPlayerNBT(player);
	}

	/**
	 * Handles registering of IEEPs for players.
	 *
	 * @param event The EntityConstructing event.
	 */
	@SubscribeEvent
	public void onEntityConstructing(EntityEvent.EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && USCExtendedPlayer.get(event.entity) == null)
		{
			USCExtendedPlayer.register((EntityPlayer) event.entity);
		}
	}
}
