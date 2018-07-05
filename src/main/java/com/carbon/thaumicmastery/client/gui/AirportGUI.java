package com.carbon.thaumicmastery.client.gui;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.networking.PacketHandler;
import com.carbon.thaumicmastery.common.networking.packets.PacketSendAir;
import com.carbon.thaumicmastery.core.Utils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class AirportGUI extends GuiScreen {
	private ResourceLocation backgroundImage;

	private GuiButton enter;

	private GuiTextField xField;
	private GuiTextField yField;
	private GuiTextField zField;
	private GuiTextField dField;

	@Override
	@SuppressWarnings("unchecked")
	public void initGui() {
		backgroundImage = new ResourceLocation(ThaumicMastery.MODID, "textures/gui/airport_gui.png");

		final int quarterWidth = this.width / 4;
		final int quarterHeight = this.height / 4;
		final int tbHeight = 20;
		final int tbWidth = 70;
		final int halfW = tbWidth / 2;
		final int halfH = tbHeight / 2;
		final int buttonW = 100;
		final int buttonH = 20;

		final int adj = 20;

		xField = new GuiTextField(this.fontRendererObj, this.width / 2 - halfW, this.height / 2 - halfH - (tbHeight * 3 + buttonH + 16) + adj, tbWidth, tbHeight);
		yField = new GuiTextField(this.fontRendererObj, this.width / 2 - halfW, this.height / 2 - halfH - (tbHeight * 2 + buttonH + 12) + adj, tbWidth, tbHeight);
		zField = new GuiTextField(this.fontRendererObj, this.width / 2 - halfW, this.height / 2 - halfH - (tbHeight + buttonH + 8) + adj, tbWidth, tbHeight);
		dField = new GuiTextField(this.fontRendererObj, this.width / 2 - halfW, this.height / 2 - halfH - (buttonH + 4) + adj, tbWidth, tbHeight);

		// One day, but not today.
		/*
		xField = new GuiTextField(this.fontRendererObj, quarterWidth - halfW, quarterHeight * 3 - halfH, tbWidth, tbHeight);
		yField = new GuiTextField(this.fontRendererObj, this.width / 2 - halfW, quarterHeight - halfH, tbWidth, tbHeight);
		zField = new GuiTextField(this.fontRendererObj, quarterWidth * 3 - halfW, quarterHeight * 3 - halfH, tbWidth, tbHeight);
		dField = new GuiTextField(this.fontRendererObj, this.width / 2 - halfW, this.height / 2 - halfH, tbWidth, tbHeight);
        */
		this.buttonList.add(this.enter = new GuiButton(0, this.width / 2 - buttonW / 2, this.height / 2 - buttonH / 2 + 10 + adj, buttonW, buttonH, "Set Coordinates"));
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialticks) {
		this.drawDefaultBackground();

		GL11.glColor4f(1, 1, 1, 1);
		this.mc.renderEngine.bindTexture(backgroundImage);
		this.drawTexturedModalRect(width / 2 - 128, height / 2 - 128, 0, 0, 256, 256);

		this.xField.drawTextBox();
		this.yField.drawTextBox();
		this.zField.drawTextBox();
		this.dField.drawTextBox();

		super.drawScreen(mouseX, mouseY, partialticks);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();

		this.xField.updateCursorCounter();
		this.yField.updateCursorCounter();
		this.zField.updateCursorCounter();
		this.dField.updateCursorCounter();
	}

	@Override
	protected void mouseClicked(int x, int y, int btn) {
		super.mouseClicked(x, y, btn);

		this.xField.mouseClicked(x, y, btn);
		this.yField.mouseClicked(x, y, btn);
		this.zField.mouseClicked(x, y, btn);
		this.dField.mouseClicked(x, y, btn);
	}

	@Override
	protected void keyTyped(char letter, int par2) {
		if (Character.isDigit(letter) || letter == '-' || Utils.isCharDelete(letter, par2)) {
			super.keyTyped(letter, par2);

			if (this.xField.isFocused()) {
				this.xField.textboxKeyTyped(letter, par2);
			} else if (this.yField.isFocused()) {
				this.yField.textboxKeyTyped(letter, par2);
			} else if (this.zField.isFocused()) {
				this.zField.textboxKeyTyped(letter, par2);
			} else if (this.dField.isFocused()) {
				this.dField.textboxKeyTyped(letter, par2);
			}


		} else if (Utils.isCharEsc(letter, par2)) {
			Utils.closeGui(this);
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button == this.enter && !(xField.getText().isEmpty() || yField.getText().isEmpty() || zField.getText().isEmpty())) {
			int x = Integer.parseInt(this.xField.getText());
			int y = Integer.parseInt(this.yField.getText());
			int z = Integer.parseInt(this.zField.getText());
			int d = 0;
			boolean hasD = true;

			if (dField.getText().isEmpty()) {
				hasD = false;
			} else {
				d = Integer.parseInt(this.dField.getText());
			}

			PacketHandler.INSTANCE.sendToServer(new PacketSendAir(x, y, z, d, hasD));

			Utils.closeGui(this);
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
