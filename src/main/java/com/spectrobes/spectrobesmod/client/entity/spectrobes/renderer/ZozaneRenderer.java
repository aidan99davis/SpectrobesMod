package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.ZozaneModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.zoza.EntityZozane;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class ZozaneRenderer extends GeoEntityRenderer<EntityZozane> {

    public ZozaneRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ZozaneModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityZozane entity)
    {
        return switch (entity.getSpectrobeData().Variant) {
            case 1 -> new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/zozane_1.png");
            case 2 -> new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/zozane_2.png");
            default -> new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/zozane_0.png");
        };
    }
}
