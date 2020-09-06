// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin.EntityShakor;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class ShakorModel extends AnimatedEntityModel<EntityShakor> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer blade;
	private final AnimatedModelRenderer left_leg_front;
	private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer jaw;
	private final AnimatedModelRenderer right;
	private final AnimatedModelRenderer ridge_5;
	private final AnimatedModelRenderer ridge_6;
	private final AnimatedModelRenderer ridge_7;
	private final AnimatedModelRenderer left;
	private final AnimatedModelRenderer ridge_2;
	private final AnimatedModelRenderer ridge_3;
	private final AnimatedModelRenderer torso;
	private final AnimatedModelRenderer torso2;
	private final AnimatedModelRenderer torso3;
	private final AnimatedModelRenderer torso4;
	private final AnimatedModelRenderer torso5;
	private final AnimatedModelRenderer torso6;
	private final AnimatedModelRenderer tail;
	private final AnimatedModelRenderer spikes4;
	private final AnimatedModelRenderer right_leg_back;
	private final AnimatedModelRenderer left_leg_back;
	private final AnimatedModelRenderer spikes3;
	private final AnimatedModelRenderer spikes2;
	private final AnimatedModelRenderer spikes;
	private final AnimatedModelRenderer right_leg_front;

    public ShakorModel()
    {
        textureWidth = 64;
    textureHeight = 64;
    body = new AnimatedModelRenderer(this);
		body.setRotationPoint(-1.0F, 13.5F, -4.0F);
		
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		blade = new AnimatedModelRenderer(this);
		blade.setRotationPoint(1.0F, -1.125F, -3.75F);
		body.addChild(blade);
		setRotationAngle(blade, -0.3927F, 0.0F, 0.0F);
		blade.setTextureOffset(26, 32).addBox(-1.0F, -2.125F, -1.5F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		blade.setTextureOffset(0, 0).addBox(0.0F, -3.2346F, -17.8478F, 0.0F, 6.0F, 17.0F, 0.0F, false);
		blade.setModelRendererName("blade");
		this.registerModelRenderer(blade);

		left_leg_front = new AnimatedModelRenderer(this);
		left_leg_front.setRotationPoint(2.7768F, 2.0478F, 4.9911F);
		body.addChild(left_leg_front);
		setRotationAngle(left_leg_front, -1.4203F, 0.0301F, -1.0404F);
		left_leg_front.setTextureOffset(16, 23).addBox(0.1248F, -0.7939F, -0.0385F, 1.0F, 2.0F, 3.0F, 0.0F, false);
		left_leg_front.setTextureOffset(47, 20).addBox(0.1248F, -3.7939F, 2.9615F, 1.0F, 5.0F, 4.0F, 0.0F, false);
		left_leg_front.setTextureOffset(0, 12).addBox(1.1248F, -4.7939F, 2.9615F, 0.0F, 1.0F, 4.0F, 0.0F, false);
		left_leg_front.setModelRendererName("left_leg_front");
		this.registerModelRenderer(left_leg_front);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(1.0F, 1.0F, -1.0F);
		body.addChild(head);
		setRotationAngle(head, 0.0F, 0.7854F, 0.0F);
		head.setTextureOffset(28, 8).addBox(-1.0F, -3.0F, -3.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		jaw = new AnimatedModelRenderer(this);
		jaw.setRotationPoint(0.7803F, 0.75F, -0.7803F);
		head.addChild(jaw);
		setRotationAngle(jaw, 0.0F, 0.0F, 0.0F);
		jaw.setTextureOffset(24, 40).addBox(-1.5875F, 0.7946F, -2.4125F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		jaw.setTextureOffset(8, 37).addBox(1.4125F, -1.2054F, 0.5875F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		jaw.setTextureOffset(0, 37).addBox(-1.5875F, -1.2054F, -2.4125F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		jaw.setTextureOffset(50, 0).addBox(-0.5875F, -0.7054F, -2.4125F, 3.0F, 2.0F, 0.0F, 0.0F, false);
		jaw.setTextureOffset(58, 7).addBox(2.4125F, -0.7054F, -2.4125F, 0.0F, 2.0F, 3.0F, 0.0F, false);
		jaw.setModelRendererName("jaw");
		this.registerModelRenderer(jaw);

		right = new AnimatedModelRenderer(this);
		right.setRotationPoint(-4.5962F, 0.0F, -6.7175F);
		head.addChild(right);
		setRotationAngle(right, -3.1416F, -1.4399F, 3.1416F);
		
		right.setModelRendererName("right");
		this.registerModelRenderer(right);

		ridge_5 = new AnimatedModelRenderer(this);
		ridge_5.setRotationPoint(-3.184F, -6.1667F, -2.7545F);
		right.addChild(ridge_5);
		setRotationAngle(ridge_5, 0.5651F, 0.328F, -0.4735F);
		ridge_5.setTextureOffset(44, 44).addBox(4.0882F, 5.1273F, -6.0616F, 1.0F, 3.0F, 5.0F, 0.0F, false);
		ridge_5.setTextureOffset(16, 40).addBox(4.0882F, 4.1273F, -6.0616F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		ridge_5.setTextureOffset(7, 44).addBox(5.0882F, 4.1273F, -6.0616F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		ridge_5.setModelRendererName("ridge_5");
		this.registerModelRenderer(ridge_5);

		ridge_6 = new AnimatedModelRenderer(this);
		ridge_6.setRotationPoint(-1.2665F, 1.2672F, 2.233F);
		right.addChild(ridge_6);
		setRotationAngle(ridge_6, -0.4363F, -0.2182F, -0.7854F);
		ridge_6.setTextureOffset(43, 12).addBox(1.8475F, 6.4918F, -6.2662F, 1.0F, 3.0F, 5.0F, 0.0F, false);
		ridge_6.setTextureOffset(35, 17).addBox(1.8475F, 5.4918F, -6.2662F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		ridge_6.setTextureOffset(41, 32).addBox(2.8475F, 5.4918F, -6.2662F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		ridge_6.setModelRendererName("ridge_6");
		this.registerModelRenderer(ridge_6);

		ridge_7 = new AnimatedModelRenderer(this);
		ridge_7.setRotationPoint(2.6544F, -2.9203F, -2.4616F);
		head.addChild(ridge_7);
		setRotationAngle(ridge_7, -0.0755F, -1.2378F, 1.2675F);
		ridge_7.setTextureOffset(36, 47).addBox(-0.1742F, 0.9976F, 0.8989F, 1.0F, 2.0F, 5.0F, 0.0F, false);
		ridge_7.setTextureOffset(32, 32).addBox(-0.1742F, -0.0024F, -0.1011F, 1.0F, 1.0F, 7.0F, 0.0F, false);
		ridge_7.setTextureOffset(41, 6).addBox(0.8258F, -0.0024F, 0.8989F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		ridge_7.setModelRendererName("ridge_7");
		this.registerModelRenderer(ridge_7);

		left = new AnimatedModelRenderer(this);
		left.setRotationPoint(5.8336F, 0.15F, 5.0156F);
		head.addChild(left);
		setRotationAngle(left, 3.1416F, 0.0F, 3.1416F);
		
		left.setModelRendererName("left");
		this.registerModelRenderer(left);

		ridge_2 = new AnimatedModelRenderer(this);
		ridge_2.setRotationPoint(-3.184F, -6.0667F, 2.7545F);
		left.addChild(ridge_2);
		setRotationAngle(ridge_2, -0.5651F, -0.328F, -0.4735F);
		ridge_2.setTextureOffset(0, 41).addBox(4.0882F, 5.1273F, 1.0616F, 1.0F, 3.0F, 5.0F, 0.0F, false);
		ridge_2.setTextureOffset(8, 35).addBox(4.0882F, 4.1273F, 0.0616F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		ridge_2.setTextureOffset(40, 0).addBox(5.0882F, 4.1273F, 1.0616F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		ridge_2.setModelRendererName("ridge_2");
		this.registerModelRenderer(ridge_2);

		ridge_3 = new AnimatedModelRenderer(this);
		ridge_3.setRotationPoint(-1.2665F, 1.3672F, -2.233F);
		left.addChild(ridge_3);
		setRotationAngle(ridge_3, 0.4363F, 0.2182F, -0.7854F);
		ridge_3.setTextureOffset(40, 24).addBox(1.8475F, 6.4918F, 1.2662F, 1.0F, 3.0F, 5.0F, 0.0F, false);
		ridge_3.setTextureOffset(0, 34).addBox(1.8475F, 5.4918F, 0.2662F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		ridge_3.setTextureOffset(35, 40).addBox(2.8475F, 5.4918F, 1.2662F, 2.0F, 1.0F, 5.0F, 0.0F, false);
		ridge_3.setModelRendererName("ridge_3");
		this.registerModelRenderer(ridge_3);

		torso = new AnimatedModelRenderer(this);
		torso.setRotationPoint(1.0F, 1.0F, -0.75F);
		body.addChild(torso);
		setRotationAngle(torso, 0.0F, 0.0F, -0.7854F);
		torso.setTextureOffset(0, 0).addBox(-1.7071F, -1.7929F, -0.25F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		torso.setModelRendererName("torso");
		this.registerModelRenderer(torso);

		torso2 = new AnimatedModelRenderer(this);
		torso2.setRotationPoint(0.0F, 0.0F, 3.0F);
		torso.addChild(torso2);
		torso2.setTextureOffset(0, 23).addBox(-3.0F, -2.5F, -0.25F, 5.0F, 5.0F, 6.0F, 0.0F, false);
		torso2.setModelRendererName("torso2");
		this.registerModelRenderer(torso2);

		torso3 = new AnimatedModelRenderer(this);
		torso3.setRotationPoint(0.0F, 0.0F, 6.0F);
		torso2.addChild(torso3);
		torso3.setTextureOffset(22, 23).addBox(-2.5F, -2.0F, -0.25F, 4.0F, 4.0F, 5.0F, 0.0F, false);
		torso3.setModelRendererName("torso3");
		this.registerModelRenderer(torso3);

		torso4 = new AnimatedModelRenderer(this);
		torso4.setRotationPoint(0.0F, 0.0F, 5.0F);
		torso3.addChild(torso4);
		torso4.setTextureOffset(0, 6).addBox(-1.8536F, -1.6464F, -0.25F, 3.0F, 3.0F, 6.0F, 0.0F, false);
		torso4.setModelRendererName("torso4");
		this.registerModelRenderer(torso4);

		torso5 = new AnimatedModelRenderer(this);
		torso5.setRotationPoint(0.0F, 0.0F, 6.0F);
		torso4.addChild(torso5);
		torso5.setTextureOffset(16, 32).addBox(-1.2071F, -1.2929F, -0.25F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		torso5.setModelRendererName("torso5");
		this.registerModelRenderer(torso5);

		torso6 = new AnimatedModelRenderer(this);
		torso6.setRotationPoint(-0.0607F, -0.4393F, 5.75F);
		torso5.addChild(torso6);
		torso6.setTextureOffset(32, 0).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		torso6.setModelRendererName("torso6");
		this.registerModelRenderer(torso6);

		tail = new AnimatedModelRenderer(this);
		tail.setRotationPoint(0.0F, 0.0F, 0.0F);
		torso6.addChild(tail);
		setRotationAngle(tail, 0.0F, 0.0F, 0.7854F);
		tail.setTextureOffset(0, 0).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 12.0F, 0.0F, false);
		tail.setModelRendererName("tail");
		this.registerModelRenderer(tail);

		spikes4 = new AnimatedModelRenderer(this);
		spikes4.setRotationPoint(1.2678F, -1.5607F, -4.75F);
		torso5.addChild(spikes4);
		setRotationAngle(spikes4, 0.3185F, 0.3035F, 0.0491F);
		spikes4.setTextureOffset(0, 27).addBox(-2.8265F, 1.673F, 3.8355F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		spikes4.setTextureOffset(23, 15).addBox(-3.2001F, 2.0466F, 4.9684F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		spikes4.setTextureOffset(8, 15).addBox(-3.6483F, 2.4948F, 6.3278F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		spikes4.setTextureOffset(0, 10).addBox(-4.0966F, 2.9431F, 7.6873F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		spikes4.setModelRendererName("spikes4");
		this.registerModelRenderer(spikes4);

		right_leg_back = new AnimatedModelRenderer(this);
		right_leg_back.setRotationPoint(-1.0303F, -1.091F, 0.25F);
		torso5.addChild(right_leg_back);
		setRotationAngle(right_leg_back, 0.0804F, 0.1231F, 2.3202F);
		right_leg_back.setTextureOffset(34, 34).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		right_leg_back.setTextureOffset(0, 6).addBox(-0.5F, 1.5F, 0.5F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		right_leg_back.setTextureOffset(0, 0).addBox(-0.5F, 1.5F, 2.5F, 0.0F, 2.0F, 1.0F, 0.0F, false);
		right_leg_back.setModelRendererName("right_leg_back");
		this.registerModelRenderer(right_leg_back);

		left_leg_back = new AnimatedModelRenderer(this);
		left_leg_back.setRotationPoint(0.5F, 0.5F, 0.25F);
		torso5.addChild(left_leg_back);
		setRotationAngle(left_leg_back, 0.0309F, -0.0308F, -0.7859F);
		left_leg_back.setTextureOffset(34, 17).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		left_leg_back.setTextureOffset(0, 23).addBox(-0.5F, 1.5F, 0.5F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		left_leg_back.setTextureOffset(0, 5).addBox(0.5F, 1.5F, 2.5F, 0.0F, 2.0F, 1.0F, 0.0F, false);
		left_leg_back.setModelRendererName("left_leg_back");
		this.registerModelRenderer(left_leg_back);

		spikes3 = new AnimatedModelRenderer(this);
		spikes3.setRotationPoint(1.6213F, -1.9142F, -4.75F);
		torso4.addChild(spikes3);
		setRotationAngle(spikes3, 0.3185F, 0.3035F, 0.0491F);
		spikes3.setTextureOffset(32, 3).addBox(-3.8265F, 1.673F, 3.8355F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		spikes3.setTextureOffset(32, 0).addBox(-4.2001F, 2.0466F, 4.9684F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		spikes3.setTextureOffset(21, 23).addBox(-4.6483F, 2.4948F, 6.3278F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		spikes3.setTextureOffset(20, 12).addBox(-5.0966F, 2.9431F, 7.6873F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		spikes3.setModelRendererName("spikes3");
		this.registerModelRenderer(spikes3);

		spikes2 = new AnimatedModelRenderer(this);
		spikes2.setRotationPoint(1.6213F, -1.9142F, 0.25F);
		torso3.addChild(spikes2);
		setRotationAngle(spikes2, 0.3185F, 0.3035F, 0.0491F);
		spikes2.setTextureOffset(16, 34).addBox(-1.777F, -0.3765F, -0.6057F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		spikes2.setTextureOffset(8, 34).addBox(-2.3F, 0.1464F, 0.9804F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		spikes2.setTextureOffset(0, 34).addBox(-2.8229F, 0.6694F, 2.5664F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		spikes2.setModelRendererName("spikes2");
		this.registerModelRenderer(spikes2);

		spikes = new AnimatedModelRenderer(this);
		spikes.setRotationPoint(1.9749F, -2.2678F, 0.5F);
		torso2.addChild(spikes);
		setRotationAngle(spikes, 0.3185F, 0.3035F, 0.0491F);
		spikes.setTextureOffset(44, 40).addBox(-1.777F, -0.3765F, -0.6057F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		spikes.setTextureOffset(35, 24).addBox(-2.6735F, 0.52F, 2.1133F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		spikes.setModelRendererName("spikes");
		this.registerModelRenderer(spikes);

		right_leg_front = new AnimatedModelRenderer(this);
		right_leg_front.setRotationPoint(-1.7768F, 2.0478F, 4.9911F);
		body.addChild(right_leg_front);
		setRotationAngle(right_leg_front, -1.4203F, -0.0301F, 1.0404F);
		right_leg_front.setTextureOffset(15, 12).addBox(-0.6191F, -0.9082F, -0.8936F, 1.0F, 2.0F, 3.0F, 0.0F, false);
		right_leg_front.setTextureOffset(26, 45).addBox(-0.6191F, -3.9082F, 2.1064F, 1.0F, 5.0F, 4.0F, 0.0F, false);
		right_leg_front.setTextureOffset(0, 11).addBox(-0.6191F, -4.9082F, 2.1064F, 0.0F, 1.0F, 4.0F, 0.0F, false);
		right_leg_front.setModelRendererName("right_leg_front");
		this.registerModelRenderer(right_leg_front);

    this.rootBones.add(body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("spectrobesmod", "animations/spectrobe/shakor.json");
    }
}