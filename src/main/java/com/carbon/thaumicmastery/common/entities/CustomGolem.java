package com.carbon.thaumicmastery.common.entities;

import com.carbon.thaumicmastery.common.entities.AI.AIGolem;
import javafx.beans.DefaultProperty;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class CustomGolem extends EntityTameable {
	private String caster;
	private int visInput;

	public CustomGolem(World world) {
		super(world);
		setupAI();
	}

	private void setupAI() {
		getNavigator().setAvoidsWater(true);
		clearAITasks();

		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIFollowOwner(this, 1.0D, 2F, 30F));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, 1.0D, true));
		tasks.addTask(3, new EntityAIWander(this, 1.0D));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(1, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.setTamed(true);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return null;
	}

	private void clearAITasks() {
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float par2) {
		return super.attackEntityFrom(damageSource, par2);
	}

	@Override
	public void applyEntityAttributes() {
		super.applyEntityAttributes();

		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1.0);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);

		getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0);
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity e) {
		return true;
	}

	public String getCaster() {
		return caster;
	}

	public void updateStats(int vis) {
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(vis);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(vis/2);
	}

	@Override
	public String func_152113_b() {
		return null;
	}
}
