package com.carbon.thaumicmastery.common.entities.tileentities;

import com.carbon.thaumicmastery.core.Utils;
import com.carbon.thaumicmastery.core.lib.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

import java.util.Random;

public class TileEntityMirrorDimension extends TileEntity {
	public static int SCALE = 10;

	private final int MAX_DISTANCE = SCALE+1;
	private final int ticksToReset = 20; // the number of ticks until the counter resets

	private String casterName;
	private boolean casterAssigned = false;

	private int counter;
	private int seconds = 0;
	private int updateSpeed = 5; // every 5 ticks = 4 times a second
	private boolean counterEnabled = true;
	private boolean casterExited = true;
	private int duration = 10;

	// TODO: Fix Flying code
	// TODO: Add potion effects

	@Override
	public void updateEntity() {
		if (seconds > duration) {
			deleteThis(worldObj.getPlayerEntityByName(casterName));
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
		double d;
		for (int i = 0; i < worldObj.playerEntities.size(); i++) {
			EntityPlayer player = (EntityPlayer) worldObj.playerEntities.get(i);

			Vec3 position = player.getPosition(1.0F);
			d = Utils.dist(xCoord, yCoord, zCoord, (int) position.xCoord, (int) position.yCoord, (int) position.zCoord);

			if (!(d > MAX_DISTANCE)) {
				if (Utils.isMirrorDimCaster(player, xCoord, yCoord, zCoord)) {
					casterExited = false;

					if (!casterAssigned) {
						casterName = player.getDisplayName();
						casterAssigned = true;
					}

					// apply effects
					player.capabilities.allowFlying = true;
					player.capabilities.setFlySpeed(2.0F);

					player.addPotionEffect(new PotionEffect(10, 3, 2, false));
					player.addPotionEffect(new PotionEffect(11, 3, 1, false));
					player.addPotionEffect(new PotionEffect(12, 3, 1, false));

					player.extinguish();

					player.setAbsorptionAmount(10.0F);
				}
			} else {
				if (Utils.isMirrorDimCaster(player, xCoord, yCoord, zCoord) && !player.capabilities.isCreativeMode && !casterExited) {
					casterExited = true;

					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
				}
			}
		}
	}


	private void deleteThis(EntityPlayer player) {
		worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		worldObj.removeTileEntity(xCoord, yCoord, zCoord);

		player.getEntityData().setBoolean(LibMisc.TAG_MD_CASTED, false);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		casterName = tag.getString("casterName");
		counter = tag.getInteger("counter");
		updateSpeed = tag.getInteger("updateSpeed");
		seconds = tag.getInteger("seconds");
		counterEnabled = tag.getBoolean("counterEnabled");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setString("casterName", casterName);
		tag.setInteger("counter", counter);
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

	// getters
	public int getCounter() {
		return counter;
	}

	public int getDuration() {
		return duration;
	}
}
