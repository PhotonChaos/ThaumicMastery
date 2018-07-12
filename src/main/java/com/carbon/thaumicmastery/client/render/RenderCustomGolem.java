package com.carbon.thaumicmastery.client.render;

import com.carbon.thaumicmastery.common.entities.CustomGolem;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderCustomGolem extends RenderLiving {
	private ResourceLocation texture;

	public RenderCustomGolem(ModelBase model, float shadowSize) {
		super(model, shadowSize);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float f) {
		preRenderCallbackGolem((CustomGolem) entity, f);
	}

	private void preRenderCallbackGolem(CustomGolem golem, float f) {
		double scale;

		scale = golem.getVis() / 100;

		if (scale < 0.1) {
			scale = 0.1;
		}

		GL11.glScaled(scale, scale, scale);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}
}
