package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.SubarModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySubar;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SubarRenderer extends GeoEntityRenderer<EntitySubar> {

    public SubarRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SubarModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntitySubar entity)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/krawl/subar.png");
    }
}
