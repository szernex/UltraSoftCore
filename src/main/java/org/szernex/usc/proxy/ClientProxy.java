package org.szernex.usc.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;
import org.szernex.usc.entity.projectile.EntityGlowingOrb;
import org.szernex.usc.init.ModItems;

public class ClientProxy extends CommonProxy
{
	@Override
	public ClientProxy getClientProxy()
	{
		return this;
	}

	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
	}

	@Override
	public void initRenderingAndTextures()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityGlowingOrb.class, new RenderSnowball(ModItems.glowingOrb));
	}
}
