package com.spectrobes.spectrobesmod.client.entity.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.komainu.EntityKomanoto;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.model.AnimatedModelRenderer;

// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.12.2 or 1.15.2 (same format for both) for entity models animated with GeckoLib
// Paste this class into your mod and follow the documentation for GeckoLib to use animations. You can find the documentation here: https://github.com/bernie-g/geckolib
// Blockbench plugin created by Gecko
public class KomanotoModel extends AnimatedEntityModel<EntityKomanoto> {

    private final AnimatedModelRenderer body;
	private final AnimatedModelRenderer leg_front_1;
	private final AnimatedModelRenderer leg_lower;
	private final AnimatedModelRenderer foot;
	private final AnimatedModelRenderer toes_middle;
	private final AnimatedModelRenderer leg_front_2;
	private final AnimatedModelRenderer leg_lower2;
	private final AnimatedModelRenderer foot2;
	private final AnimatedModelRenderer toes_middle2;
	private final AnimatedModelRenderer leg_back_2;
	private final AnimatedModelRenderer leg_lower4;
	private final AnimatedModelRenderer foot4;
	private final AnimatedModelRenderer toes_middle4;
	private final AnimatedModelRenderer body_adornment_1;
	private final AnimatedModelRenderer leg_back_1;
	private final AnimatedModelRenderer leg_lower3;
	private final AnimatedModelRenderer foot3;
	private final AnimatedModelRenderer toes_middle3;
	private final AnimatedModelRenderer body_adornment_2;
	private final AnimatedModelRenderer body_adornment_3;
	private final AnimatedModelRenderer body_adornment_4;
	private final AnimatedModelRenderer head;
	private final AnimatedModelRenderer ear_1;
	private final AnimatedModelRenderer ear_2;
	private final AnimatedModelRenderer decoration_1;
	private final AnimatedModelRenderer teeth;
	private final AnimatedModelRenderer canines_upper;
	private final AnimatedModelRenderer canines_lower;
	private final AnimatedModelRenderer eyes;

    public KomanotoModel()
    {
        textureWidth = 128;
		textureHeight = 128;
		body = new AnimatedModelRenderer(this);
		body.setRotationPoint(0.0F, 18.0F, 0.0F);
		body.setTextureOffset(32, 0).addBox(-8.0F, -26.0F, -8.0F, 16.0F, 16.0F, 32.0F, 0.0F, true);
		body.setModelRendererName("body");
		this.registerModelRenderer(body);

		leg_front_1 = new AnimatedModelRenderer(this);
		leg_front_1.setRotationPoint(8.5F, -16.8333F, -2.6667F);
		body.addChild(leg_front_1);
		setRotationAngle(leg_front_1, 0.1745F, 0.0F, 0.0F);
		leg_front_1.setTextureOffset(0, 45).addBox(-0.5F, -3.1667F, -6.3333F, 7.0F, 10.0F, 10.0F, 0.0F, false);
		leg_front_1.setTextureOffset(68, 0).addBox(0.0F, 6.8333F, -5.3333F, 6.0F, 7.0F, 7.0F, 0.0F, false);
		leg_front_1.setModelRendererName("leg_front_1");
		this.registerModelRenderer(leg_front_1);

		leg_lower = new AnimatedModelRenderer(this);
		leg_lower.setRotationPoint(2.5F, 13.2451F, -0.5882F);
		leg_front_1.addChild(leg_lower);
		setRotationAngle(leg_lower, 1.2217F, 0.0F, 0.0F);
		leg_lower.setTextureOffset(66, 0).addBox(-2.0F, -2.7096F, -8.6336F, 4.0F, 4.0F, 10.0F, 0.0F, false);
		leg_lower.setModelRendererName("leg_lower");
		this.registerModelRenderer(leg_lower);

		foot = new AnimatedModelRenderer(this);
		foot.setRotationPoint(0.0F, -2.0963F, -10.4481F);
		leg_lower.addChild(foot);
		setRotationAngle(foot, -1.4835F, 0.1745F, -0.6981F);
		foot.setTextureOffset(62, 0).addBox(-3.5F, -1.5F, -4.0F, 7.0F, 3.0F, 8.0F, 0.0F, false);
		foot.setTextureOffset(118, 59).addBox(-3.0F, -1.0F, -7.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		foot.setTextureOffset(118, 60).addBox(3.0F, -1.0F, 1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		foot.setModelRendererName("foot");
		this.registerModelRenderer(foot);

		toes_middle = new AnimatedModelRenderer(this);
		toes_middle.setRotationPoint(3.8232F, -0.5F, -4.1768F);
		foot.addChild(toes_middle);
		setRotationAngle(toes_middle, 0.0F, 0.7854F, 0.0F);
		toes_middle.setTextureOffset(116, 60).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		toes_middle.setModelRendererName("toes_middle");
		this.registerModelRenderer(toes_middle);

		leg_front_2 = new AnimatedModelRenderer(this);
		leg_front_2.setRotationPoint(-8.5F, -16.8333F, -2.6667F);
		body.addChild(leg_front_2);
		setRotationAngle(leg_front_2, 0.1745F, 0.0F, 0.0F);
		leg_front_2.setTextureOffset(0, 65).addBox(-6.5F, -3.1667F, -6.3333F, 7.0F, 10.0F, 10.0F, 0.0F, false);
		leg_front_2.setTextureOffset(68, 0).addBox(-6.0F, 6.8333F, -5.3333F, 6.0F, 7.0F, 7.0F, 0.0F, false);
		leg_front_2.setModelRendererName("leg_front_2");
		this.registerModelRenderer(leg_front_2);

		leg_lower2 = new AnimatedModelRenderer(this);
		leg_lower2.setRotationPoint(-3.5F, 13.2451F, -0.5882F);
		leg_front_2.addChild(leg_lower2);
		setRotationAngle(leg_lower2, 1.2217F, 0.0F, 0.0F);
		leg_lower2.setTextureOffset(66, 0).addBox(-2.0F, -2.7096F, -8.6336F, 4.0F, 4.0F, 10.0F, 0.0F, false);
		leg_lower2.setModelRendererName("leg_lower2");
		this.registerModelRenderer(leg_lower2);

		foot2 = new AnimatedModelRenderer(this);
		foot2.setRotationPoint(0.0F, -2.0963F, -10.4481F);
		leg_lower2.addChild(foot2);
		setRotationAngle(foot2, -1.4835F, 0.1745F, -0.6981F);
		foot2.setTextureOffset(66, 0).addBox(-3.5F, -1.5F, -4.0F, 7.0F, 3.0F, 8.0F, 0.0F, false);
		foot2.setTextureOffset(118, 59).addBox(-3.0F, -1.0F, -7.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		foot2.setTextureOffset(118, 60).addBox(3.0F, -1.0F, 1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		foot2.setModelRendererName("foot2");
		this.registerModelRenderer(foot2);

		toes_middle2 = new AnimatedModelRenderer(this);
		toes_middle2.setRotationPoint(3.8232F, -0.5F, -4.1768F);
		foot2.addChild(toes_middle2);
		setRotationAngle(toes_middle2, 0.0F, 0.7854F, 0.0F);
		toes_middle2.setTextureOffset(116, 60).addBox(-2.0F, -0.5F, -1.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		toes_middle2.setModelRendererName("toes_middle2");
		this.registerModelRenderer(toes_middle2);

		leg_back_2 = new AnimatedModelRenderer(this);
		leg_back_2.setRotationPoint(-8.5F, -15.8333F, 19.3333F);
		body.addChild(leg_back_2);
		setRotationAngle(leg_back_2, -0.6109F, 0.0F, 0.0F);
		leg_back_2.setTextureOffset(68, 49).addBox(-6.5F, -6.2068F, -5.0533F, 7.0F, 12.0F, 12.0F, 0.0F, false);
		leg_back_2.setTextureOffset(64, 0).addBox(-6.0F, 5.7932F, -4.0533F, 6.0F, 7.0F, 10.0F, 0.0F, false);
		leg_back_2.setModelRendererName("leg_back_2");
		this.registerModelRenderer(leg_back_2);

		leg_lower4 = new AnimatedModelRenderer(this);
		leg_lower4.setRotationPoint(-3.5F, 12.4856F, -0.5594F);
		leg_back_2.addChild(leg_lower4);
		setRotationAngle(leg_lower4, 2.4435F, 0.0F, 0.0F);
		leg_lower4.setTextureOffset(64, 0).addBox(-2.0F, -0.9054F, -10.7558F, 5.0F, 5.0F, 11.0F, 0.0F, false);
		leg_lower4.setModelRendererName("leg_lower4");
		this.registerModelRenderer(leg_lower4);

		foot4 = new AnimatedModelRenderer(this);
		foot4.setRotationPoint(23.5804F, -3.4945F, -13.3755F);
		leg_lower4.addChild(foot4);
		setRotationAngle(foot4, -1.5708F, 0.0873F, -0.7854F);
		foot4.setTextureOffset(64, 0).addBox(-23.6572F, -1.3457F, -17.1161F, 8.0F, 3.0F, 8.0F, 0.0F, false);
		foot4.setTextureOffset(118, 59).addBox(-22.1572F, -0.8457F, -20.1161F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		foot4.setTextureOffset(118, 60).addBox(-16.1572F, -0.8457F, -12.1161F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		foot4.setModelRendererName("foot4");
		this.registerModelRenderer(foot4);

		toes_middle4 = new AnimatedModelRenderer(this);
		toes_middle4.setRotationPoint(16.7429F, -6.4909F, -19.7237F);
		foot4.addChild(toes_middle4);
		setRotationAngle(toes_middle4, 0.0F, 0.7854F, 0.0F);
		toes_middle4.setTextureOffset(116, 60).addBox(-26.4005F, 5.6452F, -21.9629F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		toes_middle4.setModelRendererName("toes_middle4");
		this.registerModelRenderer(toes_middle4);

		body_adornment_1 = new AnimatedModelRenderer(this);
		body_adornment_1.setRotationPoint(-6.5F, -5.6667F, 5.6667F);
		leg_back_2.addChild(body_adornment_1);
		setRotationAngle(body_adornment_1, -0.6981F, 0.0F, 0.0F);
		body_adornment_1.setTextureOffset(49, 76).addBox(0.0F, -16.4319F, -8.4824F, 0.0F, 29.0F, 18.0F, 0.0F, false);
		body_adornment_1.setModelRendererName("body_adornment_1");
		this.registerModelRenderer(body_adornment_1);

		leg_back_1 = new AnimatedModelRenderer(this);
		leg_back_1.setRotationPoint(8.5F, -15.8333F, 19.3333F);
		body.addChild(leg_back_1);
		setRotationAngle(leg_back_1, -0.5236F, 0.0F, 0.0F);
		leg_back_1.setTextureOffset(68, 49).addBox(-0.5F, -6.0597F, -4.9595F, 7.0F, 12.0F, 12.0F, 0.0F, false);
		leg_back_1.setTextureOffset(64, 0).addBox(0.0F, 5.9403F, -3.9595F, 6.0F, 7.0F, 10.0F, 0.0F, false);
		leg_back_1.setModelRendererName("leg_back_1");
		this.registerModelRenderer(leg_back_1);

		leg_lower3 = new AnimatedModelRenderer(this);
		leg_lower3.setRotationPoint(2.5F, 12.4856F, -0.5594F);
		leg_back_1.addChild(leg_lower3);
		setRotationAngle(leg_lower3, 2.4435F, 0.0F, 0.0F);
		leg_lower3.setTextureOffset(64, 0).addBox(-2.0F, -0.9579F, -10.9222F, 5.0F, 5.0F, 11.0F, 0.0F, false);
		leg_lower3.setModelRendererName("leg_lower3");
		this.registerModelRenderer(leg_lower3);

		foot3 = new AnimatedModelRenderer(this);
		foot3.setRotationPoint(0.5804F, -3.4945F, -13.3755F);
		leg_lower3.addChild(foot3);
		setRotationAngle(foot3, -1.6581F, -0.0873F, -0.6981F);
		foot3.setTextureOffset(64, 0).addBox(-7.4029F, -2.9494F, -0.7343F, 8.0F, 3.0F, 8.0F, 0.0F, false);
		foot3.setTextureOffset(118, 59).addBox(-5.9029F, -2.4494F, -3.7343F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		foot3.setTextureOffset(118, 60).addBox(0.0971F, -2.4494F, 4.2657F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		foot3.setModelRendererName("foot3");
		this.registerModelRenderer(foot3);

		toes_middle3 = new AnimatedModelRenderer(this);
		toes_middle3.setRotationPoint(16.7429F, -6.4909F, -19.7237F);
		foot3.addChild(toes_middle3);
		setRotationAngle(toes_middle3, 0.0F, 0.7854F, 0.0F);
		toes_middle3.setTextureOffset(116, 60).addBox(-26.4908F, 4.0415F, 1.1143F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		toes_middle3.setModelRendererName("toes_middle3");
		this.registerModelRenderer(toes_middle3);

		body_adornment_2 = new AnimatedModelRenderer(this);
		body_adornment_2.setRotationPoint(6.75F, -5.6667F, 5.6667F);
		leg_back_1.addChild(body_adornment_2);
		setRotationAngle(body_adornment_2, -0.6981F, 0.0F, 0.0F);
		body_adornment_2.setTextureOffset(49, 76).addBox(0.0F, -16.3794F, -8.316F, 0.0F, 29.0F, 18.0F, 0.0F, false);
		body_adornment_2.setModelRendererName("body_adornment_2");
		this.registerModelRenderer(body_adornment_2);

		body_adornment_3 = new AnimatedModelRenderer(this);
		body_adornment_3.setRotationPoint(6.25F, -32.2154F, 13.2567F);
		body.addChild(body_adornment_3);
		setRotationAngle(body_adornment_3, 2.3562F, 0.3491F, 0.0F);
		body_adornment_3.setTextureOffset(31, 76).addBox(0.0F, -14.5F, -9.0F, 0.0F, 29.0F, 18.0F, 0.0F, false);
		body_adornment_3.setModelRendererName("body_adornment_3");
		this.registerModelRenderer(body_adornment_3);

		body_adornment_4 = new AnimatedModelRenderer(this);
		body_adornment_4.setRotationPoint(6.25F, -32.2154F, 18.2567F);
		body.addChild(body_adornment_4);
		setRotationAngle(body_adornment_4, 2.3562F, -0.3491F, 0.0F);
		body_adornment_4.setTextureOffset(31, 76).addBox(-14.0F, -14.5F, -9.0F, 0.0F, 29.0F, 18.0F, 0.0F, false);
		body_adornment_4.setModelRendererName("body_adornment_4");
		this.registerModelRenderer(body_adornment_4);

		head = new AnimatedModelRenderer(this);
		head.setRotationPoint(0.0F, -5.2857F, -4.9286F);
		head.setTextureOffset(0, 0).addBox(-8.0F, -11.7143F, -16.0714F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-2.0F, -3.7143F, -17.0714F, 4.0F, 2.0F, 1.0F, 0.0F, false);
		head.setModelRendererName("head");
		this.registerModelRenderer(head);

		ear_1 = new AnimatedModelRenderer(this);
		ear_1.setRotationPoint(7.0F, -9.7143F, -5.0714F);
		head.addChild(ear_1);
		setRotationAngle(ear_1, -0.4363F, 0.7854F, 0.4363F);
		ear_1.setTextureOffset(104, 109).addBox(-3.0F, -10.0F, -3.0F, 6.0F, 13.0F, 6.0F, 0.0F, false);
		ear_1.setTextureOffset(102, 87).addBox(-3.0F, -10.0588F, 2.7764F, 6.0F, 11.0F, 7.0F, 0.0F, false);
		ear_1.setModelRendererName("ear_1");
		this.registerModelRenderer(ear_1);

		ear_2 = new AnimatedModelRenderer(this);
		ear_2.setRotationPoint(-12.0F, -10.7437F, -1.9332F);
		head.addChild(ear_2);
		setRotationAngle(ear_2, -0.4363F, -0.7854F, -0.4363F);
		ear_2.setTextureOffset(104, 109).addBox(-1.7183F, -4.6629F, -6.9426F, 6.0F, 13.0F, 6.0F, 0.0F, true);
		ear_2.setTextureOffset(102, 87).addBox(-1.7183F, -4.7217F, -1.1662F, 6.0F, 11.0F, 7.0F, 0.0F, true);
		ear_2.setModelRendererName("ear_2");
		this.registerModelRenderer(ear_2);

		decoration_1 = new AnimatedModelRenderer(this);
		decoration_1.setRotationPoint(0.0F, -14.7143F, -12.0714F);
		head.addChild(decoration_1);
		setRotationAngle(decoration_1, -0.7854F, 0.0F, 0.0F);
		decoration_1.setTextureOffset(0, 96).addBox(-8.0F, -3.9393F, -0.7071F, 16.0F, 10.0F, 0.0F, 0.0F, false);
		decoration_1.setModelRendererName("decoration_1");
		this.registerModelRenderer(decoration_1);

		teeth = new AnimatedModelRenderer(this);
		teeth.setRotationPoint(3.0F, -0.8988F, -15.5245F);
		head.addChild(teeth);
		setRotationAngle(teeth, -0.1745F, 0.0F, 0.0F);
		
		teeth.setModelRendererName("teeth");
		this.registerModelRenderer(teeth);

		canines_upper = new AnimatedModelRenderer(this);
		canines_upper.setRotationPoint(0.0F, 0.0F, 0.0F);
		teeth.addChild(canines_upper);
		canines_upper.setTextureOffset(118, 59).addBox(1.0F, -1.0F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		canines_upper.setTextureOffset(118, 59).addBox(-8.0F, -1.0F, -0.5F, 1.0F, 7.0F, 1.0F, 0.0F, false);
		canines_upper.setModelRendererName("canines_upper");
		this.registerModelRenderer(canines_upper);

		canines_lower = new AnimatedModelRenderer(this);
		canines_lower.setRotationPoint(0.0F, 0.0F, 0.0F);
		teeth.addChild(canines_lower);
		setRotationAngle(canines_lower, 0.4363F, 0.0F, 0.0F);
		canines_lower.setTextureOffset(118, 59).addBox(2.0F, -1.9392F, -1.1946F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		canines_lower.setTextureOffset(118, 59).addBox(-10.0F, -1.9392F, -1.1946F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		canines_lower.setModelRendererName("canines_lower");
		this.registerModelRenderer(canines_lower);

		eyes = new AnimatedModelRenderer(this);
		eyes.setRotationPoint(0.0F, -4.2143F, -16.0714F);
		head.addChild(eyes);
		setRotationAngle(eyes, 0.7854F, 0.0F, 0.0F);
		eyes.setTextureOffset(122, 72).addBox(3.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		eyes.setTextureOffset(122, 75).addBox(-5.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		eyes.setModelRendererName("eyes");
		this.registerModelRenderer(eyes);

		this.rootBones.add(body);
		this.rootBones.add(head);
	}


    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "animations/spectrobe/komanoto.json");
    }
}