package com.carbon.thaumicmastery.common.networking.packets;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.UUID;

public class PacketSendMD implements IMessage {
	private int length;

	String uuid;
	boolean flying;

	public PacketSendMD(UUID id, boolean flying) {
		uuid = id.toString();
		length = uuid.length();
		this.flying = flying;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		flying = buf.readBoolean();
		length = buf.readInt();
		uuid = "";

		for (int i = 0; i < length; i++) {
			uuid += buf.readChar();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(flying);
		buf.writeInt(length);
		for (char c: uuid.toCharArray()) {
			buf.writeChar(c);
		}
	}

	public static class Handler implements IMessageHandler<PacketSendMD, IMessage> {
		@Override
		public IMessage onMessage(PacketSendMD message, MessageContext ctx) {
			EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;

			if (player.getUniqueID().toString().equals(message.uuid)) {
				player.capabilities.allowFlying = message.flying;
			}

			return null;
		}
	}
}
