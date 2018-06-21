package com.carbon.thaumicmastery.entities.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

import java.util.Collection;
import java.util.Collections;

public class TileEntityMirrorDimension extends TileEntity {
	private final int maxDistance = 10;
	private final int ticksToReset = 40; // the number of ticks until the counter resets
	private final int animationLengthTicks = 40;

	public boolean isActive = true;

	private String caster;
	private int counter;
	private int updateSpeed = 10; // every 5 ticks = 4 times a second
	private int scale = 0;
	private boolean scaleAnimationFinished = false;
	private boolean counterEnabled = true;
	private boolean casterExited = false;
	private boolean startupComplete = false;

/*
	public TileEntityMirrorDimension(String username) {
		caster = username;
	}*/

	@Override
	public void updateEntity() {
		// Rendering
		if (counter == animationLengthTicks && !scaleAnimationFinished) scaleAnimationFinished = true;

		// functionality
		if (counter % updateSpeed == 0) {
			processFunctionality();
		}

		// counter mechanics
		if(counter == ticksToReset) {
			counter = 0;
		}
		counter++;

	}

	private void processFunctionality() {
		int d;
		for (int i = 0; i < worldObj.playerEntities.size(); i++) {
			EntityPlayer player = (EntityPlayer) worldObj.playerEntities.get(i);

			Vec3 position = player.getPosition(1.0F);
			d = dist(xCoord, yCoord, zCoord, (int)position.xCoord, (int)position.yCoord, (int)position.zCoord);

			if(!(d > maxDistance)) {
				if(startupComplete) casterExited = true;
				if ((player.getDisplayName().equals(caster) || player.getDisplayName().equals(player.getDisplayName())) && casterExited) {
					// if it is the caster
					casterExited = false;
					// apply effects
					player.capabilities.allowFlying = true;

				} else {
					// if it is not the caster

				}
			} else {
				if ((player.getDisplayName().equals(caster) || player.getDisplayName().equals(player.getDisplayName())) && !player.capabilities.isCreativeMode && !casterExited) {
					casterExited = true;

					player.capabilities.allowFlying = false;
					player.setJumping(true);
				}
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		caster = tag.getString("caster");
		counter = tag.getInteger("counter");
		scale = tag.getInteger("scale");
		updateSpeed = tag.getInteger("updateSpeed");
		counterEnabled = tag.getBoolean("counterEnabled");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setString("caster", caster);
		tag.setInteger("counter", counter);
		tag.setInteger("scale", scale);
		tag.setInteger("updateSpeed", updateSpeed);
		tag.setBoolean("counterEnabled", counterEnabled);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}

	private static int dist(int x, int y, int z, int xx, int yy, int zz) {
		return (int) Math.sqrt((Math.pow(Math.abs(xx - x), 2) + Math.pow(Math.abs(yy - y), 2) + Math.pow(Math.abs(zz - z), 2)));
	}


	// setters
	public void setCasterName(String name) {
		caster = name;
	}

	// getters
	public int getCounter() {
		return counter;
	}
}
