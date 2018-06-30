package com.carbon.thaumicmastery.core;

import java.util.UUID;

public class Core {
	// TODO: Make it so that it's one active spell per player

	public static boolean isSpellActive = false;

	// aqua shield
	public static UUID shieldCaster;

	// Mirror Dimension
	public static int[] activeSpellCoords = new int[3];
}
