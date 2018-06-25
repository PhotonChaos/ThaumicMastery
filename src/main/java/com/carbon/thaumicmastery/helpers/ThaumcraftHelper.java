package com.carbon.thaumicmastery.helpers;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.sun.org.apache.regexp.internal.RE;
import com.thaumcraft.*;
import com.thaumcraft.aspects.AspectList;
import com.thaumcraft.research.ResearchCategories;
import com.thaumcraft.research.ResearchItem;
import com.thaumcraft.research.ResearchPage;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ThaumcraftHelper implements IModHelper{
	@Override
	public void preInit() {

	}

	@Override
	public void init() {

	}

	public void postInit() {
		initRecipes();
		initResearch();
	}

	private static void initResearch() {
		String category = "THAUMICMASTERY";
		ResourceLocation bg   = new ResourceLocation(ThaumicMastery.MODID, "textures/research/category/background.png");
		ResourceLocation logo = new ResourceLocation(ThaumicMastery.MODID, "textures/research/category/thaumicmastery.png");

		ResearchCategories.registerCategory(category, logo, bg);

		new ResearchItem("TM_root", category, new AspectList(), 0, 0, 3, logo)
				.setSpecial().setStub().setAutoUnlock()
				.setPages(new ResearchPage("THIS IS A TEST PAGE"))
				.registerResearchItem();
	}

	private static void initRecipes() {

	}

	private static ResearchPage getPage(String ident) {
		return new ResearchPage("TEST");
	}
}
