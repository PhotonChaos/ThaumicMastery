package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityMirrorDimension;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class MirrorFocus extends MasterFocusBase {
	private int cost = 10;
	private int cooldown = 10;
	private AspectList visCost = new AspectList().add(Aspect.ORDER, cost * 100);

	public MirrorFocus() {
		super();
		this.focusName = "mirror";
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack wand, World world, EntityPlayer player, MovingObjectPosition mop) {
		if (!world.isRemote && ThaumcraftApiHelper.consumeVisFromWand(wand, player, visCost, true, false)) {
			int x = (int) player.posX;
			int y = (int) player.posY;
			int z = (int) player.posZ;

			player.addChatMessage(new ChatComponentText("X:" + x + " Y:" + y + " Z:" + z + " MIRROR"));

			if (world.isAirBlock(x, y, z)) {
				world.setBlock(x, y, z, ThaumicMastery.blockMirrorDim);
				((TileEntityMirrorDimension) world.getTileEntity(x, y, z)).setCasterID(player.getUniqueID().toString());
			}
		}

		return wand;
	}

	@Override
	public int getActivationCooldown(ItemStack item) {
		return cooldown * 1000;
	}

	@Override
	public int getFocusColor(ItemStack focus) {
		return 0xDDDDDD;
	}

	@Override
	public boolean isVisCostPerTick(ItemStack itemStack) {
		return false;
	}

	@Override
	public AspectList getVisCost(ItemStack itemStack) {
		return visCost;
	}

	@Override
	public WandFocusAnimation getAnimation(ItemStack wand) {
		return WandFocusAnimation.CHARGE;
	}
}
