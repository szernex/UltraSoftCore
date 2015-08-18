package org.szernex.usc;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.common.MinecraftForge;
import org.szernex.usc.core.HealthManager;
import org.szernex.usc.handler.ConfigHandler;
import org.szernex.usc.handler.PlayerHandler;

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
		PlayerHandler player_handler = new PlayerHandler();

		FMLCommonHandler.instance().bus().register(player_handler);
		MinecraftForge.EVENT_BUS.register(player_handler);

		HealthManager.getInstance().init();
	}
}
