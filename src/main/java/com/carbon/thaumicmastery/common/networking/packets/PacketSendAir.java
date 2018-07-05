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
	private boolean hasD;

	public PacketSendAir() {
		// NO-OP
	}

	public PacketSendAir(int x, int y, int z, int d, boolean hasD) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.d = d;
		this.hasD = hasD;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		d = buf.readInt();
		hasD = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(d);
		buf.writeBoolean(hasD);
	}

	public static class Handler implements IMessageHandler<PacketSendAir, IMessage> {
		@Override
		public IMessage onMessage(PacketSendAir message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().playerEntity;

			player.getEntityData().setInteger(LibMisc.TAG_AIR_X, message.x);
			player.getEntityData().setInteger(LibMisc.TAG_AIR_Y, message.y);
			player.getEntityData().setInteger(LibMisc.TAG_AIR_Z, message.z);
			if (message.hasD) {
				player.getEntityData().setInteger(LibMisc.TAG_AIR_D, message.d);
			} else {
				player.getEntityData().setInteger(LibMisc.TAG_AIR_D, player.dimension);
			}

			return null;
		}
	}
}
