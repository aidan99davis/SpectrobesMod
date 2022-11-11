package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SamuriteModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamurite;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SamuriteRenderer extends GeoEntityRenderer<EntitySamurite> {

    public SamuriteRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SamuriteModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntitySamurite entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/samurite.png");
    }
}
