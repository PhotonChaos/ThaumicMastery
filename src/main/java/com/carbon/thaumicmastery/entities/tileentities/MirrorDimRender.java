package com.carbon.thaumicmastery.entities.tileentities;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class MirrorDimRender extends TileEntitySpecialRenderer {

	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float scale) {
		GL11.glPushMatrix();

	}
}
