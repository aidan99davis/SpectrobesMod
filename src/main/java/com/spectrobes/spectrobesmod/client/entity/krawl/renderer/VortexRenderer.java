package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.VortexModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class VortexRenderer extends GeoEntityRenderer<EntityVortex> {

    public VortexRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new VortexModel());
    }

    @Override
    public void render(EntityVortex animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(3f, 3f, 3f);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
