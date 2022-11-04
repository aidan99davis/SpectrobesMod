package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.GrildaFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GrildaFossilModel extends AnimatedGeoModel<GrildaFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(GrildaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/grilda.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GrildaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GrildaFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
