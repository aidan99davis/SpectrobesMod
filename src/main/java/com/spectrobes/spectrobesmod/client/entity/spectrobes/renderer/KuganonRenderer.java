package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.KuganonModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKuganon;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class KuganonRenderer extends GeoEntityRenderer<EntityKuganon> {

    public KuganonRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new KuganonModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityKuganon entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/kuganon.png");
    }

    @Override
    public void render(EntityKuganon animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.5f, 1.5f, 1.5f);
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
