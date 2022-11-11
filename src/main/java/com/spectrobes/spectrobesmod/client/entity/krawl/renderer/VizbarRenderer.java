package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.VizbarModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVizbar;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class VizbarRenderer extends GeoEntityRenderer<EntityVizbar> {

    public VizbarRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new VizbarModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityVizbar entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/krawl/vizbar.png");
    }

    @Override
    public void render(EntityVizbar animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
