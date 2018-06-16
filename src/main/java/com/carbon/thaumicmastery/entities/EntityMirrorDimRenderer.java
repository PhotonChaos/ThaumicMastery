package com.carbon.thaumicmastery.entities;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.blocks.BlockDecay;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class EntityMirrorDimRenderer extends Render {
	private IModelCustom model;
	public EntityMirrorDimRenderer() {
		model = AdvancedModelLoader.loadModel(new ResourceLocation("thaumicmastery:models/MirrorDimension.obj"));
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		model.renderAll();
		bindEntityTexture(entity);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("thaumicmastery:textures/models/");
	}
}
