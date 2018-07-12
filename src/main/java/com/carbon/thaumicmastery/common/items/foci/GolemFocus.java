package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.client.gui.GUIHandler;
import com.carbon.thaumicmastery.core.lib.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class GolemFocus extends MasterFocusBase {
	private int vis = 1;

	public GolemFocus() {
		super();
		this.focusName = "golem";
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack wand, World world, EntityPlayer player, MovingObjectPosition mop) {
		vis = player.getEntityData().getInteger(LibMisc.TAG_GOLEM_VIS);

		if (player.isSneaking()) {
			if (world.isRemote) {
				player.openGui(ThaumicMastery.instance, GUIHandler.TERRA_GUI, world, player.serverPosX, player.serverPosY, player.serverPosZ);
			}
		} else {

		}

		return wand;
	}

	@Override
	public WandFocusAnimation getAnimation(ItemStack focusstack) {
		return WandFocusAnimation.CHARGE;
	}

	@Override
	public int getFocusColor(ItemStack focusstack) {
		return 0x2BFF5C;
	}

	@Override
	public boolean isVisCostPerTick(ItemStack focusstack) {
		return false;
	}
}
