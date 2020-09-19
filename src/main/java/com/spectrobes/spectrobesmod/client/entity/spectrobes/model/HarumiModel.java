// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.harumi.EntityHarumi;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class HarumiModel extends AnimatedEntityModel<EntityHarumi> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer torso;
	private final AnimatedModelRenderer tail;
	private final AnimatedModelRenderer legs_left;
	private final AnimatedModelRenderer leg_left_1;
	private final AnimatedModelRenderer leg_left_2;
	private final AnimatedModelRenderer legs_right;
	private final AnimatedModelRenderer leg_right_3;
	private final AnimatedModelRenderer leg_right_4;
	private final AnimatedModelRenderer arm_left;
	private final AnimatedModelRenderer hand;
	private final AnimatedModelRenderer guard;
	private final AnimatedModelRenderer finger_left_1;
	private final AnimatedModelRenderer finger_left_2;
	private final AnimatedModelRenderer antena;
	private final AnimatedModelRenderer antena2;
	private final AnimatedModelRenderer antena3;
	private final AnimatedModelRenderer face;
	private final AnimatedModelRenderer left;
	private final AnimatedModelRenderer right;
	private final AnimatedModelRenderer arm_right;
	private final AnimatedModelRenderer hand2;
	private final AnimatedModelRenderer guard2;
	private final AnimatedModelRenderer finger_right_3;
	private final AnimatedModelRenderer finger_right_4;

    public HarumiModel()
    {
        textureWidth = 32;
    	textureHeight = 32;
    	body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 24.75F, 0.0F);
		
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		torso = new AnimatedModelRenderer(this);
		torso.setRotationPoint(0.0F, -1.5F, 0.0F);
		body.addChild(torso);
		setRotationAngle(torso, 0.0F, 0.7854F, 0.0F);
		torso.setTextureOffset(16, 13).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		torso.setTextureOffset(0, 0).addBox(-2.0F, -3.25F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		torso.setTextureOffset(12, 0).addBox(-1.5F, -1.25F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		torso.setTextureOffset(0, 16).addBox(-1.0F, -5.75F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		torso.setTextureOffset(0, 21).addBox(-0.5F, -6.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		torso.setTextureOffset(8, 8).addBox(-1.5F, -5.25F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		torso.setModelRendererName("torso");
		this.registerModelRenderer(torso);

		tail = new AnimatedModelRenderer(this);
		tail.setRotationPoint(0.0F, -2.0F, 1.75F);
		body.addChild(tail);
		setRotationAngle(tail, -0.124F, -0.1231F, -0.7777F);
		tail.setTextureOffset(11, 14).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		tail.setTextureOffset(25, 10).addBox(-0.5F, -2.5F, 2.5F, 3.0F, 3.0F, 0.0F, 0.0F, false);
		tail.setTextureOffset(9, 0).addBox(0.5F, -0.25F, -0.5F, 1.0F, 0.0F, 3.0F, 0.0F, false);
		tail.setTextureOffset(0, 8).addBox(0.25F, -1.5F, -0.5F, 0.0F, 1.0F, 3.0F, 0.0F, false);
		tail.setModelRendererName("tail");
		this.registerModelRenderer(tail);

		legs_left = new AnimatedModelRenderer(this);
		legs_left.setRotationPoint(-0.25F, 0.0F, 0.25F);
		body.addChild(legs_left);
		
		legs_left.setModelRendererName("legs_left");
		this.registerModelRenderer(legs_left);

		leg_left_1 = new AnimatedModelRenderer(this);
		leg_left_1.setRotationPoint(1.75F, -1.75F, -0.25F);
		legs_left.addChild(leg_left_1);
		setRotationAngle(leg_left_1, 0.0F, -0.7854F, -2.3562F);
		leg_left_1.setTextureOffset(20, 11).addBox(-0.625F, -0.6768F, -0.375F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		leg_left_1.setModelRendererName("leg_left_1");
		this.registerModelRenderer(leg_left_1);

		leg_left_2 = new AnimatedModelRenderer(this);
		leg_left_2.setRotationPoint(1.0F, -1.75F, 1.0F);
		legs_left.addChild(leg_left_2);
		setRotationAngle(leg_left_2, 0.0F, -0.7854F, 0.7854F);
		leg_left_2.setTextureOffset(20, 20).addBox(-0.375F, -0.3232F, -0.625F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		leg_left_2.setModelRendererName("leg_left_2");
		this.registerModelRenderer(leg_left_2);

		legs_right = new AnimatedModelRenderer(this);
		legs_right.setRotationPoint(0.25F, 0.0F, 0.25F);
		body.addChild(legs_right);
		
		legs_right.setModelRendererName("legs_right");
		this.registerModelRenderer(legs_right);

		leg_right_3 = new AnimatedModelRenderer(this);
		leg_right_3.setRotationPoint(-1.75F, -1.75F, -0.25F);
		legs_right.addChild(leg_right_3);
		setRotationAngle(leg_right_3, 0.0F, 0.7854F, 2.3562F);
		leg_right_3.setTextureOffset(16, 20).addBox(-0.375F, -0.6768F, -0.375F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		leg_right_3.setModelRendererName("leg_right_3");
		this.registerModelRenderer(leg_right_3);

		leg_right_4 = new AnimatedModelRenderer(this);
		leg_right_4.setRotationPoint(-1.0F, -1.75F, 1.0F);
		legs_right.addChild(leg_right_4);
		setRotationAngle(leg_right_4, 0.0F, 0.7854F, -0.7854F);
		leg_right_4.setTextureOffset(12, 20).addBox(-0.625F, -0.3232F, -0.625F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		leg_right_4.setModelRendererName("leg_right_4");
		this.registerModelRenderer(leg_right_4);

		arm_left = new AnimatedModelRenderer(this);
		arm_left.setRotationPoint(2.0F, -2.75F, -0.25F);
		body.addChild(arm_left);
		setRotationAngle(arm_left, 0.3876F, -0.3614F, -0.8571F);
		arm_left.setTextureOffset(13, 4).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		arm_left.setModelRendererName("arm_left");
		this.registerModelRenderer(arm_left);

		hand = new AnimatedModelRenderer(this);
		hand.setRotationPoint(-0.0589F, 0.0589F, -2.1667F);
		arm_left.addChild(hand);
		setRotationAngle(hand, 0.6891F, 0.6767F, 0.4011F);
		hand.setTextureOffset(7, 18).addBox(-0.9411F, -1.0589F, -2.3333F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		hand.setTextureOffset(19, 16).addBox(-0.1179F, -0.8821F, -0.5833F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		hand.setModelRendererName("hand");
		this.registerModelRenderer(hand);

		guard = new AnimatedModelRenderer(this);
		guard.setRotationPoint(0.5589F, -0.5589F, -2.8333F);
		hand.addChild(guard);
		setRotationAngle(guard, 0.2849F, 0.274F, 0.0395F);
		guard.setTextureOffset(3, 20).addBox(-0.6734F, -0.3266F, -0.2675F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		guard.setModelRendererName("guard");
		this.registerModelRenderer(guard);

		finger_left_1 = new AnimatedModelRenderer(this);
		finger_left_1.setRotationPoint(-0.4411F, -0.5589F, -2.3333F);
		hand.addChild(finger_left_1);
		setRotationAngle(finger_left_1, 0.7854F, 0.7854F, 0.0F);
		finger_left_1.setTextureOffset(0, 19).addBox(-0.6036F, 0.1768F, -0.3232F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		finger_left_1.setModelRendererName("finger_left_1");
		this.registerModelRenderer(finger_left_1);

		finger_left_2 = new AnimatedModelRenderer(this);
		finger_left_2.setRotationPoint(0.2357F, 0.0143F, -1.7298F);
		hand.addChild(finger_left_2);
		setRotationAngle(finger_left_2, 1.0995F, -0.8419F, -0.0595F);
		finger_left_2.setTextureOffset(18, 4).addBox(-0.625F, -0.4634F, -0.7134F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		finger_left_2.setModelRendererName("finger_left_2");
		this.registerModelRenderer(finger_left_2);

		antena = new AnimatedModelRenderer(this);
		antena.setRotationPoint(0.0F, -2.75F, -1.75F);
		body.addChild(antena);
		setRotationAngle(antena, -0.3927F, 0.0F, 0.0F);
		antena.setTextureOffset(0, 13).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, 0.0F, false);
		antena.setModelRendererName("antena");
		this.registerModelRenderer(antena);

		antena2 = new AnimatedModelRenderer(this);
		antena2.setRotationPoint(0.0F, 0.7654F, -1.8478F);
		antena.addChild(antena2);
		setRotationAngle(antena2, 0.3927F, 0.0F, 0.0F);
		antena2.setTextureOffset(0, 9).addBox(-2.0F, -1.148F, -2.7713F, 4.0F, 0.0F, 2.0F, 0.0F, false);
		antena2.setModelRendererName("antena2");
		this.registerModelRenderer(antena2);

		antena3 = new AnimatedModelRenderer(this);
		antena3.setRotationPoint(0.0F, -2.5028F, -4.5449F);
		antena2.addChild(antena3);
		setRotationAngle(antena3, 1.1781F, 0.0F, 0.0F);
		antena3.setTextureOffset(0, 6).addBox(-2.0F, 2.1581F, -3.5729F, 4.0F, 0.0F, 3.0F, 0.0F, false);
		antena3.setModelRendererName("antena3");
		this.registerModelRenderer(antena3);

		face = new AnimatedModelRenderer(this);
		face.setRotationPoint(0.0F, -0.25F, -0.25F);
		body.addChild(face);
		
		face.setModelRendererName("face");
		this.registerModelRenderer(face);

		left = new AnimatedModelRenderer(this);
		left.setRotationPoint(-0.75F, -1.5F, -1.25F);
		face.addChild(left);
		setRotationAngle(left, 0.5299F, 0.7119F, 0.3655F);
		left.setTextureOffset(24, 0).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, false);
		left.setModelRendererName("left");
		this.registerModelRenderer(left);

		right = new AnimatedModelRenderer(this);
		right.setRotationPoint(0.75F, -1.5F, -1.25F);
		face.addChild(right);
		setRotationAngle(right, 0.5299F, -0.7119F, -0.3655F);
		right.setTextureOffset(24, 0).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, 0.0F, true);
		right.setModelRendererName("right");
		this.registerModelRenderer(right);

		arm_right = new AnimatedModelRenderer(this);
		arm_right.setRotationPoint(-2.0F, -2.75F, -0.25F);
		body.addChild(arm_right);
		setRotationAngle(arm_right, 0.3876F, 0.3614F, 0.8571F);
		arm_right.setTextureOffset(13, 4).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, true);
		arm_right.setModelRendererName("arm_right");
		this.registerModelRenderer(arm_right);

		hand2 = new AnimatedModelRenderer(this);
		hand2.setRotationPoint(0.0589F, 0.0589F, -2.1667F);
		arm_right.addChild(hand2);
		setRotationAngle(hand2, 0.6891F, -0.6767F, -0.4011F);
		hand2.setTextureOffset(7, 18).addBox(-1.0589F, -1.0589F, -2.3333F, 2.0F, 2.0F, 2.0F, 0.0F, true);
		hand2.setTextureOffset(19, 16).addBox(-0.8821F, -0.8821F, -0.5833F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		hand2.setModelRendererName("hand2");
		this.registerModelRenderer(hand2);

		guard2 = new AnimatedModelRenderer(this);
		guard2.setRotationPoint(-0.5589F, -0.5589F, -2.8333F);
		hand2.addChild(guard2);
		setRotationAngle(guard2, 0.2849F, -0.274F, -0.0395F);
		guard2.setTextureOffset(3, 20).addBox(-0.3266F, -0.3266F, -0.2675F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		guard2.setModelRendererName("guard2");
		this.registerModelRenderer(guard2);

		finger_right_3 = new AnimatedModelRenderer(this);
		finger_right_3.setRotationPoint(0.4411F, -0.5589F, -2.3333F);
		hand2.addChild(finger_right_3);
		setRotationAngle(finger_right_3, 0.7854F, -0.7854F, 0.0F);
		finger_right_3.setTextureOffset(0, 19).addBox(-0.3964F, 0.1768F, -0.3232F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		finger_right_3.setModelRendererName("finger_right_3");
		this.registerModelRenderer(finger_right_3);

		finger_right_4 = new AnimatedModelRenderer(this);
		finger_right_4.setRotationPoint(-0.2357F, 0.0143F, -1.7298F);
		hand2.addChild(finger_right_4);
		setRotationAngle(finger_right_4, 1.0995F, 0.8419F, 0.0595F);
		finger_right_4.setTextureOffset(18, 4).addBox(-0.375F, -0.4634F, -0.7134F, 1.0F, 1.0F, 1.0F, 0.0F, true);
		finger_right_4.setModelRendererName("finger_right_4");
		this.registerModelRenderer(finger_right_4);

    this.rootBones.add(body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("spectrobesmod", "animations/spectrobe/harumi.json");
    }
}