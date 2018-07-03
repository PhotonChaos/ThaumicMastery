package com.carbon.thaumicmastery.common.networking.packets;

import com.carbon.thaumicmastery.core.lib.LibMisc;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.gui.GuiTextField;

public class PacketSendDecay implements IMessage {
	private int decayLevel;

	@Override
	public void fromBytes(ByteBuf buf) {
		decayLevel = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(decayLevel);
	}

	public PacketSendDecay() {

	}

	public PacketSendDecay(GuiTextField textField) {
		this.decayLevel = Integer.parseInt(textField.getText());
	}

	public static class Handler implements IMessageHandler<PacketSendDecay, IMessage> {
		@Override
		public IMessage onMessage(PacketSendDecay message, MessageContext ctx) {
			ctx.getServerHandler().playerEntity.getEntityData().setInteger(LibMisc.TAG_DECAY_LEVEL, message.decayLevel);
			return null;
		}
	}
}
