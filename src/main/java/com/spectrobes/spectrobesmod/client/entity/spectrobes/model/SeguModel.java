// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegu;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class SeguModel extends AnimatedEntityModel<EntitySegu> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer wheel_left;
	private final AnimatedModelRenderer wheel_left_2;
	private final AnimatedModelRenderer wheel_right;
	private final AnimatedModelRenderer wheel_right_3;

    public SeguModel()
    {
        textureWidth = 32;
    	textureHeight = 32;
    	body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 22.0F, 0.0F);
		body.setTextureOffset(0, 13).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(12, 12).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-2.5F, -3.0F, -2.5F, 5.0F, 2.0F, 5.0F, 0.0F, false);
		body.setTextureOffset(0, 7).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(12, 7).addBox(-1.5F, -6.0F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-0.5F, -8.0F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		wheel_left = new AnimatedModelRenderer(this);
		wheel_left.setRotationPoint(1.0F, 0.0F, 0.0F);
		body.addChild(wheel_left);
		setRotationAngle(wheel_left, 0.0F, 0.0F, 0.1745F);
		wheel_left.setTextureOffset(15, 2).addBox(-0.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		wheel_left.setModelRendererName("wheel_left");
		this.registerModelRenderer(wheel_left);

		wheel_left_2 = new AnimatedModelRenderer(this);
		wheel_left_2.setRotationPoint(3.0F, 0.0F, 0.0F);
		wheel_left.addChild(wheel_left_2);
		setRotationAngle(wheel_left_2, 0.6981F, 0.0F, -0.3927F);
		wheel_left_2.setTextureOffset(8, 17).addBox(-0.6533F, -1.7294F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		wheel_left_2.setTextureOffset(0, 7).addBox(0.0F, -0.75F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		wheel_left_2.setModelRendererName("wheel_left_2");
		this.registerModelRenderer(wheel_left_2);

		wheel_right = new AnimatedModelRenderer(this);
		wheel_right.setRotationPoint(-1.0F, 0.0F, 0.0F);
		body.addChild(wheel_right);
		setRotationAngle(wheel_right, 0.0F, 0.0F, -0.1745F);
		wheel_right.setTextureOffset(15, 0).addBox(-2.5F, -0.5F, -0.5F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		wheel_right.setModelRendererName("wheel_right");
		this.registerModelRenderer(wheel_right);

		wheel_right_3 = new AnimatedModelRenderer(this);
		wheel_right_3.setRotationPoint(-3.0F, 0.0F, 0.0F);
		wheel_right.addChild(wheel_right_3);
		setRotationAngle(wheel_right_3, 0.6981F, 0.0F, 0.3927F);
		wheel_right_3.setTextureOffset(0, 17).addBox(-0.3467F, -1.7294F, -1.5F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		wheel_right_3.setTextureOffset(0, 3).addBox(-1.0F, -0.75F, -0.5F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		wheel_right_3.setModelRendererName("wheel_right_3");
		this.registerModelRenderer(wheel_right_3);

    this.rootBones.add(body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("spectrobesmod", "animations/spectrobe/segu.json");
    }
}