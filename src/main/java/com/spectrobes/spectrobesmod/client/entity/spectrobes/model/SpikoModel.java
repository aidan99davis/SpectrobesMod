package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpiko;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
public class SpikoModel extends AnimatedEntityModel<EntitySpiko> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer front_left_leg;
	private final AnimatedModelRenderer front_right_leg;
	private final AnimatedModelRenderer back_left_leg;
	private final AnimatedModelRenderer back_right_leg;
	private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer decoration_1;
	private final AnimatedModelRenderer decoration_2;
	private final AnimatedModelRenderer tail;
	private final AnimatedModelRenderer tail_mid;
	private final AnimatedModelRenderer tail_bottom;
	private final AnimatedModelRenderer club_decorations;
	private final AnimatedModelRenderer decorations_1;
	private final AnimatedModelRenderer decorations_2;
	private final AnimatedModelRenderer decorations_3;

    public SpikoModel()
    {
        textureWidth = 128;
		textureHeight = 128;
		body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 94).addBox(-8.0F, -20.0F, -8.0F, 16.0F, 12.0F, 22.0F, 0.0F, false);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		front_left_leg = new AnimatedModelRenderer(this);
		front_left_leg.setRotationPoint(5.5F, -9.0F, -5.5F);
		body.addChild(front_left_leg);
		front_left_leg.setTextureOffset(108, 0).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
		front_left_leg.setModelRendererName("front_left_leg");
		this.registerModelRenderer(front_left_leg);

		front_right_leg = new AnimatedModelRenderer(this);
		front_right_leg.setRotationPoint(-5.5F, -9.0F, -5.5F);
		body.addChild(front_right_leg);
		front_right_leg.setTextureOffset(108, 0).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
		front_right_leg.setModelRendererName("front_right_leg");
		this.registerModelRenderer(front_right_leg);

		back_left_leg = new AnimatedModelRenderer(this);
		back_left_leg.setRotationPoint(5.5F, -9.0F, 11.5F);
		body.addChild(back_left_leg);
		back_left_leg.setTextureOffset(108, 0).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
		back_left_leg.setModelRendererName("back_left_leg");
		this.registerModelRenderer(back_left_leg);

		back_right_leg = new AnimatedModelRenderer(this);
		back_right_leg.setRotationPoint(-5.5F, -9.0F, 11.5F);
		body.addChild(back_right_leg);
		back_right_leg.setTextureOffset(108, 0).addBox(-2.5F, -1.0F, -2.5F, 5.0F, 10.0F, 5.0F, 0.0F, false);
		back_right_leg.setModelRendererName("back_right_leg");
		this.registerModelRenderer(back_right_leg);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(0.0F, -9.5F, -8.0F);
		body.addChild(head);
		setRotationAngle(head, 0.0F, 0.7854F, 0.0F);
		head.setTextureOffset(0, 70).addBox(-5.0F, -9.5F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		decoration_1 = new AnimatedModelRenderer(this);
		decoration_1.setRotationPoint(1.8787F, -9.0F, -0.1213F);
		head.addChild(decoration_1);
		setRotationAngle(decoration_1, -1.0472F, 0.6981F, -0.6109F);
		decoration_1.setTextureOffset(80, 0).addBox(-0.7603F, -12.4238F, -0.1014F, 0.0F, 13.0F, 4.0F, 0.0F, false);
		decoration_1.setModelRendererName("decoration_1");
		this.registerModelRenderer(decoration_1);

		decoration_2 = new AnimatedModelRenderer(this);
		decoration_2.setRotationPoint(-2.9497F, -8.0F, -1.5355F);
		head.addChild(decoration_2);
		setRotationAngle(decoration_2, -0.1745F, -2.4435F, -0.4363F);
		decoration_2.setTextureOffset(61, 0).addBox(-1.6573F, -11.0858F, -1.771F, 0.0F, 13.0F, 4.0F, 0.0F, false);
		decoration_2.setModelRendererName("decoration_2");
		this.registerModelRenderer(decoration_2);

		tail = new AnimatedModelRenderer(this);
		tail.setRotationPoint(-0.5F, -10.5F, 13.5F);
		body.addChild(tail);
		setRotationAngle(tail, -0.3491F, 0.0F, 0.0F);
		tail.setTextureOffset(106, 58).addBox(-2.5F, -2.5F, -0.5F, 5.0F, 5.0F, 6.0F, 0.0F, false);
		tail.setModelRendererName("tail");
		this.registerModelRenderer(tail);

		tail_mid = new AnimatedModelRenderer(this);
		tail_mid.setRotationPoint(0.0F, 0.0F, 5.0F);
		tail.addChild(tail_mid);
		tail_mid.setTextureOffset(102, 77).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 9.0F, 0.0F, false);
		tail_mid.setModelRendererName("tail_mid");
		this.registerModelRenderer(tail_mid);

		tail_bottom = new AnimatedModelRenderer(this);
		tail_bottom.setRotationPoint(0.0F, 0.0F, 8.0F);
		tail_mid.addChild(tail_bottom);
		setRotationAngle(tail_bottom, 0.2618F, 0.0F, 0.0F);
		tail_bottom.setTextureOffset(108, 94).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);
		tail_bottom.setTextureOffset(96, 112).addBox(-4.0F, -4.0F, 7.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		tail_bottom.setModelRendererName("tail_bottom");
		this.registerModelRenderer(tail_bottom);

		club_decorations = new AnimatedModelRenderer(this);
		club_decorations.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail_bottom.addChild(club_decorations);
		club_decorations.setTextureOffset(75, 54).addBox(-1.0F, -4.9037F, 10.1986F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		club_decorations.setTextureOffset(75, 54).addBox(-1.0F, 4.1492F, 9.9868F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		club_decorations.setTextureOffset(75, 54).addBox(-1.0F, -1.2714F, 14.6191F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		club_decorations.setTextureOffset(75, 54).addBox(4.0F, -0.8356F, 9.6382F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		club_decorations.setTextureOffset(75, 54).addBox(-5.0F, -0.8356F, 9.6382F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		club_decorations.setModelRendererName("club_decorations");
		this.registerModelRenderer(club_decorations);

		decorations_1 = new AnimatedModelRenderer(this);
		decorations_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(decorations_1);
		setRotationAngle(decorations_1, 0.0F, 0.1745F, 0.0F);
		decorations_1.setTextureOffset(118, 32).addBox(8.1302F, -14.25F, -5.7386F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(6.9147F, -12.0F, 1.155F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(6.0465F, -15.25F, 6.0791F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(7.7829F, -15.0F, -3.769F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(7.2186F, -15.5F, -0.5684F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(7.6093F, -19.5F, -2.7842F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(8.1302F, -18.5F, -5.7386F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(8.3039F, -15.25F, -6.7234F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(5.8728F, -16.5F, 7.0639F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(5.8728F, -19.5F, 7.0639F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(5.4821F, -17.5F, 9.2797F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(5.8728F, -13.0F, 7.0639F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(6.524F, -16.5F, 3.3709F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(6.3938F, -18.5F, 4.1095F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(6.9147F, -17.5F, 1.155F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(7.0883F, -14.25F, 0.1702F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(7.9566F, -17.5F, -4.7538F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(7.4356F, -18.5F, -1.7994F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(7.5659F, -16.5F, -2.538F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(7.9566F, -12.0F, -4.7538F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setTextureOffset(118, 32).addBox(7.4356F, -13.0F, -1.7994F, 1.0F, 2.0F, 4.0F, 0.0F, false);
		decorations_1.setModelRendererName("decorations_1");
		this.registerModelRenderer(decorations_1);

		decorations_2 = new AnimatedModelRenderer(this);
		decorations_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(decorations_2);
		setRotationAngle(decorations_2, 0.0F, -0.1745F, 0.0F);
		decorations_2.setTextureOffset(118, 32).addBox(-9.1302F, -14.25F, -5.7386F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-7.9147F, -12.0F, 1.155F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-7.0465F, -15.25F, 6.0791F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-8.7829F, -15.0F, -3.769F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-8.2186F, -15.5F, -0.5684F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-8.6093F, -19.5F, -2.7842F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-9.1302F, -18.5F, -5.7386F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-9.3039F, -15.25F, -6.7234F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-6.8728F, -16.5F, 7.0639F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-6.8728F, -19.5F, 7.0639F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-6.4821F, -17.5F, 9.2797F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-6.8728F, -13.0F, 7.0639F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-7.524F, -16.5F, 3.3709F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-7.3938F, -18.5F, 4.1095F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-7.9147F, -17.5F, 1.155F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-8.0883F, -14.25F, 0.1702F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-8.9566F, -17.5F, -4.7538F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-8.4356F, -18.5F, -1.7994F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-8.5659F, -16.5F, -2.538F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-8.9566F, -12.0F, -4.7538F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setTextureOffset(118, 32).addBox(-8.4356F, -13.0F, -1.7994F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_2.setModelRendererName("decorations_2");
		this.registerModelRenderer(decorations_2);

		decorations_3 = new AnimatedModelRenderer(this);
		decorations_3.setRotationPoint(-17.0F, -12.0F, 1.0F);
		body.addChild(decorations_3);
		setRotationAngle(decorations_3, 0.0F, -0.1745F, 1.5708F);
		decorations_3.setTextureOffset(118, 32).addBox(-9.1302F, -14.25F, -5.7386F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.4356F, -18.0F, -1.7994F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.9566F, -17.0F, -4.7538F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.5659F, -21.5F, -2.538F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.4356F, -23.5F, -1.7994F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.9566F, -22.5F, -4.7538F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.0883F, -19.25F, 0.1702F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-7.9147F, -22.5F, 1.155F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-7.3938F, -23.5F, 4.1095F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-7.524F, -21.5F, 3.3709F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-6.4821F, -22.5F, 9.2797F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-6.8728F, -24.5F, 7.0639F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-6.8728F, -21.5F, 7.0639F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-9.3039F, -20.25F, -6.7234F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-9.1302F, -23.5F, -5.7386F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.6093F, -24.5F, -2.7842F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.2186F, -20.5F, -0.5684F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.7829F, -20.0F, -3.769F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-7.0465F, -20.25F, 6.0791F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-7.9147F, -17.0F, 1.155F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-9.1302F, -19.25F, -5.7386F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-7.9147F, -12.0F, 1.155F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-7.0465F, -15.25F, 6.0791F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.7829F, -15.0F, -3.769F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.2186F, -15.5F, -0.5684F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.6093F, -19.5F, -2.7842F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-9.1302F, -18.5F, -5.7386F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-9.3039F, -15.25F, -6.7234F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-6.8728F, -16.5F, 7.0639F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-6.4821F, -17.5F, 9.2797F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-6.8728F, -13.0F, 7.0639F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-7.524F, -16.5F, 3.3709F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-7.3938F, -18.5F, 4.1095F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-7.9147F, -17.5F, 1.155F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.0883F, -14.25F, 0.1702F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.9566F, -17.5F, -4.7538F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.5659F, -16.5F, -2.538F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.9566F, -12.0F, -4.7538F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setTextureOffset(118, 32).addBox(-8.4356F, -13.0F, -1.7994F, 1.0F, 2.0F, 4.0F, 0.0F, true);
		decorations_3.setModelRendererName("decorations_3");
		this.registerModelRenderer(decorations_3);

		this.rootBones.add(body);
	}


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/spiko.json");
    }
}