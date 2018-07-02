package com.carbon.thaumicmastery.common.networking;

import com.carbon.thaumicmastery.common.networking.packets.PacketSendDecay;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {
	private static int packetId = 0;
	public static SimpleNetworkWrapper INSTANCE = null;

	public PacketHandler() {

	}

	public static int nextId() {
		return packetId++;
	}

	public static void registerMessages(String channelName) {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		registerMessages();
	}

	public static void registerMessages() {
		INSTANCE.registerMessage(PacketSendDecay.Handler.class, PacketSendDecay.class, nextId(), Side.SERVER);
	}
}
