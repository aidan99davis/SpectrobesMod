// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
package com.spectrobes.spectrobesmod.client.entity.spectrobes.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKubaku;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class KubakuModel extends AnimatedEntityModel<EntityKubaku> {

    private final AnimatedModelRenderer spectrobe;
	private final AnimatedModelRenderer bowl;
	private final AnimatedModelRenderer cyl;
	private final AnimatedModelRenderer face;
	private final AnimatedModelRenderer face2;
	private final AnimatedModelRenderer face3;
	private final AnimatedModelRenderer face4;
	private final AnimatedModelRenderer face5;
	private final AnimatedModelRenderer face6;
	private final AnimatedModelRenderer face7;
	private final AnimatedModelRenderer face8;
	private final AnimatedModelRenderer face9;
	private final AnimatedModelRenderer face10;
	private final AnimatedModelRenderer face11;
	private final AnimatedModelRenderer face12;
	private final AnimatedModelRenderer face13;
	private final AnimatedModelRenderer face14;
	private final AnimatedModelRenderer face15;
	private final AnimatedModelRenderer face16;
	private final AnimatedModelRenderer leader;
	private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer hair;
	private final AnimatedModelRenderer hair2;
	private final AnimatedModelRenderer hair3;
	private final AnimatedModelRenderer hair4;
	private final AnimatedModelRenderer hair5;
	private final AnimatedModelRenderer hair6;
	private final AnimatedModelRenderer hair7;
	private final AnimatedModelRenderer hair8;

    public KubakuModel()
    {
        textureWidth = 64;
    	textureHeight = 64;
    	spectrobe = new AnimatedModelRenderer(this);
		spectrobe.setRotationPoint(0.0F, 0.0F, 0.0F);
		
		spectrobe.setModelRendererName("spectrobe");
		this.registerModelRenderer(spectrobe);

		bowl = new AnimatedModelRenderer(this);
		bowl.setRotationPoint(3.0F, 27.0F, -3.0F);
		spectrobe.addChild(bowl);
		bowl.setTextureOffset(16, 19).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
		bowl.setTextureOffset(0, 9).addBox(-3.0F, -8.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);
		bowl.setTextureOffset(0, 0).addBox(-3.5F, -7.0F, -3.5F, 7.0F, 2.0F, 7.0F, 0.0F, false);
		bowl.setModelRendererName("bowl");
		this.registerModelRenderer(bowl);

		cyl = new AnimatedModelRenderer(this);
		cyl.setRotationPoint(3.5F, 26.0F, 5.5F);
		spectrobe.addChild(cyl);
		cyl.setTextureOffset(27, 6).addBox(-1.5F, -3.25F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		cyl.setTextureOffset(24, 15).addBox(-1.5F, -7.75F, -1.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		cyl.setModelRendererName("cyl");
		this.registerModelRenderer(cyl);

		face = new AnimatedModelRenderer(this);
		face.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face);
		face.setTextureOffset(38, 38).addBox(-2.0F, -9.0F, 0.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face.setModelRendererName("face");
		this.registerModelRenderer(face);

		face2 = new AnimatedModelRenderer(this);
		face2.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face2);
		setRotationAngle(face2, 0.0F, -0.3927F, 0.0F);
		face2.setTextureOffset(0, 38).addBox(-1.8478F, -9.0F, -0.2346F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face2.setModelRendererName("face2");
		this.registerModelRenderer(face2);

		face3 = new AnimatedModelRenderer(this);
		face3.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face3);
		setRotationAngle(face3, 0.0F, -0.7854F, 0.0F);
		face3.setTextureOffset(29, 37).addBox(-1.7969F, -9.0F, -0.5097F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face3.setModelRendererName("face3");
		this.registerModelRenderer(face3);

		face4 = new AnimatedModelRenderer(this);
		face4.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face4);
		setRotationAngle(face4, 0.0F, -1.1781F, 0.0F);
		face4.setTextureOffset(36, 16).addBox(-1.8552F, -9.0F, -0.7832F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face4.setModelRendererName("face4");
		this.registerModelRenderer(face4);

		face5 = new AnimatedModelRenderer(this);
		face5.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face5);
		setRotationAngle(face5, 0.0F, -1.5708F, 0.0F);
		face5.setTextureOffset(36, 0).addBox(-2.0137F, -9.0F, -1.0137F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face5.setModelRendererName("face5");
		this.registerModelRenderer(face5);

		face6 = new AnimatedModelRenderer(this);
		face6.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face6);
		setRotationAngle(face6, 0.0F, -1.9635F, 0.0F);
		face6.setTextureOffset(25, 36).addBox(-2.2483F, -9.0F, -1.1659F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face6.setModelRendererName("face6");
		this.registerModelRenderer(face6);

		face7 = new AnimatedModelRenderer(this);
		face7.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face7);
		setRotationAngle(face7, 0.0F, -2.3562F, 0.0F);
		face7.setTextureOffset(34, 26).addBox(-2.5233F, -9.0F, -1.2168F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face7.setModelRendererName("face7");
		this.registerModelRenderer(face7);

		face8 = new AnimatedModelRenderer(this);
		face8.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face8);
		setRotationAngle(face8, 0.0F, -2.7489F, 0.0F);
		face8.setTextureOffset(34, 34).addBox(-2.7969F, -9.0F, -1.1585F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face8.setModelRendererName("face8");
		this.registerModelRenderer(face8);

		face9 = new AnimatedModelRenderer(this);
		face9.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face9);
		setRotationAngle(face9, 0.0F, 3.1416F, 0.0F);
		face9.setTextureOffset(18, 34).addBox(-3.0273F, -9.0F, -1.0F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face9.setModelRendererName("face9");
		this.registerModelRenderer(face9);

		face10 = new AnimatedModelRenderer(this);
		face10.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face10);
		setRotationAngle(face10, 0.0F, 2.7489F, 0.0F);
		face10.setTextureOffset(14, 34).addBox(-3.1796F, -9.0F, -0.7654F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face10.setModelRendererName("face10");
		this.registerModelRenderer(face10);

		face11 = new AnimatedModelRenderer(this);
		face11.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face11);
		setRotationAngle(face11, 0.0F, 2.3562F, 0.0F);
		face11.setTextureOffset(10, 34).addBox(-3.2304F, -9.0F, -0.4903F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face11.setModelRendererName("face11");
		this.registerModelRenderer(face11);

		face12 = new AnimatedModelRenderer(this);
		face12.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face12);
		setRotationAngle(face12, 0.0F, 1.9635F, 0.0F);
		face12.setTextureOffset(6, 34).addBox(-3.1722F, -9.0F, -0.2168F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face12.setModelRendererName("face12");
		this.registerModelRenderer(face12);

		face13 = new AnimatedModelRenderer(this);
		face13.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face13);
		setRotationAngle(face13, 0.0F, 1.5708F, 0.0F);
		face13.setTextureOffset(33, 10).addBox(-3.0137F, -9.0F, 0.0137F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face13.setModelRendererName("face13");
		this.registerModelRenderer(face13);

		face14 = new AnimatedModelRenderer(this);
		face14.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face14);
		setRotationAngle(face14, 0.0F, 1.1781F, 0.0F);
		face14.setTextureOffset(32, 19).addBox(-2.779F, -9.0F, 0.1659F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face14.setModelRendererName("face14");
		this.registerModelRenderer(face14);

		face15 = new AnimatedModelRenderer(this);
		face15.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face15);
		setRotationAngle(face15, 0.0F, 0.7854F, 0.0F);
		face15.setTextureOffset(30, 30).addBox(-2.504F, -9.0F, 0.2168F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face15.setModelRendererName("face15");
		this.registerModelRenderer(face15);

		face16 = new AnimatedModelRenderer(this);
		face16.setRotationPoint(-0.5F, 1.0F, -0.5F);
		cyl.addChild(face16);
		setRotationAngle(face16, 0.0F, 0.3927F, 0.0F);
		face16.setTextureOffset(26, 29).addBox(-2.2304F, -9.0F, 0.1585F, 1.0F, 6.0F, 1.0F, 0.0F, false);
		face16.setModelRendererName("face16");
		this.registerModelRenderer(face16);

		leader = new AnimatedModelRenderer(this);
		leader.setRotationPoint(-4.0F, 26.0F, 2.0F);
		spectrobe.addChild(leader);
		
		leader.setModelRendererName("leader");
		this.registerModelRenderer(leader);

		body = new AnimatedModelRenderer(this);
		body.setRotationPoint(4.0F, -2.0F, -2.0F);
		leader.addChild(body);
		body.setTextureOffset(21, 0).addBox(-5.5F, -1.0F, 0.5F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		body.setTextureOffset(0, 19).addBox(-6.0F, -7.0F, 0.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		hair = new AnimatedModelRenderer(this);
		hair.setRotationPoint(4.0F, -2.0F, -2.0F);
		leader.addChild(hair);
		hair.setTextureOffset(18, 9).addBox(-5.5F, -8.5F, 0.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		hair.setModelRendererName("hair");
		this.registerModelRenderer(hair);

		hair2 = new AnimatedModelRenderer(this);
		hair2.setRotationPoint(-4.0F, -8.0F, 2.0F);
		hair.addChild(hair2);
		hair2.setTextureOffset(14, 29).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		hair2.setModelRendererName("hair2");
		this.registerModelRenderer(hair2);

		hair3 = new AnimatedModelRenderer(this);
		hair3.setRotationPoint(0.0F, -2.75F, 0.0F);
		hair2.addChild(hair3);
		setRotationAngle(hair3, -0.829F, 0.0F, 0.0F);
		hair3.setTextureOffset(6, 29).addBox(-1.0F, -2.4316F, -0.8599F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		hair3.setModelRendererName("hair3");
		this.registerModelRenderer(hair3);

		hair4 = new AnimatedModelRenderer(this);
		hair4.setRotationPoint(0.0F, -3.1415F, 0.3615F);
		hair3.addChild(hair4);
		setRotationAngle(hair4, -0.2182F, 0.0F, 0.0F);
		hair4.setTextureOffset(0, 0).addBox(-0.5F, -2.0425F, -1.0388F, 1.0F, 3.0F, 2.0F, 0.0F, false);
		hair4.setModelRendererName("hair4");
		this.registerModelRenderer(hair4);

		hair5 = new AnimatedModelRenderer(this);
		hair5.setRotationPoint(0.0F, -2.0F, 1.0F);
		hair4.addChild(hair5);
		setRotationAngle(hair5, -1.3526F, 0.0F, 0.0F);
		hair5.setTextureOffset(0, 29).addBox(-0.5F, -5.0187F, -0.4828F, 1.0F, 7.0F, 2.0F, 0.0F, false);
		hair5.setModelRendererName("hair5");
		this.registerModelRenderer(hair5);

		hair6 = new AnimatedModelRenderer(this);
		hair6.setRotationPoint(0.0F, -6.0F, 1.0F);
		hair5.addChild(hair6);
		setRotationAngle(hair6, -1.1345F, 0.0F, 0.0F);
		hair6.setTextureOffset(22, 29).addBox(-0.5F, -5.2414F, 0.2627F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		hair6.setModelRendererName("hair6");
		this.registerModelRenderer(hair6);

		hair7 = new AnimatedModelRenderer(this);
		hair7.setRotationPoint(0.0F, -6.0F, 0.0F);
		hair6.addChild(hair7);
		setRotationAngle(hair7, 1.2217F, 0.0F, 0.0F);
		hair7.setTextureOffset(0, 19).addBox(-0.5F, -1.554F, -1.281F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		hair7.setModelRendererName("hair7");
		this.registerModelRenderer(hair7);

		hair8 = new AnimatedModelRenderer(this);
		hair8.setRotationPoint(0.0F, -0.799F, -2.0885F);
		hair7.addChild(hair8);
		setRotationAngle(hair8, -1.7453F, 0.0F, 0.0F);
		hair8.setTextureOffset(0, 9).addBox(-0.5F, -1.6489F, -1.0574F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		hair8.setModelRendererName("hair8");
		this.registerModelRenderer(hair8);

    this.rootBones.add(spectrobe);
  }


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/kubaku.json");
    }
}