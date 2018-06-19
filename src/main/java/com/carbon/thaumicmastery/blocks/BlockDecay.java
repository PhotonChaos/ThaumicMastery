package com.carbon.thaumicmastery.blocks;


import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.entities.tileentities.TileEntityDecay;
import com.thaumcraft.ThaumcraftApi;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockDecay extends BlockContainer {
	// Required Variables
	private String name = "decayblock";

	public BlockDecay(Material material) {
		super(material);
		setBlockName("BlockDecay");
		setCreativeTab(CreativeTabs.tabDecorations);
		setHardness(0f);
		setResistance(0f);
		setStepSound(soundTypeStone);
		setBlockTextureName(ThaumicMastery.MODID+":BlockDecay");
		setHarvestLevel("", 100);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileEntityDecay();
	}
}
