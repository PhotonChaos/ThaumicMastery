package com.carbon.thaumicmastery.research.catagories;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.thaumcraft.research.ResearchCategoryList;
import net.minecraft.util.ResourceLocation;

public class RCThaumicMastery extends ResearchCategoryList {
	public ResourceLocation tabIcon = new ResourceLocation(ThaumicMastery.MODID, "textures/research/category/thaumicmastery.png");
	public ResourceLocation bg = new ResourceLocation(ThaumicMastery.MODID, "textures/research/category/bg.png");

	public RCThaumicMastery(ResourceLocation icon, ResourceLocation background) {
		super(icon, background);
	}


}
