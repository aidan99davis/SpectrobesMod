package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.KomanotoModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomanoto;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class KomanotoRenderer extends GeoEntityRenderer<EntityKomanoto> {

    public KomanotoRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new KomanotoModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityKomanoto entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/komanoto_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/komanoto_1.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/komanoto_2.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/komanoto_0.png");

        }
    }

    @Override
    public void render(EntityKomanoto animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.75f, 0.75f, 0.75f);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
