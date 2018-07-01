package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class WaterFocus extends MasterFocusBase {
	private IIcon modelOrnament;

	private int costPerTick = 5;
	private AspectList visCost = new AspectList().add(Aspect.WATER, costPerTick * 100);

	public WaterFocus() {
		super();
		this.focusName = "water";
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
		modelOrnament = register.registerIcon(ThaumicMastery.MODID + ":foci/orn/focus_water_model_orn");
	}

	@Override
	public IIcon getOrnament(ItemStack item) {
		return modelOrnament;
	}
}
