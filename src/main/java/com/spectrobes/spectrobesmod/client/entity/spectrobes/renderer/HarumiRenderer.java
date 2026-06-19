package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.HarumiModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.harumi.EntityHarumi;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class HarumiRenderer extends GeoEntityRenderer<EntityHarumi> {

    public HarumiRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new HarumiModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityHarumi entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/harumi_0.png");
            case 1:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/harumi_1.png");
            case 2:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/harumi_2.png");
            default:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/harumi_0.png");

        }
    }

    @Override
    public void render(EntityHarumi animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.5f, 1.5f, 1.5f);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
