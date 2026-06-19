package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SpikanModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpikan;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SpikanRenderer extends GeoEntityRenderer<EntitySpikan> {

    public SpikanRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SpikanModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntitySpikan entity)
    {
        return switch (entity.getSpectrobeData().Variant) {
            case 1 -> ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/spikan_1.png");
            case 2 -> ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/spikan_2.png");
            default -> ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/spikan_0.png");
        };
    }
}
