package com.carbon.thaumicmastery.common.networking.packets;

import com.carbon.thaumicmastery.common.items.foci.DecayFocus;
import com.carbon.thaumicmastery.core.lib.LibMisc;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayerMP;

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

	public PacketSendDecay(GuiTextField textField) {
		this.decayLevel = Integer.parseInt(textField.getText());
	}

	public static class Handler implements IMessageHandler<PacketSendDecay, IMessage> {
		@Override
		public IMessage onMessage(PacketSendDecay message, MessageContext ctx) {
			EntityPlayerMP sender = ctx.getServerHandler().playerEntity;

			sender.getEntityData().setInteger(LibMisc.TAG_DECAY_LEVEL, message.decayLevel);
			return null;
		}
	}
}
