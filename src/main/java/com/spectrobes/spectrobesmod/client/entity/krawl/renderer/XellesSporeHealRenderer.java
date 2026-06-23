package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.XellesSporeHealModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityHealingSpore;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class XellesSporeHealRenderer extends GeoEntityRenderer<EntityHealingSpore> {

    public XellesSporeHealRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new XellesSporeHealModel());
    }

    @Override
    public void render(EntityHealingSpore animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(4,4,4);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
