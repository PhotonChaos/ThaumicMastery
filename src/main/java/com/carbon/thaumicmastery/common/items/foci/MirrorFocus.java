package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityMirrorDimension;
import com.carbon.thaumicmastery.core.lib.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;

public class MirrorFocus extends MasterFocusBase {
	private final int cost = 10;
	private int cooldown = 10;

	private AspectList vis = new AspectList()
			.add(Aspect.AIR, cost)
			.add(Aspect.FIRE, cost)
			.add(Aspect.WATER, cost)
			.add(Aspect.EARTH, cost)
			.add(Aspect.ENTROPY, cost);

	public MirrorFocus() {
		super();
		visCost = new AspectList().add(Aspect.ORDER, cost * 100);
		this.focusName = "mirror";
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack wand, World world, EntityPlayer player, MovingObjectPosition mop) {
		if (!world.isRemote && ThaumcraftApiHelper.consumeVisFromWand(wand, player, visCost, true, false)) {
			int x = (int) player.posX;
			int y = (int) player.posY;
			int z = (int) player.posZ;

			ItemWandCasting w = (ItemWandCasting)wand.getItem();

			for (Aspect aspect : vis.getAspects()) {
				w.addRealVis(new ItemStack(w), aspect, cost, true);
			}

			if (world.isAirBlock(x, y, z)) {
				world.setBlock(x, y, z, ThaumicMastery.blockMirrorDim);

				player.getEntityData().setInteger(LibMisc.TAG_MD_X, x);
				player.getEntityData().setInteger(LibMisc.TAG_MD_Y, y);
				player.getEntityData().setInteger(LibMisc.TAG_MD_Z, z);
				player.getEntityData().setBoolean(LibMisc.TAG_MD_CASTED, true);
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
	public WandFocusAnimation getAnimation(ItemStack wand) {
		return WandFocusAnimation.CHARGE;
	}
}
