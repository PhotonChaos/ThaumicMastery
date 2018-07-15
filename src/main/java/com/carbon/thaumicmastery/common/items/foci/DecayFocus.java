package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.client.gui.GUIHandler;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityDecay;
import com.carbon.thaumicmastery.core.lib.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class DecayFocus extends MasterFocusBase {
	private int decaySetLevel = 2;
	private int costPerLevel = 10;

	public DecayFocus() {
		super();
		this.focusName = "decay";
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack wand, World world, EntityPlayer player, MovingObjectPosition movingObjectPosition) {
		decaySetLevel = player.getEntityData().getInteger(LibMisc.TAG_DECAY_LEVEL);

		if (player.isSneaking()) {
			if (world.isRemote) {
				player.openGui(ThaumicMastery.instance, GUIHandler.DECAY_GUI, world, player.serverPosX, player.serverPosY, player.serverPosZ);
			}
		} else {
			if (!world.isRemote) {
				if (movingObjectPosition != null && ThaumcraftApiHelper.consumeVisFromWand(wand, player, getVisCost(wand), true, false)) {
					int x = movingObjectPosition.blockX;
					int y = movingObjectPosition.blockY;
					int z = movingObjectPosition.blockZ;

					world.setBlock(x, y, z, ThaumicMastery.blockDecay);
					((TileEntityDecay) world.getTileEntity(x, y, z)).setDecayLevel(decaySetLevel);
					player.addChatMessage(new ChatComponentText("Decay level: " + decaySetLevel));
				}
			}
		}

		return wand;
	}

	@Override
	public WandFocusAnimation getAnimation(ItemStack itemStack) {
		return WandFocusAnimation.CHARGE;
	}

	@Override
	public AspectList getVisCost(ItemStack item) {
		return new AspectList().add(Aspect.ENTROPY, costPerLevel * decaySetLevel * 100);
	}

	@Override
	public int getFocusColor(ItemStack item) {
		return 0; // no need for a hex code since it's just 0
	}

	@Override
	public boolean isVisCostPerTick(ItemStack i) {
		return false;
	}

	public void setDecaySetLevel(int d) {
		decaySetLevel = d;
	}
}
