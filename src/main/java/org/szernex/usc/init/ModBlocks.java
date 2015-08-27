package org.szernex.usc.init;

import cpw.mods.fml.common.registry.GameRegistry;
import org.szernex.usc.block.BlockGlowingOrb;
import org.szernex.usc.reference.Names;
import org.szernex.usc.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
	public static final BlockGlowingOrb blockGlowingOrb = new BlockGlowingOrb();

	public static void init()
	{
		GameRegistry.registerBlock(blockGlowingOrb, Names.Blocks.GLOWING_ORB);
	}
}
