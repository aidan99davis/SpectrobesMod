// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.samubaku.EntitySamurite;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class SamuriteModel extends AnimatedEntityModel<EntitySamurite> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer left_leg;
	private final AnimatedModelRenderer calf;
	private final AnimatedModelRenderer leg_lower;
	private final AnimatedModelRenderer foot;
	private final AnimatedModelRenderer waist_front_cover;
	private final AnimatedModelRenderer waist_back_cover;
	private final AnimatedModelRenderer shoulder_left;
	private final AnimatedModelRenderer shoulder_part_two;
	private final AnimatedModelRenderer elbow;
	private final AnimatedModelRenderer elbow_decoration;
	private final AnimatedModelRenderer part_2;
	private final AnimatedModelRenderer upper_arm;
	private final AnimatedModelRenderer right_leg;
	private final AnimatedModelRenderer calf2;
	private final AnimatedModelRenderer leg_lower2;
	private final AnimatedModelRenderer foot2;
	private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer helmet;
	private final AnimatedModelRenderer left;
	private final AnimatedModelRenderer front;
	private final AnimatedModelRenderer decoration;
	private final AnimatedModelRenderer right;
	private final AnimatedModelRenderer back;
	private final AnimatedModelRenderer top;
	private final AnimatedModelRenderer shoulder_right;
	private final AnimatedModelRenderer shoulder_part_two2;
	private final AnimatedModelRenderer elbow2;
	private final AnimatedModelRenderer elbow_decoration2;
	private final AnimatedModelRenderer part_3;
	private final AnimatedModelRenderer upper_arm2;

    public SamuriteModel()
    {
        textureWidth = 128;
    	textureHeight = 128;
    	body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 17.0F, 0.0F);
		body.setTextureOffset(56, 0).addBox(-3.0F, -12.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(32, 7).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-6.0F, -19.0F, -4.0F, 12.0F, 7.0F, 8.0F, 0.0F, false);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		left_leg = new AnimatedModelRenderer(this);
		left_leg.setRotationPoint(3.5F, -4.0F, -1.0F);
		body.addChild(left_leg);
		setRotationAngle(left_leg, 0.7854F, 0.2618F, 0.4363F);
		left_leg.setTextureOffset(66, 66).addBox(-0.5F, -4.0F, -3.0F, 1.0F, 7.0F, 7.0F, 0.0F, false);
		left_leg.setModelRendererName("left_leg");
		this.registerModelRenderer(left_leg);

		calf = new AnimatedModelRenderer(this);
		calf.setRotationPoint(0.0F, 0.0F, 0.0F);
		left_leg.addChild(calf);
		calf.setTextureOffset(0, 66).addBox(-0.0189F, -3.1736F, -2.8358F, 4.0F, 6.0F, 6.0F, 0.0F, false);
		calf.setModelRendererName("calf");
		this.registerModelRenderer(calf);

		leg_lower = new AnimatedModelRenderer(this);
		leg_lower.setRotationPoint(2.2491F, 0.2659F, -0.9294F);
		calf.addChild(leg_lower);
		setRotationAngle(leg_lower, 1.1345F, 0.0F, -0.3491F);
		leg_lower.setTextureOffset(22, 54).addBox(0.0F, -2.0F, -8.0F, 2.0F, 3.0F, 9.0F, 0.0F, false);
		leg_lower.setModelRendererName("leg_lower");
		this.registerModelRenderer(leg_lower);

		foot = new AnimatedModelRenderer(this);
		foot.setRotationPoint(0.914F, -0.7369F, -9.1781F);
		leg_lower.addChild(foot);
		setRotationAngle(foot, 0.0F, -1.8326F, -1.0472F);
		foot.setTextureOffset(75, 33).addBox(0.0267F, -1.2506F, -5.0114F, 2.0F, 3.0F, 6.0F, 0.0F, false);
		foot.setModelRendererName("foot");
		this.registerModelRenderer(foot);

		waist_front_cover = new AnimatedModelRenderer(this);
		waist_front_cover.setRotationPoint(0.0F, -8.3264F, -4.0152F);
		body.addChild(waist_front_cover);
		setRotationAngle(waist_front_cover, -0.1745F, 0.0F, 0.0F);
		waist_front_cover.setTextureOffset(12, 80).addBox(-3.0F, -0.5F, 0.0F, 6.0F, 9.0F, 0.0F, 0.0F, false);
		waist_front_cover.setModelRendererName("waist_front_cover");
		this.registerModelRenderer(waist_front_cover);

		waist_back_cover = new AnimatedModelRenderer(this);
		waist_back_cover.setRotationPoint(0.0F, -8.3264F, 4.0152F);
		body.addChild(waist_back_cover);
		setRotationAngle(waist_back_cover, 0.1745F, 0.0F, 0.0F);
		waist_back_cover.setTextureOffset(6, 80).addBox(-3.0F, -0.5F, 0.0F, 6.0F, 9.0F, 0.0F, 0.0F, false);
		waist_back_cover.setModelRendererName("waist_back_cover");
		this.registerModelRenderer(waist_back_cover);

		shoulder_left = new AnimatedModelRenderer(this);
		shoulder_left.setRotationPoint(4.0F, -19.0F, 0.5F);
		body.addChild(shoulder_left);
		setRotationAngle(shoulder_left, -0.7854F, 0.0F, 0.0F);
		shoulder_left.setTextureOffset(21, 24).addBox(0.0F, -3.0F, -4.0F, 6.0F, 9.0F, 9.0F, 0.0F, false);
		shoulder_left.setTextureOffset(79, 79).addBox(2.0F, -8.0F, -1.0F, 2.0F, 6.0F, 3.0F, 0.0F, false);
		shoulder_left.setTextureOffset(65, 54).addBox(2.0F, 0.0F, -9.0F, 2.0F, 3.0F, 6.0F, 0.0F, false);
		shoulder_left.setModelRendererName("shoulder_left");
		this.registerModelRenderer(shoulder_left);

		shoulder_part_two = new AnimatedModelRenderer(this);
		shoulder_part_two.setRotationPoint(5.0F, 1.2929F, 2.1213F);
		shoulder_left.addChild(shoulder_part_two);
		setRotationAngle(shoulder_part_two, 2.1396F, 0.793F, 1.6532F);
		shoulder_part_two.setTextureOffset(64, 10).addBox(-1.0F, -0.8787F, -1.8787F, 4.0F, 9.0F, 5.0F, 0.0F, false);
		shoulder_part_two.setModelRendererName("shoulder_part_two");
		this.registerModelRenderer(shoulder_part_two);

		elbow = new AnimatedModelRenderer(this);
		elbow.setRotationPoint(1.25F, 8.0F, 0.5F);
		shoulder_part_two.addChild(elbow);
		setRotationAngle(elbow, 1.1345F, 0.0F, 0.0F);
		elbow.setTextureOffset(39, 48).addBox(-1.75F, -1.5F, -7.0F, 3.0F, 3.0F, 9.0F, 0.0F, false);
		elbow.setTextureOffset(10, 33).addBox(-1.3797F, -2.6684F, -10.0342F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		elbow.setModelRendererName("elbow");
		this.registerModelRenderer(elbow);

		elbow_decoration = new AnimatedModelRenderer(this);
		elbow_decoration.setRotationPoint(-0.1806F, -0.8632F, -1.8865F);
		elbow.addChild(elbow_decoration);
		setRotationAngle(elbow_decoration, 1.0472F, 0.0F, 0.0F);
		elbow_decoration.setTextureOffset(36, 75).addBox(-1.1272F, -2.9757F, -4.4338F, 2.0F, 13.0F, 3.0F, 0.0F, false);
		elbow_decoration.setTextureOffset(91, 0).addBox(-1.25F, -4.0F, -5.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		elbow_decoration.setModelRendererName("elbow_decoration");
		this.registerModelRenderer(elbow_decoration);

		part_2 = new AnimatedModelRenderer(this);
		part_2.setRotationPoint(-0.1272F, 9.5243F, -3.4338F);
		elbow_decoration.addChild(part_2);
		setRotationAngle(part_2, 0.5672F, 0.0F, 0.0F);
		part_2.setTextureOffset(0, 0).addBox(-0.5F, -0.25F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, false);
		part_2.setModelRendererName("part_2");
		this.registerModelRenderer(part_2);

		upper_arm = new AnimatedModelRenderer(this);
		upper_arm.setRotationPoint(8.0F, 2.7374F, 1.7374F);
		shoulder_left.addChild(upper_arm);
		setRotationAngle(upper_arm, 0.0F, -0.3927F, 0.3927F);
		upper_arm.setTextureOffset(82, 0).addBox(-5.0202F, -3.3921F, -2.7913F, 3.0F, 6.0F, 6.0F, 0.0F, false);
		upper_arm.setTextureOffset(82, 0).addBox(-5.0202F, -3.3921F, -2.7913F, 5.0F, 4.0F, 1.0F, 0.0F, false);
		upper_arm.setTextureOffset(82, 0).addBox(-5.0202F, -3.3921F, -2.7913F, 5.0F, 1.0F, 4.0F, 0.0F, false);
		upper_arm.setModelRendererName("upper_arm");
		this.registerModelRenderer(upper_arm);

		right_leg = new AnimatedModelRenderer(this);
		right_leg.setRotationPoint(-3.5F, -4.0F, -1.0F);
		body.addChild(right_leg);
		setRotationAngle(right_leg, 0.7854F, -0.2618F, -0.4363F);
		right_leg.setTextureOffset(20, 66).addBox(-0.5F, -4.0F, -3.0F, 1.0F, 7.0F, 7.0F, 0.0F, false);
		right_leg.setModelRendererName("right_leg");
		this.registerModelRenderer(right_leg);

		calf2 = new AnimatedModelRenderer(this);
		calf2.setRotationPoint(0.0F, 0.0F, 0.0F);
		right_leg.addChild(calf2);
		calf2.setTextureOffset(63, 42).addBox(-3.9811F, -3.1736F, -2.8358F, 4.0F, 6.0F, 6.0F, 0.0F, false);
		calf2.setModelRendererName("calf2");
		this.registerModelRenderer(calf2);

		leg_lower2 = new AnimatedModelRenderer(this);
		leg_lower2.setRotationPoint(-2.2491F, 0.2659F, -0.9294F);
		calf2.addChild(leg_lower2);
		setRotationAngle(leg_lower2, 1.1345F, 0.0F, 0.3491F);
		leg_lower2.setTextureOffset(0, 54).addBox(-2.0F, -2.0F, -8.0F, 2.0F, 3.0F, 9.0F, 0.0F, false);
		leg_lower2.setModelRendererName("leg_lower2");
		this.registerModelRenderer(leg_lower2);

		foot2 = new AnimatedModelRenderer(this);
		foot2.setRotationPoint(-0.914F, -0.7369F, -9.1781F);
		leg_lower2.addChild(foot2);
		setRotationAngle(foot2, 0.0F, 1.8326F, 1.0472F);
		foot2.setTextureOffset(13, 54).addBox(-2.0267F, -1.2506F, -5.0114F, 2.0F, 3.0F, 6.0F, 0.0F, false);
		foot2.setModelRendererName("foot2");
		this.registerModelRenderer(foot2);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(45, 36).addBox(-3.0F, -25.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		helmet = new AnimatedModelRenderer(this);
		helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(helmet);
		
		helmet.setModelRendererName("helmet");
		this.registerModelRenderer(helmet);

		left = new AnimatedModelRenderer(this);
		left.setRotationPoint(-3.5F, -19.5F, 0.0F);
		helmet.addChild(left);
		setRotationAngle(left, 0.0F, 0.0F, 0.1309F);
		left.setTextureOffset(36, 60).addBox(-0.5F, -6.5F, -4.0F, 1.0F, 7.0F, 8.0F, 0.0F, false);
		left.setModelRendererName("left");
		this.registerModelRenderer(left);

		front = new AnimatedModelRenderer(this);
		front.setRotationPoint(0.0F, -10.0F, 0.0F);
		helmet.addChild(front);
		front.setTextureOffset(31, 22).addBox(-3.0F, -15.0F, -4.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		front.setModelRendererName("front");
		this.registerModelRenderer(front);

		decoration = new AnimatedModelRenderer(this);
		decoration.setRotationPoint(0.0F, -13.0F, -6.5F);
		front.addChild(decoration);
		setRotationAngle(decoration, -0.2182F, 0.2182F, 0.7854F);
		decoration.setTextureOffset(0, 42).addBox(-10.964F, -11.0967F, 0.667F, 12.0F, 12.0F, 0.0F, 0.0F, false);
		decoration.setModelRendererName("decoration");
		this.registerModelRenderer(decoration);

		right = new AnimatedModelRenderer(this);
		right.setRotationPoint(3.5F, -19.5F, 0.0F);
		helmet.addChild(right);
		setRotationAngle(right, 0.0F, 0.0F, -0.1309F);
		right.setTextureOffset(55, 55).addBox(-0.5F, -6.5F, -4.0F, 1.0F, 7.0F, 8.0F, 0.0F, false);
		right.setModelRendererName("right");
		this.registerModelRenderer(right);

		back = new AnimatedModelRenderer(this);
		back.setRotationPoint(0.0F, -20.0F, 4.25F);
		helmet.addChild(back);
		setRotationAngle(back, 0.0436F, 0.0F, 0.0F);
		back.setTextureOffset(75, 63).addBox(-3.0F, -6.0F, -1.0F, 6.0F, 7.0F, 1.0F, 0.0F, false);
		back.setModelRendererName("back");
		this.registerModelRenderer(back);

		top = new AnimatedModelRenderer(this);
		top.setRotationPoint(0.0F, 0.0F, 0.0F);
		helmet.addChild(top);
		top.setTextureOffset(42, 22).addBox(-3.0F, -26.0F, -4.0F, 6.0F, 1.0F, 7.0F, 0.0F, false);
		top.setModelRendererName("top");
		this.registerModelRenderer(top);

		shoulder_right = new AnimatedModelRenderer(this);
		shoulder_right.setRotationPoint(-4.0F, -19.0F, 0.5F);
		body.addChild(shoulder_right);
		setRotationAngle(shoulder_right, -0.7854F, 0.0F, 0.0F);
		shoulder_right.setTextureOffset(21, 24).addBox(-6.0F, -3.0F, -4.0F, 6.0F, 9.0F, 9.0F, 0.0F, true);
		shoulder_right.setTextureOffset(79, 79).addBox(-4.0F, -8.0F, -1.0F, 2.0F, 6.0F, 3.0F, 0.0F, true);
		shoulder_right.setTextureOffset(65, 54).addBox(-4.0F, 0.0F, -9.0F, 2.0F, 3.0F, 6.0F, 0.0F, true);
		shoulder_right.setModelRendererName("shoulder_right");
		this.registerModelRenderer(shoulder_right);

		shoulder_part_two2 = new AnimatedModelRenderer(this);
		shoulder_part_two2.setRotationPoint(-5.0F, 1.2929F, 2.1213F);
		shoulder_right.addChild(shoulder_part_two2);
		setRotationAngle(shoulder_part_two2, 2.1396F, -0.793F, -1.6532F);
		shoulder_part_two2.setTextureOffset(64, 10).addBox(-3.0F, -0.8787F, -1.8787F, 4.0F, 9.0F, 5.0F, 0.0F, true);
		shoulder_part_two2.setModelRendererName("shoulder_part_two2");
		this.registerModelRenderer(shoulder_part_two2);

		elbow2 = new AnimatedModelRenderer(this);
		elbow2.setRotationPoint(-1.25F, 8.0F, 0.5F);
		shoulder_part_two2.addChild(elbow2);
		setRotationAngle(elbow2, 1.1345F, 0.0F, 0.0F);
		elbow2.setTextureOffset(39, 48).addBox(-1.25F, -1.5F, -7.0F, 3.0F, 3.0F, 9.0F, 0.0F, true);
		elbow2.setTextureOffset(10, 33).addBox(-0.6203F, -2.6684F, -10.0342F, 2.0F, 3.0F, 3.0F, 0.0F, true);
		elbow2.setModelRendererName("elbow2");
		this.registerModelRenderer(elbow2);

		elbow_decoration2 = new AnimatedModelRenderer(this);
		elbow_decoration2.setRotationPoint(0.1806F, -0.8632F, -1.8865F);
		elbow2.addChild(elbow_decoration2);
		setRotationAngle(elbow_decoration2, 1.0472F, 0.0F, 0.0F);
		elbow_decoration2.setTextureOffset(36, 75).addBox(-0.8728F, -2.9757F, -4.4338F, 2.0F, 13.0F, 3.0F, 0.0F, true);
		elbow_decoration2.setTextureOffset(91, 0).addBox(-0.75F, -4.0F, -5.0F, 2.0F, 2.0F, 2.0F, 0.0F, true);
		elbow_decoration2.setModelRendererName("elbow_decoration2");
		this.registerModelRenderer(elbow_decoration2);

		part_3 = new AnimatedModelRenderer(this);
		part_3.setRotationPoint(0.1272F, 9.5243F, -3.4338F);
		elbow_decoration2.addChild(part_3);
		setRotationAngle(part_3, 0.5672F, 0.0F, 0.0F);
		part_3.setTextureOffset(0, 0).addBox(-0.5F, -0.25F, -1.0F, 1.0F, 6.0F, 2.0F, 0.0F, true);
		part_3.setModelRendererName("part_3");
		this.registerModelRenderer(part_3);

		upper_arm2 = new AnimatedModelRenderer(this);
		upper_arm2.setRotationPoint(-8.0F, 2.7374F, 1.7374F);
		shoulder_right.addChild(upper_arm2);
		setRotationAngle(upper_arm2, 0.0F, 0.3927F, -0.3927F);
		upper_arm2.setTextureOffset(82, 0).addBox(2.0202F, -3.3921F, -2.7913F, 3.0F, 6.0F, 6.0F, 0.0F, true);
		upper_arm2.setTextureOffset(82, 0).addBox(0.0202F, -3.3921F, -2.7913F, 5.0F, 4.0F, 1.0F, 0.0F, true);
		upper_arm2.setTextureOffset(82, 0).addBox(0.0202F, -3.3921F, -2.7913F, 5.0F, 1.0F, 4.0F, 0.0F, true);
		upper_arm2.setModelRendererName("upper_arm2");
		this.registerModelRenderer(upper_arm2);

    this.rootBones.add(body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/samurite.json");
    }
}