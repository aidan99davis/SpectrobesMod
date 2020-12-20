package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.SpikanModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpikan;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SpikanRenderer extends GeoEntityRenderer<EntitySpikan> {

    public SpikanRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SpikanModel());
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntitySpikan entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/spikan.png");
    }
}
