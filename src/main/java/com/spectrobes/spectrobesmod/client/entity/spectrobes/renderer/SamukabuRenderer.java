package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SamukabuModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamukabu;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SamukabuRenderer extends GeoEntityRenderer<EntitySamukabu> {

    public SamukabuRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new SamukabuModel());
    }

    @Nullable
    @Override
    public ResourceLocation getTextureLocation(EntitySamukabu entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/samukabu.png");
    }
}
