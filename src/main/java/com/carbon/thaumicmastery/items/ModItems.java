package com.carbon.thaumicmastery.items;

import com.carbon.thaumicmastery.ThaumicMastery;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {
	public static Item mirrordim_item;

	public static void init() {
		mirrordim_item = new Item()
				.setUnlocalizedName("tm_mirrordim_discover")
				.setTextureName(ThaumicMastery.MODID + ":ordo")
				.setCreativeTab(ThaumicMastery.tab);

		GameRegistry.registerItem(mirrordim_item, mirrordim_item.getUnlocalizedName());
	}
}
