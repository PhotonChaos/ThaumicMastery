package com.carbon.thaumicmastery.research.nodes;

import com.thaumcraft.aspects.AspectList;
import com.thaumcraft.research.ResearchItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class MasterResearch extends ResearchItem {
	public MasterResearch(String key, String category) {
		super(key, category);
	}

	public MasterResearch(String key, String category, AspectList tags, int col, int row, int complex, ResourceLocation icon) {
		super(key, category, tags, col, row, complex, icon);
	}

	public MasterResearch(String key, String category, AspectList tags, int col, int row, int complex, ItemStack icon) {
		super(key, category, tags, col, row, complex, icon);
	}
}
