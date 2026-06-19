package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.HarumiteModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.harumi.EntityHarumite;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class HarumiteRenderer extends GeoEntityRenderer<EntityHarumite> {

    public HarumiteRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new HarumiteModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityHarumite entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/harumite_0.png");
            case 1:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/harumite_1.png");
            case 2:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/harumite_2.png");
            default:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/harumite_0.png");

        }
    }

    @Override
    public void render(EntityHarumite animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(2, 2, 2);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
