package com.carbon.thaumicmastery.client.gui;

import com.carbon.thaumicmastery.common.networking.PacketHandler;
import com.carbon.thaumicmastery.common.networking.packets.PacketSendGolem;
import com.carbon.thaumicmastery.core.Utils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;

public class TerraGUI extends GuiScreen {
	private GuiButton submit;
	private GuiTextField visInputTextBox;

	private ResourceLocation backgroundImage;


	@Override
	@SuppressWarnings("unchecked")
	public void initGui() {
		visInputTextBox = new GuiTextField(fontRendererObj, width / 2 - 20, height / 2 - 30, 40, 20);
		visInputTextBox.setMaxStringLength(4);

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button == submit && !visInputTextBox.getText().isEmpty()) {
			PacketHandler.INSTANCE.sendToServer(new PacketSendGolem(Integer.parseInt(visInputTextBox.getText())));
			Utils.closeGui(this);
		}
	}

	@Override
	protected void keyTyped(char letter, int i) {
		if (Utils.isCharDelete(letter, i) || Character.isDigit(letter)) {
			visInputTextBox.textboxKeyTyped(letter, i);
		} else if (Utils.isCharEsc(letter, i)) {
			Utils.closeGui(this);
		} else {
			System.out.println("C: " + Character.getNumericValue(letter) + "\nI: " + i);
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
