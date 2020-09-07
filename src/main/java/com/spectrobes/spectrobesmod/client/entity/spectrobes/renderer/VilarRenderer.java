package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SpikoModel;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.VilarModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpiko;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilar;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class VilarRenderer extends MobRenderer<EntityVilar, VilarModel> {

    public VilarRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new VilarModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntityVilar entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/vilar.png");
    }

    @Override
    public void render(EntityVilar entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.scale(0.75f, 0.75f, 0.75f);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}
