package com.carbon.thaumicmastery.helpers;

import com.carbon.thaumicmastery.ThaumicMastery;
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

		ResearchCategories.registerCategory(category, new ResourceLocation(ThaumicMastery.MODID, "textures/research/category/thaumicmastery.png"), new ResourceLocation(ThaumicMastery.MODID, "textures/research/category/background.png"));

		new ResearchItem("TM_root", category, new AspectList(), 0, 0, 3, new ResourceLocation(ThaumicMastery.MODID, "textures/research/category/thaumicmastery.png"))
				.setSpecial().setStub().setAutoUnlock().setPages(new ResearchPage("THIS IS A TEST PAGE"));
	}

	private static void initRecipes() {

	}

	private static ResearchPage getPage(String ident) {
		return new ResearchPage("TEST");
	}
}
