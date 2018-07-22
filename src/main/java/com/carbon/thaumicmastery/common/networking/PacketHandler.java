package com.carbon.thaumicmastery.common.networking;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.networking.packets.PacketSendAir;
import com.carbon.thaumicmastery.common.networking.packets.PacketSendDecay;
import com.carbon.thaumicmastery.common.networking.packets.PacketSendMD;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
	private static final int DECAY_ID = 0;
	private static final int GOLEM_ID = 1;
	private static final int PORTL_ID = 2;

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ThaumicMastery.MODID);

	public PacketHandler() {

	}

	public static void registerMessages() {
		INSTANCE.registerMessage(PacketSendDecay.Handler.class, PacketSendDecay.class, DECAY_ID, Side.SERVER);
		INSTANCE.registerMessage(PacketSendAir.Handler.class, PacketSendAir.class, PORTL_ID, Side.SERVER);
	}
}
