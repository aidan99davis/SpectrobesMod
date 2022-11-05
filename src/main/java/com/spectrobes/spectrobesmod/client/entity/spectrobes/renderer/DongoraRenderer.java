package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.DongoraModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.dongor.EntityDongora;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class DongoraRenderer extends GeoEntityRenderer<EntityDongora> {

    public DongoraRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new DongoraModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityDongora entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/dongora_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/dongora_1.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/dongora_2.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/dongora_0.png");

        }
    }

    @Override
    public void render(EntityDongora animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(2.5f, 2.5f, 2.5f);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
