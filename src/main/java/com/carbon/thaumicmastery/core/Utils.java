package com.carbon.thaumicmastery.core;

import com.carbon.thaumicmastery.core.lib.LibMisc;
import net.minecraft.entity.player.EntityPlayer;

public class Utils {
	public static boolean isShielded(EntityPlayer player) {
		return player.getEntityData().getBoolean(LibMisc.TAG_SHIELD);
	}

	public static double dist(int x, int y, int z, int xx, int yy, int zz) {
		return Math.sqrt((Math.pow(Math.abs(xx - x), 2) + Math.pow(Math.abs(yy - y), 2) + Math.pow(Math.abs(zz - z), 2)));
	}
}
