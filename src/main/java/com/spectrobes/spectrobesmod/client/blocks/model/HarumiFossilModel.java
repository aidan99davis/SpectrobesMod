package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.HarumiFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HarumiFossilModel extends AnimatedGeoModel<HarumiFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(HarumiFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/harumi.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HarumiFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HarumiFossilBlockTileEntity fossil) {
        return null;
    }
}
