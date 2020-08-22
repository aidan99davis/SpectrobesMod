package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.KomainuModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomainu;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class KomainuRenderer extends MobRenderer<EntityKomainu, KomainuModel> {

    public KomainuRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KomainuModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntityKomainu entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/komainu.png");
    }
}
