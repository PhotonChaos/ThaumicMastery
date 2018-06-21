package com.carbon.thaumicmastery.keybinds;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybinds {
	public static KeyBinding test;

	public static void register() {
		test = new KeyBinding("key.decay", Keyboard.KEY_P, "key.categories.thaumicmastery");
		ClientRegistry.registerKeyBinding(test);
	}
}
