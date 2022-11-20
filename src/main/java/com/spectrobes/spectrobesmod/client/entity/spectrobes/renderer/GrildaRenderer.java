package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.GrildaModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda.EntityGrilda;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.resource.GeckoLibCache;

import javax.annotation.Nullable;

public class GrildaRenderer extends GeoEntityRenderer<EntityGrilda> {

    public GrildaRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new GrildaModel());
        GeckoLibCache.getInstance().parser.setValue("anim_speed", () -> 0.5d);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityGrilda entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/grilda_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/grilda_1.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/grilda_2.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/grilda_0.png");

        }
    }

    @Override
    public void render(EntityGrilda animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
