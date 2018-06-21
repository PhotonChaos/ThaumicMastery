package com.carbon.thaumicmastery.blocks;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.entities.tileentities.TileEntityMirrorDimension;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMirrorDimension extends BlockContainer {
	private String casterName;

	public BlockMirrorDimension() {
		super(Material.rock);
		setBlockTextureName(ThaumicMastery.MODID + ":MirrorDimBlock");
		setBlockName("BlockMirrorDimension");
		setBlockUnbreakable();
		setResistance(1000000F);
		setCreativeTab(CreativeTabs.tabCombat);
		setLightLevel(1.0F);
		setBlockBounds(0,0,0,1F,1F,1F);
	}

	@Override
	public boolean isNormalCube() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
		return null;
	}

	@Override
	public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
		// this is to stay empty
	}

	// Tile entity
	@Override
	public TileEntity createNewTileEntity(World world, int n) {
		TileEntityMirrorDimension md = new TileEntityMirrorDimension();
		md.setCasterName("decay");
		return md;
	}


}
