package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.ZozaFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ZozaFossilModel extends AnimatedGeoModel<ZozaFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(ZozaFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/zoza.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ZozaFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ZozaFossilBlockTileEntity fossil) {
        return null;
    }
}
