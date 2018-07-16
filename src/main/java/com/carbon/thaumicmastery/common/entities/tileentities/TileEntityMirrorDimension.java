package com.carbon.thaumicmastery.common.entities.tileentities;

import com.carbon.thaumicmastery.core.Utils;
import com.carbon.thaumicmastery.core.lib.LibMisc;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;

import java.util.Iterator;
import java.util.List;

public class TileEntityMirrorDimension extends TileEntity {
	public static int SCALE = 10;
	private final int MAX_DISTANCE = SCALE+1;
	private final int ticksToReset = 20; // the number of ticks until the counter resets

	public String casterName = "";
	// private boolean casterAssigned = false;
	private boolean exited = false;
	//private boolean hadFlyingBefore;
	private boolean initialized = false;

	private int counter;
	private int seconds = 0;
	private int updateSpeed = 20; // every 20 ticks = every second
	private boolean counterEnabled = true;
	private int duration = 10;

	private EntityPlayer caster;

	// TODO: Fix Flying code
	// TODO: Add potion effects

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			if (seconds > duration) {
				deleteThis(caster, "TIME EXPIRED");
				return;
			}

			caster = getCaster();
			if (caster == null) deleteThis(null, "NULL CASTER");

			// functionality
			if (counter % updateSpeed == 0) {
				processFunctionality(caster);
			}

			// counter mechanics
			if (counter == ticksToReset) {
				seconds++;
				counter = 0;
			}
			counter++;
		}
	}

	private void processFunctionality(EntityPlayer player) {
		if (worldObj.isRemote && player == null) return;

		if (Utils.dist(player.posX, player.posY, player.posZ, xCoord, yCoord, zCoord) <= MAX_DISTANCE) {
			player.capabilities.allowFlying = true;
			player.sendPlayerAbilities();

			if (exited) exited = false;

			System.out.println("PLAYER IS HERE");
			//player.capabilities.setFlySpeed(player.capabilities.getFlySpeed()+0.5F);

			// Potion Effects
			player.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 5*20, 2));
			player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 5*20, 1));

			player.extinguish();
		} else {
			if (!exited) {
				exited = true;
				System.out.println("PLAYER IS GONE");
				if (!player.capabilities.isCreativeMode) {
					player.capabilities.allowFlying = false;
					player.capabilities.isFlying = false;
				}
				player.sendPlayerAbilities();
			}
		}
	}

	private void deleteThis(EntityPlayer player, String location) {
		System.out.println("!!!!!!!! ERROR:"+location);

		worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		worldObj.removeTileEntity(xCoord, yCoord, zCoord);

		if (player == null) return;
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

	private EntityPlayer getCaster() {
		for (int i = 0; i < worldObj.playerEntities.size(); i++) {
			EntityPlayer p = (EntityPlayer) worldObj.playerEntities.get(i);

			if (isMirrorDimCaster(p)) {
				return p;
			}
		}
		return null;
	}

	private boolean isMirrorDimCaster(EntityPlayer player) {
		NBTTagCompound tag = player.getEntityData();
		return tag.getInteger(LibMisc.TAG_MD_X) == xCoord && tag.getInteger(LibMisc.TAG_MD_Y) == yCoord && tag.getInteger(LibMisc.TAG_MD_Z) == zCoord && tag.getBoolean(LibMisc.TAG_MD_CASTED);
	}
}
