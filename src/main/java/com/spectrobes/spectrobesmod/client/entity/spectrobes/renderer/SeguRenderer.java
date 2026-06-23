package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SeguModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegu;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

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
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/segu_0.png");
            case 1:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/segu_1.png");
            case 2:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/segu_2.png");
            default:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/segu_0.png");

        }
    }
}
