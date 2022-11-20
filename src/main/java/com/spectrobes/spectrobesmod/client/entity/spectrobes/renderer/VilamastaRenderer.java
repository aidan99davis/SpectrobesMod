package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.VilamastaModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilamasta;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class VilamastaRenderer extends GeoEntityRenderer<EntityVilamasta> {

    public VilamastaRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new VilamastaModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityVilamasta entity)
    {
        return switch (entity.getSpectrobeData().Variant) {
            case 1 -> new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/vilamasta_1.png");
            case 2 -> new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/vilamasta_2.png");
            default -> new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/vilamasta_0.png");
        };
    }

    @Override
    public void render(EntityVilamasta animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.5f, 1.5f, 1.5f);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
