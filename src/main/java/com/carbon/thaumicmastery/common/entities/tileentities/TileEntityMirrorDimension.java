package com.carbon.thaumicmastery.common.entities.tileentities;

import com.carbon.thaumicmastery.core.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public class TileEntityMirrorDimension extends TileEntity {
	private final int MAX_DISTANCE = 11;
	private final int ticksToReset = 20; // the number of ticks until the counter resets
	private final int DURATION = 10; // seconds

	public static boolean isActive = true;

	private static String caster;

	private int counter;
	private int seconds = 0;
	private int updateSpeed = 10; // every 5 ticks = 4 times a second
	private int scale = 0;
	private boolean counterEnabled = true;
	private boolean casterExited = true;
	private int duration = 10;

	// TODO: Fix Flying code
	// TODO: Add potion effects

	@Override
	public void updateEntity() {
		if (seconds > duration) {
			deleteThis();
		}

		// functionality
		if (counter % updateSpeed == 0) {
			processFunctionality();
		}

		// counter mechanics
		if (counter == ticksToReset) {
			seconds++;
			counter = 0;
		}
		counter++;
	}

	private void processFunctionality() {
		int d;
		for (int i = 0; i < worldObj.playerEntities.size(); i++) {
			EntityPlayer player = (EntityPlayer) worldObj.playerEntities.get(i);

			Vec3 position = player.getPosition(1.0F);
			d = Utils.dist(xCoord, yCoord, zCoord, (int) position.xCoord, (int) position.yCoord, (int) position.zCoord);

			if (!(d > MAX_DISTANCE)) {
				if (player.getDisplayName().equals(caster) && casterExited && !player.capabilities.isCreativeMode) {
					// if it is the caster
					casterExited = false;
					// apply effects
					player.capabilities.allowFlying = true;
					player.addPotionEffect(new PotionEffect(10, 3, 2, true));
					player.addPotionEffect(new PotionEffect(11, 3, 1, true));
					player.addPotionEffect(new PotionEffect(12, 3, 1, true));
					player.setAbsorptionAmount(3.0F);
				} else {
					// if it is not the caster
					player.addPotionEffect(new PotionEffect(1, 1, 1));

				}
			} else {
				if (player.getDisplayName().equals(caster) && !player.capabilities.isCreativeMode && !casterExited) {
					casterExited = true;

					player.capabilities.allowFlying = false;
					player.setJumping(true);
				}
			}
		}
	}

	private void deleteThis() {
		worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		worldObj.removeTileEntity(xCoord, yCoord, zCoord);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		caster = tag.getString("caster");
		counter = tag.getInteger("counter");
		scale = tag.getInteger("scale");
		updateSpeed = tag.getInteger("updateSpeed");
		seconds = tag.getInteger("seconds");
		counterEnabled = tag.getBoolean("counterEnabled");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setString("caster", caster);
		tag.setInteger("counter", counter);
		tag.setInteger("scale", scale);
		tag.setInteger("updateSpeed", updateSpeed);
		tag.setInteger("seconds", seconds);
		tag.setBoolean("counterEnabled", counterEnabled);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}

	// setters
	public void setCasterID(String id) {
		caster = id;
	}

	// getters
	public int getCounter() {
		return counter;
	}

	public String getCasterID() {
		return caster;
	}
}
