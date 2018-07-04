package com.carbon.thaumicmastery.common.helpers;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.items.ModItems;
import com.carbon.thaumicmastery.core.Utils;
import com.carbon.thaumicmastery.core.lib.LibMisc;
import com.carbon.thaumicmastery.core.lib.LibResearchKeys;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import java.util.HashMap;

public class ThaumcraftHelper {
	private static HashMap recipes = new HashMap();

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
				.setPages(getPage("TM_Root.1"), new ResearchPage((InfusionRecipe) recipes.get("orderDiscover")))
				.registerResearchItem();


		// ASPECT RESEARCH
		(new ResearchItem(LibResearchKeys.KEY_ORDER, category, new AspectList().add(Aspect.AIR, 1).add(Aspect.ORDER, 2), 0, -2, 3, new ItemStack(ModItems.mirrordim_item)))
				.setSpecial().setConcealed().setParents(LibResearchKeys.KEY_ROOT)
				.setPages(getPage("TM_Ordo.1"), getPage("TM_Ordo.2"))
				.registerResearchItem();

		(new ResearchItem(LibResearchKeys.KEY_ENTROPY, category, new AspectList().add(Aspect.ENTROPY, 1), 0, 2, 3, new ItemStack(ModItems.worldeater_item)))
				.setSpecial().setConcealed().setLost().setParents(LibResearchKeys.KEY_ROOT)
				.setPages(getPage("TM_Perditio.1"), new ResearchPage((InfusionRecipe) recipes.get("entropyDiscover")))
				.registerResearchItem();

		/*
		(new ResearchItem(LibResearchKeys.KEY_AIR, category, new AspectList().add(Aspect.AIR, 3), -1, -1, 3, new ItemStack(ModItems.airport_item)))
				.setSpecial().setConcealed().setPages(LibResearchKeys.KEY_ROOT)
				.setPages(getPage("TM_Aer.1"), new ResearchPage((InfusionRecipe) recipes.get("airDiscover")))
				.registerResearchItem();*/

	}

	@SuppressWarnings("unchecked")
	private static void initRecipes() {
		recipes.put("orderDiscover", ThaumcraftApi.addInfusionCraftingRecipe(LibResearchKeys.KEY_ORDER, new ItemStack(ModItems.mirrordim_item), 12,
		new AspectList().add(Aspect.MAGIC, 64).add(Aspect.ORDER, 128).add(Aspect.AURA, 128),
				ItemApi.getBlock("blockMirror", 0),
				new ItemStack[] {
				ItemApi.getItem("itemShard", 6),
						Utils.getKami(2),
						ItemApi.getBlock("fireOrder", 0),
						Utils.getKami(2),
						ItemApi.getItem("itemShard", 6),
						Utils.getKami(2),
						ItemApi.getBlock("fireOrder", 0),
						Utils.getKami(2)
				}));

		recipes.put("entropyDiscover", ThaumcraftApi.addInfusionCraftingRecipe(LibResearchKeys.KEY_ENTROPY, new ItemStack(ModItems.worldeater_item), 12,
				new AspectList().add(Aspect.MAGIC, 64).add(Aspect.ENTROPY, 128).add(Aspect.TAINT, 128),
				ItemApi.getBlock("blockMirror", 0),
				new ItemStack[]{
				ItemApi.getItem("itemShard", 6),
						ItemApi.getItem("itemShard", 6),
						new ItemStack(Items.nether_star),
						Utils.getKami(6),
						Utils.getKami(2),
						ItemApi.getBlock("fireChaos", 0),
						Utils.getKami(2),
						Utils.getKami(7),
						Utils.getKami(2),
						ItemApi.getBlock("fireChaos", 0),
						Utils.getKami(2)
				}));
	}

	private static ResearchPage getPage(String ident) {
		return new ResearchPage(StatCollector.translateToLocal("thaumicmastery.research_text." + ident));
	}
}
