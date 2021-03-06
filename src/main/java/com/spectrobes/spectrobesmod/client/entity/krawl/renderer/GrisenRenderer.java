package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.GrisModel;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.GrisenModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityGris;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityGrisen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class GrisenRenderer extends GeoEntityRenderer<EntityGrisen> {

    public GrisenRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GrisenModel());
    }

    @Override
    public void render(EntityGrisen entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.scale(2, 2, 2);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}
