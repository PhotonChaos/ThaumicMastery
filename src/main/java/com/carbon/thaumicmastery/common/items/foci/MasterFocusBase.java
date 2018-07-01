package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.wands.ItemFocusBasic;

public class MasterFocusBase extends ItemFocusBasic {
	protected IIcon icon;
	protected IIcon ornament;

	protected String focusName;

	public MasterFocusBase() {
		super();
		setCreativeTab(ThaumicMastery.tab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int renderPass) {
		return renderPass == 1 ? this.icon : this.ornament;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		this.icon = register.registerIcon(ThaumicMastery.MODID + ":focus_" + focusName);
		this.ornament = register.registerIcon(ThaumicMastery.MODID + ":focus_" + focusName + "_orn");
	}

	@Override
	public IIcon getOrnament(ItemStack focus) {
		return ornament;
	}
}
