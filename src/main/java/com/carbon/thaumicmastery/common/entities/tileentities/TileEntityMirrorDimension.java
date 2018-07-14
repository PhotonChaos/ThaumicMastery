package com.carbon.thaumicmastery.common.entities.tileentities;

import com.carbon.thaumicmastery.core.Utils;
import com.carbon.thaumicmastery.core.lib.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMirrorDimension extends TileEntity {
	public static int SCALE = 10;
	private final int MAX_DISTANCE = SCALE+1;
	private final int ticksToReset = 20; // the number of ticks until the counter resets

	public String casterName;
	// private boolean casterAssigned = false;
	private boolean exited = false;
	private boolean hadFlyingBefore;
	private boolean initialized = false;

	private int counter;
	private int seconds = 0;
	private int updateSpeed = 10; // every 10 ticks = 2 times a second
	private boolean counterEnabled = true;
	private int duration = 10;

	EntityPlayer casterF;

	// TODO: Fix Flying code
	// TODO: Add potion effects

	@Override
	public void updateEntity() {
		if (casterName.isEmpty()) return;

		if (!initialized) {
			casterF = worldObj.getPlayerEntityByName(casterName);
			hadFlyingBefore = casterF.capabilities.allowFlying;

			initialized = true;
		}

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
		EntityPlayer player = worldObj.getPlayerEntityByName(casterName);

		if (Utils.dist(player.posX, player.posY, player.posZ, xCoord, yCoord, zCoord) <= MAX_DISTANCE) {
			if (exited) exited = false;

			player.capabilities.allowFlying = true;
			player.capabilities.setFlySpeed(player.capabilities.getFlySpeed()+0.5F);

			// Potion Effects
			if (!worldObj.isRemote) {
				player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 5, 2));
				player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 5, 1));
			}

			player.extinguish();

			float abs = player.getAbsorptionAmount();

			if (abs*2 > 10.0F) {
				player.setAbsorptionAmount(abs*2);
			} else {
				player.setAbsorptionAmount(10.0F);
			}
		} else {
			if (!exited) {
				exited = true;

				if (!hadFlyingBefore && !player.capabilities.isCreativeMode) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
				}

				player.capabilities.setFlySpeed(player.capabilities.getFlySpeed() - 0.5F); // slow the speed back to normal
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
