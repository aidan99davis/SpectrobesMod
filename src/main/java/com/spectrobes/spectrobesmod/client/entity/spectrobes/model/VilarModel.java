// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilar;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class VilarModel extends AnimatedEntityModel<EntityVilar> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer body6;
	private final AnimatedModelRenderer sting;
	private final AnimatedModelRenderer body5;
	private final AnimatedModelRenderer leftleg5;
	private final AnimatedModelRenderer rightleg5;
	private final AnimatedModelRenderer body4;
	private final AnimatedModelRenderer leftleg4;
	private final AnimatedModelRenderer rightleg4;
	private final AnimatedModelRenderer body3;
	private final AnimatedModelRenderer leftleg3;
	private final AnimatedModelRenderer rightleg3;
	private final AnimatedModelRenderer body2;
	private final AnimatedModelRenderer leftleg2;
	private final AnimatedModelRenderer rightleg2;
	private final AnimatedModelRenderer body1;
	private final AnimatedModelRenderer leftleg1;
	private final AnimatedModelRenderer rightleg1;
	private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer head2;
	private final AnimatedModelRenderer horn1;
	private final AnimatedModelRenderer horn2;
	private final AnimatedModelRenderer face;
	private final AnimatedModelRenderer faceright;
	private final AnimatedModelRenderer faceleft;
	private final AnimatedModelRenderer mandibles;

    public VilarModel()
    {
        textureWidth = 64;
    textureHeight = 64;
    body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 23.5F, 0.0F);
		
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		body6 = new AnimatedModelRenderer(this);
		body6.setRotationPoint(0.0F, -3.5F, 14.5F);
		body.addChild(body6);
		body6.setTextureOffset(0, 0).addBox(-4.0F, -1.5F, 1.5F, 8.0F, 3.0F, 3.0F, 0.0F, false);
		body6.setTextureOffset(0, 16).addBox(-2.5F, -3.0F, 0.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
		body6.setTextureOffset(22, 27).addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
		body6.setTextureOffset(0, 6).addBox(-3.0F, -2.5F, 0.5F, 6.0F, 5.0F, 5.0F, 0.0F, false);
		body6.setModelRendererName("body6");
		this.registerModelRenderer(body6);

		sting = new AnimatedModelRenderer(this);
		sting.setRotationPoint(0.0F, 1.0F, 5.9F);
		body6.addChild(sting);
		setRotationAngle(sting, -0.0873F, 0.0F, 0.0F);
		sting.setTextureOffset(5, 52).addBox(-0.5F, -0.5F, 6.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		sting.setTextureOffset(0, 53).addBox(-0.65F, -0.65F, 3.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		sting.setTextureOffset(4, 59).addBox(-0.8F, -0.8F, -0.5F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		sting.setTextureOffset(32, 25).addBox(-1.1F, -1.0F, 0.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		sting.setTextureOffset(32, 25).addBox(-1.6F, 1.0F, 0.0F, 2.0F, 0.0F, 2.0F, 0.0F, false);
		sting.setTextureOffset(38, 25).addBox(1.0F, -0.6F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, false);
		sting.setTextureOffset(38, 25).addBox(-1.0F, -1.1F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, false);
		sting.setModelRendererName("sting");
		this.registerModelRenderer(sting);

		body5 = new AnimatedModelRenderer(this);
		body5.setRotationPoint(0.0F, 0.0F, 0.0F);
		body6.addChild(body5);
		body5.setTextureOffset(18, 19).addBox(-2.5F, -0.75F, -2.75F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		body5.setTextureOffset(52, 22).addBox(-1.5F, -2.0F, -3.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		body5.setTextureOffset(18, 12).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		body5.setTextureOffset(20, 21).addBox(-2.0F, -1.5F, -3.5F, 4.0F, 3.0F, 3.0F, 0.0F, false);
		body5.setModelRendererName("body5");
		this.registerModelRenderer(body5);

		leftleg5 = new AnimatedModelRenderer(this);
		leftleg5.setRotationPoint(0.0F, 2.0F, -2.5F);
		body5.addChild(leftleg5);
		leftleg5.setTextureOffset(0, 28).addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		leftleg5.setTextureOffset(22, 27).addBox(0.5F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		leftleg5.setModelRendererName("leftleg5");
		this.registerModelRenderer(leftleg5);

		rightleg5 = new AnimatedModelRenderer(this);
		rightleg5.setRotationPoint(0.0F, 2.0F, -2.5F);
		body5.addChild(rightleg5);
		rightleg5.setTextureOffset(0, 28).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		rightleg5.setTextureOffset(22, 27).addBox(-0.5F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		rightleg5.setModelRendererName("rightleg5");
		this.registerModelRenderer(rightleg5);

		body4 = new AnimatedModelRenderer(this);
		body4.setRotationPoint(0.0F, 0.0F, -4.0F);
		body5.addChild(body4);
		body4.setTextureOffset(18, 19).addBox(-2.5F, -0.75F, -2.75F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		body4.setTextureOffset(52, 22).addBox(-1.5F, -2.0F, -3.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		body4.setTextureOffset(18, 12).addBox(-1.5F, -1.5F, -4.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		body4.setTextureOffset(20, 21).addBox(-2.0F, -1.5F, -3.5F, 4.0F, 3.0F, 3.0F, 0.0F, false);
		body4.setModelRendererName("body4");
		this.registerModelRenderer(body4);

		leftleg4 = new AnimatedModelRenderer(this);
		leftleg4.setRotationPoint(0.0F, 2.0F, -2.5F);
		body4.addChild(leftleg4);
		leftleg4.setTextureOffset(0, 28).addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		leftleg4.setTextureOffset(22, 27).addBox(0.5F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		leftleg4.setModelRendererName("leftleg4");
		this.registerModelRenderer(leftleg4);

		rightleg4 = new AnimatedModelRenderer(this);
		rightleg4.setRotationPoint(0.0F, 2.0F, -2.5F);
		body4.addChild(rightleg4);
		rightleg4.setTextureOffset(0, 28).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		rightleg4.setTextureOffset(22, 27).addBox(-0.5F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		rightleg4.setModelRendererName("rightleg4");
		this.registerModelRenderer(rightleg4);

		body3 = new AnimatedModelRenderer(this);
		body3.setRotationPoint(0.0F, -0.5F, -4.0F);
		body4.addChild(body3);
		body3.setTextureOffset(34, 21).addBox(-3.5F, -1.0F, -3.5F, 7.0F, 2.0F, 2.0F, 0.0F, false);
		body3.setTextureOffset(36, 12).addBox(-2.0F, -2.5F, -4.5F, 4.0F, 5.0F, 4.0F, 0.0F, false);
		body3.setTextureOffset(39, 33).addBox(-2.0F, -2.0F, -5.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);
		body3.setTextureOffset(38, 25).addBox(-2.5F, -2.0F, -4.5F, 5.0F, 4.0F, 4.0F, 0.0F, false);
		body3.setModelRendererName("body3");
		this.registerModelRenderer(body3);

		leftleg3 = new AnimatedModelRenderer(this);
		leftleg3.setRotationPoint(0.25F, 2.5F, -3.0F);
		body3.addChild(leftleg3);
		leftleg3.setTextureOffset(0, 40).addBox(-0.0609F, 0.0F, -0.0037F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		leftleg3.setTextureOffset(0, 40).addBox(-0.0609F, 0.0F, -0.9963F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		leftleg3.setTextureOffset(0, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		leftleg3.setModelRendererName("leftleg3");
		this.registerModelRenderer(leftleg3);

		rightleg3 = new AnimatedModelRenderer(this);
		rightleg3.setRotationPoint(-0.25F, 2.5F, -3.0F);
		body3.addChild(rightleg3);
		rightleg3.setTextureOffset(0, 38).addBox(-3.9391F, 0.0F, -0.0037F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		rightleg3.setTextureOffset(0, 38).addBox(-3.9391F, 0.0F, -0.9963F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		rightleg3.setTextureOffset(0, 30).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		rightleg3.setModelRendererName("rightleg3");
		this.registerModelRenderer(rightleg3);

		body2 = new AnimatedModelRenderer(this);
		body2.setRotationPoint(0.0F, 0.0F, -5.0F);
		body3.addChild(body2);
		body2.setTextureOffset(34, 21).addBox(-3.5F, -1.0F, -3.5F, 7.0F, 2.0F, 2.0F, 0.0F, false);
		body2.setTextureOffset(36, 12).addBox(-2.0F, -2.5F, -4.5F, 4.0F, 5.0F, 4.0F, 0.0F, false);
		body2.setTextureOffset(39, 33).addBox(-2.0F, -2.0F, -5.0F, 4.0F, 4.0F, 5.0F, 0.0F, false);
		body2.setTextureOffset(38, 25).addBox(-2.5F, -2.0F, -4.5F, 5.0F, 4.0F, 4.0F, 0.0F, false);
		body2.setModelRendererName("body2");
		this.registerModelRenderer(body2);

		leftleg2 = new AnimatedModelRenderer(this);
		leftleg2.setRotationPoint(0.25F, 2.5F, -3.0F);
		body2.addChild(leftleg2);
		leftleg2.setTextureOffset(0, 40).addBox(-0.0609F, 0.0F, -0.0037F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		leftleg2.setTextureOffset(0, 40).addBox(-0.0609F, 0.0F, -0.9963F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		leftleg2.setTextureOffset(0, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		leftleg2.setModelRendererName("leftleg2");
		this.registerModelRenderer(leftleg2);

		rightleg2 = new AnimatedModelRenderer(this);
		rightleg2.setRotationPoint(-0.25F, 2.5F, -3.0F);
		body2.addChild(rightleg2);
		rightleg2.setTextureOffset(0, 38).addBox(-3.9391F, 0.0F, -0.0037F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		rightleg2.setTextureOffset(0, 38).addBox(-3.9391F, 0.0F, -0.9963F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		rightleg2.setTextureOffset(0, 30).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		rightleg2.setModelRendererName("rightleg2");
		this.registerModelRenderer(rightleg2);

		body1 = new AnimatedModelRenderer(this);
		body1.setRotationPoint(0.0F, -0.5F, -5.0F);
		body2.addChild(body1);
		body1.setTextureOffset(0, 0).addBox(-4.0F, -1.5F, -4.5F, 8.0F, 3.0F, 3.0F, 0.0F, false);
		body1.setTextureOffset(0, 16).addBox(-2.5F, -3.0F, -5.5F, 5.0F, 6.0F, 5.0F, 0.0F, false);
		body1.setTextureOffset(0, 27).addBox(-2.5F, -2.5F, -6.0F, 5.0F, 5.0F, 6.0F, 0.0F, false);
		body1.setTextureOffset(0, 6).addBox(-3.0F, -2.5F, -5.5F, 6.0F, 5.0F, 5.0F, 0.0F, false);
		body1.setTextureOffset(30, 0).addBox(-1.5F, -3.5F, -6.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		body1.setTextureOffset(22, 0).addBox(-1.0F, -4.5F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		body1.setTextureOffset(22, 4).addBox(-0.5F, -5.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		body1.setModelRendererName("body1");
		this.registerModelRenderer(body1);

		leftleg1 = new AnimatedModelRenderer(this);
		leftleg1.setRotationPoint(0.25F, 3.0F, -4.0F);
		body1.addChild(leftleg1);
		leftleg1.setTextureOffset(0, 40).addBox(-0.0609F, 0.0F, -0.0037F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		leftleg1.setTextureOffset(0, 40).addBox(-0.0609F, 0.0F, -0.9963F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		leftleg1.setTextureOffset(0, 30).addBox(0.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		leftleg1.setModelRendererName("leftleg1");
		this.registerModelRenderer(leftleg1);

		rightleg1 = new AnimatedModelRenderer(this);
		rightleg1.setRotationPoint(-0.25F, 3.0F, -4.0F);
		body1.addChild(rightleg1);
		rightleg1.setTextureOffset(0, 38).addBox(-3.9391F, 0.0F, -0.0037F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		rightleg1.setTextureOffset(0, 38).addBox(-3.9391F, 0.0F, -0.9963F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		rightleg1.setTextureOffset(0, 30).addBox(-1.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		rightleg1.setModelRendererName("rightleg1");
		this.registerModelRenderer(rightleg1);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(0.0F, -1.25F, -5.75F);
		body1.addChild(head);
		setRotationAngle(head, 0.0F, 0.0F, 0.0F);
		
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		head2 = new AnimatedModelRenderer(this);
		head2.setRotationPoint(-0.0858F, 0.0F, -0.8358F);
		head.addChild(head2);
		setRotationAngle(head2, 0.0F, 0.7854F, 0.0F);
		head2.setTextureOffset(0, 42).addBox(-1.5F, -1.75F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		head2.setTextureOffset(0, 49).addBox(-1.25F, -2.25F, -1.25F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head2.setTextureOffset(8, 49).addBox(-1.25F, 1.25F, -1.25F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		head2.setModelRendererName("head2");
		this.registerModelRenderer(head2);

		horn1 = new AnimatedModelRenderer(this);
		horn1.setRotationPoint(0.0F, 1.25F, -1.75F);
		head.addChild(horn1);
		setRotationAngle(horn1, 0.4363F, 0.0F, 0.0F);
		horn1.setTextureOffset(4, 57).addBox(-0.5F, -6.0F, 2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		horn1.setTextureOffset(24, 30).addBox(-0.6F, -4.0F, 1.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		horn1.setModelRendererName("horn1");
		this.registerModelRenderer(horn1);

		horn2 = new AnimatedModelRenderer(this);
		horn2.setRotationPoint(0.0F, -0.25F, -2.75F);
		head.addChild(horn2);
		setRotationAngle(horn2, 0.48F, 0.0F, 0.0F);
		horn2.setTextureOffset(48, 7).addBox(-4.0F, -4.5F, 0.0F, 8.0F, 4.0F, 0.0F, 0.0F, false);
		horn2.setModelRendererName("horn2");
		this.registerModelRenderer(horn2);

		face = new AnimatedModelRenderer(this);
		face.setRotationPoint(-1.5F, -0.25F, -2.0F);
		head.addChild(face);
		
		face.setModelRendererName("face");
		this.registerModelRenderer(face);

		faceright = new AnimatedModelRenderer(this);
		faceright.setRotationPoint(0.25F, 0.0F, 0.0F);
		face.addChild(faceright);
		setRotationAngle(faceright, 0.0F, 0.7854F, 0.0F);
		faceright.setTextureOffset(56, 3).addBox(-2.5F, -1.5F, 0.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);
		faceright.setModelRendererName("faceright");
		this.registerModelRenderer(faceright);

		faceleft = new AnimatedModelRenderer(this);
		faceleft.setRotationPoint(3.0F, 0.0F, 0.0F);
		face.addChild(faceleft);
		setRotationAngle(faceleft, 0.0F, -0.7854F, 0.0F);
		faceleft.setTextureOffset(56, 3).addBox(-1.6768F, -1.5F, 0.1768F, 4.0F, 4.0F, 0.0F, 0.0F, true);
		faceleft.setModelRendererName("faceleft");
		this.registerModelRenderer(faceleft);

		mandibles = new AnimatedModelRenderer(this);
		mandibles.setRotationPoint(-1.5F, -0.25F, -3.75F);
		head.addChild(mandibles);
		mandibles.setTextureOffset(12, 6).addBox(-2.35F, 0.75F, -2.75F, 8.0F, 0.0F, 5.0F, 0.0F, false);
		mandibles.setModelRendererName("mandibles");
		this.registerModelRenderer(mandibles);

    this.rootBones.add(body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("spectrobesmod", "animations/spectrobe/vilar.json");
    }
}