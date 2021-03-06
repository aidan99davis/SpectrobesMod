package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.GrildaModel;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.NaguModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda.EntityGrilda;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.nagu.EntityNagu;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.resource.GeckoLibCache;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class NaguRenderer extends GeoEntityRenderer<EntityNagu> {

    public NaguRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NaguModel());
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntityNagu entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/nagu_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/nagu_1.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/nagu_2.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/nagu_0.png");

        }
    }

    @Override
    public void render(EntityNagu entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
