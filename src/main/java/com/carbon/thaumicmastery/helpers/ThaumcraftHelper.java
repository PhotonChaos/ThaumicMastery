package com.carbon.thaumicmastery.helpers;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.lib.LibResearchKeys;
import com.carbon.thaumicmastery.research.nodes.MasterResearch;
import com.sun.org.apache.regexp.internal.RE;
import com.thaumcraft.*;
import com.thaumcraft.aspects.AspectList;
import com.thaumcraft.research.ResearchCategories;
import com.thaumcraft.research.ResearchItem;
import com.thaumcraft.research.ResearchPage;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class ThaumcraftHelper {
	public static void preInit() {

	}

	public static void init() {

	}

	public static void postInit() {
		initRecipes();
		initResearch();
	}

	private static void initResearch() {
		String category = "THAUMICMASTERY";
		ResourceLocation bg   = new ResourceLocation(ThaumicMastery.MODID, "textures/research/category/background.png");
		ResourceLocation logo = ThaumicMastery.logo;

		ResearchCategories.registerCategory(category, logo, bg);

		(new ResearchItem(LibResearchKeys.KEY_ROOT, category, new AspectList(), 0, 0, 0, logo))
				.setAutoUnlock().setRound().setStub()
				.setPages(getPage("TM_Root.1"))
				.registerResearchItem();

	}

	private static void initRecipes() {

	}

	private static ResearchPage getPage(String ident) {
		return new ResearchPage(StatCollector.translateToLocal("thaumicmastery.research_text." + ident));
	}
}
