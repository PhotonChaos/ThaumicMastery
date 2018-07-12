package com.carbon.thaumicmastery.common.blocks;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityMirrorDimension;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliser;

public class BlockMirrorDimension extends BlockContainer implements IInfusionStabiliser {
	public BlockMirrorDimension() {
		super(Material.air);
		setBlockTextureName(ThaumicMastery.MODID + ":MirrorDimBlock");
		setBlockName("BlockMirrorDimension");
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setCreativeTab(ThaumicMastery.tab);
		setLightLevel(1.0F);
		setBlockBounds(0,0,0,0,0,0);
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
		return new TileEntityMirrorDimension();
	}

	@Override
	public boolean canStabaliseInfusion(World world, int x, int y, int z) {
		return true;
	}
}
