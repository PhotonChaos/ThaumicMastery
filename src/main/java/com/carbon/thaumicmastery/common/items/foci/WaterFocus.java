package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.core.lib.LibMisc;
import com.carbon.thaumicmastery.core.lib.LibPaths;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import java.util.List;

public class WaterFocus extends MasterFocusBase {
	private IIcon modelOrnament;

	private int costPerTick = 5;
	private int potency = 5;
	private AspectList visCost = new AspectList().add(Aspect.WATER, costPerTick * 100);

	public WaterFocus() {
		super();
		this.focusName = "water";
	}

	@Override
	public void onUsingFocusTick(ItemStack wand, EntityPlayer player, int count) {
		if (!player.worldObj.isRemote && wand != null && ThaumcraftApiHelper.consumeVisFromWand(wand, player, getVisCost(wand), true, false)) {
			player.getEntityData().setBoolean(LibMisc.TAG_SHIELD, true);
			player.extinguish();

			deflectProjectiles(player);
		}
	}

	@Override
	public void onPlayerStoppedUsingFocus(ItemStack wand, World world, EntityPlayer player, int count) {
		player.getEntityData().setBoolean(LibMisc.TAG_SHIELD, false);
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
		modelOrnament = register.registerIcon(ThaumicMastery.MODID + ":" + LibPaths.focusOrnamentPath + "focus_water_model_orn");
	}

	@Override
	public IIcon getOrnament(ItemStack item) {
		return modelOrnament;
	}

	@Override
	public WandFocusAnimation getAnimation(ItemStack item) {
		return WandFocusAnimation.CHARGE;
	}

	@SuppressWarnings("unchecked")
	private void deflectProjectiles(EntityPlayer p) {
		List<Entity> projectiles = p.worldObj.getEntitiesWithinAABB(IProjectile.class, AxisAlignedBB.getBoundingBox(p.posX - 4, p.posY - 4, p.posZ - 4, p.posX + 3, p.posY + 3, p.posZ + 3));

		for (Entity e : projectiles) {
			e.motionX -= e.motionX*potency;
			e.motionY -= e.motionY*potency;
			e.motionZ -= e.motionZ*potency;
		}
	}
}
