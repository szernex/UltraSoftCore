package org.szernex.usc.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraftforge.common.MinecraftForge;
import org.szernex.usc.UltraSoftCore;
import org.szernex.usc.entity.projectile.EntityGlowingOrb;
import org.szernex.usc.handler.ConfigHandler;
import org.szernex.usc.handler.PlayerEventHandler;
import org.szernex.usc.reference.Names;

public abstract class CommonProxy implements IProxy
{
	@Override
	public void registerEventHandlers()
	{
		PlayerEventHandler player_event_handler = new PlayerEventHandler();

		FMLCommonHandler.instance().bus().register(new ConfigHandler());
		FMLCommonHandler.instance().bus().register(player_event_handler);
		MinecraftForge.EVENT_BUS.register(player_event_handler);
	}

	@Override
	public void registerEntities()
	{
		int id = 0;

		EntityRegistry.registerModEntity(EntityGlowingOrb.class, Names.Items.GLOWING_ORB, id++, UltraSoftCore.instance, 64, 3, true);
	}
}
