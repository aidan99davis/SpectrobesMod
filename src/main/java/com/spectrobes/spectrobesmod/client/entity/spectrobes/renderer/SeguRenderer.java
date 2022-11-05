package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SeguModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegu;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SeguRenderer extends GeoEntityRenderer<EntitySegu> {

    public SeguRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SeguModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntitySegu entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/segu_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/segu_1.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/segu_2.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/segu_0.png");

        }
    }
}
