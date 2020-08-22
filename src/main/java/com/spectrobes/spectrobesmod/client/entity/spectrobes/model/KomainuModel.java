package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomainu;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity model animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
public class KomainuModel extends AnimatedEntityModel<EntityKomainu> {

    private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer leg1;
	private final AnimatedModelRenderer leg2;
	private final AnimatedModelRenderer leg3;
	private final AnimatedModelRenderer leg4;
	private final AnimatedModelRenderer mane;
	private final AnimatedModelRenderer tail;

    public KomainuModel()
    {
        textureWidth = 64;
		textureHeight = 32;
		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(1.0F, 12.5F, -8.0F);
		head.setTextureOffset(0, 0).addBox(-5.0F, -5.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, true);
		head.setTextureOffset(4, 21).addBox(2.0F, -6.5F, -2.0F, 2.0F, 5.0F, 6.0F, 0.0F, true);
		head.setTextureOffset(4, 21).addBox(-6.0F, -6.5F, -2.0F, 2.0F, 5.0F, 6.0F, 0.0F, true);
		head.setTextureOffset(40, 6).addBox(-2.5F, -1.02F, -5.0F, 3.0F, 2.0F, 2.0F, 0.0F, true);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 15.0F, 2.0F);
		setRotationAngle(body, 0.0F, 0.0F, -3.1416F);
		body.setTextureOffset(20, 10).addBox(-4.0F, -3.0F, -8.0F, 8.0F, 8.0F, 14.0F, 0.0F, true);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		leg1 = new AnimatedModelRenderer(this);
		leg1.setRotationPoint(2.5F, 1.0F, 5.0F);
		body.addChild(leg1);
		leg1.setTextureOffset(56, 14).addBox(-1.0F, -10.0F, -2.0F, 2.0F, 8.0F, 2.0F, 0.0F, true);
		leg1.setModelRendererName("leg1");
		this.registerModelRenderer(leg1);

		leg2 = new AnimatedModelRenderer(this);
		leg2.setRotationPoint(-0.5F, 1.0F, 5.0F);
		body.addChild(leg2);
		leg2.setTextureOffset(56, 14).addBox(-3.0F, -10.0F, -2.0F, 2.0F, 8.0F, 2.0F, 0.0F, true);
		leg2.setModelRendererName("leg2");
		this.registerModelRenderer(leg2);

		leg3 = new AnimatedModelRenderer(this);
		leg3.setRotationPoint(2.5F, 1.0F, -6.0F);
		body.addChild(leg3);
		leg3.setTextureOffset(56, 14).addBox(-1.0F, -10.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, true);
		leg3.setModelRendererName("leg3");
		this.registerModelRenderer(leg3);

		leg4 = new AnimatedModelRenderer(this);
		leg4.setRotationPoint(-0.5F, 1.0F, -6.0F);
		body.addChild(leg4);
		leg4.setTextureOffset(56, 14).addBox(-3.0F, -10.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, true);
		leg4.setModelRendererName("leg4");
		this.registerModelRenderer(leg4);

		mane = new AnimatedModelRenderer(this);
		mane.setRotationPoint(1.0F, 14.0F, 2.0F);
		
		mane.setModelRendererName("mane");
		this.registerModelRenderer(mane);

		tail = new AnimatedModelRenderer(this);
		tail.setRotationPoint(1.0F, 12.0F, 8.0F);
		
		tail.setModelRendererName("tail");
		this.registerModelRenderer(tail);

		this.rootBones.add(head);
		this.rootBones.add(body);
		this.rootBones.add(mane);
		this.rootBones.add(tail);
	}

	@Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/komainu.json");
    }
}