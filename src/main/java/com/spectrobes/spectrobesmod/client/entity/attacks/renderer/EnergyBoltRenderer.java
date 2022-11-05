package com.spectrobes.spectrobesmod.client.entity.attacks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.entity.attacks.model.EnergyBoltModel;
import com.spectrobes.spectrobesmod.common.entities.attacks.EnergyBoltEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

@OnlyIn(Dist.CLIENT)
public class EnergyBoltRenderer extends GeoProjectilesRenderer<EnergyBoltEntity> {

    public EnergyBoltRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new EnergyBoltModel());
    }

    @Override
    public void render(EnergyBoltEntity animatable, float yaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(4,4,4);
        super.render(animatable, yaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
