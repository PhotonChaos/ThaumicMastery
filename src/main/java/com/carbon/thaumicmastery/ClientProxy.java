package com.carbon.thaumicmastery;

import com.carbon.thaumicmastery.entities.EntityMirrorDimRenderer;
import com.carbon.thaumicmastery.entities.EntityMirrorDimension;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRendering() {
		RenderingRegistry.registerEntityRenderingHandler(EntityMirrorDimension.class, new EntityMirrorDimRenderer());
	}
}
