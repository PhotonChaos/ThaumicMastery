package com.carbon.thaumicmastery.core;

import net.minecraft.entity.player.EntityPlayer;

public class Utils {
	public static boolean isShielded(EntityPlayer player) {
		return player.getUniqueID().equals(Core.shieldCaster);
	}

	public static int dist(int x, int y, int z, int xx, int yy, int zz) {
		return (int) Math.sqrt((Math.pow(Math.abs(xx - x), 2) + Math.pow(Math.abs(yy - y), 2) + Math.pow(Math.abs(zz - z), 2)));
	}
}
