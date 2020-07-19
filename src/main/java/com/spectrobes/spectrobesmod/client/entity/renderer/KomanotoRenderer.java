package com.spectrobes.spectrobesmod.client.entity.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.model.KomainuModel;
import com.spectrobes.spectrobesmod.client.entity.model.KomanotoModel;
import com.spectrobes.spectrobesmod.common.entities.komainu.EntityKomainu;
import com.spectrobes.spectrobesmod.common.entities.komainu.EntityKomanoto;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class KomanotoRenderer extends MobRenderer<EntityKomanoto, KomanotoModel> {

    public KomanotoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new KomanotoModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntityKomanoto entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/komanoto.png");
    }
}
