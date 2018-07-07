package com.carbon.thaumicmastery.common.eventhandlers;

import com.carbon.thaumicmastery.client.keybinds.Keybinds;
import com.carbon.thaumicmastery.core.Utils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class MainEventHandler {
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent key) {
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

		if (Keybinds.debug.isPressed() && player.getHeldItem() != null) {
			player.sendChatMessage(player.getHeldItem().getUnlocalizedName());

		} else if (Keybinds.revealDim.isPressed()) {
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GRAY + "[You are in Dimension " + EnumChatFormatting.BOLD + EnumChatFormatting.AQUA + player.dimension + EnumChatFormatting.RESET + EnumChatFormatting.DARK_GRAY + "]"));
		}
	}

	@SubscribeEvent
	public void onGetHurt(LivingAttackEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			if (Utils.isShielded(player)) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onAttacked(LivingAttackEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			if (Utils.isShielded(player)) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			if (Utils.isShielded(player)) {
				event.setCanceled(true);
				player.setHealth(player.getMaxHealth());
			}
		}
	}
}