package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.SubarModel;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.VizbarModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySubar;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVizbar;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class VizbarRenderer extends GeoEntityRenderer<EntityVizbar> {

    public VizbarRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new VizbarModel());
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntityVizbar entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/krawl/vizbar.png");
    }

    @Override
    public void render(EntityVizbar entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}
