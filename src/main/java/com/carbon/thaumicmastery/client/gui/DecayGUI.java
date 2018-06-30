package com.carbon.thaumicmastery.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class DecayGUI extends GuiScreen{
	private GuiButton submit;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		super.drawScreen(mouseX, mouseX, partialTicks);
	}

	@Override
	public void initGui() {
		this.buttonList.add(this.submit = new GuiButton(0, this.width/2 - 100, this.height / 2 - 24, "Submit"));
	}

	@Override
	public void actionPerformed(GuiButton button) {
		if (button == this.submit) {
			this.mc.displayGuiScreen(null);

			if (this.mc.currentScreen == null) {
				this.mc.setIngameFocus();
			}
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

}
