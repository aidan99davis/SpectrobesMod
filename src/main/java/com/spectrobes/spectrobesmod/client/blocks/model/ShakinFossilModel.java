package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.ShakinFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShakinFossilModel extends AnimatedGeoModel<ShakinFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(ShakinFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/shakin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShakinFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShakinFossilBlockTileEntity fossil) {
        return null;
    }
}
