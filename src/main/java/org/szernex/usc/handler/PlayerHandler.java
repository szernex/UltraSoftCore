package org.szernex.usc.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.szernex.usc.core.HealthManager;
import org.szernex.usc.core.USCExtendedPlayer;
import org.szernex.usc.util.LogHelper;

public class PlayerHandler
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
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event)
	{
		if (event.entity.worldObj.isRemote || !(event.entity instanceof EntityPlayer))
			return;

		NBTTagCompound data = new NBTTagCompound();
		USCExtendedPlayer extended_player =  USCExtendedPlayer.get(event.entity);

		if (extended_player != null)
		{
			extended_player.saveNBTData(data);
			healthManager.storePlayerData((EntityPlayer) event.entity, data);
			LogHelper.info("%s died, storing NBT data", event.entity.getCommandSenderName());
		}
	}

	/**
	 * Only handles spawning of EntityPlayer's.
	 *
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerRevive(EntityJoinWorldEvent event)
	{
		if (event.entity.worldObj.isRemote || !(event.entity instanceof EntityPlayer))
			return;

		NBTTagCompound data = healthManager.retrievePlayerData((EntityPlayer) event.entity);
		USCExtendedPlayer extended_player =  USCExtendedPlayer.get(event.entity);

		if (extended_player != null && data != null)
		{
			extended_player.loadNBTData(data);
			extended_player.increaseRegenModifier();
			LogHelper.info("%s revived, loading NBT data and altering regeneration rate: %d", extended_player.getPlayer().getCommandSenderName(), extended_player.getRegenRate());
		}
	}

	/**
	 * Handles registering of IEEPs for players.
	 *
	 * @param event
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
