package com.carbon.thaumicmastery.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GUIHandler implements IGuiHandler {
	public static final int DECAY_GUI = 0;
	public static final int TERRA_GUI = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == DECAY_GUI) {
			return new DecayGUI();
		}

		return null;
	}
}
