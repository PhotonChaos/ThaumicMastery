package com.carbon.thaumicmastery.entities.tileentities;

import com.carbon.thaumicmastery.ThaumicMastery;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class RenderMirrorDim extends TileEntitySpecialRenderer {
	private static final ResourceLocation model_texture = new ResourceLocation(ThaumicMastery.MODID, ""); // model texture
	private IModelCustom model;
	private float sScale = 2;

	public RenderMirrorDim() {
		model = AdvancedModelLoader.loadModel(new ResourceLocation(ThaumicMastery.MODID, "models/MirrorDimension.obj"));
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeSinceLastTick) {
		if (tile == null || !(tile instanceof TileEntityMirrorDimension)) return;
		TileEntityMirrorDimension mirrorDimension = (TileEntityMirrorDimension) tile;
		if (!mirrorDimension.isActive) return;

		// start of rendering
		sScale = 1;
		float rotation = 0; // the entity should not rotate
		float brightness = (float) Math.abs(Math.sin((float) Minecraft.getSystemTime() / 3000f) * 100f);

		GL11.glPushMatrix();
		GL11.glColor4f(0.8F, 1.0F, 0.96F, 0.3F);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 150f, 150f);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glScalef(sScale, sScale, sScale);
		GL11.glPopMatrix();
	}
}