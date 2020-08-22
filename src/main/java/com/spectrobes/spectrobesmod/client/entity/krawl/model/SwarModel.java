// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.krawl.model;

import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySwar;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class SwarModel extends AnimatedEntityModel<EntitySwar> {

    private final AnimatedModelRenderer base;
	private final AnimatedModelRenderer waist;
	private final AnimatedModelRenderer torso;
	private final AnimatedModelRenderer torso_top;
	private final AnimatedModelRenderer shoulder_left;
	private final AnimatedModelRenderer arm;
	private final AnimatedModelRenderer arm_1;
	private final AnimatedModelRenderer hand;
	private final AnimatedModelRenderer shoulder_right;
	private final AnimatedModelRenderer arm2;
	private final AnimatedModelRenderer arm_2;
	private final AnimatedModelRenderer hand2;
	private final AnimatedModelRenderer head;

    public SwarModel()
    {
        textureWidth = 128;
    	textureHeight = 128;
    	base = new AnimatedModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.setTextureOffset(0, 26).addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		base.setModelRendererName("base");
		this.registerModelRenderer(base);

		waist = new AnimatedModelRenderer(this);
		waist.setRotationPoint(0.0F, -1.5F, 0.0F);
		base.addChild(waist);
		setRotationAngle(waist, -0.3054F, 0.0F, 0.0F);
		waist.setTextureOffset(18, 44).addBox(-3.0F, -3.5F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);
		waist.setTextureOffset(0, 48).addBox(-2.0F, -9.5F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
		waist.setModelRendererName("waist");
		this.registerModelRenderer(waist);

		torso = new AnimatedModelRenderer(this);
		torso.setRotationPoint(0.0F, -9.0319F, 0.4555F);
		waist.addChild(torso);
		setRotationAngle(torso, 0.5236F, 0.0F, 0.0F);
		torso.setTextureOffset(42, 44).addBox(-3.0F, -2.5F, -3.0F, 6.0F, 3.0F, 6.0F, 0.0F, false);
		torso.setModelRendererName("torso");
		this.registerModelRenderer(torso);

		torso_top = new AnimatedModelRenderer(this);
		torso_top.setRotationPoint(0.0F, -1.4881F, 1.1082F);
		torso.addChild(torso_top);
		setRotationAngle(torso_top, 0.3054F, 0.0F, 0.0F);
		torso_top.setTextureOffset(0, 14).addBox(-5.0F, -5.5F, -4.0F, 10.0F, 5.0F, 7.0F, 0.0F, false);
		torso_top.setTextureOffset(0, 0).addBox(-7.0F, -11.5F, -5.0F, 14.0F, 6.0F, 8.0F, 0.0F, false);
		torso_top.setModelRendererName("torso_top");
		this.registerModelRenderer(torso_top);

		shoulder_left = new AnimatedModelRenderer(this);
		shoulder_left.setRotationPoint(6.5F, -9.3341F, -2.9849F);
		torso_top.addChild(shoulder_left);
		shoulder_left.setTextureOffset(0, 36).addBox(-1.5F, -4.5F, -3.5F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		shoulder_left.setModelRendererName("shoulder_left");
		this.registerModelRenderer(shoulder_left);

		arm = new AnimatedModelRenderer(this);
		arm.setRotationPoint(4.0F, -2.0F, -1.0F);
		shoulder_left.addChild(arm);
		setRotationAngle(arm, 0.0F, 0.0F, 0.1745F);
		arm.setTextureOffset(50, 36).addBox(0.0F, -2.25F, -1.0F, 8.0F, 3.0F, 3.0F, 0.0F, false);
		arm.setModelRendererName("arm");
		this.registerModelRenderer(arm);

		arm_1 = new AnimatedModelRenderer(this);
		arm_1.setRotationPoint(8.0F, 0.0F, 0.0F);
		arm.addChild(arm_1);
		setRotationAngle(arm_1, 0.0F, 0.0F, 0.6545F);
		arm_1.setTextureOffset(36, 0).addBox(0.0F, -2.25F, -1.0F, 11.0F, 3.0F, 3.0F, 0.0F, false);
		arm_1.setModelRendererName("arm_1");
		this.registerModelRenderer(arm_1);

		hand = new AnimatedModelRenderer(this);
		hand.setRotationPoint(7.0F, -0.5F, 0.5F);
		arm_1.addChild(hand);
		hand.setTextureOffset(48, 28).addBox(0.0F, -2.25F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		hand.setModelRendererName("hand");
		this.registerModelRenderer(hand);

		shoulder_right = new AnimatedModelRenderer(this);
		shoulder_right.setRotationPoint(-6.5F, -9.3341F, -2.9849F);
		torso_top.addChild(shoulder_right);
		shoulder_right.setTextureOffset(0, 36).addBox(-4.5F, -4.5F, -3.5F, 6.0F, 6.0F, 6.0F, 0.0F, true);
		shoulder_right.setModelRendererName("shoulder_right");
		this.registerModelRenderer(shoulder_right);

		arm2 = new AnimatedModelRenderer(this);
		arm2.setRotationPoint(-4.0F, -2.0F, -1.0F);
		shoulder_right.addChild(arm2);
		setRotationAngle(arm2, 0.0F, 0.0F, -0.2182F);
		arm2.setTextureOffset(50, 36).addBox(-8.0F, -2.25F, -1.0F, 8.0F, 3.0F, 3.0F, 0.0F, true);
		arm2.setModelRendererName("arm2");
		this.registerModelRenderer(arm2);

		arm_2 = new AnimatedModelRenderer(this);
		arm_2.setRotationPoint(-8.0F, 0.0F, 0.0F);
		arm2.addChild(arm_2);
		setRotationAngle(arm_2, 0.0F, 0.0F, -0.4363F);
		arm_2.setTextureOffset(36, 0).addBox(-11.0F, -2.25F, -1.0F, 11.0F, 3.0F, 3.0F, 0.0F, true);
		arm_2.setModelRendererName("arm_2");
		this.registerModelRenderer(arm_2);

		hand2 = new AnimatedModelRenderer(this);
		hand2.setRotationPoint(-7.0F, -0.5F, 0.5F);
		arm_2.addChild(hand2);
		hand2.setTextureOffset(48, 28).addBox(-8.0F, -2.25F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, true);
		hand2.setModelRendererName("hand2");
		this.registerModelRenderer(hand2);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(0.0F, -10.0F, -6.0F);
		torso.addChild(head);
		head.setTextureOffset(28, 20).addBox(-3.0F, -5.0F, -5.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

    this.rootBones.add(base);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("spectrobesmod", "animations/spectrobe/swar.json");
    }
}