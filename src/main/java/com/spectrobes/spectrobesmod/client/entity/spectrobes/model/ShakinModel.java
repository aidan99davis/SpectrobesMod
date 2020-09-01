// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin.EntityShakin;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class ShakinModel extends AnimatedEntityModel<EntityShakin> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer blade;
	private final AnimatedModelRenderer blade2;
	private final AnimatedModelRenderer blade3;
	private final AnimatedModelRenderer blade4;
	private final AnimatedModelRenderer edge;
	private final AnimatedModelRenderer edge2;
	private final AnimatedModelRenderer edge3;
	private final AnimatedModelRenderer edge4;
	private final AnimatedModelRenderer edge5;
	private final AnimatedModelRenderer fins;
	private final AnimatedModelRenderer top;
	private final AnimatedModelRenderer bottom;
	private final AnimatedModelRenderer right;
	private final AnimatedModelRenderer left;
	private final AnimatedModelRenderer left_leg;
	private final AnimatedModelRenderer right_leg;

    public ShakinModel()
    {
        textureWidth = 32;
    	textureHeight = 32;
    	body = new AnimatedModelRenderer(this);
		body.setRotationPoint(-1.0F, 17.5F, 3.0F);
		body.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -4.0F, 2.0F, 2.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(23, 5).addBox(-0.5F, -1.0F, 3.25F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(15, 24).addBox(0.0F, -1.0F, 2.25F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(0, 19).addBox(0.0F, -2.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		blade = new AnimatedModelRenderer(this);
		blade.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(blade);
		blade.setTextureOffset(23, 13).addBox(0.5F, -2.0F, -8.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
		blade.setModelRendererName("blade");
		this.registerModelRenderer(blade);

		blade2 = new AnimatedModelRenderer(this);
		blade2.setRotationPoint(1.0F, 0.0F, -9.5F);
		blade.addChild(blade2);
		setRotationAngle(blade2, -0.3927F, 0.0F, 0.0F);
		blade2.setTextureOffset(15, 19).addBox(-0.5F, -1.6501F, -1.2315F, 1.0F, 2.0F, 3.0F, 0.0F, false);
		blade2.setModelRendererName("blade2");
		this.registerModelRenderer(blade2);

		blade3 = new AnimatedModelRenderer(this);
		blade3.setRotationPoint(0.0F, -1.0031F, -1.4979F);
		blade2.addChild(blade3);
		setRotationAngle(blade3, 0.3054F, 0.0F, 0.0F);
		blade3.setTextureOffset(23, 1).addBox(-0.5F, -0.6501F, -1.2315F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		blade3.setModelRendererName("blade3");
		this.registerModelRenderer(blade3);

		blade4 = new AnimatedModelRenderer(this);
		blade4.setRotationPoint(0.0F, 1.0304F, -3.1723F);
		blade3.addChild(blade4);
		setRotationAngle(blade4, -0.7418F, 0.0F, 0.0F);
		blade4.setTextureOffset(24, 18).addBox(-0.5F, -2.8129F, 0.9711F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		blade4.setModelRendererName("blade4");
		this.registerModelRenderer(blade4);

		edge = new AnimatedModelRenderer(this);
		edge.setRotationPoint(1.0F, 0.8478F, -6.2346F);
		blade.addChild(edge);
		setRotationAngle(edge, 0.0F, 0.0F, 0.7854F);
		edge.setTextureOffset(10, 23).addBox(-0.5F, -0.5F, -2.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		edge.setModelRendererName("edge");
		this.registerModelRenderer(edge);

		edge2 = new AnimatedModelRenderer(this);
		edge2.setRotationPoint(0.0F, 0.0F, -4.0F);
		edge.addChild(edge2);
		setRotationAngle(edge2, -0.2849F, 0.274F, -0.0395F);
		edge2.setTextureOffset(5, 22).addBox(-0.944F, -0.944F, -1.3436F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		edge2.setModelRendererName("edge2");
		this.registerModelRenderer(edge2);

		edge3 = new AnimatedModelRenderer(this);
		edge3.setRotationPoint(-0.2242F, 1.0258F, -2.4609F);
		edge2.addChild(edge3);
		setRotationAngle(edge3, -0.2849F, 0.274F, -0.0395F);
		edge3.setTextureOffset(0, 14).addBox(-0.9959F, -2.2459F, -0.1543F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		edge3.setModelRendererName("edge3");
		this.registerModelRenderer(edge3);

		edge4 = new AnimatedModelRenderer(this);
		edge4.setRotationPoint(0.5F, 0.5F, -2.1213F);
		edge3.addChild(edge4);
		setRotationAngle(edge4, -0.2849F, 0.274F, -0.0395F);
		edge4.setTextureOffset(12, 2).addBox(-1.9428F, -3.1928F, 0.2106F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		edge4.setModelRendererName("edge4");
		this.registerModelRenderer(edge4);

		edge5 = new AnimatedModelRenderer(this);
		edge5.setRotationPoint(-0.5412F, -0.5412F, -1.8478F);
		edge4.addChild(edge5);
		setRotationAngle(edge5, -0.2849F, 0.274F, -0.0395F);
		edge5.setTextureOffset(12, 0).addBox(-1.8805F, -3.1305F, 0.3461F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		edge5.setModelRendererName("edge5");
		this.registerModelRenderer(edge5);

		fins = new AnimatedModelRenderer(this);
		fins.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(fins);
		
		fins.setModelRendererName("fins");
		this.registerModelRenderer(fins);

		top = new AnimatedModelRenderer(this);
		top.setRotationPoint(1.0F, -3.5F, -3.5F);
		fins.addChild(top);
		setRotationAngle(top, 0.6155F, 0.5236F, -0.6155F);
		top.setTextureOffset(18, 7).addBox(-0.9393F, 0.0607F, -2.2929F, 1.0F, 3.0F, 3.0F, 0.0F, false);
		top.setTextureOffset(14, 15).addBox(-2.9393F, 0.0607F, -2.2929F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		top.setModelRendererName("top");
		this.registerModelRenderer(top);

		bottom = new AnimatedModelRenderer(this);
		bottom.setRotationPoint(1.0F, 2.5F, -3.75F);
		fins.addChild(bottom);
		setRotationAngle(bottom, -1.0409F, 0.7119F, 0.3655F);
		bottom.setTextureOffset(0, 0).addBox(-0.9393F, -4.0607F, -2.2929F, 1.0F, 4.0F, 3.0F, 0.0F, false);
		bottom.setTextureOffset(0, 10).addBox(-3.9393F, -1.0607F, -2.2929F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		bottom.setModelRendererName("bottom");
		this.registerModelRenderer(bottom);

		right = new AnimatedModelRenderer(this);
		right.setRotationPoint(-1.75F, -2.0F, -2.75F);
		fins.addChild(right);
		setRotationAngle(right, 0.6155F, -0.5236F, -0.1699F);
		right.setTextureOffset(8, 15).addBox(-0.0607F, 0.0607F, -3.2929F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		right.setTextureOffset(0, 14).addBox(-0.0607F, 0.0607F, -3.2929F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		right.setModelRendererName("right");
		this.registerModelRenderer(right);

		left = new AnimatedModelRenderer(this);
		left.setRotationPoint(3.75F, -2.0F, -2.75F);
		fins.addChild(left);
		setRotationAngle(left, 0.6155F, 0.5236F, 0.1699F);
		left.setTextureOffset(12, 0).addBox(-0.9393F, 0.0607F, -3.2929F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		left.setTextureOffset(10, 10).addBox(-1.9393F, 0.0607F, -3.2929F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		left.setModelRendererName("left");
		this.registerModelRenderer(left);

		left_leg = new AnimatedModelRenderer(this);
		left_leg.setRotationPoint(1.5F, 0.5F, -0.5F);
		body.addChild(left_leg);
		setRotationAngle(left_leg, -1.0093F, 0.36F, -0.3504F);
		left_leg.setTextureOffset(20, 21).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		left_leg.setModelRendererName("left_leg");
		this.registerModelRenderer(left_leg);

		right_leg = new AnimatedModelRenderer(this);
		right_leg.setRotationPoint(0.5F, 0.5F, -0.5F);
		body.addChild(right_leg);
		setRotationAngle(right_leg, -1.0093F, -0.36F, 0.3504F);
		right_leg.setTextureOffset(18, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		right_leg.setModelRendererName("right_leg");
		this.registerModelRenderer(right_leg);

    this.rootBones.add(body);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("spectrobesmod", "animations/spectrobe/shakin.json");
    }
}