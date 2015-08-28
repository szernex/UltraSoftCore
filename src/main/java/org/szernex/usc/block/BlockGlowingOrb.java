package org.szernex.usc.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.szernex.usc.reference.Names;
import org.szernex.usc.reference.Reference;
import org.szernex.usc.tileentity.TEGlowingOrb;

import java.util.List;
import java.util.Random;

public class BlockGlowingOrb extends BlockFallingTEUSC
{
	protected IIcon iconSides;
	protected IIcon iconTopBottom;

	public BlockGlowingOrb()
	{
		super(Material.glass);

		setBlockName(Names.Blocks.GLOWING_ORB);
		setTickRandomly(true);
		setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
		setHardness(0.0F);


		setLightLevel(1.0F);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		iconSides = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Names.Blocks.GLOWING_ORB + "_sides");
		iconTopBottom = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Names.Blocks.GLOWING_ORB + "_topbottom");
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side < 2)
			return iconTopBottom;
		else
			return iconSides;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB bounding_box, List list, Entity entity)
	{
		if (entity instanceof EntityFallingBlock)
			super.addCollisionBoxesToList(world, x, y, z, bounding_box, list, entity);
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 0;
	}

	@Override
	protected void func_149829_a(EntityFallingBlock entity)
	{
		NBTTagCompound tag = new NBTTagCompound();

		TileEntity te = entity.worldObj.getTileEntity((int) entity.posX, (int) entity.posY, (int) entity.posZ);

		if (te instanceof TEGlowingOrb)
		{
			tag.setInteger(TEGlowingOrb.NBT_TIME_TO_LIVE, ((TEGlowingOrb) te).getTimeToLive());
		}

		entity.field_145810_d = tag;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TEGlowingOrb();
	}
}
