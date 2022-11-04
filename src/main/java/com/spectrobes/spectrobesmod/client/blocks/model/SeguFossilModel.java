package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.SeguFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SeguFossilModel extends AnimatedGeoModel<SeguFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(SeguFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/segu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SeguFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SeguFossilBlockTileEntity fossil) {
        return null;
    }
}
