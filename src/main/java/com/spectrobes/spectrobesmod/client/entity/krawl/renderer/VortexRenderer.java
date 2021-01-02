package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.SwarModel;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.VortexModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySwar;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class VortexRenderer extends GeoEntityRenderer<EntityVortex> {

    public VortexRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new VortexModel());
    }

    @Override
    public void render(EntityVortex entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.scale(3f, 3f, 3f);
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        /*matrixStackIn.pop();
        matrixStackIn.push();*/
    }

}
