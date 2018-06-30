package com.carbon.thaumicmastery;

import com.carbon.thaumicmastery.client.render.RenderMirrorDim;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityMirrorDimension;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRendering() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMirrorDimension.class, new RenderMirrorDim());
		System.out.println("===========!!!!!!!!!!!!!!!! CLIENT PROXY !!!!!!!!!!!!!!!!===========");
	}
}
