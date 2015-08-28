package org.szernex.usc;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import org.szernex.usc.commands.CommandUSC;
import org.szernex.usc.core.HealthManager;
import org.szernex.usc.handler.ConfigHandler;
import org.szernex.usc.init.ModBlocks;
import org.szernex.usc.init.ModItems;
import org.szernex.usc.init.TileEntities;
import org.szernex.usc.proxy.IProxy;
import org.szernex.usc.init.Recipes;
import org.szernex.usc.reference.Reference;
import org.szernex.usc.util.LogHelper;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class UltraSoftCore
{
	@Mod.Instance(Reference.MOD_ID)
	public static UltraSoftCore instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		ModItems.init();
		ModBlocks.init();

		proxy.registerEventHandlers();
		proxy.registerEntities();
		proxy.initRenderingAndTextures(); // client only

		LogHelper.info("Pre initialization complete");
	}

	@Mod.EventHandler
	public void onInit(FMLInitializationEvent event)
	{
		Recipes.init();
		TileEntities.init();

		LogHelper.info("Initialization complete");
	}

	@Mod.EventHandler
	public void onServerStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandUSC());
	}

	@Mod.EventHandler
	public void onServerStarted(FMLServerStartedEvent event)
	{
		HealthManager.getInstance().init();
	}
}
