package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.core.lib.LibPaths;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;

public class WaterFocus extends MasterFocusBase {
	private IIcon modelOrnament;

	private int costPerTick = 5;
	private AspectList visCost = new AspectList().add(Aspect.WATER, costPerTick * 100);

	public WaterFocus() {
		super();
		this.focusName = "water";
	}

	@Override
	public void onUsingFocusTick(ItemStack wand, EntityPlayer player, int count) {
		if (wand != null && player != null && ThaumcraftApiHelper.consumeVisFromWand(wand, player, getVisCost(wand), true, false)) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setBoolean("isShielded", true);
			player.writeToNBT(tag);
		}
	}

	@Override
	public WandFocusAnimation getAnimation(ItemStack item) {
		return super.getAnimation(item);
	}

	@Override
	public int getFocusColor(ItemStack item) {
		return 0x607FC3;
	}

	@Override
	public boolean isVisCostPerTick(ItemStack item) {
		return true;
	}

	@Override
	public AspectList getVisCost(ItemStack item) {
		return visCost;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		super.registerIcons(register);
		modelOrnament = register.registerIcon(ThaumicMastery.MODID + ":" + LibPaths.focusOrnamentPath +"focus_water_model_orn");
	}

	@Override
	public IIcon getOrnament(ItemStack item) {
		return modelOrnament;
	}
}
