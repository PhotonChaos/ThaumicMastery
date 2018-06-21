package com.carbon.thaumicmastery.eventhandlers;

import com.carbon.thaumicmastery.keybinds.Keybinds;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class KeyInputHandler {
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent key) {
		if (Keybinds.test.isPressed()) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Happy 6/21!"));
		}
	}
}
