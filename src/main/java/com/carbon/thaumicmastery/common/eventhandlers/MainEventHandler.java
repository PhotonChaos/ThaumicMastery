package com.carbon.thaumicmastery.common.eventhandlers;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityMirrorDimension;
import com.carbon.thaumicmastery.common.keybinds.Keybinds;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class MainEventHandler {
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent key) {
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

		if (Keybinds.decay.isPressed()) {

			try {
				ThaumcraftApiHelper.consumeVisFromWand(player.getHeldItem(), player, new AspectList().add(Aspect.AIR, 10), true, false);
			} catch (NullPointerException e) {
				FMLLog.warning("The player " + player.getDisplayName() + "is not holding a wand!");
			} finally {
				player.sendChatMessage("Wolf x Fox is the best ship");
			}

			player.sendChatMessage(player.getHeldItem().getUnlocalizedName());
		} else if (Keybinds.mirrorDimension.isPressed()) {
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
	}

}