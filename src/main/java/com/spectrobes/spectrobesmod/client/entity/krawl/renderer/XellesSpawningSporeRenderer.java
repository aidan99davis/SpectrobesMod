package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.XellesSpawningSporeModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySpawningSpore;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class XellesSpawningSporeRenderer extends GeoEntityRenderer<EntitySpawningSpore> {

    public XellesSpawningSporeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new XellesSpawningSporeModel());
    }

    @Override
    public void render(EntitySpawningSpore entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        if(entityIn.isBossSpore()) {
            matrixStackIn.scale(4,4,4);
        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}
