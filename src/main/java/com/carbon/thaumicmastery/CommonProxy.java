package com.carbon.thaumicmastery;

import com.carbon.thaumicmastery.common.networking.PacketHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void registerRendering() {
		// Empty b/c this is supposed to be done in ClientProxy
	}

	public void preInit(FMLPreInitializationEvent event) {
		PacketHandler.registerMessages();
	}
}

