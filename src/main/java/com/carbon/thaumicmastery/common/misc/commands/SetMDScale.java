package com.carbon.thaumicmastery.common.misc.commands;

import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityMirrorDimension;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import scala.Int;

import java.util.ArrayList;
import java.util.List;

public class SetMDScale implements ICommand {
	private List aliases;
	private int scale;

	@SuppressWarnings("unchecked")
	public SetMDScale() {
		aliases = new ArrayList();
		aliases.add("setMDScale");
		aliases.add("mdscale");
	}

	@Override
	public String getCommandName() {
		return "setMirrorScale";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "setMirrorScale <scale>";
	}

	@Override
	public List getCommandAliases() {
		return aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (sender.getEntityWorld().isRemote) return;

		if (args.length == 0) {
			sender.addChatMessage(new ChatComponentText("Invalid Argument"));
			return;
		}

		try {
			TileEntityMirrorDimension.SCALE = Integer.parseInt(args[0]);
		} catch (Exception e) {
			sender.addChatMessage(new ChatComponentText("Invalid Argument"));
			return;
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] args) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}
}
