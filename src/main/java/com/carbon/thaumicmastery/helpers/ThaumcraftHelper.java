package com.carbon.thaumicmastery.helpers;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.items.ModItems;
import com.carbon.thaumicmastery.lib.LibResearchKeys;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
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
		//ResourceLocation ordo = new ResourceLocation("textures/items/ordo.png");

		ResearchCategories.registerCategory(category, logo, bg);

		(new ResearchItem(LibResearchKeys.KEY_ROOT, category, new AspectList(), 0, 0, 0, logo))
				.setAutoUnlock().setRound().setStub()
				.setPages(getPage("TM_Root.1"))
				.registerResearchItem();

		(new ResearchItem(LibResearchKeys.KEY_ORDER, category, new AspectList().add(Aspect.AIR, 1).add(Aspect.ORDER, 2), 0, -2, 3, new ItemStack(ModItems.mirrordim_item)))
				.setSpecial().setConcealed().setParents(LibResearchKeys.KEY_ROOT)
				.setPages(getPage("TM_Ordo.1"))
				.registerResearchItem();

		(new ResearchItem(LibResearchKeys.KEY_ENTROPY, category, new AspectList().add(Aspect.ENTROPY, 1), 0, 2, 3, new ItemStack(ModItems.worldeater_item)))
				.setSpecial().setConcealed().setLost().setParents(LibResearchKeys.KEY_ROOT)
				.setPages(getPage("TM_Perditio.1"))
				.registerResearchItem();

	}

	private static void initRecipes() {

	}

	private static ResearchPage getPage(String ident) {
		return new ResearchPage(StatCollector.translateToLocal("thaumicmastery.research_text." + ident));
	}
}
