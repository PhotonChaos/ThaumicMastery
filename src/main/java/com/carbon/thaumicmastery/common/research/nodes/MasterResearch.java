package com.carbon.thaumicmastery.common.research.nodes;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

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

	@Override
	@SideOnly(Side.CLIENT)
	public String getName() {
		return StatCollector.translateToLocal("thaumicmastery.research_name." + key);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getText() {
		return StatCollector.translateToLocal("thaumicmastery.research_text." + key);
	}

}
