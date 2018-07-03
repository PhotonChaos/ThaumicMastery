package com.carbon.thaumicmastery.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class AirportGUI extends GuiScreen {
	private GuiButton enter;

	private GuiTextField xField;
	private GuiTextField yField;
	private GuiTextField zField;
	private GuiTextField dField;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialticks) {
		this.drawDefaultBackground();

		this.xField.drawTextBox();
		this.yField.drawTextBox();
		this.zField.drawTextBox();
		this.dField.drawTextBox();

		super.drawScreen(mouseX, mouseY, partialticks);
	}

	@Override
	public void initGui() {
		final int quarterWidth = this.width / 4;
		final int quarterHeight = this.height / 4;
		final int tbHeight = 20;
		final int tbWidth = 70;

		xField = new GuiTextField(this.fontRendererObj, quarterWidth, quarterHeight*3, tbWidth, tbHeight);
		yField = new GuiTextField(this.fontRendererObj, this.width/2, quarterHeight, tbWidth, tbHeight);
		zField = new GuiTextField(this.fontRendererObj, quarterWidth*3, quarterHeight*3, tbWidth, tbHeight);
		dField = new GuiTextField(this.fontRendererObj, this.width / 2, this.height / 2, tbWidth, tbHeight);
		super.initGui();
	}
}
