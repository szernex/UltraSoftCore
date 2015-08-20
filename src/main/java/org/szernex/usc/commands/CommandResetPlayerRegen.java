package org.szernex.usc.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import org.szernex.usc.core.HealthManager;
import org.szernex.usc.core.USCExtendedPlayer;
import org.szernex.usc.util.ChatHelper;
import org.szernex.usc.util.LogHelper;
import org.szernex.usc.util.StringHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandResetPlayerRegen extends CommandBase
{
	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args)
	{
		if (args.length == 1)
			return new ArrayList<>(StringHelper.getWordsStartingWith(args[0], Arrays.asList(MinecraftServer.getServer().getAllUsernames())));

		return null;
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 2;
	}

	@Override
	public String getCommandName()
	{
		return null;
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_)
	{
		return "commands.usc.resetplayerregen.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
	{
		if (args.length == 0)
		{
			ChatHelper.sendLocalizedUserChatMsg(sender, "commands.usc.resetplayerregen.usage");
			return;
		}

		String player_name = args[0];
		EntityPlayer player = MinecraftServer.getServer().getEntityWorld().getPlayerEntityByName(player_name);

		HealthManager.getInstance().resetPlayerHealthRegen(player);
	}
}
