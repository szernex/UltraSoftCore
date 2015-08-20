package org.szernex.usc.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import org.szernex.usc.util.ChatHelper;
import org.szernex.usc.util.StringHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandUSC extends CommandBase
{
	private HashMap<String, CommandBase> availableCommands = new HashMap<>();

	public CommandUSC()
	{
		super();

		availableCommands.put("resetplayerregen", new CommandResetPlayerRegen());
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args)
	{
		if (args.length == 1)
		{
			return new ArrayList<>(StringHelper.getWordsStartingWith(args[0], availableCommands.keySet()));
		}

		String sub_command = args[0].toLowerCase();

		if (availableCommands.containsKey(sub_command))
		{
			return availableCommands.get(sub_command).addTabCompletionOptions(sender, Arrays.copyOfRange(args, 1, args.length));
		}

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
		return "usc";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "commands.usc.usage";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args)
	{
		if (args.length == 0)
		{
			ChatHelper.sendLocalizedUserChatMsg(sender, "commands.usc.usage");
			return;
		}

		String sub_command = args[0].toLowerCase();

		if (availableCommands.containsKey(sub_command))
		{
			availableCommands.get(sub_command).processCommand(sender, Arrays.copyOfRange(args, 1, args.length));
		}
		else
		{
			ChatHelper.sendLocalizedUserChatMsg(sender, "commands.usc.error.invalid_command");
		}
	}
}
