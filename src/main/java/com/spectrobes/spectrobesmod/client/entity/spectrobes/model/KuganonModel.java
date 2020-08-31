// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKuganon;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class KuganonModel extends AnimatedEntityModel<EntityKuganon> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer left;
	private final AnimatedModelRenderer wheels;
	private final AnimatedModelRenderer wheel;
	private final AnimatedModelRenderer wheel2;
	private final AnimatedModelRenderer wheel3;
	private final AnimatedModelRenderer wheel4;
	private final AnimatedModelRenderer wheel5;
	private final AnimatedModelRenderer wheel6;
	private final AnimatedModelRenderer cannon;
	private final AnimatedModelRenderer right;
	private final AnimatedModelRenderer wheels2;
	private final AnimatedModelRenderer wheel7;
	private final AnimatedModelRenderer wheel8;
	private final AnimatedModelRenderer wheel9;
	private final AnimatedModelRenderer wheel10;
	private final AnimatedModelRenderer wheel11;
	private final AnimatedModelRenderer wheel12;
	private final AnimatedModelRenderer back;
	private final AnimatedModelRenderer hair;
	private final AnimatedModelRenderer hair2;
	private final AnimatedModelRenderer hair3;
	private final AnimatedModelRenderer hair4;

    public KuganonModel()
    {
        textureWidth = 128;
    textureHeight = 128;
    body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 23.5F, 0.0F);
		body.setTextureOffset(32, 0).addBox(-5.0F, -7.5F, -5.0F, 10.0F, 6.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(32, 24).addBox(-4.5F, -8.5F, -4.5F, 9.0F, 6.0F, 9.0F, 0.0F, false);
		body.setTextureOffset(44, 44).addBox(-4.0F, -9.5F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		left = new AnimatedModelRenderer(this);
		left.setRotationPoint(0.0F, 4.5F, 0.0F);
		body.addChild(left);
		left.setTextureOffset(0, 24).addBox(5.0F, -10.0F, -14.0F, 6.0F, 4.0F, 20.0F, 0.0F, false);
		left.setTextureOffset(59, 22).addBox(7.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		left.setTextureOffset(56, 59).addBox(7.0F, -14.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		left.setTextureOffset(48, 59).addBox(7.0F, -14.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		left.setModelRendererName("left");
		this.registerModelRenderer(left);

		wheels = new AnimatedModelRenderer(this);
		wheels.setRotationPoint(0.0F, 0.0F, 0.0F);
		left.addChild(wheels);
		
		wheels.setModelRendererName("wheels");
		this.registerModelRenderer(wheels);

		wheel = new AnimatedModelRenderer(this);
		wheel.setRotationPoint(3.5F, -6.5F, -7.5F);
		wheels.addChild(wheel);
		setRotationAngle(wheel, 0.7854F, 0.0F, 0.0F);
		wheel.setTextureOffset(24, 54).addBox(-0.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel.setTextureOffset(14, 54).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel.setModelRendererName("wheel");
		this.registerModelRenderer(wheel);

		wheel2 = new AnimatedModelRenderer(this);
		wheel2.setRotationPoint(3.5F, -6.5F, -12.5F);
		wheels.addChild(wheel2);
		setRotationAngle(wheel2, 0.7854F, 0.0F, 0.0F);
		wheel2.setTextureOffset(52, 16).addBox(-0.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel2.setTextureOffset(0, 54).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel2.setModelRendererName("wheel2");
		this.registerModelRenderer(wheel2);

		wheel3 = new AnimatedModelRenderer(this);
		wheel3.setRotationPoint(12.5F, -6.5F, -7.5F);
		wheels.addChild(wheel3);
		setRotationAngle(wheel3, 0.7854F, 0.0F, 0.0F);
		wheel3.setTextureOffset(34, 48).addBox(-1.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel3.setTextureOffset(50, 39).addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel3.setModelRendererName("wheel3");
		this.registerModelRenderer(wheel3);

		wheel4 = new AnimatedModelRenderer(this);
		wheel4.setRotationPoint(12.5F, -6.5355F, -12.0355F);
		wheels.addChild(wheel4);
		setRotationAngle(wheel4, 0.7854F, 0.0F, 0.0F);
		wheel4.setTextureOffset(24, 48).addBox(-1.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel4.setTextureOffset(44, 48).addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel4.setModelRendererName("wheel4");
		this.registerModelRenderer(wheel4);

		wheel5 = new AnimatedModelRenderer(this);
		wheel5.setRotationPoint(12.5F, -6.4645F, -2.4645F);
		wheels.addChild(wheel5);
		setRotationAngle(wheel5, 0.7854F, 0.0F, 0.0F);
		wheel5.setTextureOffset(14, 48).addBox(-1.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel5.setTextureOffset(44, 39).addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel5.setModelRendererName("wheel5");
		this.registerModelRenderer(wheel5);

		wheel6 = new AnimatedModelRenderer(this);
		wheel6.setRotationPoint(12.5F, -6.4289F, 2.5711F);
		wheels.addChild(wheel6);
		setRotationAngle(wheel6, 0.7854F, 0.0F, 0.0F);
		wheel6.setTextureOffset(0, 48).addBox(-1.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel6.setTextureOffset(44, 16).addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel6.setModelRendererName("wheel6");
		this.registerModelRenderer(wheel6);

		cannon = new AnimatedModelRenderer(this);
		cannon.setRotationPoint(0.0F, -7.5F, -5.0F);
		body.addChild(cannon);
		setRotationAngle(cannon, -0.3927F, 0.0F, 0.0F);
		cannon.setTextureOffset(0, 48).addBox(-1.0F, -2.0F, -9.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);
		cannon.setTextureOffset(9, 0).addBox(-1.5F, -2.5F, -9.25F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		cannon.setTextureOffset(0, 24).addBox(-1.5F, -2.5F, -11.25F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		cannon.setTextureOffset(0, 14).addBox(-1.5F, -0.5F, -11.25F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		cannon.setTextureOffset(11, 4).addBox(0.5F, -2.5F, -11.25F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		cannon.setTextureOffset(11, 11).addBox(-1.5F, -2.5F, -11.25F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		cannon.setTextureOffset(92, 13).addBox(-1.5F, -2.5F, -1.25F, 3.0F, 3.0F, 0.0F, 0.0F, false);
		cannon.setModelRendererName("cannon");
		this.registerModelRenderer(cannon);

		right = new AnimatedModelRenderer(this);
		right.setRotationPoint(0.0F, 4.5F, 0.0F);
		body.addChild(right);
		right.setTextureOffset(0, 0).addBox(-11.0F, -10.0F, -14.0F, 6.0F, 4.0F, 20.0F, 0.0F, false);
		right.setTextureOffset(40, 59).addBox(-9.0F, -14.0F, 2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		right.setTextureOffset(34, 54).addBox(-9.0F, -14.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		right.setTextureOffset(32, 24).addBox(-9.0F, -14.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		right.setModelRendererName("right");
		this.registerModelRenderer(right);

		wheels2 = new AnimatedModelRenderer(this);
		wheels2.setRotationPoint(0.0F, 0.0F, 0.0F);
		right.addChild(wheels2);
		
		wheels2.setModelRendererName("wheels2");
		this.registerModelRenderer(wheels2);

		wheel7 = new AnimatedModelRenderer(this);
		wheel7.setRotationPoint(-3.5F, -6.5F, -7.5F);
		wheels2.addChild(wheel7);
		setRotationAngle(wheel7, 0.7854F, 0.0F, 0.0F);
		wheel7.setTextureOffset(7, 37).addBox(-1.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel7.setTextureOffset(0, 40).addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel7.setModelRendererName("wheel7");
		this.registerModelRenderer(wheel7);

		wheel8 = new AnimatedModelRenderer(this);
		wheel8.setRotationPoint(-3.5F, -6.5F, -12.5F);
		wheels2.addChild(wheel8);
		setRotationAngle(wheel8, 0.7854F, 0.0F, 0.0F);
		wheel8.setTextureOffset(0, 34).addBox(-1.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel8.setTextureOffset(38, 39).addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel8.setModelRendererName("wheel8");
		this.registerModelRenderer(wheel8);

		wheel9 = new AnimatedModelRenderer(this);
		wheel9.setRotationPoint(-12.5F, -6.5F, -7.5F);
		wheels2.addChild(wheel9);
		setRotationAngle(wheel9, 0.7854F, 0.0F, 0.0F);
		wheel9.setTextureOffset(32, 0).addBox(-0.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel9.setTextureOffset(32, 39).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel9.setModelRendererName("wheel9");
		this.registerModelRenderer(wheel9);

		wheel10 = new AnimatedModelRenderer(this);
		wheel10.setRotationPoint(-12.5F, -6.5355F, -12.5355F);
		wheels2.addChild(wheel10);
		setRotationAngle(wheel10, 0.7854F, 0.0F, 0.0F);
		wheel10.setTextureOffset(7, 31).addBox(-0.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel10.setTextureOffset(38, 16).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel10.setModelRendererName("wheel10");
		this.registerModelRenderer(wheel10);

		wheel11 = new AnimatedModelRenderer(this);
		wheel11.setRotationPoint(-12.5F, -6.4645F, -2.7145F);
		wheels2.addChild(wheel11);
		setRotationAngle(wheel11, 0.7854F, 0.0F, 0.0F);
		wheel11.setTextureOffset(0, 28).addBox(-0.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel11.setTextureOffset(32, 16).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel11.setModelRendererName("wheel11");
		this.registerModelRenderer(wheel11);

		wheel12 = new AnimatedModelRenderer(this);
		wheel12.setRotationPoint(-12.5F, -6.4289F, 2.5711F);
		wheels2.addChild(wheel12);
		setRotationAngle(wheel12, 0.7854F, 0.0F, 0.0F);
		wheel12.setTextureOffset(9, 25).addBox(-0.5F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		wheel12.setTextureOffset(32, 6).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		wheel12.setModelRendererName("wheel12");
		this.registerModelRenderer(wheel12);

		back = new AnimatedModelRenderer(this);
		back.setRotationPoint(0.0F, -4.0F, 5.0F);
		body.addChild(back);
		setRotationAngle(back, 0.3054F, 0.0F, 0.0F);
		back.setTextureOffset(0, 7).addBox(3.0F, -2.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		back.setTextureOffset(0, 0).addBox(-5.0F, -2.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		back.setModelRendererName("back");
		this.registerModelRenderer(back);

		hair = new AnimatedModelRenderer(this);
		hair.setRotationPoint(0.0F, -9.0F, 0.0F);
		body.addChild(hair);
		setRotationAngle(hair, -0.3054F, 0.0F, 0.0F);
		hair.setTextureOffset(74, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		hair.setModelRendererName("hair");
		this.registerModelRenderer(hair);

		hair2 = new AnimatedModelRenderer(this);
		hair2.setRotationPoint(0.0F, -2.0F, 0.0F);
		hair.addChild(hair2);
		setRotationAngle(hair2, -0.2182F, 0.0F, 0.0F);
		hair2.setTextureOffset(90, 0).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
		hair2.setModelRendererName("hair2");
		this.registerModelRenderer(hair2);

		hair3 = new AnimatedModelRenderer(this);
		hair3.setRotationPoint(0.0F, -3.5F, 0.0F);
		hair2.addChild(hair3);
		setRotationAngle(hair3, -0.3054F, 0.0F, 0.0F);
		hair3.setTextureOffset(74, 0).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
		hair3.setModelRendererName("hair3");
		this.registerModelRenderer(hair3);

		hair4 = new AnimatedModelRenderer(this);
		hair4.setRotationPoint(0.0F, -5.0F, 0.0F);
		hair3.addChild(hair4);
		setRotationAngle(hair4, -0.3491F, 0.0F, 0.0F);
		hair4.setTextureOffset(74, 0).addBox(-0.5F, -4.75F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		hair4.setModelRendererName("hair4");
		this.registerModelRenderer(hair4);

    this.rootBones.add(body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("spectrobesmod", "animations/spectrobe/kuganon.json");
    }
}