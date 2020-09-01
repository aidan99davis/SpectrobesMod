package com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.KomainuModel;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.model.ShakinModel;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomainu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin.EntityShakin;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class ShakinRenderer extends MobRenderer<EntityShakin, ShakinModel> {

    public ShakinRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ShakinModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntityShakin entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/shakin.png");
    }
}
