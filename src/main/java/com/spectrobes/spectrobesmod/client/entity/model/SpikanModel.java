package com.spectrobes.spectrobesmod.client.entity.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spiko.EntitySpikan;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
public class SpikanModel extends AnimatedEntityModel<EntitySpikan> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer tail;
	private final AnimatedModelRenderer tail_main;
	private final AnimatedModelRenderer tail_bottom;
	private final AnimatedModelRenderer tail_club;
	private final AnimatedModelRenderer spikes;
	private final AnimatedModelRenderer left_leg;
	private final AnimatedModelRenderer knee;
	private final AnimatedModelRenderer foot;
	private final AnimatedModelRenderer spike;
	private final AnimatedModelRenderer right_leg;
	private final AnimatedModelRenderer knee2;
	private final AnimatedModelRenderer foot2;
	private final AnimatedModelRenderer spike2;
	private final AnimatedModelRenderer left_arm;
	private final AnimatedModelRenderer lower_arm;
	private final AnimatedModelRenderer fingers;
	private final AnimatedModelRenderer thumb;
	private final AnimatedModelRenderer right_arm;
	private final AnimatedModelRenderer lower_arm2;
	private final AnimatedModelRenderer fingers2;
	private final AnimatedModelRenderer thumb2;
	private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer left_horn;
	private final AnimatedModelRenderer part_two;
	private final AnimatedModelRenderer part_three;
	private final AnimatedModelRenderer part_four;
	private final AnimatedModelRenderer right_horn;
	private final AnimatedModelRenderer part_two2;
	private final AnimatedModelRenderer part_three2;
	private final AnimatedModelRenderer part_four2;
	private final AnimatedModelRenderer jaw;
	private final AnimatedModelRenderer teeth_right;
	private final AnimatedModelRenderer teeth_left;
	private final AnimatedModelRenderer teeth_middle;
	private final AnimatedModelRenderer decoration;
	private final AnimatedModelRenderer bone162;
	private final AnimatedModelRenderer bone163;
	private final AnimatedModelRenderer bone164;
	private final AnimatedModelRenderer bone165;
	private final AnimatedModelRenderer bone166;
	private final AnimatedModelRenderer bone167;
	private final AnimatedModelRenderer bone168;
	private final AnimatedModelRenderer bone155;
	private final AnimatedModelRenderer bone156;
	private final AnimatedModelRenderer bone157;
	private final AnimatedModelRenderer bone158;
	private final AnimatedModelRenderer bone159;
	private final AnimatedModelRenderer bone160;
	private final AnimatedModelRenderer bone161;
	private final AnimatedModelRenderer fur5;
	private final AnimatedModelRenderer fur_right5;
	private final AnimatedModelRenderer bone61;
	private final AnimatedModelRenderer bone62;
	private final AnimatedModelRenderer bone63;
	private final AnimatedModelRenderer bone64;
	private final AnimatedModelRenderer bone65;
	private final AnimatedModelRenderer bone66;
	private final AnimatedModelRenderer bone67;
	private final AnimatedModelRenderer fur_left5;
	private final AnimatedModelRenderer bone68;
	private final AnimatedModelRenderer bone69;
	private final AnimatedModelRenderer bone70;
	private final AnimatedModelRenderer bone71;
	private final AnimatedModelRenderer bone72;
	private final AnimatedModelRenderer bone73;
	private final AnimatedModelRenderer bone74;
	private final AnimatedModelRenderer bone77;
	private final AnimatedModelRenderer bone78;
	private final AnimatedModelRenderer bone75;
	private final AnimatedModelRenderer bone76;
	private final AnimatedModelRenderer bone79;
	private final AnimatedModelRenderer bone80;
	private final AnimatedModelRenderer bone81;
	private final AnimatedModelRenderer bone82;
	private final AnimatedModelRenderer bone83;
	private final AnimatedModelRenderer bone84;
	private final AnimatedModelRenderer bone85;
	private final AnimatedModelRenderer bone86;
	private final AnimatedModelRenderer bone87;
	private final AnimatedModelRenderer bone88;
	private final AnimatedModelRenderer bone89;
	private final AnimatedModelRenderer bone90;
	private final AnimatedModelRenderer bone91;
	private final AnimatedModelRenderer bone92;
	private final AnimatedModelRenderer bone93;
	private final AnimatedModelRenderer bone94;
	private final AnimatedModelRenderer bone95;
	private final AnimatedModelRenderer bone96;
	private final AnimatedModelRenderer bone97;
	private final AnimatedModelRenderer bone98;
	private final AnimatedModelRenderer bone99;
	private final AnimatedModelRenderer bone100;
	private final AnimatedModelRenderer bone101;
	private final AnimatedModelRenderer bone102;
	private final AnimatedModelRenderer bone103;
	private final AnimatedModelRenderer bone104;
	private final AnimatedModelRenderer bone105;
	private final AnimatedModelRenderer bone106;
	private final AnimatedModelRenderer bone107;
	private final AnimatedModelRenderer bone108;
	private final AnimatedModelRenderer bone109;
	private final AnimatedModelRenderer bone110;
	private final AnimatedModelRenderer bone111;
	private final AnimatedModelRenderer bone112;
	private final AnimatedModelRenderer bone113;
	private final AnimatedModelRenderer bone114;
	private final AnimatedModelRenderer bone115;
	private final AnimatedModelRenderer bone116;
	private final AnimatedModelRenderer bone117;
	private final AnimatedModelRenderer bone118;
	private final AnimatedModelRenderer bone119;
	private final AnimatedModelRenderer bone120;
	private final AnimatedModelRenderer bone121;
	private final AnimatedModelRenderer bone122;
	private final AnimatedModelRenderer bone123;
	private final AnimatedModelRenderer bone124;
	private final AnimatedModelRenderer bone125;
	private final AnimatedModelRenderer fur4;
	private final AnimatedModelRenderer fur_right4;
	private final AnimatedModelRenderer bone47;
	private final AnimatedModelRenderer bone48;
	private final AnimatedModelRenderer bone49;
	private final AnimatedModelRenderer bone50;
	private final AnimatedModelRenderer bone51;
	private final AnimatedModelRenderer bone52;
	private final AnimatedModelRenderer bone53;
	private final AnimatedModelRenderer fur_left4;
	private final AnimatedModelRenderer bone54;
	private final AnimatedModelRenderer bone55;
	private final AnimatedModelRenderer bone56;
	private final AnimatedModelRenderer bone57;
	private final AnimatedModelRenderer bone58;
	private final AnimatedModelRenderer bone59;
	private final AnimatedModelRenderer bone60;
	private final AnimatedModelRenderer fur3;
	private final AnimatedModelRenderer fur_right3;
	private final AnimatedModelRenderer bone33;
	private final AnimatedModelRenderer bone34;
	private final AnimatedModelRenderer bone35;
	private final AnimatedModelRenderer bone36;
	private final AnimatedModelRenderer bone37;
	private final AnimatedModelRenderer bone38;
	private final AnimatedModelRenderer bone39;
	private final AnimatedModelRenderer fur_left3;
	private final AnimatedModelRenderer bone40;
	private final AnimatedModelRenderer bone41;
	private final AnimatedModelRenderer bone42;
	private final AnimatedModelRenderer bone43;
	private final AnimatedModelRenderer bone44;
	private final AnimatedModelRenderer bone45;
	private final AnimatedModelRenderer bone46;
	private final AnimatedModelRenderer fur2;
	private final AnimatedModelRenderer fur_right2;
	private final AnimatedModelRenderer bone19;
	private final AnimatedModelRenderer bone20;
	private final AnimatedModelRenderer bone21;
	private final AnimatedModelRenderer bone22;
	private final AnimatedModelRenderer bone23;
	private final AnimatedModelRenderer bone24;
	private final AnimatedModelRenderer bone25;
	private final AnimatedModelRenderer fur_left2;
	private final AnimatedModelRenderer bone26;
	private final AnimatedModelRenderer bone27;
	private final AnimatedModelRenderer bone28;
	private final AnimatedModelRenderer bone29;
	private final AnimatedModelRenderer bone30;
	private final AnimatedModelRenderer bone31;
	private final AnimatedModelRenderer bone32;
	private final AnimatedModelRenderer sideburn_left;
	private final AnimatedModelRenderer bone15;
	private final AnimatedModelRenderer bone18;
	private final AnimatedModelRenderer sideburn_right;
	private final AnimatedModelRenderer bone16;
	private final AnimatedModelRenderer bone17;
	private final AnimatedModelRenderer bone126;
	private final AnimatedModelRenderer bone127;
	private final AnimatedModelRenderer bone128;
	private final AnimatedModelRenderer bone129;
	private final AnimatedModelRenderer bone130;
	private final AnimatedModelRenderer bone131;
	private final AnimatedModelRenderer bone132;
	private final AnimatedModelRenderer bone133;
	private final AnimatedModelRenderer bone134;
	private final AnimatedModelRenderer bone135;
	private final AnimatedModelRenderer bone136;
	private final AnimatedModelRenderer bone137;
	private final AnimatedModelRenderer bone138;
	private final AnimatedModelRenderer bone139;
	private final AnimatedModelRenderer bone140;
	private final AnimatedModelRenderer bone141;
	private final AnimatedModelRenderer bone142;
	private final AnimatedModelRenderer bone143;
	private final AnimatedModelRenderer bone144;
	private final AnimatedModelRenderer bone145;
	private final AnimatedModelRenderer bone146;
	private final AnimatedModelRenderer bone147;
	private final AnimatedModelRenderer bone148;
	private final AnimatedModelRenderer bone149;
	private final AnimatedModelRenderer bone150;
	private final AnimatedModelRenderer bone151;
	private final AnimatedModelRenderer bone152;
	private final AnimatedModelRenderer bone153;
	private final AnimatedModelRenderer bone154;
	private final AnimatedModelRenderer fur;
	private final AnimatedModelRenderer fur_right;
	private final AnimatedModelRenderer bone8;
	private final AnimatedModelRenderer bone9;
	private final AnimatedModelRenderer bone10;
	private final AnimatedModelRenderer bone11;
	private final AnimatedModelRenderer bone12;
	private final AnimatedModelRenderer bone13;
	private final AnimatedModelRenderer bone14;
	private final AnimatedModelRenderer fur_left;
	private final AnimatedModelRenderer bone;
	private final AnimatedModelRenderer bone2;
	private final AnimatedModelRenderer bone3;
	private final AnimatedModelRenderer bone4;
	private final AnimatedModelRenderer bone5;
	private final AnimatedModelRenderer bone6;
	private final AnimatedModelRenderer bone7;

    public SpikanModel()
    {
        textureWidth = 128;
		textureHeight = 128;
		body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 6.8333F, 0.0F);
		setRotationAngle(body, 0.3491F, 0.0F, 0.0F);
		body.setTextureOffset(0, 44).addBox(-3.0F, -4.8333F, -2.0F, 6.0F, 10.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(36, 36).addBox(-4.0F, 5.1667F, -3.0F, 8.0F, 5.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(0, 32).addBox(-6.0F, -10.8333F, -3.0F, 12.0F, 6.0F, 6.0F, 0.0F, false);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		tail = new AnimatedModelRenderer(this);
		tail.setRotationPoint(-0.75F, 10.0094F, 3.06F);
		body.addChild(tail);
		setRotationAngle(tail, 0.8727F, 0.0F, 0.0F);
		tail.setTextureOffset(64, 14).addBox(-1.0F, -1.5F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);
		tail.setModelRendererName("tail");
		this.registerModelRenderer(tail);

		tail_main = new AnimatedModelRenderer(this);
		tail_main.setRotationPoint(0.0F, 4.0F, 0.0F);
		tail.addChild(tail_main);
		setRotationAngle(tail_main, 0.2618F, 0.0F, 0.0F);
		tail_main.setTextureOffset(58, 65).addBox(-0.5F, 0.0F, -1.0F, 2.0F, 13.0F, 2.0F, 0.0F, false);
		tail_main.setModelRendererName("tail_main");
		this.registerModelRenderer(tail_main);

		tail_bottom = new AnimatedModelRenderer(this);
		tail_bottom.setRotationPoint(0.0F, 13.0F, 0.0F);
		tail_main.addChild(tail_bottom);
		setRotationAngle(tail_bottom, 0.2618F, 0.0F, 0.0F);
		tail_bottom.setTextureOffset(0, 32).addBox(0.0F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		tail_bottom.setModelRendererName("tail_bottom");
		this.registerModelRenderer(tail_bottom);

		tail_club = new AnimatedModelRenderer(this);
		tail_club.setRotationPoint(0.0F, 5.0F, 0.0F);
		tail_bottom.addChild(tail_club);
		tail_club.setTextureOffset(49, 18).addBox(-2.0F, 0.0F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
		tail_club.setModelRendererName("tail_club");
		this.registerModelRenderer(tail_club);

		spikes = new AnimatedModelRenderer(this);
		spikes.setRotationPoint(0.0F, 1.0F, -3.0F);
		tail_club.addChild(spikes);
		spikes.setTextureOffset(68, 33).addBox(0.0F, 1.0F, 5.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		spikes.setTextureOffset(68, 29).addBox(0.0F, 1.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		spikes.setTextureOffset(33, 0).addBox(0.0F, 4.0F, 2.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		spikes.setTextureOffset(39, 0).addBox(-5.0F, 1.0F, 2.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		spikes.setTextureOffset(0, 9).addBox(3.0F, 1.0F, 2.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		spikes.setModelRendererName("spikes");
		this.registerModelRenderer(spikes);

		left_leg = new AnimatedModelRenderer(this);
		left_leg.setRotationPoint(3.5F, 9.0F, -0.5F);
		body.addChild(left_leg);
		left_leg.setTextureOffset(40, 47).addBox(0.5F, -2.0F, -5.5F, 3.0F, 4.0F, 7.0F, 0.0F, false);
		left_leg.setModelRendererName("left_leg");
		this.registerModelRenderer(left_leg);

		knee = new AnimatedModelRenderer(this);
		knee.setRotationPoint(0.0F, 0.0F, -6.0F);
		left_leg.addChild(knee);
		knee.setTextureOffset(0, 14).addBox(1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		knee.setModelRendererName("knee");
		this.registerModelRenderer(knee);

		foot = new AnimatedModelRenderer(this);
		foot.setRotationPoint(2.5F, 6.5F, -0.5F);
		knee.addChild(foot);
		setRotationAngle(foot, -0.3491F, 0.0F, 0.0F);
		foot.setTextureOffset(42, 64).addBox(-1.5F, -0.5F, -5.5F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		foot.setModelRendererName("foot");
		this.registerModelRenderer(foot);

		spike = new AnimatedModelRenderer(this);
		spike.setRotationPoint(0.0F, 0.0F, 6.0F);
		knee.addChild(spike);
		spike.setTextureOffset(33, 5).addBox(1.5F, -3.0F, -7.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		spike.setModelRendererName("spike");
		this.registerModelRenderer(spike);

		right_leg = new AnimatedModelRenderer(this);
		right_leg.setRotationPoint(-3.5F, 9.0F, -0.5F);
		body.addChild(right_leg);
		right_leg.setTextureOffset(20, 44).addBox(-3.5F, -2.0F, -5.5F, 3.0F, 4.0F, 7.0F, 0.0F, false);
		right_leg.setModelRendererName("right_leg");
		this.registerModelRenderer(right_leg);

		knee2 = new AnimatedModelRenderer(this);
		knee2.setRotationPoint(0.0F, 0.0F, 0.0F);
		right_leg.addChild(knee2);
		knee2.setTextureOffset(0, 0).addBox(-3.0F, -1.0F, -7.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
		knee2.setModelRendererName("knee2");
		this.registerModelRenderer(knee2);

		foot2 = new AnimatedModelRenderer(this);
		foot2.setRotationPoint(-2.5F, 6.5F, -6.5F);
		knee2.addChild(foot2);
		setRotationAngle(foot2, -0.3491F, 0.0F, 0.0F);
		foot2.setTextureOffset(58, 32).addBox(-0.5F, -0.5F, -5.5F, 2.0F, 1.0F, 6.0F, 0.0F, false);
		foot2.setModelRendererName("foot2");
		this.registerModelRenderer(foot2);

		spike2 = new AnimatedModelRenderer(this);
		spike2.setRotationPoint(0.0F, 0.0F, 0.0F);
		knee2.addChild(spike2);
		spike2.setTextureOffset(30, 17).addBox(-2.5F, -3.0F, -7.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		spike2.setModelRendererName("spike2");
		this.registerModelRenderer(spike2);

		left_arm = new AnimatedModelRenderer(this);
		left_arm.setRotationPoint(6.0F, -7.5F, -0.5F);
		body.addChild(left_arm);
		setRotationAngle(left_arm, -0.3491F, 0.1745F, -0.6981F);
		left_arm.setTextureOffset(0, 58).addBox(0.0F, -2.5F, -2.5F, 4.0F, 5.0F, 5.0F, 0.0F, false);
		left_arm.setTextureOffset(30, 64).addBox(0.5F, 2.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F, false);
		left_arm.setModelRendererName("left_arm");
		this.registerModelRenderer(left_arm);

		lower_arm = new AnimatedModelRenderer(this);
		lower_arm.setRotationPoint(1.9645F, 8.2943F, -0.2987F);
		left_arm.addChild(lower_arm);
		setRotationAngle(lower_arm, 0.7854F, 0.7854F, 0.0F);
		lower_arm.setTextureOffset(33, 3).addBox(-2.0F, -1.0F, -10.0F, 4.0F, 4.0F, 11.0F, 0.0F, false);
		lower_arm.setTextureOffset(33, 5).addBox(-1.6014F, 1.8127F, 0.6897F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		lower_arm.setModelRendererName("lower_arm");
		this.registerModelRenderer(lower_arm);

		fingers = new AnimatedModelRenderer(this);
		fingers.setRotationPoint(0.0F, 0.0F, 0.0F);
		lower_arm.addChild(fingers);
		fingers.setTextureOffset(68, 0).addBox(-2.1014F, 1.8127F, -12.3103F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		fingers.setTextureOffset(8, 68).addBox(-0.6014F, 1.8127F, -12.3103F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		fingers.setTextureOffset(0, 68).addBox(0.8986F, 1.8127F, -12.3103F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		fingers.setTextureOffset(67, 25).addBox(0.8986F, -0.1873F, -12.3103F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		fingers.setModelRendererName("fingers");
		this.registerModelRenderer(fingers);

		thumb = new AnimatedModelRenderer(this);
		thumb.setRotationPoint(-1.1014F, -1.1873F, -8.3103F);
		lower_arm.addChild(thumb);
		setRotationAngle(thumb, -0.1745F, 0.2618F, 0.0F);
		thumb.setTextureOffset(64, 39).addBox(-0.2603F, 0.3913F, -3.615F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		thumb.setModelRendererName("thumb");
		this.registerModelRenderer(thumb);

		right_arm = new AnimatedModelRenderer(this);
		right_arm.setRotationPoint(-6.0F, -7.5F, -0.5F);
		body.addChild(right_arm);
		setRotationAngle(right_arm, -0.3491F, -0.1745F, 0.6981F);
		right_arm.setTextureOffset(55, 55).addBox(-4.0F, -2.5F, -2.5F, 4.0F, 5.0F, 5.0F, 0.0F, false);
		right_arm.setTextureOffset(18, 63).addBox(-3.5F, 2.0F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F, false);
		right_arm.setModelRendererName("right_arm");
		this.registerModelRenderer(right_arm);

		lower_arm2 = new AnimatedModelRenderer(this);
		lower_arm2.setRotationPoint(-1.9645F, 8.2943F, -0.2987F);
		right_arm.addChild(lower_arm2);
		setRotationAngle(lower_arm2, 0.7854F, -0.7854F, 0.0F);
		lower_arm2.setTextureOffset(30, 21).addBox(-2.0F, -1.0F, -10.0F, 4.0F, 4.0F, 11.0F, 0.0F, false);
		lower_arm2.setTextureOffset(30, 18).addBox(0.6014F, 1.8127F, 0.6897F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		lower_arm2.setModelRendererName("lower_arm2");
		this.registerModelRenderer(lower_arm2);

		fingers2 = new AnimatedModelRenderer(this);
		fingers2.setRotationPoint(0.0F, 0.0F, 0.0F);
		lower_arm2.addChild(fingers2);
		fingers2.setTextureOffset(66, 66).addBox(1.1014F, 1.8127F, -12.3103F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		fingers2.setTextureOffset(60, 51).addBox(-0.3986F, 1.8127F, -12.3103F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		fingers2.setTextureOffset(33, 47).addBox(-1.8986F, 1.8127F, -12.3103F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		fingers2.setTextureOffset(16, 44).addBox(-1.8986F, -0.1873F, -12.3103F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		fingers2.setModelRendererName("fingers2");
		this.registerModelRenderer(fingers2);

		thumb2 = new AnimatedModelRenderer(this);
		thumb2.setRotationPoint(1.1014F, -1.1873F, -8.3103F);
		lower_arm2.addChild(thumb2);
		setRotationAngle(thumb2, -0.1745F, -0.2618F, 0.0F);
		thumb2.setTextureOffset(33, 0).addBox(-0.7397F, 0.3913F, -3.615F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		thumb2.setModelRendererName("thumb2");
		this.registerModelRenderer(thumb2);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(0, 14).addBox(-5.0F, -19.0F, -7.0F, 10.0F, 8.0F, 10.0F, 0.0F, false);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		left_horn = new AnimatedModelRenderer(this);
		left_horn.setRotationPoint(5.0F, -18.0F, -1.0F);
		head.addChild(left_horn);
		setRotationAngle(left_horn, -0.7854F, 0.0F, -0.5236F);
		left_horn.setTextureOffset(16, 55).addBox(-2.5F, -2.0F, -2.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);
		left_horn.setModelRendererName("left_horn");
		this.registerModelRenderer(left_horn);

		part_two = new AnimatedModelRenderer(this);
		part_two.setRotationPoint(3.0F, 1.0F, 0.0F);
		left_horn.addChild(part_two);
		setRotationAngle(part_two, 0.0F, -0.1745F, 0.4363F);
		part_two.setTextureOffset(36, 58).addBox(-0.51F, -2.0448F, -0.8811F, 6.0F, 3.0F, 3.0F, 0.0F, false);
		part_two.setModelRendererName("part_two");
		this.registerModelRenderer(part_two);

		part_three = new AnimatedModelRenderer(this);
		part_three.setRotationPoint(6.49F, -0.5448F, 0.6189F);
		part_two.addChild(part_three);
		setRotationAngle(part_three, 0.0F, 0.0F, 1.2217F);
		part_three.setTextureOffset(53, 47).addBox(-1.75F, 0.0F, -1.0F, 8.0F, 2.0F, 2.0F, 0.0F, false);
		part_three.setModelRendererName("part_three");
		this.registerModelRenderer(part_three);

		part_four = new AnimatedModelRenderer(this);
		part_four.setRotationPoint(6.0F, 1.0F, 0.0F);
		part_three.addChild(part_four);
		setRotationAngle(part_four, 0.0F, 0.0F, 0.6981F);
		part_four.setTextureOffset(36, 18).addBox(0.0F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		part_four.setModelRendererName("part_four");
		this.registerModelRenderer(part_four);

		right_horn = new AnimatedModelRenderer(this);
		right_horn.setRotationPoint(-5.0F, -18.0F, -1.0F);
		head.addChild(right_horn);
		setRotationAngle(right_horn, -0.7854F, 0.0F, 0.5236F);
		right_horn.setTextureOffset(52, 0).addBox(-3.5F, -2.0F, -2.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);
		right_horn.setModelRendererName("right_horn");
		this.registerModelRenderer(right_horn);

		part_two2 = new AnimatedModelRenderer(this);
		part_two2.setRotationPoint(-3.0F, 1.0F, 0.0F);
		right_horn.addChild(part_two2);
		setRotationAngle(part_two2, 0.0F, 0.1745F, -0.4363F);
		part_two2.setTextureOffset(52, 8).addBox(-5.49F, -2.0448F, -0.8811F, 6.0F, 3.0F, 3.0F, 0.0F, false);
		part_two2.setModelRendererName("part_two2");
		this.registerModelRenderer(part_two2);

		part_three2 = new AnimatedModelRenderer(this);
		part_three2.setRotationPoint(-6.49F, -0.5448F, 0.6189F);
		part_two2.addChild(part_three2);
		setRotationAngle(part_three2, 0.0F, 0.0F, -1.2217F);
		part_three2.setTextureOffset(49, 28).addBox(-6.25F, 0.0F, -1.0F, 8.0F, 2.0F, 2.0F, 0.0F, false);
		part_three2.setModelRendererName("part_three2");
		this.registerModelRenderer(part_three2);

		part_four2 = new AnimatedModelRenderer(this);
		part_four2.setRotationPoint(-6.0F, 1.0F, 0.0F);
		part_three2.addChild(part_four2);
		setRotationAngle(part_four2, 0.0F, 0.0F, -0.6981F);
		part_four2.setTextureOffset(30, 36).addBox(-5.0F, -0.5F, -0.5F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		part_four2.setModelRendererName("part_four2");
		this.registerModelRenderer(part_four2);

		jaw = new AnimatedModelRenderer(this);
		jaw.setRotationPoint(-0.5F, 0.0F, 0.0F);
		head.addChild(jaw);
		jaw.setTextureOffset(0, 0).addBox(-5.0F, -14.0F, -8.0F, 11.0F, 3.0F, 11.0F, 0.0F, false);
		jaw.setModelRendererName("jaw");
		this.registerModelRenderer(jaw);

		teeth_right = new AnimatedModelRenderer(this);
		teeth_right.setRotationPoint(0.0F, 0.0F, 0.0F);
		jaw.addChild(teeth_right);
		teeth_right.setTextureOffset(21, 44).addBox(-5.0F, -16.0F, -7.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		teeth_right.setTextureOffset(0, 44).addBox(-5.0F, -11.0F, -8.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		teeth_right.setModelRendererName("teeth_right");
		this.registerModelRenderer(teeth_right);

		teeth_left = new AnimatedModelRenderer(this);
		teeth_left.setRotationPoint(1.0F, 0.0F, 0.0F);
		jaw.addChild(teeth_left);
		teeth_left.setTextureOffset(39, 5).addBox(4.0F, -16.0F, -7.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		teeth_left.setTextureOffset(36, 38).addBox(4.0F, -11.0F, -8.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		teeth_left.setModelRendererName("teeth_left");
		this.registerModelRenderer(teeth_left);

		teeth_middle = new AnimatedModelRenderer(this);
		teeth_middle.setRotationPoint(0.0F, -13.75F, -7.0F);
		jaw.addChild(teeth_middle);
		setRotationAngle(teeth_middle, -0.6981F, 0.6981F, -0.5236F);
		teeth_middle.setTextureOffset(36, 20).addBox(-2.0F, -0.5F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		teeth_middle.setTextureOffset(6, 14).addBox(-1.0F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		teeth_middle.setTextureOffset(7, 8).addBox(1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		teeth_middle.setTextureOffset(6, 0).addBox(0.0F, -0.5F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		teeth_middle.setModelRendererName("teeth_middle");
		this.registerModelRenderer(teeth_middle);

		decoration = new AnimatedModelRenderer(this);
		decoration.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(decoration);
		
		decoration.setModelRendererName("decoration");
		this.registerModelRenderer(decoration);

		bone162 = new AnimatedModelRenderer(this);
		bone162.setRotationPoint(0.75F, -14.9631F, -7.373F);
		decoration.addChild(bone162);
		setRotationAngle(bone162, 0.0F, 0.0F, -0.3491F);
		bone162.setTextureOffset(76, 0).addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone162.setModelRendererName("bone162");
		this.registerModelRenderer(bone162);

		bone163 = new AnimatedModelRenderer(this);
		bone163.setRotationPoint(0.9962F, -0.0872F, 0.0F);
		bone162.addChild(bone163);
		setRotationAngle(bone163, 0.0F, 0.0F, 0.1396F);
		bone163.setTextureOffset(76, 0).addBox(-1.4059F, -6.4226F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone163.setModelRendererName("bone163");
		this.registerModelRenderer(bone163);

		bone164 = new AnimatedModelRenderer(this);
		bone164.setRotationPoint(-0.9848F, 0.1736F, 0.0F);
		bone163.addChild(bone164);
		setRotationAngle(bone164, 0.0F, 0.0F, 0.2618F);
		bone164.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone164.setModelRendererName("bone164");
		this.registerModelRenderer(bone164);

		bone165 = new AnimatedModelRenderer(this);
		bone165.setRotationPoint(-0.2165F, 0.125F, 0.0F);
		bone164.addChild(bone165);
		setRotationAngle(bone165, 0.0F, 0.0F, 0.3491F);
		bone165.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone165.setModelRendererName("bone165");
		this.registerModelRenderer(bone165);

		bone166 = new AnimatedModelRenderer(this);
		bone166.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone165.addChild(bone166);
		setRotationAngle(bone166, 0.0F, 0.0F, 0.0873F);
		bone166.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone166.setModelRendererName("bone166");
		this.registerModelRenderer(bone166);

		bone167 = new AnimatedModelRenderer(this);
		bone167.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone166.addChild(bone167);
		setRotationAngle(bone167, 0.0F, 0.0F, 0.2618F);
		bone167.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone167.setModelRendererName("bone167");
		this.registerModelRenderer(bone167);

		bone168 = new AnimatedModelRenderer(this);
		bone168.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone167.addChild(bone168);
		setRotationAngle(bone168, 0.0F, 0.0F, 0.2618F);
		bone168.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone168.setModelRendererName("bone168");
		this.registerModelRenderer(bone168);

		bone155 = new AnimatedModelRenderer(this);
		bone155.setRotationPoint(-0.75F, -14.9631F, -7.373F);
		decoration.addChild(bone155);
		setRotationAngle(bone155, 0.0F, 0.0F, 0.3491F);
		bone155.setTextureOffset(76, 0).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone155.setModelRendererName("bone155");
		this.registerModelRenderer(bone155);

		bone156 = new AnimatedModelRenderer(this);
		bone156.setRotationPoint(-0.9962F, -0.0872F, 0.0F);
		bone155.addChild(bone156);
		setRotationAngle(bone156, 0.0F, 0.0F, -0.1396F);
		bone156.setTextureOffset(76, 0).addBox(0.4059F, -6.4226F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone156.setModelRendererName("bone156");
		this.registerModelRenderer(bone156);

		bone157 = new AnimatedModelRenderer(this);
		bone157.setRotationPoint(0.9848F, 0.1736F, 0.0F);
		bone156.addChild(bone157);
		setRotationAngle(bone157, 0.0F, 0.0F, -0.2618F);
		bone157.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone157.setModelRendererName("bone157");
		this.registerModelRenderer(bone157);

		bone158 = new AnimatedModelRenderer(this);
		bone158.setRotationPoint(0.2165F, 0.125F, 0.0F);
		bone157.addChild(bone158);
		setRotationAngle(bone158, 0.0F, 0.0F, -0.3491F);
		bone158.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone158.setModelRendererName("bone158");
		this.registerModelRenderer(bone158);

		bone159 = new AnimatedModelRenderer(this);
		bone159.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone158.addChild(bone159);
		setRotationAngle(bone159, 0.0F, 0.0F, -0.0873F);
		bone159.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone159.setModelRendererName("bone159");
		this.registerModelRenderer(bone159);

		bone160 = new AnimatedModelRenderer(this);
		bone160.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone159.addChild(bone160);
		setRotationAngle(bone160, 0.0F, 0.0F, -0.2618F);
		bone160.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone160.setModelRendererName("bone160");
		this.registerModelRenderer(bone160);

		bone161 = new AnimatedModelRenderer(this);
		bone161.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone160.addChild(bone161);
		setRotationAngle(bone161, 0.0F, 0.0F, -0.2618F);
		bone161.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone161.setModelRendererName("bone161");
		this.registerModelRenderer(bone161);

		fur5 = new AnimatedModelRenderer(this);
		fur5.setRotationPoint(0.0F, -15.9404F, 3.2248F);
		decoration.addChild(fur5);
		setRotationAngle(fur5, -2.2689F, 0.0F, 0.0F);
		
		fur5.setModelRendererName("fur5");
		this.registerModelRenderer(fur5);

		fur_right5 = new AnimatedModelRenderer(this);
		fur_right5.setRotationPoint(0.2267F, 6.4791F, -3.4339F);
		fur5.addChild(fur_right5);
		setRotationAngle(fur_right5, -0.4363F, 0.0F, -0.1222F);
		fur_right5.setTextureOffset(76, 0).addBox(-0.1299F, -19.126F, -0.4003F, 1.0F, 13.0F, 1.0F, 0.0F, true);
		fur_right5.setModelRendererName("fur_right5");
		this.registerModelRenderer(fur_right5);

		bone61 = new AnimatedModelRenderer(this);
		bone61.setRotationPoint(-1.5F, -1.8333F, -4.25F);
		fur_right5.addChild(bone61);
		bone61.setTextureOffset(76, 0).addBox(0.6201F, -16.5417F, 3.8715F, 1.0F, 12.0F, 1.0F, 0.0F, true);
		bone61.setModelRendererName("bone61");
		this.registerModelRenderer(bone61);

		bone62 = new AnimatedModelRenderer(this);
		bone62.setRotationPoint(-0.9962F, -0.0872F, 0.0F);
		bone61.addChild(bone62);
		setRotationAngle(bone62, 0.0F, 0.0F, -0.0524F);
		bone62.setTextureOffset(76, 0).addBox(1.652F, -16.8338F, 3.8279F, 1.0F, 12.0F, 1.0F, 0.0F, true);
		bone62.setModelRendererName("bone62");
		this.registerModelRenderer(bone62);

		bone63 = new AnimatedModelRenderer(this);
		bone63.setRotationPoint(0.9848F, 0.1736F, 0.0F);
		bone62.addChild(bone63);
		setRotationAngle(bone63, 0.0F, 0.0F, -0.1745F);
		bone63.setTextureOffset(76, 0).addBox(2.2683F, -15.4904F, 3.8279F, 1.0F, 12.0F, 1.0F, 0.0F, true);
		bone63.setModelRendererName("bone63");
		this.registerModelRenderer(bone63);

		bone64 = new AnimatedModelRenderer(this);
		bone64.setRotationPoint(0.2165F, 0.125F, 0.0F);
		bone63.addChild(bone64);
		setRotationAngle(bone64, 0.0F, 0.0F, -0.1745F);
		bone64.setTextureOffset(76, 0).addBox(3.4739F, -14.4507F, 3.8279F, 1.0F, 12.0F, 1.0F, 0.0F, true);
		bone64.setModelRendererName("bone64");
		this.registerModelRenderer(bone64);

		bone65 = new AnimatedModelRenderer(this);
		bone65.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone64.addChild(bone65);
		setRotationAngle(bone65, 0.0F, 0.0F, -0.0873F);
		bone65.setTextureOffset(76, 0).addBox(3.713F, -14.1302F, 3.8279F, 1.0F, 12.0F, 1.0F, 0.0F, true);
		bone65.setModelRendererName("bone65");
		this.registerModelRenderer(bone65);

		bone66 = new AnimatedModelRenderer(this);
		bone66.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone65.addChild(bone66);
		setRotationAngle(bone66, 0.0F, 0.0F, -0.0873F);
		bone66.setTextureOffset(76, 0).addBox(4.2511F, -13.0614F, 3.8279F, 1.0F, 12.0F, 1.0F, 0.0F, true);
		bone66.setModelRendererName("bone66");
		this.registerModelRenderer(bone66);

		bone67 = new AnimatedModelRenderer(this);
		bone67.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone66.addChild(bone67);
		setRotationAngle(bone67, 0.0F, 0.0F, -0.0873F);
		bone67.setTextureOffset(76, 0).addBox(4.4943F, -11.8898F, 3.8279F, 1.0F, 12.0F, 1.0F, 0.0F, true);
		bone67.setModelRendererName("bone67");
		this.registerModelRenderer(bone67);

		fur_left5 = new AnimatedModelRenderer(this);
		fur_left5.setRotationPoint(-1.2733F, 6.0092F, -4.1049F);
		fur5.addChild(fur_left5);
		setRotationAngle(fur_left5, -0.6109F, 0.0F, 0.1396F);
		fur_left5.setTextureOffset(76, 0).addBox(-0.2929F, -19.1948F, -0.4324F, 1.0F, 13.0F, 1.0F, 0.0F, false);
		fur_left5.setModelRendererName("fur_left5");
		this.registerModelRenderer(fur_left5);

		bone68 = new AnimatedModelRenderer(this);
		bone68.setRotationPoint(0.5F, -1.8333F, -4.25F);
		fur_left5.addChild(bone68);
		setRotationAngle(bone68, 0.0F, 0.0F, 0.0873F);
		bone68.setTextureOffset(76, 0).addBox(-1.4446F, -19.5893F, 3.8394F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		bone68.setModelRendererName("bone68");
		this.registerModelRenderer(bone68);

		bone69 = new AnimatedModelRenderer(this);
		bone69.setRotationPoint(0.9962F, -0.0872F, 0.0F);
		bone68.addChild(bone69);
		setRotationAngle(bone69, 0.0F, 0.0F, 0.0524F);
		bone69.setTextureOffset(76, 0).addBox(-2.4849F, -19.9054F, 3.7958F, 1.0F, 16.0F, 1.0F, 0.0F, false);
		bone69.setModelRendererName("bone69");
		this.registerModelRenderer(bone69);

		bone70 = new AnimatedModelRenderer(this);
		bone70.setRotationPoint(-0.9848F, 0.1736F, 0.0F);
		bone69.addChild(bone70);
		setRotationAngle(bone70, 0.0F, 0.0F, 0.1745F);
		bone70.setTextureOffset(76, 0).addBox(-3.47F, -18.3954F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		bone70.setModelRendererName("bone70");
		this.registerModelRenderer(bone70);

		bone71 = new AnimatedModelRenderer(this);
		bone71.setRotationPoint(-0.2165F, 0.125F, 0.0F);
		bone70.addChild(bone71);
		setRotationAngle(bone71, 0.0F, 0.0F, 0.1745F);
		bone71.setTextureOffset(76, 0).addBox(-4.6309F, -17.2925F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		bone71.setModelRendererName("bone71");
		this.registerModelRenderer(bone71);

		bone72 = new AnimatedModelRenderer(this);
		bone72.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone71.addChild(bone72);
		setRotationAngle(bone72, 0.0F, 0.0F, 0.1745F);
		bone72.setTextureOffset(76, 0).addBox(-5.2139F, -16.2394F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		bone72.setModelRendererName("bone72");
		this.registerModelRenderer(bone72);

		bone73 = new AnimatedModelRenderer(this);
		bone73.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone72.addChild(bone73);
		setRotationAngle(bone73, 0.0F, 0.0F, -0.6109F);
		bone73.setTextureOffset(76, 0).addBox(-5.5044F, -15.0713F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		bone73.setModelRendererName("bone73");
		this.registerModelRenderer(bone73);

		bone74 = new AnimatedModelRenderer(this);
		bone74.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone73.addChild(bone74);
		setRotationAngle(bone74, 0.0F, 0.0F, 0.0873F);
		bone74.setTextureOffset(76, 0).addBox(-5.4827F, -13.8679F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		bone74.setModelRendererName("bone74");
		this.registerModelRenderer(bone74);

		bone77 = new AnimatedModelRenderer(this);
		bone77.setRotationPoint(3.0F, -3.0F, 0.0F);
		bone73.addChild(bone77);
		setRotationAngle(bone77, 0.0873F, 0.0F, -0.2618F);
		bone77.setTextureOffset(76, 0).addBox(-4.7027F, -13.2737F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone77.setModelRendererName("bone77");
		this.registerModelRenderer(bone77);

		bone78 = new AnimatedModelRenderer(this);
		bone78.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone77.addChild(bone78);
		setRotationAngle(bone78, 0.0F, 0.0F, 0.0873F);
		bone78.setTextureOffset(76, 0).addBox(-4.876F, -12.1317F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone78.setModelRendererName("bone78");
		this.registerModelRenderer(bone78);

		bone75 = new AnimatedModelRenderer(this);
		bone75.setRotationPoint(3.0F, -3.0F, 0.0F);
		bone72.addChild(bone75);
		setRotationAngle(bone75, 0.0873F, 0.0F, -0.2618F);
		bone75.setTextureOffset(76, 0).addBox(-5.5044F, -11.0713F, 3.7958F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone75.setModelRendererName("bone75");
		this.registerModelRenderer(bone75);

		bone76 = new AnimatedModelRenderer(this);
		bone76.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone75.addChild(bone76);
		setRotationAngle(bone76, 0.0F, 0.0F, 0.0873F);
		bone76.setTextureOffset(76, 0).addBox(-5.4827F, -9.8679F, 3.7958F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone76.setModelRendererName("bone76");
		this.registerModelRenderer(bone76);

		bone79 = new AnimatedModelRenderer(this);
		bone79.setRotationPoint(1.0F, -3.0F, 0.0F);
		bone72.addChild(bone79);
		setRotationAngle(bone79, 0.0F, 0.0F, -0.6109F);
		bone79.setTextureOffset(76, 0).addBox(-5.5044F, -15.0713F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		bone79.setModelRendererName("bone79");
		this.registerModelRenderer(bone79);

		bone80 = new AnimatedModelRenderer(this);
		bone80.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone79.addChild(bone80);
		setRotationAngle(bone80, 0.0F, 0.0F, 0.0873F);
		bone80.setTextureOffset(76, 0).addBox(-5.4827F, -13.8679F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		bone80.setModelRendererName("bone80");
		this.registerModelRenderer(bone80);

		bone81 = new AnimatedModelRenderer(this);
		bone81.setRotationPoint(3.0F, -3.0F, 0.0F);
		bone79.addChild(bone81);
		setRotationAngle(bone81, 0.0873F, 0.0F, -0.2618F);
		bone81.setTextureOffset(76, 0).addBox(-4.7027F, -13.2737F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone81.setModelRendererName("bone81");
		this.registerModelRenderer(bone81);

		bone82 = new AnimatedModelRenderer(this);
		bone82.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone81.addChild(bone82);
		setRotationAngle(bone82, 0.0F, 0.0F, 0.0873F);
		bone82.setTextureOffset(76, 0).addBox(-4.876F, -12.1317F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone82.setModelRendererName("bone82");
		this.registerModelRenderer(bone82);

		bone83 = new AnimatedModelRenderer(this);
		bone83.setRotationPoint(-5.4497F, -0.0872F, 0.0F);
		bone68.addChild(bone83);
		setRotationAngle(bone83, 0.0873F, 0.0F, -0.0524F);
		bone83.setTextureOffset(76, 0).addBox(1.4849F, -19.9054F, 3.7958F, 1.0F, 16.0F, 1.0F, 0.0F, true);
		bone83.setModelRendererName("bone83");
		this.registerModelRenderer(bone83);

		bone84 = new AnimatedModelRenderer(this);
		bone84.setRotationPoint(0.9848F, 0.1736F, 0.0F);
		bone83.addChild(bone84);
		setRotationAngle(bone84, 0.0F, 0.0F, -0.1745F);
		bone84.setTextureOffset(76, 0).addBox(2.47F, -18.3954F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, true);
		bone84.setModelRendererName("bone84");
		this.registerModelRenderer(bone84);

		bone85 = new AnimatedModelRenderer(this);
		bone85.setRotationPoint(0.2165F, 0.125F, 0.0F);
		bone84.addChild(bone85);
		setRotationAngle(bone85, 0.0F, 0.0F, -0.1745F);
		bone85.setTextureOffset(76, 0).addBox(3.6309F, -17.2925F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, true);
		bone85.setModelRendererName("bone85");
		this.registerModelRenderer(bone85);

		bone86 = new AnimatedModelRenderer(this);
		bone86.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone85.addChild(bone86);
		setRotationAngle(bone86, 0.0F, 0.0F, -0.1745F);
		bone86.setTextureOffset(76, 0).addBox(4.2139F, -16.2394F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, true);
		bone86.setModelRendererName("bone86");
		this.registerModelRenderer(bone86);

		bone87 = new AnimatedModelRenderer(this);
		bone87.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone86.addChild(bone87);
		setRotationAngle(bone87, 0.0F, 0.0F, 0.6109F);
		bone87.setTextureOffset(76, 0).addBox(4.5044F, -15.0713F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, true);
		bone87.setModelRendererName("bone87");
		this.registerModelRenderer(bone87);

		bone88 = new AnimatedModelRenderer(this);
		bone88.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone87.addChild(bone88);
		setRotationAngle(bone88, 0.0F, 0.0F, -0.0873F);
		bone88.setTextureOffset(76, 0).addBox(4.4827F, -13.8679F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, true);
		bone88.setModelRendererName("bone88");
		this.registerModelRenderer(bone88);

		bone89 = new AnimatedModelRenderer(this);
		bone89.setRotationPoint(-3.0F, -3.0F, 0.0F);
		bone87.addChild(bone89);
		setRotationAngle(bone89, 0.0873F, 0.0F, 0.2618F);
		bone89.setTextureOffset(76, 0).addBox(3.7027F, -13.2737F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone89.setModelRendererName("bone89");
		this.registerModelRenderer(bone89);

		bone90 = new AnimatedModelRenderer(this);
		bone90.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone89.addChild(bone90);
		setRotationAngle(bone90, 0.0F, 0.0F, -0.0873F);
		bone90.setTextureOffset(76, 0).addBox(3.876F, -12.1317F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone90.setModelRendererName("bone90");
		this.registerModelRenderer(bone90);

		bone91 = new AnimatedModelRenderer(this);
		bone91.setRotationPoint(-3.0F, -3.0F, 0.0F);
		bone86.addChild(bone91);
		setRotationAngle(bone91, 0.0873F, 0.0F, 0.2618F);
		bone91.setTextureOffset(76, 0).addBox(4.5044F, -11.0713F, 3.7958F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone91.setModelRendererName("bone91");
		this.registerModelRenderer(bone91);

		bone92 = new AnimatedModelRenderer(this);
		bone92.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone91.addChild(bone92);
		setRotationAngle(bone92, 0.0F, 0.0F, -0.0873F);
		bone92.setTextureOffset(76, 0).addBox(4.4827F, -9.8679F, 3.7958F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone92.setModelRendererName("bone92");
		this.registerModelRenderer(bone92);

		bone93 = new AnimatedModelRenderer(this);
		bone93.setRotationPoint(-1.0F, -3.0F, 0.0F);
		bone86.addChild(bone93);
		setRotationAngle(bone93, 0.0F, 0.0F, 0.6109F);
		bone93.setTextureOffset(76, 0).addBox(4.5044F, -15.0713F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, true);
		bone93.setModelRendererName("bone93");
		this.registerModelRenderer(bone93);

		bone94 = new AnimatedModelRenderer(this);
		bone94.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone93.addChild(bone94);
		setRotationAngle(bone94, 0.0F, 0.0F, -0.0873F);
		bone94.setTextureOffset(76, 0).addBox(4.4827F, -13.8679F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, true);
		bone94.setModelRendererName("bone94");
		this.registerModelRenderer(bone94);

		bone95 = new AnimatedModelRenderer(this);
		bone95.setRotationPoint(-3.0F, -3.0F, 0.0F);
		bone93.addChild(bone95);
		setRotationAngle(bone95, 0.0873F, 0.0F, 0.2618F);
		bone95.setTextureOffset(76, 0).addBox(3.7027F, -13.2737F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone95.setModelRendererName("bone95");
		this.registerModelRenderer(bone95);

		bone96 = new AnimatedModelRenderer(this);
		bone96.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone95.addChild(bone96);
		setRotationAngle(bone96, 0.0F, 0.0F, -0.0873F);
		bone96.setTextureOffset(76, 0).addBox(3.876F, -12.1317F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone96.setModelRendererName("bone96");
		this.registerModelRenderer(bone96);

		bone97 = new AnimatedModelRenderer(this);
		bone97.setRotationPoint(5.4805F, -2.0613F, -4.4097F);
		fur_left5.addChild(bone97);
		setRotationAngle(bone97, 0.0F, 0.0F, -0.1745F);
		bone97.setTextureOffset(76, 0).addBox(-1.4446F, -19.5893F, 3.8394F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		bone97.setModelRendererName("bone97");
		this.registerModelRenderer(bone97);

		bone98 = new AnimatedModelRenderer(this);
		bone98.setRotationPoint(0.9962F, -0.0872F, 0.0F);
		bone97.addChild(bone98);
		setRotationAngle(bone98, 0.0F, 0.0F, 0.0524F);
		bone98.setTextureOffset(76, 0).addBox(-2.4849F, -19.9054F, 3.7958F, 1.0F, 16.0F, 1.0F, 0.0F, false);
		bone98.setModelRendererName("bone98");
		this.registerModelRenderer(bone98);

		bone99 = new AnimatedModelRenderer(this);
		bone99.setRotationPoint(-0.9848F, 0.1736F, 0.0F);
		bone98.addChild(bone99);
		setRotationAngle(bone99, 0.0F, 0.0F, 0.1745F);
		bone99.setTextureOffset(76, 0).addBox(-3.47F, -18.3954F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		bone99.setModelRendererName("bone99");
		this.registerModelRenderer(bone99);

		bone100 = new AnimatedModelRenderer(this);
		bone100.setRotationPoint(-0.2165F, 0.125F, 0.0F);
		bone99.addChild(bone100);
		setRotationAngle(bone100, 0.0F, 0.0F, 0.1745F);
		bone100.setTextureOffset(76, 0).addBox(-4.6309F, -17.2925F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		bone100.setModelRendererName("bone100");
		this.registerModelRenderer(bone100);

		bone101 = new AnimatedModelRenderer(this);
		bone101.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone100.addChild(bone101);
		setRotationAngle(bone101, 0.0F, 0.0F, 0.1745F);
		bone101.setTextureOffset(76, 0).addBox(-5.2139F, -16.2394F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		bone101.setModelRendererName("bone101");
		this.registerModelRenderer(bone101);

		bone102 = new AnimatedModelRenderer(this);
		bone102.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone101.addChild(bone102);
		setRotationAngle(bone102, 0.0F, 0.0F, -0.6109F);
		bone102.setTextureOffset(76, 0).addBox(-5.5044F, -15.0713F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		bone102.setModelRendererName("bone102");
		this.registerModelRenderer(bone102);

		bone103 = new AnimatedModelRenderer(this);
		bone103.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone102.addChild(bone103);
		setRotationAngle(bone103, 0.0F, 0.0F, 0.0873F);
		bone103.setTextureOffset(76, 0).addBox(-5.4827F, -13.8679F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		bone103.setModelRendererName("bone103");
		this.registerModelRenderer(bone103);

		bone104 = new AnimatedModelRenderer(this);
		bone104.setRotationPoint(3.0F, -3.0F, 0.0F);
		bone102.addChild(bone104);
		setRotationAngle(bone104, 0.0873F, 0.0F, -0.2618F);
		bone104.setTextureOffset(76, 0).addBox(-4.7027F, -13.2737F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone104.setModelRendererName("bone104");
		this.registerModelRenderer(bone104);

		bone105 = new AnimatedModelRenderer(this);
		bone105.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone104.addChild(bone105);
		setRotationAngle(bone105, 0.0F, 0.0F, 0.0873F);
		bone105.setTextureOffset(76, 0).addBox(-4.876F, -12.1317F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone105.setModelRendererName("bone105");
		this.registerModelRenderer(bone105);

		bone106 = new AnimatedModelRenderer(this);
		bone106.setRotationPoint(3.0F, -3.0F, 0.0F);
		bone101.addChild(bone106);
		setRotationAngle(bone106, 0.0873F, 0.0F, -0.2618F);
		bone106.setTextureOffset(76, 0).addBox(-5.5044F, -11.0713F, 3.7958F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone106.setModelRendererName("bone106");
		this.registerModelRenderer(bone106);

		bone107 = new AnimatedModelRenderer(this);
		bone107.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone106.addChild(bone107);
		setRotationAngle(bone107, 0.0F, 0.0F, 0.0873F);
		bone107.setTextureOffset(76, 0).addBox(-5.4827F, -9.8679F, 3.7958F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone107.setModelRendererName("bone107");
		this.registerModelRenderer(bone107);

		bone108 = new AnimatedModelRenderer(this);
		bone108.setRotationPoint(1.0F, -3.0F, 0.0F);
		bone101.addChild(bone108);
		setRotationAngle(bone108, 0.0F, 0.0F, -0.6109F);
		bone108.setTextureOffset(76, 0).addBox(-5.5044F, -15.0713F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		bone108.setModelRendererName("bone108");
		this.registerModelRenderer(bone108);

		bone109 = new AnimatedModelRenderer(this);
		bone109.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone108.addChild(bone109);
		setRotationAngle(bone109, 0.0F, 0.0F, 0.0873F);
		bone109.setTextureOffset(76, 0).addBox(-5.4827F, -13.8679F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		bone109.setModelRendererName("bone109");
		this.registerModelRenderer(bone109);

		bone110 = new AnimatedModelRenderer(this);
		bone110.setRotationPoint(3.0F, -3.0F, 0.0F);
		bone108.addChild(bone110);
		setRotationAngle(bone110, 0.0873F, 0.0F, -0.2618F);
		bone110.setTextureOffset(76, 0).addBox(-4.7027F, -13.2737F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone110.setModelRendererName("bone110");
		this.registerModelRenderer(bone110);

		bone111 = new AnimatedModelRenderer(this);
		bone111.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone110.addChild(bone111);
		setRotationAngle(bone111, 0.0F, 0.0F, 0.0873F);
		bone111.setTextureOffset(76, 0).addBox(-4.876F, -12.1317F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, false);
		bone111.setModelRendererName("bone111");
		this.registerModelRenderer(bone111);

		bone112 = new AnimatedModelRenderer(this);
		bone112.setRotationPoint(-5.4497F, -0.0872F, 0.0F);
		bone97.addChild(bone112);
		setRotationAngle(bone112, 0.0873F, 0.0F, -0.0524F);
		bone112.setTextureOffset(76, 0).addBox(1.4849F, -19.9054F, 3.7958F, 1.0F, 16.0F, 1.0F, 0.0F, true);
		bone112.setModelRendererName("bone112");
		this.registerModelRenderer(bone112);

		bone113 = new AnimatedModelRenderer(this);
		bone113.setRotationPoint(0.9848F, 0.1736F, 0.0F);
		bone112.addChild(bone113);
		setRotationAngle(bone113, 0.0F, 0.0F, -0.1745F);
		bone113.setTextureOffset(76, 0).addBox(2.47F, -18.3954F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, true);
		bone113.setModelRendererName("bone113");
		this.registerModelRenderer(bone113);

		bone114 = new AnimatedModelRenderer(this);
		bone114.setRotationPoint(0.2165F, 0.125F, 0.0F);
		bone113.addChild(bone114);
		setRotationAngle(bone114, 0.0F, 0.0F, -0.1745F);
		bone114.setTextureOffset(76, 0).addBox(3.6309F, -17.2925F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, true);
		bone114.setModelRendererName("bone114");
		this.registerModelRenderer(bone114);

		bone115 = new AnimatedModelRenderer(this);
		bone115.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone114.addChild(bone115);
		setRotationAngle(bone115, 0.0F, 0.0F, -0.1745F);
		bone115.setTextureOffset(76, 0).addBox(4.2139F, -16.2394F, 3.7958F, 1.0F, 15.0F, 1.0F, 0.0F, true);
		bone115.setModelRendererName("bone115");
		this.registerModelRenderer(bone115);

		bone116 = new AnimatedModelRenderer(this);
		bone116.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone115.addChild(bone116);
		setRotationAngle(bone116, 0.0F, 0.0F, 0.6109F);
		bone116.setTextureOffset(76, 0).addBox(4.5044F, -15.0713F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, true);
		bone116.setModelRendererName("bone116");
		this.registerModelRenderer(bone116);

		bone117 = new AnimatedModelRenderer(this);
		bone117.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone116.addChild(bone117);
		setRotationAngle(bone117, 0.0F, 0.0F, -0.0873F);
		bone117.setTextureOffset(76, 0).addBox(4.4827F, -13.8679F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, true);
		bone117.setModelRendererName("bone117");
		this.registerModelRenderer(bone117);

		bone118 = new AnimatedModelRenderer(this);
		bone118.setRotationPoint(-3.0F, -3.0F, 0.0F);
		bone116.addChild(bone118);
		setRotationAngle(bone118, 0.0873F, 0.0F, 0.2618F);
		bone118.setTextureOffset(76, 0).addBox(3.7027F, -13.2737F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone118.setModelRendererName("bone118");
		this.registerModelRenderer(bone118);

		bone119 = new AnimatedModelRenderer(this);
		bone119.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone118.addChild(bone119);
		setRotationAngle(bone119, 0.0F, 0.0F, -0.0873F);
		bone119.setTextureOffset(76, 0).addBox(3.876F, -12.1317F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone119.setModelRendererName("bone119");
		this.registerModelRenderer(bone119);

		bone120 = new AnimatedModelRenderer(this);
		bone120.setRotationPoint(-3.0F, -3.0F, 0.0F);
		bone115.addChild(bone120);
		setRotationAngle(bone120, 0.0873F, 0.0F, 0.2618F);
		bone120.setTextureOffset(76, 0).addBox(4.5044F, -11.0713F, 3.7958F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone120.setModelRendererName("bone120");
		this.registerModelRenderer(bone120);

		bone121 = new AnimatedModelRenderer(this);
		bone121.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone120.addChild(bone121);
		setRotationAngle(bone121, 0.0F, 0.0F, -0.0873F);
		bone121.setTextureOffset(76, 0).addBox(4.4827F, -9.8679F, 3.7958F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone121.setModelRendererName("bone121");
		this.registerModelRenderer(bone121);

		bone122 = new AnimatedModelRenderer(this);
		bone122.setRotationPoint(-1.0F, -3.0F, 0.0F);
		bone115.addChild(bone122);
		setRotationAngle(bone122, 0.0F, 0.0F, 0.6109F);
		bone122.setTextureOffset(76, 0).addBox(4.5044F, -15.0713F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, true);
		bone122.setModelRendererName("bone122");
		this.registerModelRenderer(bone122);

		bone123 = new AnimatedModelRenderer(this);
		bone123.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone122.addChild(bone123);
		setRotationAngle(bone123, 0.0F, 0.0F, -0.0873F);
		bone123.setTextureOffset(76, 0).addBox(4.4827F, -13.8679F, 3.7958F, 1.0F, 9.0F, 1.0F, 0.0F, true);
		bone123.setModelRendererName("bone123");
		this.registerModelRenderer(bone123);

		bone124 = new AnimatedModelRenderer(this);
		bone124.setRotationPoint(-3.0F, -3.0F, 0.0F);
		bone122.addChild(bone124);
		setRotationAngle(bone124, 0.0873F, 0.0F, 0.2618F);
		bone124.setTextureOffset(76, 0).addBox(3.7027F, -13.2737F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone124.setModelRendererName("bone124");
		this.registerModelRenderer(bone124);

		bone125 = new AnimatedModelRenderer(this);
		bone125.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone124.addChild(bone125);
		setRotationAngle(bone125, 0.0F, 0.0F, -0.0873F);
		bone125.setTextureOffset(76, 0).addBox(3.876F, -12.1317F, 3.2932F, 1.0F, 11.0F, 1.0F, 0.0F, true);
		bone125.setModelRendererName("bone125");
		this.registerModelRenderer(bone125);

		fur4 = new AnimatedModelRenderer(this);
		fur4.setRotationPoint(0.0F, -18.1904F, 2.4748F);
		decoration.addChild(fur4);
		setRotationAngle(fur4, -1.4835F, -0.0873F, 0.0F);
		
		fur4.setModelRendererName("fur4");
		this.registerModelRenderer(fur4);

		fur_right4 = new AnimatedModelRenderer(this);
		fur_right4.setRotationPoint(0.2267F, 6.4791F, -3.4339F);
		fur4.addChild(fur_right4);
		setRotationAngle(fur_right4, -0.4363F, 0.0F, -0.1222F);
		fur_right4.setTextureOffset(76, 0).addBox(-0.1299F, -13.126F, -0.4003F, 1.0F, 7.0F, 1.0F, 0.0F, true);
		fur_right4.setModelRendererName("fur_right4");
		this.registerModelRenderer(fur_right4);

		bone47 = new AnimatedModelRenderer(this);
		bone47.setRotationPoint(-1.5F, -1.8333F, -4.25F);
		fur_right4.addChild(bone47);
		bone47.setTextureOffset(76, 0).addBox(0.6201F, -10.5417F, 3.8715F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone47.setModelRendererName("bone47");
		this.registerModelRenderer(bone47);

		bone48 = new AnimatedModelRenderer(this);
		bone48.setRotationPoint(-0.9962F, -0.0872F, 0.0F);
		bone47.addChild(bone48);
		setRotationAngle(bone48, 0.0F, 0.0F, -0.1396F);
		bone48.setTextureOffset(76, 0).addBox(1.652F, -10.8338F, 3.8279F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone48.setModelRendererName("bone48");
		this.registerModelRenderer(bone48);

		bone49 = new AnimatedModelRenderer(this);
		bone49.setRotationPoint(0.9848F, 0.1736F, 0.0F);
		bone48.addChild(bone49);
		setRotationAngle(bone49, 0.0F, 0.0F, -0.2618F);
		bone49.setTextureOffset(76, 0).addBox(2.2683F, -9.4904F, 3.8279F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone49.setModelRendererName("bone49");
		this.registerModelRenderer(bone49);

		bone50 = new AnimatedModelRenderer(this);
		bone50.setRotationPoint(0.2165F, 0.125F, 0.0F);
		bone49.addChild(bone50);
		setRotationAngle(bone50, 0.0F, 0.0F, -0.3491F);
		bone50.setTextureOffset(76, 0).addBox(3.4739F, -8.4507F, 3.8279F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone50.setModelRendererName("bone50");
		this.registerModelRenderer(bone50);

		bone51 = new AnimatedModelRenderer(this);
		bone51.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone50.addChild(bone51);
		setRotationAngle(bone51, 0.0F, 0.0F, -0.0873F);
		bone51.setTextureOffset(76, 0).addBox(3.713F, -8.1302F, 3.8279F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone51.setModelRendererName("bone51");
		this.registerModelRenderer(bone51);

		bone52 = new AnimatedModelRenderer(this);
		bone52.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone51.addChild(bone52);
		setRotationAngle(bone52, 0.0F, 0.0F, -0.2618F);
		bone52.setTextureOffset(76, 0).addBox(4.2511F, -7.0614F, 3.8279F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone52.setModelRendererName("bone52");
		this.registerModelRenderer(bone52);

		bone53 = new AnimatedModelRenderer(this);
		bone53.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone52.addChild(bone53);
		setRotationAngle(bone53, 0.0F, 0.0F, -0.2618F);
		bone53.setTextureOffset(76, 0).addBox(4.4943F, -5.8898F, 3.8279F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone53.setModelRendererName("bone53");
		this.registerModelRenderer(bone53);

		fur_left4 = new AnimatedModelRenderer(this);
		fur_left4.setRotationPoint(-1.2733F, 6.4791F, -3.4339F);
		fur4.addChild(fur_left4);
		setRotationAngle(fur_left4, -0.4363F, 0.0F, 0.1396F);
		fur_left4.setTextureOffset(76, 0).addBox(-0.2929F, -13.1948F, -0.4324F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		fur_left4.setModelRendererName("fur_left4");
		this.registerModelRenderer(fur_left4);

		bone54 = new AnimatedModelRenderer(this);
		bone54.setRotationPoint(1.5F, -1.8333F, -4.25F);
		fur_left4.addChild(bone54);
		setRotationAngle(bone54, 0.0F, 0.0F, 0.0873F);
		bone54.setTextureOffset(76, 0).addBox(-1.4446F, -10.5893F, 3.8394F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone54.setModelRendererName("bone54");
		this.registerModelRenderer(bone54);

		bone55 = new AnimatedModelRenderer(this);
		bone55.setRotationPoint(0.9962F, -0.0872F, 0.0F);
		bone54.addChild(bone55);
		setRotationAngle(bone55, 0.0F, 0.0F, 0.1396F);
		bone55.setTextureOffset(76, 0).addBox(-2.4849F, -10.9054F, 3.7958F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bone55.setModelRendererName("bone55");
		this.registerModelRenderer(bone55);

		bone56 = new AnimatedModelRenderer(this);
		bone56.setRotationPoint(-0.9848F, 0.1736F, 0.0F);
		bone55.addChild(bone56);
		setRotationAngle(bone56, 0.0F, 0.0F, 0.3491F);
		bone56.setTextureOffset(76, 0).addBox(-3.47F, -9.3954F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone56.setModelRendererName("bone56");
		this.registerModelRenderer(bone56);

		bone57 = new AnimatedModelRenderer(this);
		bone57.setRotationPoint(-0.2165F, 0.125F, 0.0F);
		bone56.addChild(bone57);
		setRotationAngle(bone57, 0.0F, 0.0F, 0.3491F);
		bone57.setTextureOffset(76, 0).addBox(-4.6309F, -8.2925F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone57.setModelRendererName("bone57");
		this.registerModelRenderer(bone57);

		bone58 = new AnimatedModelRenderer(this);
		bone58.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone57.addChild(bone58);
		setRotationAngle(bone58, 0.0F, 0.0F, 0.2618F);
		bone58.setTextureOffset(76, 0).addBox(-5.2139F, -7.2394F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone58.setModelRendererName("bone58");
		this.registerModelRenderer(bone58);

		bone59 = new AnimatedModelRenderer(this);
		bone59.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone58.addChild(bone59);
		setRotationAngle(bone59, 0.0F, 0.0F, 0.2618F);
		bone59.setTextureOffset(76, 0).addBox(-5.5044F, -6.0713F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone59.setModelRendererName("bone59");
		this.registerModelRenderer(bone59);

		bone60 = new AnimatedModelRenderer(this);
		bone60.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone59.addChild(bone60);
		setRotationAngle(bone60, 0.0F, 0.0F, 0.2618F);
		bone60.setTextureOffset(76, 0).addBox(-5.4827F, -4.8679F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone60.setModelRendererName("bone60");
		this.registerModelRenderer(bone60);

		fur3 = new AnimatedModelRenderer(this);
		fur3.setRotationPoint(0.0F, -17.9404F, -4.5252F);
		decoration.addChild(fur3);
		setRotationAngle(fur3, -0.7854F, -0.0873F, -0.0873F);
		
		fur3.setModelRendererName("fur3");
		this.registerModelRenderer(fur3);

		fur_right3 = new AnimatedModelRenderer(this);
		fur_right3.setRotationPoint(0.75F, 2.2472F, 2.2068F);
		fur3.addChild(fur_right3);
		setRotationAngle(fur_right3, -0.4363F, 0.0F, -0.1222F);
		fur_right3.setTextureOffset(76, 0).addBox(-0.1166F, -12.2273F, -2.4478F, 1.0F, 7.0F, 1.0F, 0.0F, true);
		fur_right3.setModelRendererName("fur_right3");
		this.registerModelRenderer(fur_right3);

		bone33 = new AnimatedModelRenderer(this);
		bone33.setRotationPoint(-1.5F, -1.8333F, -4.25F);
		fur_right3.addChild(bone33);
		bone33.setTextureOffset(76, 0).addBox(0.6334F, -9.6431F, 1.824F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone33.setModelRendererName("bone33");
		this.registerModelRenderer(bone33);

		bone34 = new AnimatedModelRenderer(this);
		bone34.setRotationPoint(-0.9962F, -0.0872F, 0.0F);
		bone33.addChild(bone34);
		setRotationAngle(bone34, 0.0F, 0.0F, -0.1396F);
		bone34.setTextureOffset(76, 0).addBox(1.5401F, -9.9421F, 1.7804F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone34.setModelRendererName("bone34");
		this.registerModelRenderer(bone34);

		bone35 = new AnimatedModelRenderer(this);
		bone35.setRotationPoint(0.9848F, 0.1736F, 0.0F);
		bone34.addChild(bone35);
		setRotationAngle(bone35, 0.0F, 0.0F, -0.2618F);
		bone35.setTextureOffset(76, 0).addBox(1.9294F, -8.658F, 1.7804F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone35.setModelRendererName("bone35");
		this.registerModelRenderer(bone35);

		bone36 = new AnimatedModelRenderer(this);
		bone36.setRotationPoint(0.2165F, 0.125F, 0.0F);
		bone35.addChild(bone36);
		setRotationAngle(bone36, 0.0F, 0.0F, -0.3491F);
		bone36.setTextureOffset(76, 0).addBox(2.8707F, -7.7845F, 1.7804F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone36.setModelRendererName("bone36");
		this.registerModelRenderer(bone36);

		bone37 = new AnimatedModelRenderer(this);
		bone37.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone36.addChild(bone37);
		setRotationAngle(bone37, 0.0F, 0.0F, -0.0873F);
		bone37.setTextureOffset(76, 0).addBox(3.0541F, -7.5191F, 1.7804F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone37.setModelRendererName("bone37");
		this.registerModelRenderer(bone37);

		bone38 = new AnimatedModelRenderer(this);
		bone38.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone37.addChild(bone38);
		setRotationAngle(bone38, 0.0F, 0.0F, -0.2618F);
		bone38.setTextureOffset(76, 0).addBox(3.4565F, -6.6416F, 1.7804F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone38.setModelRendererName("bone38");
		this.registerModelRenderer(bone38);

		bone39 = new AnimatedModelRenderer(this);
		bone39.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone38.addChild(bone39);
		setRotationAngle(bone39, 0.0F, 0.0F, -0.2618F);
		bone39.setTextureOffset(76, 0).addBox(3.6181F, -5.69F, 1.7804F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone39.setModelRendererName("bone39");
		this.registerModelRenderer(bone39);

		fur_left3 = new AnimatedModelRenderer(this);
		fur_left3.setRotationPoint(-0.75F, 2.2472F, 2.2068F);
		fur3.addChild(fur_left3);
		setRotationAngle(fur_left3, -0.4363F, 0.0F, 0.1396F);
		fur_left3.setTextureOffset(76, 0).addBox(-0.2933F, -12.2978F, -2.4806F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		fur_left3.setModelRendererName("fur_left3");
		this.registerModelRenderer(fur_left3);

		bone40 = new AnimatedModelRenderer(this);
		bone40.setRotationPoint(1.5F, -1.8333F, -4.25F);
		fur_left3.addChild(bone40);
		setRotationAngle(bone40, 0.0F, 0.0F, 0.0873F);
		bone40.setTextureOffset(76, 0).addBox(-1.3667F, -9.6956F, 1.7912F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone40.setModelRendererName("bone40");
		this.registerModelRenderer(bone40);

		bone41 = new AnimatedModelRenderer(this);
		bone41.setRotationPoint(0.9962F, -0.0872F, 0.0F);
		bone40.addChild(bone41);
		setRotationAngle(bone41, 0.0F, 0.0F, 0.1396F);
		bone41.setTextureOffset(76, 0).addBox(-2.2834F, -10.0312F, 1.7476F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bone41.setModelRendererName("bone41");
		this.registerModelRenderer(bone41);

		bone42 = new AnimatedModelRenderer(this);
		bone42.setRotationPoint(-0.9848F, 0.1736F, 0.0F);
		bone41.addChild(bone42);
		setRotationAngle(bone42, 0.0F, 0.0F, 0.3491F);
		bone42.setTextureOffset(76, 0).addBox(-2.9817F, -8.6429F, 1.7476F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone42.setModelRendererName("bone42");
		this.registerModelRenderer(bone42);

		bone43 = new AnimatedModelRenderer(this);
		bone43.setRotationPoint(-0.2165F, 0.125F, 0.0F);
		bone42.addChild(bone43);
		setRotationAngle(bone43, 0.0F, 0.0F, 0.3491F);
		bone43.setTextureOffset(76, 0).addBox(-3.9147F, -7.7523F, 1.7476F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone43.setModelRendererName("bone43");
		this.registerModelRenderer(bone43);

		bone44 = new AnimatedModelRenderer(this);
		bone44.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone43.addChild(bone44);
		setRotationAngle(bone44, 0.0F, 0.0F, 0.2618F);
		bone44.setTextureOffset(76, 0).addBox(-4.3822F, -6.903F, 1.7476F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone44.setModelRendererName("bone44");
		this.registerModelRenderer(bone44);

		bone45 = new AnimatedModelRenderer(this);
		bone45.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone44.addChild(bone45);
		setRotationAngle(bone45, 0.0F, 0.0F, 0.2618F);
		bone45.setTextureOffset(76, 0).addBox(-4.614F, -5.9617F, 1.7476F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone45.setModelRendererName("bone45");
		this.registerModelRenderer(bone45);

		bone46 = new AnimatedModelRenderer(this);
		bone46.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone45.addChild(bone46);
		setRotationAngle(bone46, 0.0F, 0.0F, 0.2618F);
		bone46.setTextureOffset(76, 0).addBox(-4.5943F, -4.9924F, 1.7476F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone46.setModelRendererName("bone46");
		this.registerModelRenderer(bone46);

		fur2 = new AnimatedModelRenderer(this);
		fur2.setRotationPoint(0.0F, -17.9404F, -4.5252F);
		decoration.addChild(fur2);
		setRotationAngle(fur2, -0.7854F, -0.0873F, -0.0873F);
		
		fur2.setModelRendererName("fur2");
		this.registerModelRenderer(fur2);

		fur_right2 = new AnimatedModelRenderer(this);
		fur_right2.setRotationPoint(0.75F, 2.2472F, 2.2068F);
		fur2.addChild(fur_right2);
		setRotationAngle(fur_right2, -0.4363F, 0.0F, -0.1222F);
		fur_right2.setTextureOffset(76, 0).addBox(-0.75F, -8.5843F, -4.2718F, 1.0F, 7.0F, 1.0F, 0.0F, true);
		fur_right2.setModelRendererName("fur_right2");
		this.registerModelRenderer(fur_right2);

		bone19 = new AnimatedModelRenderer(this);
		bone19.setRotationPoint(-1.5F, -1.8333F, -4.25F);
		fur_right2.addChild(bone19);
		bone19.setTextureOffset(76, 0).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone19.setModelRendererName("bone19");
		this.registerModelRenderer(bone19);

		bone20 = new AnimatedModelRenderer(this);
		bone20.setRotationPoint(-0.9962F, -0.0872F, 0.0F);
		bone19.addChild(bone20);
		setRotationAngle(bone20, 0.0F, 0.0F, -0.1396F);
		bone20.setTextureOffset(76, 0).addBox(0.4059F, -6.4226F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone20.setModelRendererName("bone20");
		this.registerModelRenderer(bone20);

		bone21 = new AnimatedModelRenderer(this);
		bone21.setRotationPoint(0.9848F, 0.1736F, 0.0F);
		bone20.addChild(bone21);
		setRotationAngle(bone21, 0.0F, 0.0F, -0.2618F);
		bone21.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone21.setModelRendererName("bone21");
		this.registerModelRenderer(bone21);

		bone22 = new AnimatedModelRenderer(this);
		bone22.setRotationPoint(0.2165F, 0.125F, 0.0F);
		bone21.addChild(bone22);
		setRotationAngle(bone22, 0.0F, 0.0F, -0.3491F);
		bone22.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone22.setModelRendererName("bone22");
		this.registerModelRenderer(bone22);

		bone23 = new AnimatedModelRenderer(this);
		bone23.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone22.addChild(bone23);
		setRotationAngle(bone23, 0.0F, 0.0F, -0.0873F);
		bone23.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone23.setModelRendererName("bone23");
		this.registerModelRenderer(bone23);

		bone24 = new AnimatedModelRenderer(this);
		bone24.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone23.addChild(bone24);
		setRotationAngle(bone24, 0.0F, 0.0F, -0.2618F);
		bone24.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone24.setModelRendererName("bone24");
		this.registerModelRenderer(bone24);

		bone25 = new AnimatedModelRenderer(this);
		bone25.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone24.addChild(bone25);
		setRotationAngle(bone25, 0.0F, 0.0F, -0.2618F);
		bone25.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone25.setModelRendererName("bone25");
		this.registerModelRenderer(bone25);

		fur_left2 = new AnimatedModelRenderer(this);
		fur_left2.setRotationPoint(-0.75F, 2.2472F, 2.2068F);
		fur2.addChild(fur_left2);
		setRotationAngle(fur_left2, -0.4363F, 0.0F, 0.1396F);
		fur_left2.setTextureOffset(76, 0).addBox(-0.25F, -8.5843F, -4.2718F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		fur_left2.setModelRendererName("fur_left2");
		this.registerModelRenderer(fur_left2);

		bone26 = new AnimatedModelRenderer(this);
		bone26.setRotationPoint(1.5F, -1.8333F, -4.25F);
		fur_left2.addChild(bone26);
		setRotationAngle(bone26, 0.0F, 0.0F, 0.0873F);
		bone26.setTextureOffset(76, 0).addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone26.setModelRendererName("bone26");
		this.registerModelRenderer(bone26);

		bone27 = new AnimatedModelRenderer(this);
		bone27.setRotationPoint(0.9962F, -0.0872F, 0.0F);
		bone26.addChild(bone27);
		setRotationAngle(bone27, 0.0F, 0.0F, 0.1396F);
		bone27.setTextureOffset(76, 0).addBox(-1.4059F, -6.4226F, -0.0436F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bone27.setModelRendererName("bone27");
		this.registerModelRenderer(bone27);

		bone28 = new AnimatedModelRenderer(this);
		bone28.setRotationPoint(-0.9848F, 0.1736F, 0.0F);
		bone27.addChild(bone28);
		setRotationAngle(bone28, 0.0F, 0.0F, 0.3491F);
		bone28.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone28.setModelRendererName("bone28");
		this.registerModelRenderer(bone28);

		bone29 = new AnimatedModelRenderer(this);
		bone29.setRotationPoint(-0.2165F, 0.125F, 0.0F);
		bone28.addChild(bone29);
		setRotationAngle(bone29, 0.0F, 0.0F, 0.3491F);
		bone29.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone29.setModelRendererName("bone29");
		this.registerModelRenderer(bone29);

		bone30 = new AnimatedModelRenderer(this);
		bone30.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone29.addChild(bone30);
		setRotationAngle(bone30, 0.0F, 0.0F, 0.2618F);
		bone30.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone30.setModelRendererName("bone30");
		this.registerModelRenderer(bone30);

		bone31 = new AnimatedModelRenderer(this);
		bone31.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone30.addChild(bone31);
		setRotationAngle(bone31, 0.0F, 0.0F, 0.2618F);
		bone31.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone31.setModelRendererName("bone31");
		this.registerModelRenderer(bone31);

		bone32 = new AnimatedModelRenderer(this);
		bone32.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone31.addChild(bone32);
		setRotationAngle(bone32, 0.0F, 0.0F, 0.2618F);
		bone32.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone32.setModelRendererName("bone32");
		this.registerModelRenderer(bone32);

		sideburn_left = new AnimatedModelRenderer(this);
		sideburn_left.setRotationPoint(4.7949F, -15.8151F, -5.5684F);
		decoration.addChild(sideburn_left);
		setRotationAngle(sideburn_left, -1.0472F, 0.0F, 1.3963F);
		sideburn_left.setTextureOffset(76, 0).addBox(-0.9229F, -3.5521F, -0.0436F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		sideburn_left.setModelRendererName("sideburn_left");
		this.registerModelRenderer(sideburn_left);

		bone15 = new AnimatedModelRenderer(this);
		bone15.setRotationPoint(0.0F, 0.0F, 0.0F);
		sideburn_left.addChild(bone15);
		setRotationAngle(bone15, 0.0F, 0.0F, 0.4363F);
		bone15.setTextureOffset(76, 0).addBox(-0.9229F, -3.5521F, -0.0436F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone15.setModelRendererName("bone15");
		this.registerModelRenderer(bone15);

		bone18 = new AnimatedModelRenderer(this);
		bone18.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone15.addChild(bone18);
		setRotationAngle(bone18, 0.0F, 0.0F, 0.4363F);
		bone18.setTextureOffset(76, 0).addBox(-0.9229F, -3.5521F, -0.0436F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		bone18.setModelRendererName("bone18");
		this.registerModelRenderer(bone18);

		sideburn_right = new AnimatedModelRenderer(this);
		sideburn_right.setRotationPoint(-4.7949F, -15.8151F, -5.5684F);
		decoration.addChild(sideburn_right);
		setRotationAngle(sideburn_right, -1.0472F, 0.0F, -1.3963F);
		sideburn_right.setTextureOffset(76, 0).addBox(-0.0771F, -3.5521F, -0.0436F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		sideburn_right.setModelRendererName("sideburn_right");
		this.registerModelRenderer(sideburn_right);

		bone16 = new AnimatedModelRenderer(this);
		bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
		sideburn_right.addChild(bone16);
		setRotationAngle(bone16, 0.0F, 0.0F, -0.4363F);
		bone16.setTextureOffset(76, 0).addBox(-0.0771F, -3.5521F, -0.0436F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		bone16.setModelRendererName("bone16");
		this.registerModelRenderer(bone16);

		bone17 = new AnimatedModelRenderer(this);
		bone17.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone16.addChild(bone17);
		setRotationAngle(bone17, 0.0F, 0.0F, -0.4363F);
		bone17.setTextureOffset(76, 0).addBox(-0.0771F, -3.5521F, -0.0436F, 1.0F, 4.0F, 1.0F, 0.0F, true);
		bone17.setModelRendererName("bone17");
		this.registerModelRenderer(bone17);

		bone126 = new AnimatedModelRenderer(this);
		bone126.setRotationPoint(5.0216F, 4.5204F, 1.1093F);
		sideburn_right.addChild(bone126);
		bone126.setTextureOffset(76, 0).addBox(-1.4446F, -16.5893F, 3.8394F, 1.0F, 12.0F, 1.0F, 0.0F, false);
		bone126.setModelRendererName("bone126");
		this.registerModelRenderer(bone126);

		bone127 = new AnimatedModelRenderer(this);
		bone127.setRotationPoint(0.9962F, -0.0872F, 0.0F);
		bone126.addChild(bone127);
		setRotationAngle(bone127, 0.0F, 0.0F, 0.0524F);
		bone127.setTextureOffset(76, 0).addBox(-2.4849F, -16.9054F, 3.7958F, 1.0F, 13.0F, 1.0F, 0.0F, false);
		bone127.setModelRendererName("bone127");
		this.registerModelRenderer(bone127);

		bone128 = new AnimatedModelRenderer(this);
		bone128.setRotationPoint(-0.9848F, 0.1736F, 0.0F);
		bone127.addChild(bone128);
		setRotationAngle(bone128, 0.0F, 0.0F, 0.1745F);
		bone128.setTextureOffset(76, 0).addBox(-3.47F, -15.3954F, 3.7958F, 1.0F, 12.0F, 1.0F, 0.0F, false);
		bone128.setModelRendererName("bone128");
		this.registerModelRenderer(bone128);

		bone129 = new AnimatedModelRenderer(this);
		bone129.setRotationPoint(-0.2165F, 0.125F, 0.0F);
		bone128.addChild(bone129);
		setRotationAngle(bone129, 0.0F, 0.0F, 0.1745F);
		bone129.setTextureOffset(76, 0).addBox(-4.6309F, -14.2925F, 3.7958F, 1.0F, 12.0F, 1.0F, 0.0F, false);
		bone129.setModelRendererName("bone129");
		this.registerModelRenderer(bone129);

		bone130 = new AnimatedModelRenderer(this);
		bone130.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone129.addChild(bone130);
		setRotationAngle(bone130, 0.0F, 0.0F, 0.1745F);
		bone130.setTextureOffset(76, 0).addBox(-5.2139F, -13.2394F, 3.7958F, 1.0F, 12.0F, 1.0F, 0.0F, false);
		bone130.setModelRendererName("bone130");
		this.registerModelRenderer(bone130);

		bone131 = new AnimatedModelRenderer(this);
		bone131.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone130.addChild(bone131);
		setRotationAngle(bone131, 0.0F, 0.0F, -0.6109F);
		bone131.setTextureOffset(76, 0).addBox(-5.5044F, -12.0713F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone131.setModelRendererName("bone131");
		this.registerModelRenderer(bone131);

		bone132 = new AnimatedModelRenderer(this);
		bone132.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone131.addChild(bone132);
		setRotationAngle(bone132, 0.0F, 0.0F, 0.0873F);
		bone132.setTextureOffset(76, 0).addBox(-5.4827F, -10.8679F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone132.setModelRendererName("bone132");
		this.registerModelRenderer(bone132);

		bone133 = new AnimatedModelRenderer(this);
		bone133.setRotationPoint(3.0F, -3.0F, 0.0F);
		bone131.addChild(bone133);
		setRotationAngle(bone133, 0.0873F, 0.0F, -0.2618F);
		bone133.setTextureOffset(76, 0).addBox(-4.7027F, -10.2737F, 3.2932F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		bone133.setModelRendererName("bone133");
		this.registerModelRenderer(bone133);

		bone134 = new AnimatedModelRenderer(this);
		bone134.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone133.addChild(bone134);
		setRotationAngle(bone134, 0.0F, 0.0F, 0.0873F);
		bone134.setTextureOffset(76, 0).addBox(-4.876F, -9.1317F, 3.2932F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		bone134.setModelRendererName("bone134");
		this.registerModelRenderer(bone134);

		bone135 = new AnimatedModelRenderer(this);
		bone135.setRotationPoint(3.0F, -3.0F, 0.0F);
		bone130.addChild(bone135);
		setRotationAngle(bone135, 0.0873F, 0.0F, -0.2618F);
		bone135.setTextureOffset(76, 0).addBox(-5.5044F, -8.0713F, 3.7958F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		bone135.setModelRendererName("bone135");
		this.registerModelRenderer(bone135);

		bone136 = new AnimatedModelRenderer(this);
		bone136.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone135.addChild(bone136);
		setRotationAngle(bone136, 0.0F, 0.0F, 0.0873F);
		bone136.setTextureOffset(76, 0).addBox(-5.4827F, -6.8679F, 3.7958F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		bone136.setModelRendererName("bone136");
		this.registerModelRenderer(bone136);

		bone137 = new AnimatedModelRenderer(this);
		bone137.setRotationPoint(1.0F, -3.0F, 0.0F);
		bone130.addChild(bone137);
		setRotationAngle(bone137, 0.0F, 0.0F, -0.6109F);
		bone137.setTextureOffset(76, 0).addBox(-5.5044F, -12.0713F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone137.setModelRendererName("bone137");
		this.registerModelRenderer(bone137);

		bone138 = new AnimatedModelRenderer(this);
		bone138.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone137.addChild(bone138);
		setRotationAngle(bone138, 0.0F, 0.0F, 0.0873F);
		bone138.setTextureOffset(76, 0).addBox(-5.4827F, -10.8679F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone138.setModelRendererName("bone138");
		this.registerModelRenderer(bone138);

		bone139 = new AnimatedModelRenderer(this);
		bone139.setRotationPoint(3.0F, -3.0F, 0.0F);
		bone137.addChild(bone139);
		setRotationAngle(bone139, 0.0873F, 0.0F, -0.2618F);
		bone139.setTextureOffset(76, 0).addBox(-4.7027F, -10.2737F, 3.2932F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		bone139.setModelRendererName("bone139");
		this.registerModelRenderer(bone139);

		bone140 = new AnimatedModelRenderer(this);
		bone140.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone139.addChild(bone140);
		setRotationAngle(bone140, 0.0F, 0.0F, 0.0873F);
		bone140.setTextureOffset(76, 0).addBox(-4.876F, -9.1317F, 3.2932F, 1.0F, 8.0F, 1.0F, 0.0F, false);
		bone140.setModelRendererName("bone140");
		this.registerModelRenderer(bone140);

		bone141 = new AnimatedModelRenderer(this);
		bone141.setRotationPoint(-5.4497F, -0.0872F, 0.0F);
		bone126.addChild(bone141);
		setRotationAngle(bone141, 0.0873F, 0.0F, -0.0524F);
		bone141.setTextureOffset(76, 0).addBox(1.4849F, -16.9054F, 3.7958F, 1.0F, 13.0F, 1.0F, 0.0F, true);
		bone141.setModelRendererName("bone141");
		this.registerModelRenderer(bone141);

		bone142 = new AnimatedModelRenderer(this);
		bone142.setRotationPoint(0.9848F, 0.1736F, 0.0F);
		bone141.addChild(bone142);
		setRotationAngle(bone142, 0.0F, 0.0F, -0.1745F);
		bone142.setTextureOffset(76, 0).addBox(2.47F, -15.3954F, 3.7958F, 1.0F, 12.0F, 1.0F, 0.0F, true);
		bone142.setModelRendererName("bone142");
		this.registerModelRenderer(bone142);

		bone143 = new AnimatedModelRenderer(this);
		bone143.setRotationPoint(0.2165F, 0.125F, 0.0F);
		bone142.addChild(bone143);
		setRotationAngle(bone143, 0.0F, 0.0F, -0.1745F);
		bone143.setTextureOffset(76, 0).addBox(3.6309F, -14.2925F, 3.7958F, 1.0F, 12.0F, 1.0F, 0.0F, true);
		bone143.setModelRendererName("bone143");
		this.registerModelRenderer(bone143);

		bone144 = new AnimatedModelRenderer(this);
		bone144.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone143.addChild(bone144);
		setRotationAngle(bone144, 0.0F, 0.0F, -0.1745F);
		bone144.setTextureOffset(76, 0).addBox(4.2139F, -13.2394F, 3.7958F, 1.0F, 12.0F, 1.0F, 0.0F, true);
		bone144.setModelRendererName("bone144");
		this.registerModelRenderer(bone144);

		bone145 = new AnimatedModelRenderer(this);
		bone145.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone144.addChild(bone145);
		setRotationAngle(bone145, 0.0F, 0.0F, 0.6109F);
		bone145.setTextureOffset(76, 0).addBox(4.5044F, -12.0713F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone145.setModelRendererName("bone145");
		this.registerModelRenderer(bone145);

		bone146 = new AnimatedModelRenderer(this);
		bone146.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone145.addChild(bone146);
		setRotationAngle(bone146, 0.0F, 0.0F, -0.0873F);
		bone146.setTextureOffset(76, 0).addBox(4.4827F, -10.8679F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone146.setModelRendererName("bone146");
		this.registerModelRenderer(bone146);

		bone147 = new AnimatedModelRenderer(this);
		bone147.setRotationPoint(-3.0F, -3.0F, 0.0F);
		bone145.addChild(bone147);
		setRotationAngle(bone147, 0.0873F, 0.0F, 0.2618F);
		bone147.setTextureOffset(76, 0).addBox(3.7027F, -10.2737F, 3.2932F, 1.0F, 8.0F, 1.0F, 0.0F, true);
		bone147.setModelRendererName("bone147");
		this.registerModelRenderer(bone147);

		bone148 = new AnimatedModelRenderer(this);
		bone148.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone147.addChild(bone148);
		setRotationAngle(bone148, 0.0F, 0.0F, -0.0873F);
		bone148.setTextureOffset(76, 0).addBox(3.876F, -9.1317F, 3.2932F, 1.0F, 8.0F, 1.0F, 0.0F, true);
		bone148.setModelRendererName("bone148");
		this.registerModelRenderer(bone148);

		bone149 = new AnimatedModelRenderer(this);
		bone149.setRotationPoint(-3.0F, -3.0F, 0.0F);
		bone144.addChild(bone149);
		setRotationAngle(bone149, 0.0873F, 0.0F, 0.2618F);
		bone149.setTextureOffset(76, 0).addBox(4.5044F, -8.0713F, 3.7958F, 1.0F, 8.0F, 1.0F, 0.0F, true);
		bone149.setModelRendererName("bone149");
		this.registerModelRenderer(bone149);

		bone150 = new AnimatedModelRenderer(this);
		bone150.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone149.addChild(bone150);
		setRotationAngle(bone150, 0.0F, 0.0F, -0.0873F);
		bone150.setTextureOffset(76, 0).addBox(4.4827F, -6.8679F, 3.7958F, 1.0F, 8.0F, 1.0F, 0.0F, true);
		bone150.setModelRendererName("bone150");
		this.registerModelRenderer(bone150);

		bone151 = new AnimatedModelRenderer(this);
		bone151.setRotationPoint(-1.0F, -3.0F, 0.0F);
		bone144.addChild(bone151);
		setRotationAngle(bone151, 0.0F, 0.0F, 0.6109F);
		bone151.setTextureOffset(76, 0).addBox(4.5044F, -12.0713F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone151.setModelRendererName("bone151");
		this.registerModelRenderer(bone151);

		bone152 = new AnimatedModelRenderer(this);
		bone152.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone151.addChild(bone152);
		setRotationAngle(bone152, 0.0F, 0.0F, -0.0873F);
		bone152.setTextureOffset(76, 0).addBox(4.4827F, -10.8679F, 3.7958F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone152.setModelRendererName("bone152");
		this.registerModelRenderer(bone152);

		bone153 = new AnimatedModelRenderer(this);
		bone153.setRotationPoint(-3.0F, -3.0F, 0.0F);
		bone151.addChild(bone153);
		setRotationAngle(bone153, 0.0873F, 0.0F, 0.2618F);
		bone153.setTextureOffset(76, 0).addBox(3.7027F, -10.2737F, 3.2932F, 1.0F, 8.0F, 1.0F, 0.0F, true);
		bone153.setModelRendererName("bone153");
		this.registerModelRenderer(bone153);

		bone154 = new AnimatedModelRenderer(this);
		bone154.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone153.addChild(bone154);
		setRotationAngle(bone154, 0.0F, 0.0F, -0.0873F);
		bone154.setTextureOffset(76, 0).addBox(3.876F, -9.1317F, 3.2932F, 1.0F, 8.0F, 1.0F, 0.0F, true);
		bone154.setModelRendererName("bone154");
		this.registerModelRenderer(bone154);

		fur = new AnimatedModelRenderer(this);
		fur.setRotationPoint(0.0F, -17.9404F, -6.5252F);
		decoration.addChild(fur);
		setRotationAngle(fur, -0.6109F, -0.0873F, -0.0873F);
		
		fur.setModelRendererName("fur");
		this.registerModelRenderer(fur);

		fur_right = new AnimatedModelRenderer(this);
		fur_right.setRotationPoint(0.75F, 2.2472F, 2.2068F);
		fur.addChild(fur_right);
		setRotationAngle(fur_right, -0.4363F, 0.1745F, -0.1222F);
		fur_right.setTextureOffset(76, 0).addBox(-0.75F, -8.5843F, -4.2718F, 1.0F, 7.0F, 1.0F, 0.0F, true);
		fur_right.setModelRendererName("fur_right");
		this.registerModelRenderer(fur_right);

		bone8 = new AnimatedModelRenderer(this);
		bone8.setRotationPoint(-1.5F, -1.8333F, -4.25F);
		fur_right.addChild(bone8);
		bone8.setTextureOffset(76, 0).addBox(0.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone8.setModelRendererName("bone8");
		this.registerModelRenderer(bone8);

		bone9 = new AnimatedModelRenderer(this);
		bone9.setRotationPoint(-0.9962F, -0.0872F, 0.0F);
		bone8.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, -0.1396F);
		bone9.setTextureOffset(76, 0).addBox(0.4059F, -6.4226F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone9.setModelRendererName("bone9");
		this.registerModelRenderer(bone9);

		bone10 = new AnimatedModelRenderer(this);
		bone10.setRotationPoint(0.9848F, 0.1736F, 0.0F);
		bone9.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 0.0F, -0.2618F);
		bone10.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone10.setModelRendererName("bone10");
		this.registerModelRenderer(bone10);

		bone11 = new AnimatedModelRenderer(this);
		bone11.setRotationPoint(0.2165F, 0.125F, 0.0F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, -0.3491F);
		bone11.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone11.setModelRendererName("bone11");
		this.registerModelRenderer(bone11);

		bone12 = new AnimatedModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone11.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, -0.0873F);
		bone12.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone12.setModelRendererName("bone12");
		this.registerModelRenderer(bone12);

		bone13 = new AnimatedModelRenderer(this);
		bone13.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone12.addChild(bone13);
		setRotationAngle(bone13, 0.0F, 0.0F, -0.2618F);
		bone13.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone13.setModelRendererName("bone13");
		this.registerModelRenderer(bone13);

		bone14 = new AnimatedModelRenderer(this);
		bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone13.addChild(bone14);
		setRotationAngle(bone14, 0.0F, 0.0F, -0.2618F);
		bone14.setTextureOffset(76, 0).addBox(-0.0771F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, true);
		bone14.setModelRendererName("bone14");
		this.registerModelRenderer(bone14);

		fur_left = new AnimatedModelRenderer(this);
		fur_left.setRotationPoint(-0.75F, 2.2472F, 2.2068F);
		fur.addChild(fur_left);
		setRotationAngle(fur_left, -0.4363F, -0.1745F, 0.1396F);
		fur_left.setTextureOffset(76, 0).addBox(-0.25F, -8.5843F, -4.2718F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		fur_left.setModelRendererName("fur_left");
		this.registerModelRenderer(fur_left);

		bone = new AnimatedModelRenderer(this);
		bone.setRotationPoint(1.5F, -1.8333F, -4.25F);
		fur_left.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, 0.0873F);
		bone.setTextureOffset(76, 0).addBox(-1.0F, -6.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone.setModelRendererName("bone");
		this.registerModelRenderer(bone);

		bone2 = new AnimatedModelRenderer(this);
		bone2.setRotationPoint(0.9962F, -0.0872F, 0.0F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.1396F);
		bone2.setTextureOffset(76, 0).addBox(-1.4059F, -6.4226F, -0.0436F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		bone2.setModelRendererName("bone2");
		this.registerModelRenderer(bone2);

		bone3 = new AnimatedModelRenderer(this);
		bone3.setRotationPoint(-0.9848F, 0.1736F, 0.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, 0.3491F);
		bone3.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone3.setModelRendererName("bone3");
		this.registerModelRenderer(bone3);

		bone4 = new AnimatedModelRenderer(this);
		bone4.setRotationPoint(-0.2165F, 0.125F, 0.0F);
		bone3.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.3491F);
		bone4.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone4.setModelRendererName("bone4");
		this.registerModelRenderer(bone4);

		bone5 = new AnimatedModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, 0.2618F);
		bone5.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone5.setModelRendererName("bone5");
		this.registerModelRenderer(bone5);

		bone6 = new AnimatedModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.2618F);
		bone6.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone6.setModelRendererName("bone6");
		this.registerModelRenderer(bone6);

		bone7 = new AnimatedModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone6.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 0.0F, 0.2618F);
		bone7.setTextureOffset(76, 0).addBox(-0.9229F, -5.5521F, -0.0436F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		bone7.setModelRendererName("bone7");
		this.registerModelRenderer(bone7);

		this.rootBones.add(body);
	}


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/spikan.json");
    }
}