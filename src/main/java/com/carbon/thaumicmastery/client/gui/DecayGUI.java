package com.carbon.thaumicmastery.client.gui;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.networking.PacketHandler;
import com.carbon.thaumicmastery.common.networking.packets.PacketSendDecay;
import com.carbon.thaumicmastery.core.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class DecayGUI extends GuiScreen {
	private GuiButton submit;
	private GuiTextField levelTextBox;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.levelTextBox.drawTextBox();
		super.drawScreen(mouseX, mouseX, partialTicks);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void initGui() {
		levelTextBox = new GuiTextField(this.fontRendererObj, this.width / 2 - 20, this.height / 2 - 20, 40, 20);
		levelTextBox.setMaxStringLength(4);
		this.levelTextBox.setFocused(true);
		this.buttonList.add(this.submit = new GuiButton(0, this.width / 2 - 100, this.height / 2 + 10, "Set Decay Level"));
	}

	@Override
	public void keyTyped(char letter, int par2) {
		if (Character.isDigit(letter) || Utils.isCharDelete(letter, par2)) {
			super.keyTyped(letter, par2);
			this.levelTextBox.textboxKeyTyped(letter, par2);
		} else if (Utils.isCharEsc(letter, par2)) {
			closeGui();
		} else {
			System.out.println("Char: " + Character.getNumericValue(letter) + "\npar2: " + par2);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void actionPerformed(GuiButton button) {
		if (button == this.submit && !this.levelTextBox.getText().isEmpty()) {
			PacketHandler.INSTANCE.sendToServer(new PacketSendDecay(Integer.parseInt(this.levelTextBox.getText())));
			closeGui();
		}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		this.levelTextBox.updateCursorCounter();

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(ThaumicMastery.MODID, "textures/gui/WorldEaterGUI.png"));

		this.drawTexturedModalRect(width / 2, height / 2, 50, 0, 20, 20);
	}

	@Override
	public void mouseClicked(int x, int y, int btn) {
		super.mouseClicked(x, y, btn);
		this.levelTextBox.mouseClicked(x, y, btn);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	private void closeGui() {
		this.mc.displayGuiScreen(null);

		if (this.mc.currentScreen == null) {
			this.mc.setIngameFocus();
		}
	}
}
