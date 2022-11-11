package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.XellesModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityXelles;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class XellesRenderer extends GeoEntityRenderer<EntityXelles> {

    public XellesRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new XellesModel());
    }

    @Override
    public void render(EntityXelles animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        switch(animatable.getStage()) {
            case 1:
                break;
            case 2:
                poseStack.scale(2, 2, 2);
                break;
            case 3:
                poseStack.scale(3, 3, 3);
                break;
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}
