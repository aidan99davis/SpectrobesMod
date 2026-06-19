package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SamukabuModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamukabu;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SamukabuRenderer extends GeoEntityRenderer<EntitySamukabu> {

    public SamukabuRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SamukabuModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntitySamukabu entity)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/spectrobe/samukabu.png");
    }
}
