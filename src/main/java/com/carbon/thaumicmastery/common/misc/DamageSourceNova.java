package com.carbon.thaumicmastery.common.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;

import java.util.Random;

public class DamageSourceNova extends EntityDamageSource {
	private static Random randy = new Random();

	public DamageSourceNova(Entity source) {
		super("nova", source);
	}

	@Override
	public IChatComponent func_151519_b(EntityLivingBase target) {
		String s = "death.attack.nova";

		return new ChatComponentTranslation(s, target.func_145748_c_(), this.damageSourceEntity.func_145748_c_());
	}

	@Override
	public boolean isDifficultyScaled() {
		return false;
	}
}
