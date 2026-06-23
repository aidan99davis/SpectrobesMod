package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.GrildaModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda.EntityGrilda;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class GrildaRenderer extends GeoEntityRenderer<EntityGrilda> {

    public GrildaRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new GrildaModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityGrilda entity) {
        return switch (entity.getSpectrobeData().Variant) {
            case 1 -> ResourceLocation.fromNamespaceAndPath(
                    SpectrobesInfo.MOD_ID,
                    "textures/models/spectrobe/grilda_1.png"
            );
            case 2 -> ResourceLocation.fromNamespaceAndPath(
                    SpectrobesInfo.MOD_ID,
                    "textures/models/spectrobe/grilda_2.png"
            );
            case 0 -> ResourceLocation.fromNamespaceAndPath(
                    SpectrobesInfo.MOD_ID,
                    "textures/models/spectrobe/grilda_0.png"
            );
            default -> ResourceLocation.fromNamespaceAndPath(
                    SpectrobesInfo.MOD_ID,
                    "textures/models/spectrobe/grilda_0.png"
            );
        };
    }
}