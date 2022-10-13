package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.BartorModel;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.MasettoModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.bartor.EntityBartor;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.masetto.EntityMasetto;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class MasettoRenderer extends GeoEntityRenderer<EntityMasetto> {

    public MasettoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MasettoModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityMasetto entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/masetto_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/masetto_1.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/masetto_2.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/masetto_0.png");

        }
    }

    @Override
    public void render(EntityMasetto entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
