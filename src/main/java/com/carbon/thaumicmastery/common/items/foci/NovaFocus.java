package com.carbon.thaumicmastery.common.items.foci;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.codechicken.lib.vec.Vector3;

public class NovaFocus extends MasterFocusBase {
	public NovaFocus() {
		super();
		focusName = "novablast";
	}

	@Override
	public void onUsingFocusTick(ItemStack wandstack, EntityPlayer player, int count) {
		super.onUsingFocusTick(wandstack, player, count);
	}

	@Override
	public int getFocusColor(ItemStack focusstack) {
		return 0xE21B00;
	}

	@Override
	public String getSortingHelper(ItemStack item) {
		return "NOVABLAST";
	}

	public static class Beam extends EntityThrowable {
		final static float power = 20.0F;
		Vector3 movement;


		public Beam(World world, EntityLivingBase entity) {
			super(world, entity);
			movement = new Vector3(motionX, motionY, motionZ);
		}

		@Override
		public void onUpdate() {
			motionX = movement.x;
			motionY = movement.y;
			motionZ = movement.z;

			super.onUpdate();
		}

		@Override
		protected void onImpact(MovingObjectPosition MOP) {
			if (MOP == null) return;

			if (MOP.entityHit == null) {
				int x = MOP.blockX;
				int y = MOP.blockY;
				int z = MOP.blockZ;

			} else {
				Entity e = MOP.entityHit;
				if (!e.worldObj.isRemote && MinecraftServer.getServer().isPVPEnabled() &&  getThrower() instanceof EntityPlayer && e != getThrower()) {
					// TODO: Add check for golem

					if (e instanceof EntityPlayer) {
						MOP.entityHit.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)e), power);
					} else {
						MOP.entityHit.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)e), power);
					}
				}

				motionX = 0;
				motionY = 0;
				motionZ = 0;
			}
		}

		public void setProjectileVelocity(double x, double y, double z) {
			motionX = x;
			motionY = y;
			motionZ = z;

			if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
				float t = MathHelper.sqrt_double(x*x + z*z);
			}
		}

			@Override
		protected float getGravityVelocity() {
			return 0.0F;
		}
	}
}
