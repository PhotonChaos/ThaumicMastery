package com.carbon.thaumicmastery.common.entities.AI;

import com.carbon.thaumicmastery.common.entities.CustomGolem;
import net.minecraft.entity.ai.EntityAIBase;
import thaumcraft.common.entities.golems.GolemHelper;

public class AIGolem extends EntityAIBase {
	private CustomGolem theEntity;

	public AIGolem(CustomGolem golem) {
		theEntity = golem;
		setMutexBits(7);
	}

	@Override
	public void startExecuting() {

	}

	@Override
	public boolean shouldExecute() {
		return true;
	}
}
