package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.GrisModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityGris;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class GrisRenderer extends GeoEntityRenderer<EntityGris> {

    public GrisRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new GrisModel());
    }

    @Override
    public void render(EntityGris animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(2, 2, 2);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
