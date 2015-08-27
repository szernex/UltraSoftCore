package org.szernex.usc.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.szernex.usc.reference.Names;
import org.szernex.usc.tileentity.TEGlowingOrb;

public class TileEntities
{
	public static void init()
	{
		GameRegistry.registerTileEntity(TEGlowingOrb.class, Names.Blocks.GLOWING_ORB);
	}
}
