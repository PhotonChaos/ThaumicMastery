package com.carbon.thaumicmastery.common.items;

import com.carbon.thaumicmastery.ThaumicMastery;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems {
	public static Item mirrordim_item;
	public static Item worldeater_item;
	public static Item novablast_item;


	public static void init() {
		mirrordim_item = new Item()
				.setUnlocalizedName("tm_mirrordim_discover")
				.setTextureName(ThaumicMastery.MODID + ":ordonew")
				.setMaxStackSize(1)
				.setCreativeTab(ThaumicMastery.tab);

		worldeater_item = new Item()
				.setUnlocalizedName("tm_worldeater_discover")
				.setTextureName(ThaumicMastery.MODID + ":perditio")
				.setMaxStackSize(1)
				.setCreativeTab(ThaumicMastery.tab);

		novablast_item = new Item()
				.setUnlocalizedName("tm_novablest_discover")
				.setTextureName(ThaumicMastery.MODID + ":ignis")
				.setMaxStackSize(1)
				.setCreativeTab(ThaumicMastery.tab);


		GameRegistry.registerItem(mirrordim_item, mirrordim_item.getUnlocalizedName());
		GameRegistry.registerItem(worldeater_item, worldeater_item.getUnlocalizedName());
	}
}
