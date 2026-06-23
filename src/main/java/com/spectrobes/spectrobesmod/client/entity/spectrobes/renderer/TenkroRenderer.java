package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.TenkroModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.tenkro.EntityTenkro;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class TenkroRenderer extends GeoEntityRenderer<EntityTenkro> {

    public TenkroRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new TenkroModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityTenkro entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/tenkro_0.png");
            case 1:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/tenkro_0.png");
            case 2:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/tenkro_0.png");
            default:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/tenkro_0.png");
        }
    }
}
