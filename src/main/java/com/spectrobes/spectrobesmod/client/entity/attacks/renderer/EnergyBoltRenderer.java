package com.spectrobes.spectrobesmod.client.entity.attacks.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.entity.attacks.model.EnergyBoltModel;
import com.spectrobes.spectrobesmod.common.entities.attacks.EnergyBoltEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

@OnlyIn(Dist.CLIENT)
public class EnergyBoltRenderer extends GeoProjectilesRenderer<EnergyBoltEntity> {

    public EnergyBoltRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new EnergyBoltModel());
    }

    @Override
    public void render(EnergyBoltEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.scale(4,4,4);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}
