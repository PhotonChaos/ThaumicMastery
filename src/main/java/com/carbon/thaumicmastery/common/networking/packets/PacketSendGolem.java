package com.carbon.thaumicmastery.common.networking.packets;

import com.carbon.thaumicmastery.core.lib.LibMisc;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class PacketSendGolem implements IMessage {
	int vis;

	public PacketSendGolem() {
		// NO-OP
	}

	public PacketSendGolem(int vis) {
		this.vis = vis;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		vis = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(vis);
	}

	public static class Handler implements IMessageHandler<PacketSendGolem, IMessage> {

		@Override
		public IMessage onMessage(PacketSendGolem message, MessageContext ctx) {
			ctx.getServerHandler().playerEntity.getEntityData().setInteger(LibMisc.TAG_GOLEM_VIS, message.vis);
			return null;
		}
	}
}
