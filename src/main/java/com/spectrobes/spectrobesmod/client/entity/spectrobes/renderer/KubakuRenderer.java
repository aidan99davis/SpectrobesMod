package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.KomainuModel;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.KubakuModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomainu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKubaku;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class KubakuRenderer extends GeoEntityRenderer<EntityKubaku> {

    public KubakuRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KubakuModel());
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntityKubaku entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/kubaku.png");
    }
}
