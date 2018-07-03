package com.carbon.thaumicmastery.common.misc;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterTM extends Teleporter {
	public TeleporterTM(WorldServer worldServer) {
		super(worldServer);
	}

	@Override
	public boolean makePortal(Entity p_85188_1_) {
		return true;
	}

	@Override
	public boolean placeInExistingPortal(Entity p_77184_1_, double p_77184_2_, double p_77184_4_, double p_77184_6_, float p_77184_8_) {
		return true;
	}

	@Override
	public void placeInPortal(Entity entity, double x, double y, double z, float p_77185_8_) {
		entity.setLocationAndAngles(x, y, z, entity.rotationPitch, entity.rotationYaw);
		entity.motionX = 0;
		entity.motionY = 0;
		entity.motionZ = 0;
	}

	@Override
	public void removeStalePortalLocations(long p_85189_1_) {
		// NO-OP
	}
}
