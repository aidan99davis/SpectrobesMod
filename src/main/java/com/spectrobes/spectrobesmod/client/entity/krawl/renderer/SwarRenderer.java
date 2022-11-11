package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.SwarModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySwar;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SwarRenderer extends GeoEntityRenderer<EntitySwar> {

    public SwarRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SwarModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntitySwar entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/krawl/swar.png");
    }

    @Override
    public void render(EntitySwar animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
