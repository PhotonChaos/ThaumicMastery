/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the ThaumicTinkerer Mod.
 *
 * ThaumicTinkerer is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * ThaumicTinkerer is a Derivative Work on Thaumcraft 4.
 * Thaumcraft 4 (c) Azanor 2012
 * (http://www.minecraftforum.net/topic/1585216-)
 *
 * File Created @ [8 Sep 2013, 22:13:36 (GMT)]
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.registry.ITTinkererBlock;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

import java.util.ArrayList;
import java.util.Random;

public abstract class BlockGas extends BlockMod implements ITTinkererBlock {

	public BlockGas() {
		super(Material.air);
		setBlockBounds(0, 0, 0, 0, 0, 0);
		setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		blockIcon = IconHelper.emptyTexture(par1IconRegister);
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		if (meta != 0) {
			setAt(par1World, par2 - 1, par3, par4, meta - 1);
			setAt(par1World, par2 + 1, par3, par4, meta - 1);

			setAt(par1World, par2, par3 - 1, par4, meta - 1);
			setAt(par1World, par2, par3 + 1, par4, meta - 1);

			setAt(par1World, par2, par3, par4 - 1, meta - 1);
			setAt(par1World, par2, par3, par4 + 1, meta - 1);

			// Just in case...
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);

			placeParticle(par1World, par2, par3, par4);
		}
	}

	public void placeParticle(World world, int par2, int par3, int par4) {
		// NO-OP, override
	}

	private void setAt(World world, int x, int y, int z, int meta) {
		if (world.isAirBlock(x, y, z) && world.getBlock(x, y, z) != this) {
			if (!world.isRemote)
				world.setBlock(x, y, z, this, meta, 2);
			world.scheduleBlockUpdate(x, y, z, this, 10);
		}
	}

	@Override
	public boolean isBlockNormalCube() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity e) {
		return false;
	}

	@Override
	public boolean canCollideCheck(int par1, boolean par2) {
		return false;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean canDropFromExplosion(Explosion par1Explosion) {
		return false;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		return new ArrayList(); // Empty List
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	@Override
	public boolean isAir(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	boolean registerInCreative() {
		return false;
	}

	public BlockGas(Material par2Material) {
		super(par2Material);
	}

	@Override
	public ArrayList<Object> getSpecialParameters() {
		return null;
	}

	@Override
	public boolean shouldRegister() {
		return true;
	}

	@Override
	public boolean func_149730_j() {
		return super.func_149730_j();
	}

	@Override
	public IRegisterableResearch getResearchItem() {
		return null;
	}

	@Override
	public ThaumicTinkererRecipe getRecipeItem() {
		return null;
	}

	@Override
	public boolean shouldDisplayInTab() {
		return false;
	}

	@Override
	public Class<? extends ItemBlock> getItemBlock() {
		return null;
	}

	@Override
	public Class<? extends TileEntity> getTileEntity() {
		return null;
	}
}
