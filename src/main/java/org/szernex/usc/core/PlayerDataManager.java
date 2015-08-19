package org.szernex.usc.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import org.szernex.usc.util.LogHelper;

import java.util.HashMap;

public class PlayerDataManager
{
	private static HashMap<EntityPlayer, NBTTagCompound> playerData = new HashMap<>();

	public static void savePlayerNBT(EntityPlayer player)
	{
		// Save USCExtendedPlayer data
		NBTTagCompound data = new NBTTagCompound();
		USCExtendedPlayer extended_player =  USCExtendedPlayer.get(player);

		if (extended_player != null)
		{
			extended_player.saveNBTData(data);
			playerData.put(player, data);
		}
	}

	public static void loadPlayerNBT(EntityPlayer player)
	{
		// Load USCExtendedPlayer data
		NBTTagCompound data = playerData.get(player);
		USCExtendedPlayer extended_player =  USCExtendedPlayer.get(player);

		if (extended_player != null && data != null)
		{
			extended_player.loadNBTData(data);
			extended_player.increaseRegenModifier(); // punish desu!
			LogHelper.info("New health regen modifier for %s: %d", player.getCommandSenderName(), extended_player.getRegenRate());
		}
	}
}
