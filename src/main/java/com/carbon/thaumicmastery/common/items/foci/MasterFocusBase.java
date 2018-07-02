package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.core.lib.LibPaths;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;

public class MasterFocusBase extends ItemFocusBasic {
	protected IIcon icon;
	protected IIcon ornament;

	protected String focusName;

	protected AspectList visCost;

	public MasterFocusBase() {
		super();
		setCreativeTab(ThaumicMastery.tab);
	}

	@Override
	public AspectList getVisCost(ItemStack item) {
		return this.visCost;
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack wand, World world, EntityPlayer player, MovingObjectPosition mop) {
		player.setItemInUse(wand, Integer.MAX_VALUE);

		return wand;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int renderPass) {
		return renderPass == 1 ? this.icon : this.ornament;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		this.icon = register.registerIcon(ThaumicMastery.MODID + ":" + LibPaths.focusTexturePath +"focus_" + focusName);
		this.ornament = register.registerIcon(ThaumicMastery.MODID + ":" + LibPaths.focusOrnamentPath + "focus_" + focusName + "_orn");
	}

	@Override
	public IIcon getOrnament(ItemStack focus) {
		return ornament;
	}

	@Override
	public String getSortingHelper(ItemStack item) {
		return this.focusName.toUpperCase();
	}

	@Override
	public EnumRarity getRarity(ItemStack item) {
		return EnumRarity.epic;
	}
}
