package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.client.gui.DecayGUI;
import com.carbon.thaumicmastery.client.gui.GUIHandler;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityDecay;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class DecayFocus extends MasterFocusBase {
	private int decaySetLevel = 2;
	private int costPerLevel = 5;

	public DecayFocus() {
		super();
		this.visCost = new AspectList().add(Aspect.ENTROPY, costPerLevel * decaySetLevel * 100);
		this.focusName = "decay";
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack wand, World world, EntityPlayer player, MovingObjectPosition movingObjectPosition) {
		if (player.isSneaking()) {
			if (world.isRemote) {
				//DecayGUI.focus = this;
				player.openGui(ThaumicMastery.instance, GUIHandler.DECAY_GUI, world, player.serverPosX, player.serverPosY, player.serverPosZ);
			}
		} else {
			if (!world.isRemote) {
				MovingObjectPosition mop = player.rayTrace(4, 20);

				if (movingObjectPosition != null && ThaumcraftApiHelper.consumeVisFromWand(wand, player, getVisCost(wand), true, false)) {
					int x = movingObjectPosition.blockX;
					int y = movingObjectPosition.blockY;
					int z = movingObjectPosition.blockZ;

					world.setBlock(x, y, z, ThaumicMastery.blockDecay);
					((TileEntityDecay)world.getTileEntity(x, y, z)).setDecayLevel(decaySetLevel);
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
		return visCost;
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
