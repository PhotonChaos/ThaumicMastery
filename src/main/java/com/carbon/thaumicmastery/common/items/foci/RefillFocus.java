package com.carbon.thaumicmastery.common.items.foci;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class RefillFocus extends MasterFocusBase {
	public RefillFocus() {
		super();
		int cost = -100;
		this.visCost = new AspectList().add(Aspect.WATER, cost*100).add(Aspect.AIR, cost*100).add(Aspect.ENTROPY, cost*100).add(Aspect.ORDER, cost*100).add(Aspect.EARTH, cost*100).add(Aspect.FIRE, cost*100);
		this.focusName = "refill";
	}

	@Override
	public void onUsingFocusTick(ItemStack wand, EntityPlayer player, int time) {
		ThaumcraftApiHelper.consumeVisFromWand(wand, player, visCost, true, false);
	}

	@Override
	public int getFocusColor(ItemStack focus) {
		return 0xFCDA14;
	}
}
