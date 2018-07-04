package com.carbon.thaumicmastery.core;

import com.carbon.thaumicmastery.core.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.fire.*;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;


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
		return new ItemStack(ThaumicTinkerer.registry.getItemFromClass(ItemKamiResource.class).get(0), 1, metadata);
	}

	public static ItemStack getShard(String aspect) {
		int id;

		if (aspect.equals("air")) {
			id = 0;
		} else if (aspect.equals("fire")) {
			id = 1;
		} else if (aspect.equals("water")) {
			id = 2;
		} else if (aspect.equals("earth")) {
			id = 3;
		} else if (aspect.equals("order")) {
			id = 4;
		} else if (aspect.equals("entropy")) {
			id = 5;
		} else if (aspect.equals("balance") || aspect.equals("balanced")) {
			id = 6;
		} else {
			id = -1;
		}

		if (id > 0) {
			return getShard(id);
		} else {
			return null;
		}
	}

	public static ItemStack getShard(int meta) {
		return new ItemStack(ConfigItems.itemShard, 1, meta);
	}

	public static ItemStack getFire(String aspect) {
		ItemStack ret;
		Block block;

		if (aspect.equals("air")) {
			block = getTTBlock(BlockFireAir.class);
		} else if (aspect.equals("fire")) {
			block = getTTBlock(BlockFireIgnis.class);
		} else if (aspect.equals("water")) {
			block = getTTBlock(BlockFireWater.class);
		} else if (aspect.equals("earth")) {
			block = getTTBlock(BlockFireEarth.class);
		} else if (aspect.equals("order")) {
			block = getTTBlock(BlockFireOrder.class);
		} else if (aspect.equals("entropy")) {
			block = getTTBlock(BlockFireChaos.class);
		} else {
			return null;
		}

		ret = new ItemStack(block, 1, 0);
		return ret;
	}

	private static Block getTTBlock(Class c) {
		return ThaumicTinkerer.registry.getBlockFromClass(c).get(0);
	}

	private static Item getTTItem(Class c) {
		return ThaumicTinkerer.registry.getItemFromClass(c).get(0);
	}
}
