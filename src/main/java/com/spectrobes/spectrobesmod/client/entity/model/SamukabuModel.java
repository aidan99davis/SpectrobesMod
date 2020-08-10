// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.samubaku.EntitySamukabu;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class SamukabuModel extends AnimatedEntityModel<EntitySamukabu> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer bone;
	private final AnimatedModelRenderer right_foot;
	private final AnimatedModelRenderer left_foot;
	private final AnimatedModelRenderer left_arm;
	private final AnimatedModelRenderer right_arm;
	private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer right_ear;
	private final AnimatedModelRenderer left_ear;
	private final AnimatedModelRenderer tail;
	private final AnimatedModelRenderer tail_sub_0;

    public SamukabuModel()
    {
        textureWidth = 64;
    textureHeight = 32;
    body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 15.0F, 5.0F);
		body.setTextureOffset(0, 13).addBox(-4.0F, 0.0864F, -10.8389F, 8.0F, 7.0F, 12.0F, 0.0F, false);
		body.setTextureOffset(0, 1).addBox(-3.0F, -1.9136F, -9.8389F, 6.0F, 2.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(42, 20).addBox(-5.0F, 5.0864F, -12.8389F, 1.0F, 2.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(42, 20).addBox(4.0F, 5.0864F, -12.8389F, 1.0F, 2.0F, 10.0F, 0.0F, false);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		bone = new AnimatedModelRenderer(this);
		bone.setRotationPoint(0.0F, 3.75F, -7.55F);
		body.addChild(bone);
		setRotationAngle(bone, -0.5236F, 0.0F, 0.0F);
		bone.setTextureOffset(46, 21).addBox(-5.0F, -6.5057F, 3.7808F, 1.0F, 7.0F, 2.0F, 0.0F, false);
		bone.setTextureOffset(46, 21).addBox(4.0F, -6.5057F, 3.7808F, 1.0F, 7.0F, 2.0F, 0.0F, false);
		bone.setModelRendererName("bone");
		this.registerModelRenderer(bone);

		right_foot = new AnimatedModelRenderer(this);
		right_foot.setRotationPoint(3.0F, 1.0F, 1.2F);
		body.addChild(right_foot);
		right_foot.setTextureOffset(0, 19).addBox(-1.5F, 4.0F, -2.7F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		right_foot.setModelRendererName("right_foot");
		this.registerModelRenderer(right_foot);

		left_foot = new AnimatedModelRenderer(this);
		left_foot.setRotationPoint(-6.0F, 0.0F, 0.0F);
		right_foot.addChild(left_foot);
		left_foot.setTextureOffset(0, 19).addBox(-0.5F, 4.0F, -2.7F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		left_foot.setModelRendererName("left_foot");
		this.registerModelRenderer(left_foot);

		left_arm = new AnimatedModelRenderer(this);
		left_arm.setRotationPoint(-3.0F, 2.0F, -6.0F);
		body.addChild(left_arm);
		left_arm.setTextureOffset(0, 19).addBox(-0.5F, 3.0F, -4.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		left_arm.setModelRendererName("left_arm");
		this.registerModelRenderer(left_arm);

		right_arm = new AnimatedModelRenderer(this);
		right_arm.setRotationPoint(3.0F, 2.0F, -6.0F);
		body.addChild(right_arm);
		right_arm.setTextureOffset(0, 19).addBox(-1.5F, 3.0F, -4.0F, 2.0F, 4.0F, 2.0F, 0.0F, true);
		right_arm.setModelRendererName("right_arm");
		this.registerModelRenderer(right_arm);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(0.0F, 1.0F, -6.0F);
		body.addChild(head);
		head.setTextureOffset(22, 3).addBox(-2.5F, 1.0F, -7.5F, 5.0F, 4.0F, 4.0F, 0.0F, true);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		right_ear = new AnimatedModelRenderer(this);
		right_ear.setRotationPoint(0.0F, 1.0F, -6.0F);
		body.addChild(right_ear);
		setRotationAngle(right_ear, -0.0873F, 0.0F, -0.1745F);
		right_ear.setTextureOffset(58, 20).addBox(-2.4826F, -7.0583F, -6.4195F, 2.0F, 9.0F, 1.0F, 0.0F, true);
		right_ear.setModelRendererName("right_ear");
		this.registerModelRenderer(right_ear);

		left_ear = new AnimatedModelRenderer(this);
		left_ear.setRotationPoint(0.0F, 1.0F, -6.0F);
		body.addChild(left_ear);
		setRotationAngle(left_ear, -0.0873F, 0.0F, 0.1745F);
		left_ear.setTextureOffset(58, 10).addBox(0.5174F, -7.0583F, -6.4195F, 2.0F, 9.0F, 1.0F, 0.0F, true);
		left_ear.setModelRendererName("left_ear");
		this.registerModelRenderer(left_ear);

		tail = new AnimatedModelRenderer(this);
		tail.setRotationPoint(0.0F, 1.5F, 0.25F);
		body.addChild(tail);
		tail.setTextureOffset(28, 13).addBox(-2.0F, -2.4132F, 0.2398F, 0.0F, 6.0F, 6.0F, 0.0F, true);
		tail.setTextureOffset(28, 13).addBox(2.0F, -2.4132F, 0.2398F, 0.0F, 6.0F, 6.0F, 0.0F, true);
		tail.setModelRendererName("tail");
		this.registerModelRenderer(tail);

		tail_sub_0 = new AnimatedModelRenderer(this);
		tail_sub_0.setRotationPoint(0.0F, 7.6552F, -9.4416F);
		tail.addChild(tail_sub_0);
		tail_sub_0.setTextureOffset(28, 19).addBox(-3.0F, -9.9132F, 14.4898F, 6.0F, 6.0F, 0.0F, 0.0F, true);
		tail_sub_0.setTextureOffset(28, 19).addBox(-3.0F, -9.9132F, 10.4898F, 6.0F, 6.0F, 0.0F, 0.0F, true);
		tail_sub_0.setTextureOffset(22, 19).addBox(-3.0F, -4.9132F, 9.4898F, 6.0F, 0.0F, 6.0F, 0.0F, true);
		tail_sub_0.setTextureOffset(22, 19).addBox(-3.0F, -8.9132F, 9.4898F, 6.0F, 0.0F, 6.0F, 0.0F, true);
		tail_sub_0.setModelRendererName("tail_sub_0");
		this.registerModelRenderer(tail_sub_0);

    this.rootBones.add(body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/samukabu.json");
    }
}