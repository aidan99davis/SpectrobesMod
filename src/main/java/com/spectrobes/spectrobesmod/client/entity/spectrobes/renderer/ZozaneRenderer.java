package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.ZozaneModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.zoza.EntityZozane;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class ZozaneRenderer extends GeoEntityRenderer<EntityZozane> {

    public ZozaneRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ZozaneModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntityZozane entity)
    {
        switch (entity.getSpectrobeData().Variant) {
            case 0:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/zozane_0.png");
            case 1:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/zozane_0.png");
            case 2:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/zozane_0.png");
            default:
                return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/zozane_0.png");

        }
    }
}
