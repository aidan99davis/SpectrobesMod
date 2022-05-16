package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.SubarModel;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.SwarModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySubar;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySwar;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SubarRenderer extends GeoEntityRenderer<EntitySubar> {

    public SubarRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SubarModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntitySubar entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/krawl/subar.png");
    }

    @Override
    public void render(EntitySubar entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

}
