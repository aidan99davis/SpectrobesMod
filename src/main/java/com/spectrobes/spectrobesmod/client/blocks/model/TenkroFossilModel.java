package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.TenkroFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TenkroFossilModel extends AnimatedGeoModel<TenkroFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(TenkroFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/tenkro.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TenkroFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TenkroFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
