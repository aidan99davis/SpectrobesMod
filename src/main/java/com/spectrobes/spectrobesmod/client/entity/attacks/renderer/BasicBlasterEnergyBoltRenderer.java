package com.spectrobes.spectrobesmod.client.entity.attacks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.entity.attacks.model.BasicBlasterEnergyBoltModel;
import com.spectrobes.spectrobesmod.common.entities.attacks.BasicBlasterEnergyBoltEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

// TODO: This is essentially a duplicate of EnergyBoltRenderer. Make have different ResourceLocations.
public class BasicBlasterEnergyBoltRenderer extends GeoEntityRenderer<BasicBlasterEnergyBoltEntity> {

    public BasicBlasterEnergyBoltRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new BasicBlasterEnergyBoltModel());
    }

    @Override
    public void render(BasicBlasterEnergyBoltEntity animatable, float yaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(4,4,4);
        super.render(animatable, yaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
