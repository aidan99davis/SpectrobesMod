package com.spectrobes.spectrobesmod.client.entity.attacks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.entity.attacks.model.BasicBlasterEnergyBoltModel;
import com.spectrobes.spectrobesmod.common.entities.attacks.AbstractEnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.entities.attacks.BasicEnergyBoltEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

// TODO: This is essentially a duplicate of EnergyBoltRenderer. Make have different ResourceLocations.
/** This renders all the blaster projectiles. */
public class BasicEnergyBoltRenderer extends GeoEntityRenderer<AbstractEnergyBoltEntity> {

    public BasicEnergyBoltRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new BasicBlasterEnergyBoltModel());
    }

    @Override
    public void render(AbstractEnergyBoltEntity animatable, float yaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
//        poseStack.scale(4,4,4);
        super.render(animatable, yaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
