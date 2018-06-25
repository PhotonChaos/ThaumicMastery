package com.carbon.thaumicmastery.items;

import com.carbon.thaumicmastery.ThaumicMastery;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {
	public static Item logo_item;

	public static void init() {
		logo_item = new Item()
				.setUnlocalizedName("tm_logo")
				.setTextureName(ThaumicMastery.MODID + ":tm_logo")
				.setCreativeTab(CreativeTabs.tabMaterials);

		GameRegistry.registerItem(logo_item, logo_item.getUnlocalizedName());
	}
}
