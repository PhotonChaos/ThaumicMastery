package com.carbon.thaumicmastery.client.render;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityMirrorDimension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderMirrorDim extends TileEntitySpecialRenderer {
	//private static final ResourceLocation model_texture = new ResourceLocation(ThaumicMastery.MODID, "textures/models/MirrorDimensionUV.png"); // model texture
	private IModelCustom model;
	private float sScale = 10;

	public RenderMirrorDim() {
		model = AdvancedModelLoader.loadModel(new ResourceLocation(ThaumicMastery.MODID, "models/MD_NEW.obj"));
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float timeSinceLastTick) {
		if (tile  == null || !(tile instanceof TileEntityMirrorDimension)) return;
		TileEntityMirrorDimension mirrorDimension = (TileEntityMirrorDimension) tile;
		if (!mirrorDimension.isActive) return;

		// start of rendering
		//sScale = 1;
		float rotation = 0; // the entity should not rotate
		float brightness = (float) Math.abs(Math.sin((float) Minecraft.getSystemTime() / 3000f) * 100f);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glEnable(GL11.GL_BLEND);


		GL11.glDisable(GL11.GL_TEXTURE_2D);
		//GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glColor4f(0.8F, 1.0F, 0.99F, 0.2F);

		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.0F, (float) z + 0.5F);

		GL11.glScalef(sScale, sScale, sScale);

		model.renderAll();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}