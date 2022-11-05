package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.MasettoFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MasettoFossilModel extends AnimatedGeoModel<MasettoFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(MasettoFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/masetto.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MasettoFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MasettoFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
