package com.carbon.thaumicmastery.common.networking.packets;

import com.carbon.thaumicmastery.core.lib.LibMisc;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

import javax.swing.text.html.parser.Entity;

public class PacketSendAir implements IMessage {
	private int x;
	private int y;
	private int z;
	private int d;

	public PacketSendAir() {
		// NO-OP
	}

	public PacketSendAir(int x, int y, int z, int d) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.d = d;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		d = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(d);
	}

	public static class Handler implements IMessageHandler<PacketSendAir, IMessage> {
		@Override
		public IMessage onMessage(PacketSendAir message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;

			player.getEntityData().setInteger(LibMisc.TAG_AIR_X, message.x);
			player.getEntityData().setInteger(LibMisc.TAG_AIR_Y, message.y);
			player.getEntityData().setInteger(LibMisc.TAG_AIR_Z, message.z);
			player.getEntityData().setInteger(LibMisc.TAG_AIR_D, message.d);

			return null;
		}
	}
}
