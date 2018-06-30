package com.carbon.thaumicmastery.common.entities;

import net.minecraft.entity.*;
import net.minecraft.world.World;

public class CustomGolem extends EntityCreature implements IEntityOwnable {
	private String caster;
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
	public boolean attackEntityAsMob(Entity e) {
		return true;
	}

	@Override
	public String func_152113_b() {
		return null;
	}

	@Override
	public Entity getOwner() {
		return worldObj.getPlayerEntityByName(caster);
	}
}