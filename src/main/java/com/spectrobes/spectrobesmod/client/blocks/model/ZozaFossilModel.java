package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.ZozaFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ZozaFossilModel extends AnimatedGeoModel<ZozaFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(ZozaFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/zoza.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ZozaFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ZozaFossilBlockTileEntity fossil) {
        return null;
    }
}
