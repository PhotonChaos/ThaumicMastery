package com.carbon.thaumicmastery.core;

import com.carbon.thaumicmastery.core.lib.LibMisc;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Utils {
	public static boolean isShielded(EntityPlayer player) {
		return player.getEntityData().getBoolean(LibMisc.TAG_SHIELD);
	}

	public static double dist(int x, int y, int z, int xx, int yy, int zz) {
		return Math.sqrt((Math.pow(Math.abs(xx - x), 2) + Math.pow(Math.abs(yy - y), 2) + Math.pow(Math.abs(zz - z), 2)));
	}

	public static boolean isCharDelete(char c, int i) {
		return Character.getNumericValue(c) == -1 && i == 14;
	}

	public static boolean isCharEsc(char c, int i) {
		return Character.getNumericValue(c) == -1 && i == 1;
	}

	public static void closeGui(GuiScreen gui) {
		gui.mc.displayGuiScreen(null);

		if (gui.mc.currentScreen == null) {
			gui.mc.setIngameFocus();
		}
	}

	public static ItemStack getKami(int metadata) {
		return new ItemStack(GameRegistry.findItem(LibMisc.ThaumicTinkerer_MODID, "kamiResource"), metadata);
	}
}
