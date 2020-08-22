package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.KomanotoModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomanoto;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class KomanotoRenderer extends MobRenderer<EntityKomanoto, KomanotoModel> {

    public KomanotoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KomanotoModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntityKomanoto entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/komanoto.png");
    }

    @Override
    public void render(EntityKomanoto entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.scale(0.75f, 0.75f, 0.75f);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        /*matrixStackIn.pop();
        matrixStackIn.push();*/
    }
}
