package com.spectrobes.spectrobesmod.client.entity.krawl.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.model.SwarModel;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySwar;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SwarRenderer extends GeoEntityRenderer<EntitySwar> {

    public SwarRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SwarModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntitySwar entity)
    {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/krawl/swar.png");
    }
}
