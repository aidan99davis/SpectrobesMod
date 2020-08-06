package com.spectrobes.spectrobesmod.client.entity.renderer;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.model.KomanotoModel;
import com.spectrobes.spectrobesmod.client.entity.model.SpikanModel;
import com.spectrobes.spectrobesmod.common.entities.komainu.EntityKomanoto;
import com.spectrobes.spectrobesmod.common.entities.spiko.EntitySpikan;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SpikanRenderer extends MobRenderer<EntitySpikan, SpikanModel> {

    public SpikanRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SpikanModel(), 0.5f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(EntitySpikan entity)
    {
        return new ResourceLocation(SpectrobesInfo.MOD_ID + ":textures/models/spectrobe/spikan.png");
    }
}
