package com.carbon.thaumicmastery.common.keybinds;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybinds {
	public static String catagory = "key.categories.thaumicmastery";

	public static KeyBinding decay;
	public static KeyBinding mirrorDimension;
	public static KeyBinding novaBlast;
	public static KeyBinding airTeleport;
	public static KeyBinding waterShield;
	public static KeyBinding golem;

	public static void register() {
		decay = new KeyBinding("key.decay", Keyboard.KEY_V, catagory);
		mirrorDimension = new KeyBinding("key.mirrordimension", Keyboard.KEY_P, catagory);
		novaBlast = new KeyBinding("key.novablast", Keyboard.KEY_N, catagory);
		airTeleport = new KeyBinding("key.airteleport", Keyboard.KEY_M, catagory);
		waterShield = new KeyBinding("key.vistoessentia", Keyboard.KEY_H, catagory);
		golem = new KeyBinding("key.spawngolem", Keyboard.KEY_G, catagory);

		ClientRegistry.registerKeyBinding(decay);
		ClientRegistry.registerKeyBinding(mirrorDimension);
		ClientRegistry.registerKeyBinding(novaBlast);
		ClientRegistry.registerKeyBinding(airTeleport);
		ClientRegistry.registerKeyBinding(waterShield);
		ClientRegistry.registerKeyBinding(golem);
	}
}
