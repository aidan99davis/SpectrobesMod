package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SamuriteModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamurite;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SamuriteRenderer extends GeoEntityRenderer<EntitySamurite> {

    public SamuriteRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SamuriteModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntitySamurite entity)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/samurite.png");
    }
}
