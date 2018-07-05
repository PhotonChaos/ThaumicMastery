package com.carbon.thaumicmastery.common.helpers;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.items.ModItems;
import com.carbon.thaumicmastery.core.Utils;
import com.carbon.thaumicmastery.core.lib.LibResearchKeys;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.common.block.kami.BlockWarpGate;
import thaumic.tinkerer.common.item.foci.*;
import thaumic.tinkerer.common.item.kami.ItemBlockTalisman;
import thaumic.tinkerer.common.item.kami.ItemPlacementMirror;
import thaumic.tinkerer.common.item.kami.ItemProtoclay;
import thaumic.tinkerer.common.item.kami.ItemSkyPearl;
import thaumic.tinkerer.common.item.kami.foci.ItemFocusRecall;
import thaumic.tinkerer.common.item.kami.foci.ItemFocusShadowbeam;
import thaumic.tinkerer.common.item.kami.foci.ItemFocusXPDrain;
import thaumic.tinkerer.common.lib.LibResearch;

import java.util.HashMap;

public class ThaumcraftHelper {
	private static HashMap<String, InfusionRecipe> recipes = new HashMap<String, InfusionRecipe>();
	public static HashMap<String, ResearchItem> research = new HashMap<String, ResearchItem>();

	private static final AspectList root_aspect = new AspectList()
			.add(Aspect.AIR, 1)
			.add(Aspect.WATER, 1)
			.add(Aspect.EARTH, 1)
			.add(Aspect.FIRE, 1)
			.add(Aspect.ORDER, 1)
			.add(Aspect.ENTROPY, 1)
			.add(Aspect.MAGIC, 1)
			.add(Aspect.MAN, 1);

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
		ResourceLocation bg = new ResourceLocation(ThaumicMastery.MODID, "textures/research/category/background.png");
		ResourceLocation logo = ThaumicMastery.logo;
		//ResourceLocation ordo = new ResourceLocation("textures/items/ordo.png");

		ResearchCategories.registerCategory(category, logo, bg);

		research.put(LibResearchKeys.KEY_ROOT, new ResearchItem(LibResearchKeys.KEY_ROOT, category, root_aspect, 0, 0, 3, logo)
				.setRound().setStub().setParents(LibResearch.KEY_ICHORCLOTH_ROD).setConcealed()
				.setPages(getPage("TM_Root.1"),
						new ResearchPage(recipes.get("orderDiscover")),
						new ResearchPage(recipes.get("entropyDiscover")),
						new ResearchPage(recipes.get("airDiscover")),
						new ResearchPage(recipes.get("waterDiscover")))
				.registerResearchItem());


		// ASPECT RESEARCH
		research.put(LibResearchKeys.KEY_ORDER, new ResearchItem(LibResearchKeys.KEY_ORDER, category, new AspectList().add(Aspect.AURA, 1).add(Aspect.ORDER, 1).add(Aspect.MAGIC, 1), 0, -2, 3, new ItemStack(ModItems.mirrordim_item))
				.setSpecial().setConcealed().setParents(LibResearchKeys.KEY_ROOT)
				.setPages(getPage("TM_Ordo.1"), getPage("TM_Ordo.2"), new ResearchPage(recipes.get("MirrorFocus")))
				.registerResearchItem());

		research.put(LibResearchKeys.KEY_ENTROPY, new ResearchItem(LibResearchKeys.KEY_ENTROPY, category, new AspectList().add(Aspect.ENTROPY, 1).add(Aspect.TAINT, 1).add(Aspect.MAGIC, 1), 0, 2, 3, new ItemStack(ModItems.worldeater_item))
				.setSpecial().setConcealed().setParents(LibResearchKeys.KEY_ROOT)
				.setPages(getPage("TM_Perditio.1"), getPage("TM_Perditio.2"), new ResearchPage(recipes.get("DecayFocus")))
				.registerResearchItem());

		research.put(LibResearchKeys.KEY_AIR, new ResearchItem(LibResearchKeys.KEY_AIR, category, new AspectList().add(Aspect.AIR, 1), -2, -1, 3, new ItemStack(ModItems.airport_item))
				.setSpecial().setConcealed().setParents(LibResearchKeys.KEY_ROOT)
				.setPages(getPage("TM_Aer.1"), new ResearchPage(recipes.get("AirFocus")))
				.registerResearchItem());

		research.put(LibResearchKeys.KEY_WATER, new ResearchItem(LibResearchKeys.KEY_WATER, category, new AspectList().add(Aspect.WATER, 1).add(Aspect.ARMOR, 2), 2, 1, 3, new ItemStack(ModItems.hydroshield_item))
				.setSpecial().setConcealed().setParents(LibResearchKeys.KEY_ROOT)
				.setPages(getPage("TM_Water.1"), new ResearchPage(recipes.get("WaterFocus")))
				.registerResearchItem());
	}

	@SuppressWarnings("unchecked")
	private static void initRecipes() {
		final String o = "order";

		recipes.put("orderDiscover", ThaumcraftApi.addInfusionCraftingRecipe(LibResearchKeys.KEY_ROOT,
				new ItemStack(ModItems.mirrordim_item), 12,
				new AspectList().add(Aspect.MAGIC, 64).add(Aspect.ORDER, 128).add(Aspect.AURA, 128),
				new ItemStack(ConfigBlocks.blockMirror, 1, 0),
				new ItemStack[]{
						Utils.getShard(o),
						Utils.getKami(2),
						Utils.getFire(o),
						Utils.getKami(2),
						Utils.getShard(o),
						Utils.getKami(2),
						Utils.getFire(o),
						Utils.getKami(2)
				}));

		recipes.put("entropyDiscover", ThaumcraftApi.addInfusionCraftingRecipe(LibResearchKeys.KEY_ROOT,
				new ItemStack(ModItems.worldeater_item), 12,
				new AspectList().add(Aspect.MAGIC, 64).add(Aspect.ENTROPY, 128).add(Aspect.TAINT, 128),
				new ItemStack(ConfigBlocks.blockMirror),
				new ItemStack[]{
						Utils.getKami(6), // nether shard
						Utils.getKami(2),
						Utils.getFire("entropy"),
						Utils.getKami(2),
						Utils.getKami(7), // ender shard
						Utils.getKami(2),
						Utils.getFire("entropy"),
						Utils.getKami(2)
				}));

		recipes.put("airDiscover", ThaumcraftApi.addInfusionCraftingRecipe(LibResearchKeys.KEY_ROOT,
				new ItemStack(ModItems.airport_item), 12,
				new AspectList().add(Aspect.MAGIC, 64).add(Aspect.AIR, 128).add(Aspect.TRAVEL, 128),
				new ItemStack(ConfigBlocks.blockLifter),
				new ItemStack[]{
						Utils.getTCResource(9),
						Utils.getKami(2),
						Utils.getFire("air"),
						Utils.getKami(2),
						Utils.getTCResource(9),
						Utils.getKami(2),
						Utils.getFire("air"),
						Utils.getKami(2)
				}));

		recipes.put("waterDiscover", ThaumcraftApi.addInfusionCraftingRecipe(LibResearchKeys.KEY_ROOT,
				new ItemStack(ModItems.hydroshield_item), 12,
				new AspectList().add(Aspect.MAGIC, 64).add(Aspect.WATER, 128).add(Aspect.ARMOR, 128),
				new ItemStack(ConfigItems.itemRingRunic, 1, 1),
				new ItemStack[]{
						Utils.getTCResource(15),
						Utils.getKami(2),
						Utils.getFire("water"),
						Utils.getKami(2),
						Utils.getTCResource(15),
						Utils.getKami(2),
						Utils.getFire("water"),
						Utils.getKami(2)
				}));


		// FOCI RECIPES
		recipes.put("MirrorFocus", ThaumcraftApi.addInfusionCraftingRecipe(LibResearchKeys.KEY_ORDER,
				new ItemStack(ModItems.focus_mirror), 13,
				new AspectList().add(Aspect.ORDER, 256).add(Aspect.AURA, 128).add(Aspect.CRYSTAL, 64),
				new ItemStack(ModItems.mirrordim_item),
				new ItemStack[]{
						new ItemStack(ConfigItems.itemFocusPortableHole),
						Utils.getTCResource(0),
						Utils.getTCResource(1),
						new ItemStack(ConfigItems.itemFocusPrimal),
						Utils.getTCResource(14),
						Utils.getShard("balanced"),
						new ItemStack(Utils.getTTItem(ItemFocusXPDrain.class)),
						Utils.getTCEldritchObject(3),
						Utils.getKami(0),
						new ItemStack(Utils.getTTItem(ItemFocusHeal.class)),
						new ItemStack(ConfigItems.itemSanitySoap),
						new ItemStack(ConfigItems.itemBathSalts)
				}));

		recipes.put("DecayFocus", ThaumcraftApi.addInfusionCraftingRecipe(LibResearchKeys.KEY_ENTROPY,
				new ItemStack(ModItems.focus_decay), 13,
				new AspectList().add(Aspect.ENTROPY, 256).add(Aspect.TAINT, 128).add(Aspect.DARKNESS, 64),
				new ItemStack(ModItems.worldeater_item),
				new ItemStack[]{
						new ItemStack(Utils.getTTItem(ItemFocusEnderChest.class)),
						Utils.getTCResource(11),
						Utils.getTCResource(12),
						new ItemStack(Utils.getTTItem(ItemFocusShadowbeam.class)),
						Utils.getTCResource(17),
						new ItemStack(ConfigItems.itemBottleTaint),
						new ItemStack(ConfigItems.itemFocusExcavation),
						new ItemStack(ConfigItems.itemPrimalCrusher),
						new ItemStack(Utils.getTTItem(ItemBlockTalisman.class)),
						new ItemStack(Utils.getTTItem(ItemFocusDislocation.class)),
						new ItemStack(Utils.getTTItem(ItemPlacementMirror.class)),
						new ItemStack(Utils.getTTItem(ItemProtoclay.class))
				}));

		recipes.put("AirFocus", ThaumcraftApi.addInfusionCraftingRecipe(LibResearchKeys.KEY_AIR,
				new ItemStack(ModItems.focus_airport), 13,
				new AspectList().add(Aspect.AIR, 256).add(Aspect.TRAVEL, 128).add(Aspect.ELDRITCH, 128),
				new ItemStack(ModItems.airport_item),
				new ItemStack[]{
						new ItemStack(Utils.getTTItem(ItemFocusRecall.class)),
						new ItemStack(Utils.getTTBlock(BlockWarpGate.class)),
						new ItemStack(Utils.getTTBlock(BlockWarpGate.class)),
						new ItemStack(ConfigItems.itemFocusPortableHole),
						new ItemStack(ConfigItems.itemHoverHarness),
						new ItemStack(ConfigItems.itemGirdleHover),
						new ItemStack(Utils.getTTItem(ItemFocusFlight.class)),
						new ItemStack(Utils.getTTItem(ItemSkyPearl.class)),
						new ItemStack(Utils.getTTItem(ItemSkyPearl.class)),
						new ItemStack(Utils.getTTItem(ItemFocusDislocation.class)),
						new ItemStack(ConfigBlocks.blockWoodenDevice),
						new ItemStack(ConfigBlocks.blockMetalDevice, 1, 7)
				}));

		recipes.put("WaterFocus", ThaumcraftApi.addInfusionCraftingRecipe(LibResearchKeys.KEY_WATER,
				new ItemStack(ModItems.focus_water), 13,
				new AspectList().add(Aspect.WATER, 512).add(Aspect.ARMOR, 256).add(Aspect.HEAL, 64),
				new ItemStack(ModItems.hydroshield_item),
				new ItemStack[]{
						new ItemStack(ConfigItems.itemFocusWarding),
						new ItemStack(ConfigItems.itemRingRunic, 1, 1),
						new ItemStack(ConfigItems.itemAmuletRunic),
						new ItemStack(Utils.getTTItem(ItemFocusDeflect.class)),
						new ItemStack(ConfigItems.itemGirdleRunic),
						new ItemStack(ConfigItems.itemAmuletRunic, 1, 1),
						new ItemStack(Utils.getTTItem(ItemFocusTelekinesis.class)),
						new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 5),
						Utils.getTCEldritchObject(3),
						new ItemStack(Utils.getTTItem(ItemFocusHeal.class)),
						Utils.getKami(1),
						Utils.getKami(2)
				}));
	}

	private static ResearchPage getPage(String ident) {
		return new ResearchPage(StatCollector.translateToLocal("thaumicmastery.research_text." + ident));
	}
}
