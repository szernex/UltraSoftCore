package org.szernex.usc;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import org.szernex.usc.handler.ConfigHandler;
import org.szernex.usc.handler.PlayerTickHandler;

import java.io.File;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptableRemoteVersions = "*")
public class Main
{
	@Mod.Instance(Reference.MOD_ID)
	public static Main instance;

	public static File configFile = null;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		configFile = event.getSuggestedConfigurationFile();
		ConfigHandler.init(configFile);

		FMLCommonHandler.instance().bus().register(new ConfigHandler());
	}

	@Mod.EventHandler
	public void serverStarted(FMLServerStartedEvent event)
	{
		FMLCommonHandler.instance().bus().register(new PlayerTickHandler());
	}
}
