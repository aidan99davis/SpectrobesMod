package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.MossaritoModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.mossari.EntityMossarito;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class MossaritoRenderer extends GeoEntityRenderer<EntityMossarito> {

    public MossaritoRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new MossaritoModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityMossarito entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/mossarito_0.png");
            case 1:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/mossarito_1.png");
            case 2:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/mossarito_2.png");
            default:
                return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/mossarito_0.png");

        }
    }
}
