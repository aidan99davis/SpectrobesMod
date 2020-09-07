// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilamasta;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class VilamastaModel extends AnimatedEntityModel<EntityVilamasta> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer body_upper;
	private final AnimatedModelRenderer left_arm;
	private final AnimatedModelRenderer left_upper_arm;
	private final AnimatedModelRenderer left_lower_arm;
	private final AnimatedModelRenderer left_hand;
	private final AnimatedModelRenderer shoulder_pad_left;
	private final AnimatedModelRenderer shoulder_pad_left2;
	private final AnimatedModelRenderer shoulder_pad_left3;
	private final AnimatedModelRenderer right_arm;
	private final AnimatedModelRenderer right_upper_arm;
	private final AnimatedModelRenderer right_lower_arm;
	private final AnimatedModelRenderer right_hand;
	private final AnimatedModelRenderer shoulder_pad_right4;
	private final AnimatedModelRenderer shoulder_pad_right5;
	private final AnimatedModelRenderer shoulder_pad_right6;
	private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer horn;
	private final AnimatedModelRenderer horn2;
	private final AnimatedModelRenderer horn3;
	private final AnimatedModelRenderer horn4;
	private final AnimatedModelRenderer horn5;
	private final AnimatedModelRenderer horn_left;
	private final AnimatedModelRenderer horn_left2;
	private final AnimatedModelRenderer shell;
	private final AnimatedModelRenderer left;
	private final AnimatedModelRenderer back;
	private final AnimatedModelRenderer back_lower;
	private final AnimatedModelRenderer back_left;
	private final AnimatedModelRenderer back_left_lower;
	private final AnimatedModelRenderer back_right;
	private final AnimatedModelRenderer back_right_lower;
	private final AnimatedModelRenderer body_lower;
	private final AnimatedModelRenderer left_leg;
	private final AnimatedModelRenderer left_foot;
	private final AnimatedModelRenderer right_leg;
	private final AnimatedModelRenderer right_foot;
	private final AnimatedModelRenderer crotch_cover;

    public VilamastaModel()
    {
        textureWidth = 128;
    textureHeight = 128;
    body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 17.0F, 0.0F);
		
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		body_upper = new AnimatedModelRenderer(this);
		body_upper.setRotationPoint(0.0F, -3.0F, 3.0F);
		body.addChild(body_upper);
		setRotationAngle(body_upper, 0.3054F, 0.0F, 0.0F);
		body_upper.setTextureOffset(0, 22).addBox(-4.0F, -9.0F, -3.0F, 8.0F, 3.0F, 7.0F, 0.0F, false);
		body_upper.setTextureOffset(0, 0).addBox(-5.0F, -6.0F, -4.0F, 10.0F, 4.0F, 9.0F, 0.0F, false);
		body_upper.setTextureOffset(23, 25).addBox(-4.0F, -2.0F, -3.0F, 8.0F, 2.0F, 7.0F, 0.0F, false);
		body_upper.setModelRendererName("body_upper");
		this.registerModelRenderer(body_upper);

		left_arm = new AnimatedModelRenderer(this);
		left_arm.setRotationPoint(5.5F, -6.5F, 0.5F);
		body_upper.addChild(left_arm);
		setRotationAngle(left_arm, -0.7854F, 0.0F, 1.1781F);
		left_arm.setTextureOffset(61, 50).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		left_arm.setModelRendererName("left_arm");
		this.registerModelRenderer(left_arm);

		left_upper_arm = new AnimatedModelRenderer(this);
		left_upper_arm.setRotationPoint(0.0F, 0.0F, 0.0F);
		left_arm.addChild(left_upper_arm);
		setRotationAngle(left_upper_arm, 0.7854F, 0.0F, 0.0F);
		left_upper_arm.setTextureOffset(49, 0).addBox(-1.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, 0.0F, false);
		left_upper_arm.setModelRendererName("left_upper_arm");
		this.registerModelRenderer(left_upper_arm);

		left_lower_arm = new AnimatedModelRenderer(this);
		left_lower_arm.setRotationPoint(7.0F, 0.0F, 0.0F);
		left_upper_arm.addChild(left_lower_arm);
		setRotationAngle(left_lower_arm, 0.0F, 0.3491F, 0.0F);
		left_lower_arm.setTextureOffset(52, 53).addBox(-0.342F, -1.0F, -1.0603F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		left_lower_arm.setModelRendererName("left_lower_arm");
		this.registerModelRenderer(left_lower_arm);

		left_hand = new AnimatedModelRenderer(this);
		left_hand.setRotationPoint(0.0F, 0.0F, 0.0F);
		left_lower_arm.addChild(left_hand);
		left_hand.setTextureOffset(46, 24).addBox(1.5F, -1.0F, -2.0F, 5.0F, 3.0F, 4.0F, 0.0F, false);
		left_hand.setTextureOffset(40, 10).addBox(6.5F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		left_hand.setTextureOffset(40, 8).addBox(6.5F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		left_hand.setTextureOffset(68, 18).addBox(2.5F, -1.75F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		left_hand.setTextureOffset(37, 61).addBox(2.0F, -1.25F, -1.5F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		left_hand.setTextureOffset(66, 30).addBox(2.5F, 1.75F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		left_hand.setTextureOffset(60, 21).addBox(2.0F, 1.25F, -1.5F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		left_hand.setTextureOffset(0, 36).addBox(6.5F, -1.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		left_hand.setTextureOffset(22, 55).addBox(7.5F, -1.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		left_hand.setTextureOffset(40, 54).addBox(7.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		left_hand.setTextureOffset(53, 31).addBox(7.5F, -1.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		left_hand.setTextureOffset(18, 35).addBox(5.5F, 2.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		left_hand.setModelRendererName("left_hand");
		this.registerModelRenderer(left_hand);

		shoulder_pad_left = new AnimatedModelRenderer(this);
		shoulder_pad_left.setRotationPoint(-1.0149F, -0.8547F, 1.0256F);
		left_arm.addChild(shoulder_pad_left);
		setRotationAngle(shoulder_pad_left, 0.6264F, -0.4603F, 0.5773F);
		shoulder_pad_left.setTextureOffset(0, 47).addBox(-2.7622F, -2.9414F, -4.0909F, 1.0F, 5.0F, 6.0F, 0.0F, false);
		shoulder_pad_left.setTextureOffset(70, 47).addBox(-2.7622F, -2.9414F, -5.0909F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_left.setTextureOffset(70, 41).addBox(-2.7622F, -2.9414F, 1.9091F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_left.setModelRendererName("shoulder_pad_left");
		this.registerModelRenderer(shoulder_pad_left);

		shoulder_pad_left2 = new AnimatedModelRenderer(this);
		shoulder_pad_left2.setRotationPoint(-1.0F, 0.0F, 0.0F);
		shoulder_pad_left.addChild(shoulder_pad_left2);
		setRotationAngle(shoulder_pad_left2, 0.0F, 0.0F, 0.3927F);
		shoulder_pad_left2.setTextureOffset(58, 12).addBox(-1.7622F, -5.9414F, -3.0909F, 1.0F, 5.0F, 4.0F, 0.0F, false);
		shoulder_pad_left2.setTextureOffset(56, 69).addBox(-1.7622F, -5.9414F, -4.0909F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_left2.setTextureOffset(0, 69).addBox(-1.7622F, -5.9414F, 0.9091F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_left2.setModelRendererName("shoulder_pad_left2");
		this.registerModelRenderer(shoulder_pad_left2);

		shoulder_pad_left3 = new AnimatedModelRenderer(this);
		shoulder_pad_left3.setRotationPoint(0.0F, -3.0F, 0.0F);
		shoulder_pad_left2.addChild(shoulder_pad_left3);
		setRotationAngle(shoulder_pad_left3, 0.0F, 0.0F, 0.3927F);
		shoulder_pad_left3.setTextureOffset(24, 13).addBox(-1.7622F, -5.9414F, -2.0909F, 1.0F, 5.0F, 2.0F, 0.0F, false);
		shoulder_pad_left3.setTextureOffset(68, 34).addBox(-1.7622F, -5.9414F, -3.0909F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_left3.setTextureOffset(68, 12).addBox(-1.7622F, -5.9414F, -0.0909F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_left3.setModelRendererName("shoulder_pad_left3");
		this.registerModelRenderer(shoulder_pad_left3);

		right_arm = new AnimatedModelRenderer(this);
		right_arm.setRotationPoint(-5.5F, -6.5F, 0.5F);
		body_upper.addChild(right_arm);
		setRotationAngle(right_arm, -0.7854F, 0.0F, -1.1781F);
		right_arm.setTextureOffset(58, 42).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		right_arm.setModelRendererName("right_arm");
		this.registerModelRenderer(right_arm);

		right_upper_arm = new AnimatedModelRenderer(this);
		right_upper_arm.setRotationPoint(0.0F, 0.0F, 0.0F);
		right_arm.addChild(right_upper_arm);
		setRotationAngle(right_upper_arm, 0.7854F, 0.0F, 0.0F);
		right_upper_arm.setTextureOffset(44, 49).addBox(-7.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, 0.0F, false);
		right_upper_arm.setModelRendererName("right_upper_arm");
		this.registerModelRenderer(right_upper_arm);

		right_lower_arm = new AnimatedModelRenderer(this);
		right_lower_arm.setRotationPoint(-7.0F, 0.0F, 0.0F);
		right_upper_arm.addChild(right_lower_arm);
		setRotationAngle(right_lower_arm, 0.0F, -0.3491F, 0.0F);
		right_lower_arm.setTextureOffset(36, 34).addBox(-1.658F, -1.0F, -1.0603F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		right_lower_arm.setModelRendererName("right_lower_arm");
		this.registerModelRenderer(right_lower_arm);

		right_hand = new AnimatedModelRenderer(this);
		right_hand.setRotationPoint(0.0F, 0.0F, 0.0F);
		right_lower_arm.addChild(right_hand);
		right_hand.setTextureOffset(41, 34).addBox(-6.5F, -1.0F, -2.0F, 5.0F, 3.0F, 4.0F, 0.0F, false);
		right_hand.setTextureOffset(18, 33).addBox(-8.5F, -1.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		right_hand.setTextureOffset(29, 4).addBox(-8.5F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		right_hand.setTextureOffset(55, 35).addBox(-5.5F, -1.75F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		right_hand.setTextureOffset(57, 38).addBox(-6.0F, -1.25F, -1.5F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		right_hand.setTextureOffset(53, 21).addBox(-5.5F, 1.75F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
		right_hand.setTextureOffset(55, 31).addBox(-6.0F, 1.25F, -1.5F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		right_hand.setTextureOffset(28, 13).addBox(-8.5F, -1.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		right_hand.setTextureOffset(0, 50).addBox(-8.5F, -1.0F, -2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		right_hand.setTextureOffset(0, 47).addBox(-8.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		right_hand.setTextureOffset(46, 24).addBox(-8.5F, -1.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		right_hand.setTextureOffset(0, 18).addBox(-7.5F, 2.0F, -2.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		right_hand.setModelRendererName("right_hand");
		this.registerModelRenderer(right_hand);

		shoulder_pad_right4 = new AnimatedModelRenderer(this);
		shoulder_pad_right4.setRotationPoint(1.0149F, -0.8547F, 1.0256F);
		right_arm.addChild(shoulder_pad_right4);
		setRotationAngle(shoulder_pad_right4, 0.6264F, 0.4603F, -0.5773F);
		shoulder_pad_right4.setTextureOffset(30, 43).addBox(1.7622F, -2.9414F, -4.0909F, 1.0F, 5.0F, 6.0F, 0.0F, false);
		shoulder_pad_right4.setTextureOffset(46, 68).addBox(-1.2378F, -2.9414F, -5.0909F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_right4.setTextureOffset(26, 68).addBox(-1.2378F, -2.9414F, 1.9091F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_right4.setModelRendererName("shoulder_pad_right4");
		this.registerModelRenderer(shoulder_pad_right4);

		shoulder_pad_right5 = new AnimatedModelRenderer(this);
		shoulder_pad_right5.setRotationPoint(1.0F, 0.0F, 0.0F);
		shoulder_pad_right4.addChild(shoulder_pad_right5);
		setRotationAngle(shoulder_pad_right5, 0.0F, 0.0F, -0.3927F);
		shoulder_pad_right5.setTextureOffset(0, 58).addBox(0.7622F, -5.9414F, -3.0909F, 1.0F, 5.0F, 4.0F, 0.0F, false);
		shoulder_pad_right5.setTextureOffset(16, 68).addBox(-2.2378F, -5.9414F, -4.0909F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_right5.setTextureOffset(67, 67).addBox(-2.2378F, -5.9414F, 0.9091F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_right5.setModelRendererName("shoulder_pad_right5");
		this.registerModelRenderer(shoulder_pad_right5);

		shoulder_pad_right6 = new AnimatedModelRenderer(this);
		shoulder_pad_right6.setRotationPoint(0.0F, -3.0F, 0.0F);
		shoulder_pad_right5.addChild(shoulder_pad_right6);
		setRotationAngle(shoulder_pad_right6, 0.0F, 0.0F, -0.3927F);
		shoulder_pad_right6.setTextureOffset(0, 22).addBox(0.7622F, -5.9414F, -2.0909F, 1.0F, 5.0F, 2.0F, 0.0F, false);
		shoulder_pad_right6.setTextureOffset(36, 65).addBox(-2.2378F, -5.9414F, -3.0909F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_right6.setTextureOffset(8, 47).addBox(-2.2378F, -5.9414F, -0.0909F, 4.0F, 5.0F, 1.0F, 0.0F, false);
		shoulder_pad_right6.setModelRendererName("shoulder_pad_right6");
		this.registerModelRenderer(shoulder_pad_right6);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(0.0F, -6.2153F, -3.2745F);
		body_upper.addChild(head);
		setRotationAngle(head, -0.3038F, 0.762F, -0.2132F);
		head.setTextureOffset(56, 56).addBox(-2.0F, -2.5F, -2.5F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		horn = new AnimatedModelRenderer(this);
		horn.setRotationPoint(1.7678F, -1.0F, -2.2929F);
		head.addChild(horn);
		setRotationAngle(horn, -1.0036F, -0.2182F, 0.5236F);
		horn.setTextureOffset(5, 63).addBox(-0.5F, -0.5F, -4.5F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		horn.setModelRendererName("horn");
		this.registerModelRenderer(horn);

		horn2 = new AnimatedModelRenderer(this);
		horn2.setRotationPoint(0.0F, 0.0F, -5.0F);
		horn.addChild(horn2);
		setRotationAngle(horn2, -0.2182F, 0.2182F, 0.0F);
		horn2.setTextureOffset(46, 61).addBox(-0.6201F, -0.6409F, -4.3095F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		horn2.setModelRendererName("horn2");
		this.registerModelRenderer(horn2);

		horn3 = new AnimatedModelRenderer(this);
		horn3.setRotationPoint(0.0F, 0.0F, -2.0F);
		horn2.addChild(horn3);
		setRotationAngle(horn3, -1.5708F, 0.6981F, 0.7854F);
		horn3.setTextureOffset(33, 8).addBox(-1.053F, -1.0635F, -2.4032F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		horn3.setModelRendererName("horn3");
		this.registerModelRenderer(horn3);

		horn4 = new AnimatedModelRenderer(this);
		horn4.setRotationPoint(0.0429F, -0.3219F, -5.7805F);
		horn3.addChild(horn4);
		setRotationAngle(horn4, -2.6616F, -0.1745F, 0.0436F);
		horn4.setTextureOffset(69, 53).addBox(-0.5236F, -2.0113F, -3.4438F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		horn4.setModelRendererName("horn4");
		this.registerModelRenderer(horn4);

		horn5 = new AnimatedModelRenderer(this);
		horn5.setRotationPoint(-0.6114F, -0.4627F, 2.6972F);
		horn3.addChild(horn5);
		setRotationAngle(horn5, 2.5307F, 0.2618F, 0.0436F);
		horn5.setTextureOffset(68, 60).addBox(-0.4254F, -0.6491F, -3.4882F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		horn5.setModelRendererName("horn5");
		this.registerModelRenderer(horn5);

		horn_left = new AnimatedModelRenderer(this);
		horn_left.setRotationPoint(1.5516F, -1.7577F, -1.5516F);
		head.addChild(horn_left);
		setRotationAngle(horn_left, -0.1745F, -0.1309F, 0.829F);
		horn_left.setTextureOffset(0, 32).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		horn_left.setModelRendererName("horn_left");
		this.registerModelRenderer(horn_left);

		horn_left2 = new AnimatedModelRenderer(this);
		horn_left2.setRotationPoint(1.1981F, -1.7577F, -1.9052F);
		head.addChild(horn_left2);
		setRotationAngle(horn_left2, 1.8082F, 0.7611F, 1.6118F);
		horn_left2.setTextureOffset(29, 0).addBox(-0.5F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		horn_left2.setModelRendererName("horn_left2");
		this.registerModelRenderer(horn_left2);

		shell = new AnimatedModelRenderer(this);
		shell.setRotationPoint(0.0F, 0.5F, -1.25F);
		body_upper.addChild(shell);
		shell.setTextureOffset(0, 13).addBox(-4.0F, -11.0F, -2.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		shell.setTextureOffset(25, 15).addBox(-3.5F, -13.0F, -1.5F, 7.0F, 2.0F, 7.0F, 0.0F, false);
		shell.setTextureOffset(0, 32).addBox(-3.0F, -14.0F, -1.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		shell.setModelRendererName("shell");
		this.registerModelRenderer(shell);

		left = new AnimatedModelRenderer(this);
		left.setRotationPoint(4.5F, -10.5F, 1.5F);
		shell.addChild(left);
		setRotationAngle(left, 0.0F, 0.0F, 0.5236F);
		
		left.setModelRendererName("left");
		this.registerModelRenderer(left);

		back = new AnimatedModelRenderer(this);
		back.setRotationPoint(-0.5F, -10.5F, 7.5F);
		shell.addChild(back);
		setRotationAngle(back, 0.3491F, 0.0F, 0.0F);
		back.setTextureOffset(40, 41).addBox(-3.5F, -0.9829F, -2.2385F, 8.0F, 7.0F, 1.0F, 0.0F, false);
		back.setModelRendererName("back");
		this.registerModelRenderer(back);

		back_lower = new AnimatedModelRenderer(this);
		back_lower.setRotationPoint(0.5F, 9.1035F, -3.011F);
		back.addChild(back_lower);
		setRotationAngle(back_lower, -0.7854F, 0.0F, 0.0F);
		back_lower.setTextureOffset(0, 39).addBox(-4.0F, -3.4358F, -1.9291F, 8.0F, 7.0F, 1.0F, 0.0F, false);
		back_lower.setTextureOffset(10, 69).addBox(2.0F, 3.5642F, -1.9291F, 2.0F, 8.0F, 1.0F, 0.0F, false);
		back_lower.setTextureOffset(23, 24).addBox(-1.0F, 3.5642F, -1.9291F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		back_lower.setTextureOffset(0, 0).addBox(-4.0F, 3.5642F, -1.9291F, 2.0F, 8.0F, 1.0F, 0.0F, false);
		back_lower.setTextureOffset(30, 42).addBox(-2.0F, 3.5642F, -1.9291F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		back_lower.setTextureOffset(36, 71).addBox(1.0F, 3.5642F, -1.9291F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		back_lower.setModelRendererName("back_lower");
		this.registerModelRenderer(back_lower);

		back_left = new AnimatedModelRenderer(this);
		back_left.setRotationPoint(4.5F, 1.5F, -2.0F);
		back.addChild(back_left);
		setRotationAngle(back_left, 0.0F, 0.829F, 0.0F);
		back_left.setTextureOffset(58, 4).addBox(-0.5F, -2.5F, -0.5F, 6.0F, 7.0F, 1.0F, 0.0F, false);
		back_left.setModelRendererName("back_left");
		this.registerModelRenderer(back_left);

		back_left_lower = new AnimatedModelRenderer(this);
		back_left_lower.setRotationPoint(1.208F, 6.1298F, -1.446F);
		back_left.addChild(back_left_lower);
		setRotationAngle(back_left_lower, -0.48F, -0.0873F, -0.5672F);
		back_left_lower.setTextureOffset(46, 8).addBox(-0.5F, -2.5F, -0.5F, 5.0F, 12.0F, 1.0F, 0.0F, false);
		back_left_lower.setModelRendererName("back_left_lower");
		this.registerModelRenderer(back_left_lower);

		back_right = new AnimatedModelRenderer(this);
		back_right.setRotationPoint(-3.5F, 1.5F, -2.0F);
		back.addChild(back_right);
		setRotationAngle(back_right, 0.0F, -0.829F, 0.0F);
		back_right.setTextureOffset(26, 55).addBox(-5.5F, -2.5F, -0.5F, 6.0F, 7.0F, 1.0F, 0.0F, false);
		back_right.setModelRendererName("back_right");
		this.registerModelRenderer(back_right);

		back_right_lower = new AnimatedModelRenderer(this);
		back_right_lower.setRotationPoint(-1.208F, 6.1298F, -1.446F);
		back_right.addChild(back_right_lower);
		setRotationAngle(back_right_lower, -0.48F, 0.0873F, 0.5672F);
		back_right_lower.setTextureOffset(18, 42).addBox(-4.5F, -2.5F, -0.5F, 5.0F, 12.0F, 1.0F, 0.0F, false);
		back_right_lower.setModelRendererName("back_right_lower");
		this.registerModelRenderer(back_right_lower);

		body_lower = new AnimatedModelRenderer(this);
		body_lower.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(body_lower);
		body_lower.setTextureOffset(19, 34).addBox(-3.0F, -4.0F, 1.0F, 6.0F, 3.0F, 5.0F, 0.0F, false);
		body_lower.setTextureOffset(29, 0).addBox(-3.5F, -1.0F, 0.5F, 7.0F, 2.0F, 6.0F, 0.0F, false);
		body_lower.setModelRendererName("body_lower");
		this.registerModelRenderer(body_lower);

		left_leg = new AnimatedModelRenderer(this);
		left_leg.setRotationPoint(1.5F, -0.75F, 3.0F);
		body_lower.addChild(left_leg);
		setRotationAngle(left_leg, 0.0F, 0.0F, -0.1745F);
		left_leg.setTextureOffset(64, 25).addBox(-0.7972F, 0.2896F, -1.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		left_leg.setTextureOffset(10, 55).addBox(-1.2972F, 2.2896F, -1.5F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		left_leg.setModelRendererName("left_leg");
		this.registerModelRenderer(left_leg);

		left_foot = new AnimatedModelRenderer(this);
		left_foot.setRotationPoint(0.4962F, 6.9566F, 0.5F);
		left_leg.addChild(left_foot);
		setRotationAngle(left_foot, 0.0F, 0.0F, 0.1745F);
		left_foot.setTextureOffset(55, 64).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		left_foot.setTextureOffset(6, 58).addBox(-1.5F, 0.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		left_foot.setTextureOffset(0, 58).addBox(0.5F, 0.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		left_foot.setTextureOffset(56, 57).addBox(-0.5F, 0.0F, 1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		left_foot.setModelRendererName("left_foot");
		this.registerModelRenderer(left_foot);

		right_leg = new AnimatedModelRenderer(this);
		right_leg.setRotationPoint(-1.5F, -0.75F, 3.0F);
		body_lower.addChild(right_leg);
		setRotationAngle(right_leg, 0.0F, 0.0F, 0.1745F);
		right_leg.setTextureOffset(24, 63).addBox(-2.2028F, 0.2896F, -1.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		right_leg.setTextureOffset(40, 53).addBox(-2.7028F, 2.2896F, -1.5F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		right_leg.setModelRendererName("right_leg");
		this.registerModelRenderer(right_leg);

		right_foot = new AnimatedModelRenderer(this);
		right_foot.setRotationPoint(-0.4962F, 6.9566F, 0.5F);
		right_leg.addChild(right_foot);
		setRotationAngle(right_foot, 0.0F, 0.0F, -0.1745F);
		right_foot.setTextureOffset(12, 63).addBox(-1.5F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
		right_foot.setTextureOffset(53, 4).addBox(0.5F, 0.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		right_foot.setTextureOffset(14, 53).addBox(-1.5F, 0.0F, -2.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		right_foot.setTextureOffset(49, 4).addBox(-0.5F, 0.0F, 1.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		right_foot.setModelRendererName("right_foot");
		this.registerModelRenderer(right_foot);

		crotch_cover = new AnimatedModelRenderer(this);
		crotch_cover.setRotationPoint(0.0F, 0.0F, 0.0F);
		body_lower.addChild(crotch_cover);
		setRotationAngle(crotch_cover, -0.2182F, 0.0F, 0.0F);
		crotch_cover.setTextureOffset(0, 13).addBox(-2.0F, -0.8041F, 0.2441F, 4.0F, 5.0F, 0.0F, 0.0F, false);
		crotch_cover.setModelRendererName("crotch_cover");
		this.registerModelRenderer(crotch_cover);

    this.rootBones.add(body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/vilamasta.json");
    }
}