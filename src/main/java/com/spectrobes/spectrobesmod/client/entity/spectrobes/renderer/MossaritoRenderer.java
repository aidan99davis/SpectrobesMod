package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.MossariModel;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.MossaritoModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.mossari.EntityMossari;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.mossari.EntityMossarito;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class MossaritoRenderer extends GeoEntityRenderer<EntityMossarito> {

    public MossaritoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MossaritoModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityMossarito entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/mossarito_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/mossarito_1.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/mossarito_2.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/mossarito_0.png");

        }
    }

    @Override
    public void render(EntityMossarito entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
