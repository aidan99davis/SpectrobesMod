package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.NaguryuModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.nagu.EntityNaguryu;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class NaguryuRenderer extends GeoEntityRenderer<EntityNaguryu> {

    public NaguryuRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new NaguryuModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityNaguryu entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/naguryu_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/naguryu_1.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/naguryu_2.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/naguryu_0.png");

        }
    }

    @Override
    public void render(EntityNaguryu animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
