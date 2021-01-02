package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SeguModel;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SegulosModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegulos;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.zoza.EntityZozane;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SegulosRenderer extends GeoEntityRenderer<EntitySegulos> {

    public SegulosRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SegulosModel());
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntitySegulos entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/segulos_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/segulos_1.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/segulos_2.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/segulos_0.png");

        }
    }

    @Override
    public void render(EntitySegulos entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.scale(2.5f, 2.5f, 2.5f);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}
