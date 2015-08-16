package org.szernex.usc.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.Mod;
import net.minecraftforge.common.config.Configuration;
import org.szernex.usc.Reference;

import java.io.File;

public class ConfigHandler
{
	private static Configuration configuration;

	public static void init(File file)
	{
		if (file != null)
		{
			configuration = new Configuration(file);

			loadConfig();
		}
	}

	public static void loadConfig()
	{
		if (configuration == null)
		{
			return;
		}

		String category = Configuration.CATEGORY_GENERAL;
	}

	@Mod.EventHandler
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfig();
		}
	}
}
