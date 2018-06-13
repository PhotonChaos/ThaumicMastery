package com.carbon.thaumicmastery.entities.tileentities;

import com.carbon.thaumicmastery.ThaumicMastery;
import com.carbon.thaumicmastery.blocks.BlockDecay;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.Sys;

public class TileEntityDecay extends TileEntity {
	private int counter = 0;
	private int metadata;
	private boolean counterEnabled = true;
	public static boolean isOrigin = true;
	private int decay = 10;
	private int decayTime = 5;
	public static boolean doDecay = true;


	private int[][] getSurroundingBlocks() {
		return new int[][]{{xCoord, yCoord+1, zCoord}, {xCoord, yCoord-1, zCoord}, {xCoord+1, yCoord, zCoord}, {xCoord-1, yCoord, zCoord}, {xCoord, yCoord, zCoord+1}, {xCoord, yCoord, zCoord-1}};
	}

	@Override
	public void updateEntity() {
		if(counterEnabled) {
			counter++;

			if (counter % decayTime == 0) {
				if (doDecay) {
					for (int[] i : getSurroundingBlocks()) {
						int newX = i[0];
						int newY = i[1];
						int newZ = i[2];

						Block b = worldObj.getBlock(newX, newY, newZ);
						//System.out.println(newX + " " + newY + " " + newZ);

						if (!(b instanceof BlockDecay) && decay > 0 && !b.isAir(worldObj, newX, newY, newZ)) {
							worldObj.setBlock(newX, newY, newZ, ThaumicMastery.blockDecay);
							TileEntityDecay td = (TileEntityDecay) worldObj.getTileEntity(newX, newY, newZ);
							td.setDecayLevel(decay - 1);
						}
					}
					worldObj.removeTileEntity(xCoord, yCoord, zCoord);
					worldObj.setBlockToAir(xCoord, yCoord, zCoord);
				}
			}

			if (counter == 20) {
				counter = 0;
				metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord) + 1;

				if (metadata > 9) {
					metadata = 0;
				}

				if (!worldObj.isRemote) {
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, metadata, 2);
				}
			}
		}
		super.updateEntity();
	}

	public void setDecayLevel(int d) {
		decay = d;
	}

	public int getDecayLevel() {
		return decay;
	}
}
