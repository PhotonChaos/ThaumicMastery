package com.carbon.thaumicmastery.common.misc.damagesource;

import net.minecraft.util.DamageSource;

public class DamageSourceNova extends DamageSource {
	public static DamageSource nova = new DamageSource("nova").setDamageBypassesArmor().setMagicDamage().setDamageAllowedInCreativeMode().setDamageIsAbsolute();

	public DamageSourceNova(String damage_type) {
		super(damage_type);
	}

	@Override
	public boolean isDifficultyScaled() {
		return false;
	}

	@Override
	public boolean isDamageAbsolute() {
		return true;
	}

	@Override
	public boolean isUnblockable() {
		return true;
	}

	@Override
	public boolean isMagicDamage() {
		return true;
	}

	@Override
	public boolean canHarmInCreative() {
		return true;
	}
}
