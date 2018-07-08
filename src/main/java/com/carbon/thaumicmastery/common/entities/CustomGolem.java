package com.carbon.thaumicmastery.common.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.world.World;

public class CustomGolem extends EntityGolem {
	private String caster = "a";
	private int visInput;
	private double attack;
	private double health;

	public CustomGolem(World world, String owner, int vis) {
		super(world);
		caster = owner;
		visInput = vis;

		health = visInput;
		attack = health / 2;
	}

	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(health);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);

		getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(attack);
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity e) {
		return true;
	}
}
