package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.core.Utils;
import com.carbon.thaumicmastery.core.lib.LibPaths;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import scala.Int;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class AirFocus extends MasterFocusBase {
	private static final int COST_PER_KILOMETER = 10;
	private static final int DIM_COST = 20;

	private IIcon model_orn;

	private int sourceX;
	private int sourceY;
	private int sourceZ;
	private int sourceD;

	private int destX = 0;
	private int destY = 0;
	private int destZ = 0;
	private int destD = 0;

	public AirFocus() {
		this.focusName = "airPort";
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack wand, World world, EntityPlayer player, MovingObjectPosition mop) {
		sourceX = (int) player.posX;
		sourceY = (int) player.posY;
		sourceZ = (int) player.posZ;

		sourceD = player.dimension;

		if (player.isSneaking()) {

		} else {
			if (DimensionManager.isDimensionRegistered(destD)) {

			}
		}


		return wand;
	}

	@Override
	public WandFocusAnimation getAnimation(ItemStack focus) {
		return WandFocusAnimation.CHARGE;
	}

	@Override
	public int getFocusColor(ItemStack focus) {
		return 0xDBC949;
	}

	@Override
	public void registerIcons(IIconRegister register) {
		super.registerIcons(register);
		model_orn = register.registerIcon(ThaumicMastery.MODID + ":" + LibPaths.focusOrnamentPath + "focus_airPort_model_orn");
	}

	@Override
	public IIcon getOrnament(ItemStack focus) {
		return model_orn;
	}

	@Override
	public AspectList getVisCost(ItemStack item) {
		int dimMultiplier = (destD == sourceD) ? 0 : 1;
		int dCost;
		double dDist = Utils.dist(sourceX, sourceY, sourceZ, destX, destY, destZ) / 1000;

		if (dDist - (int) dDist > 0) {
			dCost = (int) dDist + 1;
		} else {
			dCost = (int) dDist;
		}

		return new AspectList().add(Aspect.AIR, DIM_COST * dimMultiplier * 100 + dCost * COST_PER_KILOMETER * 100);
	}

	public void setDest(int x, int y, int z, int d) {
		destX = x;
		destY = y;
		destZ = z;

		destD = d;
	}
}
