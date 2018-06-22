package com.carbon.thaumicmastery.eventhandlers;

import com.carbon.thaumicmastery.keybinds.Keybinds;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.lang.annotation.Annotation;

public class KeyInputHandler {
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent key) {
		if (Keybinds.decay.isPressed()) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Hi there"));
		}
	}

}