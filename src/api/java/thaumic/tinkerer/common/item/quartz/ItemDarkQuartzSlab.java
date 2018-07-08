/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the ThaumicTinkerer Mod.
 *
 * ThaumicTinkerer is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * ThaumicTinkerer is a Derivative Work on Thaumcraft 4.
 * Thaumcraft 4 (c) Azanor 2012
 * (http://www.minecraftforum.net/topic/1585216-)
 *
 * File Created @ [8 Sep 2013, 16:13:21 (GMT)]
 */
package thaumic.tinkerer.common.item.quartz;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.quartz.BlockDarkQuartzSlab;
import thaumic.tinkerer.common.lib.LibBlockNames;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

import java.util.ArrayList;

public class ItemDarkQuartzSlab extends ItemSlab implements ITTinkererItem {

	public ItemDarkQuartzSlab(Block par1) {
		super(par1, (BlockSlab) ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartzSlab.class), (BlockSlab) ThaumicTinkerer.registry.getBlockFromClass(BlockDarkQuartzSlab.class).get(1), false);
	}

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return LibBlockNames.DARK_QUARTZ_SLAB;
    }

    @Override
    public boolean shouldRegister() {
        return false;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }
}

