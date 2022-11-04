package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.GejioFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GejioFossilModel extends AnimatedGeoModel<GejioFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(GejioFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/gejio.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GejioFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GejioFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
