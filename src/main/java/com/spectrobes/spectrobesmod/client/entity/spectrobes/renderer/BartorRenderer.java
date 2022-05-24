package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.AoiModel;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.BartorModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.aoi.EntityAoi;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.bartor.EntityBartor;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.resource.GeckoLibCache;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class BartorRenderer extends GeoEntityRenderer<EntityBartor> {

    public BartorRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BartorModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityBartor entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/bartor_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/bartor_0.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/bartor_0.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/bartor_0.png");

        }
    }

    @Override
    public void render(EntityBartor entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
