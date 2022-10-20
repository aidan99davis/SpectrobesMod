package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.GrisenModel;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.XellesModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityGrisen;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityXelles;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class XellesRenderer extends GeoEntityRenderer<EntityXelles> {

    public XellesRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new XellesModel());
    }

    @Override
    public void render(EntityXelles entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        switch(entityIn.getStage()) {
            case 1:
                break;
            case 2:
                matrixStackIn.scale(2, 2, 2);
                break;
            case 3:
                matrixStackIn.scale(3, 3, 3);
                break;
        }
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}
