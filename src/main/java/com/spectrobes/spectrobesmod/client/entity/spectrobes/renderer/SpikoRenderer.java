package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SpikoModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpiko;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SpikoRenderer extends GeoEntityRenderer<EntitySpiko> {

    public SpikoRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SpikoModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntitySpiko entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/spiko.png");
    }

    @Override
    public void render(EntitySpiko animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.5f, 0.5f, 0.5f);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
