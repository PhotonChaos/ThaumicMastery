package com.carbon.thaumicmastery.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * CustomGolem - CalumGC
 * Created using Tabula 4.1.1
 */
public class ModelCustomGolem extends ModelBase {
	private final double leg_maxRotate = 45.0D;
	private final double arm_maxRotate = 150.0D;

	private double distanceMovedTotal = 0.0D;
	private static final double CYCLES_PER_BLOCK = 3.0D;

    public ModelRenderer Head;
    public ModelRenderer Torso;
    public ModelRenderer RArm;
    public ModelRenderer LArm;
    public ModelRenderer Waist;
    public ModelRenderer RLeg;
    public ModelRenderer LLeg;

    public ModelCustomGolem() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.RArm = new ModelRenderer(this, 60, 58);
        this.RArm.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.RArm.addBox(9.0F, -2.5F, -3.0F, 4, 25, 6, 0.0F);
        this.RLeg = new ModelRenderer(this, 60, 0);
        this.RLeg.mirror = true;
        this.RLeg.setRotationPoint(5.0F, 11.0F, 0.0F);
        this.RLeg.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);
        this.LLeg = new ModelRenderer(this, 37, 0);
        this.LLeg.setRotationPoint(-4.0F, 11.0F, 0.0F);
        this.LLeg.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, 0.0F);
        this.Head = new ModelRenderer(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -7.0F, -2.0F);
        this.Head.addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8, 0.0F);
        this.LArm = new ModelRenderer(this, 60, 21);
        this.LArm.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.LArm.addBox(-13.0F, -2.5F, -3.0F, 4, 25, 6, 0.0F);
        this.Torso = new ModelRenderer(this, 0, 40);
        this.Torso.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.Torso.addBox(-9.0F, -2.0F, -6.0F, 18, 12, 11, 0.0F);
        this.Waist = new ModelRenderer(this, 0, 70);
        this.Waist.setRotationPoint(0.0F, -7.0F, 0.0F);
        this.Waist.addBox(-4.5F, 10.0F, -3.0F, 9, 5, 6, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.RArm.render(f5);
        this.RLeg.render(f5);
        this.LLeg.render(f5);
        this.Head.render(f5);
        this.LArm.render(f5);
        this.Torso.render(f5);
        this.Waist.render(f5);
    }

	private void updateDistanceMovedTotal(Entity parEntity) {
		distanceMovedTotal += parEntity.getDistance(parEntity.prevPosX, parEntity.prevPosY, parEntity.prevPosZ);
	}

	private double getDistanceMovedTotal() {
    	return distanceMovedTotal;
	}

    @Override
    public void setRotationAngles(float time, float swingSupress, float par3, float headAngleY, float headAngleX, float par6, Entity entity) {
		updateDistanceMovedTotal(entity);
    }

    public void attackAnimate() {

    }

    public void walkCycle() {

    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
