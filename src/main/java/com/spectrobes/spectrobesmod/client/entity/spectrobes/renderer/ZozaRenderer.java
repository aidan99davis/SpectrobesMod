package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.ZozaModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.zoza.EntityZoza;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class ZozaRenderer extends GeoEntityRenderer<EntityZoza> {

    public ZozaRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ZozaModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityZoza entity)
    {
        return switch (entity.getSpectrobeData().Variant) {
            case 1 -> ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/zoza_1.png");
            case 2 -> ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/zoza_2.png");
            default -> ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/zoza_0.png");
        };
    }
}
