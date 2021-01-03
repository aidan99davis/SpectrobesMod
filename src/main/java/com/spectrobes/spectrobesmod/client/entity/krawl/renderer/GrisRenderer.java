package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.GrisModel;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.SwarModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityGris;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySwar;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class GrisRenderer extends GeoEntityRenderer<EntityGris> {

    public GrisRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GrisModel());
    }

    @Override
    public void render(EntityGris entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.scale(2, 2, 2);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}
