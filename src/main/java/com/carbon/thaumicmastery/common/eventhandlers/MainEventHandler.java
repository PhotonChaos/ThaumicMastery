package com.carbon.thaumicmastery.common.eventhandlers;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityDecay;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityMirrorDimension;
import com.carbon.thaumicmastery.common.keybinds.Keybinds;
import com.carbon.thaumicmastery.core.Core;
import com.carbon.thaumicmastery.core.Utils;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Util;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class MainEventHandler {
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent key) {
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

		if (Keybinds.decay.isPressed()) {
			try {
				player.sendChatMessage(ThaumcraftApiHelper.consumeVisFromWand(player.inventory.getCurrentItem(), player, (new AspectList()).add(Aspect.AIR, 1000), true, false) ? "YES":"NO");
				//ThaumcraftApiHelper.consumeVisFromInventory(player, (new AspectList()).add(Aspect.AIR, 100));

				//player.sendChatMessage(());

				Vec3 v = player.getLookVec();

				int x = (int)v.xCoord;
				int y = (int)v.yCoord;
				int z = (int)v.zCoord;



				player.worldObj.setBlock(x, y, z, ThaumicMastery.blockDecay);
				TileEntityDecay dec = (TileEntityDecay)player.worldObj.getTileEntity(x, y, z);
				dec.setDecayLevel(10);

			} catch (NullPointerException e) {
				FMLLog.warning("The player " + player.getDisplayName() + " is not holding a wand!");
			}

			player.sendChatMessage(player.getHeldItem().getUnlocalizedName());

		}
		else if (Keybinds.mirrorDimension.isPressed()) {
			int x = player.serverPosX;
			int y = player.serverPosY;
			int z = player.serverPosZ;

			player.worldObj.setBlock(x, y, z, ThaumicMastery.blockMirrorDim);
			TileEntity te = player.worldObj.getTileEntity(x, y, z);

			if (te instanceof TileEntityMirrorDimension) {
				TileEntityMirrorDimension md = (TileEntityMirrorDimension)te;
				md.setCasterID(player.getUniqueID().toString());
			}
		}
		else if (Keybinds.waterShield.isPressed()) {
			Core.shieldCaster = player.getUniqueID();
		}
	}

	@SubscribeEvent
	public void onGetHurt(LivingAttackEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			if (Utils.isShielded(player)) {
				event.setCanceled(true);
			}
		}
	}
	@SubscribeEvent
	public void onAttacked(LivingAttackEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			if (Utils.isShielded(player)) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entityLiving;

			if (Utils.isShielded(player)) {
				event.setCanceled(true);
				player.setHealth(player.getMaxHealth());
			}
		}
	}
}