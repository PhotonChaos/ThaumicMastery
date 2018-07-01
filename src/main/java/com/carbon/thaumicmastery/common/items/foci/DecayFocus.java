package com.carbon.thaumicmastery.common.items.foci;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.common.entities.tileentities.TileEntityDecay;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class DecayFocus extends MasterFocusBase {
	private int decaySetLevel = 10;

	public DecayFocus() {
		super();
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack wand, World world, EntityPlayer caster, MovingObjectPosition movingObjectPosition) {
		if (caster.isSneaking()) {
			// OPEN GUI
		} else {
			int x = (int)caster.posX;
			int y = (int)caster.posY;
			int z = (int)caster.posZ;

			world.setBlock(x, y, z, ThaumicMastery.blockDecay);

		}

		return wand;
	}

	@Override
	public AspectList getVisCost(ItemStack item) {
		return new AspectList().add(Aspect.ENTROPY, 5*decaySetLevel);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		this.icon = register.registerIcon(ThaumicMastery.MODID + ":focus_decay");
		this.ornament = register.registerIcon(ThaumicMastery.MODID + ":focus_decay_orn");
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
	public int getFocusColor(ItemStack item){
		return 0x909090;
	}

	@Override
	public IIcon getOrnament(ItemStack focus) {
		return ornament;
	}
}
