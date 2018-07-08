package thaumic.tinkerer.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.TileCamo;
import thaumic.tinkerer.common.block.tile.TileRPlacer;
import thaumic.tinkerer.common.lib.LibBlockNames;
import thaumic.tinkerer.common.lib.LibGuiIDs;
import thaumic.tinkerer.common.lib.LibResearch;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by nekosune on 28/06/14.
 */
public class BlockRPlacer extends  BlockCamo implements IWandable{
    public BlockRPlacer() {
        super(Material.rock);
    }
    IIcon[] icons = new IIcon[2];

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile = par1World.getTileEntity(par2, par3, par4);

        TileRPlacer dislocator = (TileRPlacer) tile;
        ItemStack currentStack = par5EntityPlayer.getCurrentEquippedItem();

        if (currentStack != null && currentStack.getItem() == ConfigItems.itemWandCasting) {
            dislocator.orientation = par6;
            par1World.playSoundEffect(par2, par3, par4, "thaumcraft:tool", 0.6F, 1F);

            par1World.markBlockForUpdate(par2, par3, par4);


            return true;
        }

        return super.onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9);
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return TileRPlacer.class;
    }
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        icons[0] = IconHelper.forBlock(par1IconRegister, this, 0);
        icons[1] = IconHelper.forBlock(par1IconRegister, this, 1);
    }

    @Override
    public TileCamo createNewTileEntity(World world, int var2) {
        return new TileRPlacer();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return LibBlockNames.REMOTE_PLACER;
    }

    @Override
    public boolean shouldRegister() {
        return false;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch) new TTResearchItem(LibResearch.KEY_REMOTE_PLACER, new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.MOTION, 1).add(Aspect.SENSES, 1), -6, 3, 3, new ItemStack(this)).setParents(LibResearch.KEY_ANIMATION_TABLET).setConcealed()
                .setPages(new ResearchPage("0"), new ResearchPage("1"), ResearchHelper.arcaneRecipePage(LibResearch.KEY_REMOTE_PLACER));
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe(LibResearch.KEY_REMOTE_PLACER,LibResearch.KEY_REMOTE_PLACER,new ItemStack(this),new AspectList().add(Aspect.AIR, 20).add(Aspect.ORDER, 5).add(Aspect.EARTH, 15).add(Aspect.ENTROPY, 5),"ses","sds","sss",'s', ConfigBlocks.blockStoneDevice,'e', Items.ender_pearl,'d', Blocks.dispenser);
    }
    @Override
    public IIcon getIcon(int par1, int par2) {
        return icons[0];
    }
    @Override
    public IIcon getIconFromSideAfterCheck(TileEntity tile, int meta, int side) {
        return icons[((TileRPlacer) tile).orientation == side ? 1 : 0];
    }
    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        TileEntity tile = par1World.getTileEntity(par2, par3, par4);
        ((TileRPlacer) tile).orientation = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLivingBase);
        par1World.markBlockForUpdate(par2, par3, par4);
    }

    @Override
    public int onWandRightClick(World world, ItemStack itemStack, EntityPlayer entityPlayer, int i, int i2, int i3, int i4, int i5) {
        if(!world.isRemote)
        {
            if(entityPlayer.isSneaking())
            {
                entityPlayer.openGui(ThaumicTinkerer.instance, LibGuiIDs.GUI_ID_REMOTE_PLACER, world, i, i2, i3);
            }
        }
        return 0;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack itemStack, EntityPlayer entityPlayer) {


        return itemStack;
    }

    @Override
    public void onUsingWandTick(ItemStack itemStack, EntityPlayer entityPlayer, int i) {

    }

    @Override
    public void onWandStoppedUsing(ItemStack itemStack, World world, EntityPlayer entityPlayer, int i) {

    }


    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
        if (par1World.isRemote)
            return;

        boolean power = par1World.isBlockIndirectlyGettingPowered(par2, par3, par4) || par1World.isBlockIndirectlyGettingPowered(par2, par3 + 1, par4);
        int meta = par1World.getBlockMetadata(par2, par3, par4);
        boolean on = meta != 0;

        if (power && !on) {
            par1World.scheduleBlockUpdate(par2, par3, par4, this, tickRate(par1World));
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 4);
        } else if (!power && on)
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 4);
    }

    @Override
    public int tickRate(World par1World) {
        return 1;
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        TileEntity tile = par1World.getTileEntity(par2, par3, par4);
        if (tile != null && tile instanceof TileRPlacer) {
            TileRPlacer placer = (TileRPlacer) tile;
            placer.receiveRedstonePulse();
        }
    }
}
