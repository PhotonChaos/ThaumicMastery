package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import net.minecraft.util.IIcon;
import thaumcraft.api.wands.ItemFocusBasic;

public class MasterFocusBase extends ItemFocusBasic  {
	protected IIcon icon;
	protected IIcon ornament;

	public MasterFocusBase() {
		super();
		setCreativeTab(ThaumicMastery.tab);
	}
}
