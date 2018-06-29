package com.carbon.thaumicmastery.eventhandlers;

import com.carbon.thaumicmastery.keybinds.Keybinds;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class MainEventHandler {
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent key) {
		if (Keybinds.decay.isPressed()) {
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

			try {
				ThaumcraftApiHelper.consumeVisFromWand(player.getHeldItem(), player, new AspectList().add(Aspect.AIR, 10), true, false);
			} catch (NullPointerException e) {
				FMLLog.warning("The player " + player.getDisplayName() + "is not holding a wand!");
			} finally {
				player.sendChatMessage("Wolf x Fox is the best ship");
			}

			player.sendChatMessage(player.getHeldItem().getUnlocalizedName());
		}
	}

}