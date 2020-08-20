package com.spectrobes.spectrobesmod.client.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.model.SamuriteModel;
import com.spectrobes.spectrobesmod.client.entity.model.SpikoModel;
import com.spectrobes.spectrobesmod.common.entities.samubaku.EntitySamurite;
import com.spectrobes.spectrobesmod.common.entities.spiko.EntitySpiko;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SamuriteRenderer extends MobRenderer<EntitySamurite, SamuriteModel> {

    public SamuriteRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SamuriteModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntitySamurite entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/samurite.png");
    }

    @Override
    public void render(EntitySamurite entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        //matrixStackIn.scale(0.5f, 0.5f, 0.5f);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}
