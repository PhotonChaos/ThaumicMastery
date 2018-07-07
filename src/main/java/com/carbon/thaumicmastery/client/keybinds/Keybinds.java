package com.carbon.thaumicmastery.client.keybinds;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybinds {
	public static String catagory = "key.categories.thaumicmastery";

	public static KeyBinding debug;
	public static KeyBinding revealDim;
	public static KeyBinding novaBlast;
	public static KeyBinding airTeleport;
	public static KeyBinding waterShield;
	public static KeyBinding golem;

	public static void register() {
		debug = new KeyBinding("key.debug", Keyboard.KEY_V, catagory);
		revealDim = new KeyBinding("key.printdim", Keyboard.KEY_P, catagory);

		ClientRegistry.registerKeyBinding(debug);
		ClientRegistry.registerKeyBinding(revealDim);
	}
}
