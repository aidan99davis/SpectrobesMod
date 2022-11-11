package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.KasumiModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kasumi.EntityKasumi;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class KasumiRenderer extends GeoEntityRenderer<EntityKasumi> {

    public KasumiRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new KasumiModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityKasumi entity)
    {
        return switch (entity.getSpectrobeData().Variant) {
            case 1 -> new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/kasumi_1.png");
            case 2 -> new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/kasumi_2.png");
            default -> new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/kasumi_0.png");
        };
    }

    @Override
    public void render(EntityKasumi animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.3f, 0.3f, 0.3f);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
