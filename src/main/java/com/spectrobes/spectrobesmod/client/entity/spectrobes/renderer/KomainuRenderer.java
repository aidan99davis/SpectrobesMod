package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.KomainuModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomainu;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class KomainuRenderer extends GeoEntityRenderer<EntityKomainu> {

    public KomainuRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new KomainuModel());
    }

    @Override
    public void render(EntityKomainu animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.5f, 1.5f, 1.5f);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityKomainu entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/komainu_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/komainu_1.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/komainu_2.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/komainu_0.png");
        }
    }
}
