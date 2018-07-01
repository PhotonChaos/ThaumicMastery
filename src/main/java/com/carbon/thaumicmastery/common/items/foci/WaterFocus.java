package com.carbon.thaumicmastery.common.items.foci;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class WaterFocus extends MasterFocusBase {
	private int costPerTick = 5;
	private AspectList visCost = new AspectList().add(Aspect.WATER, costPerTick*100);

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
}
