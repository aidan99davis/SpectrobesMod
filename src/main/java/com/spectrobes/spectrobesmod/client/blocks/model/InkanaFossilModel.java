package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.InkanaFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class InkanaFossilModel extends AnimatedGeoModel<InkanaFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(InkanaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/inkana.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(InkanaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(InkanaFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
