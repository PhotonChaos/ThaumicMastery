package com.carbon.thaumicmastery.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GUIHandler implements IGuiHandler {
	public static final int DECAY_GUI   = 0;
	public static final int TERRA_GUI   = 1;
	public static final int AIRPORT_GUI = 2;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
			case DECAY_GUI:
				return new DecayGUI();

			case TERRA_GUI:
				return new TerraGUI();

			case AIRPORT_GUI:
				return new AirportGUI();
		}

		return null;
	}
}
