package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.ShakinFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShakinFossilModel extends AnimatedGeoModel<ShakinFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(ShakinFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/shakin.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ShakinFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ShakinFossilBlockTileEntity fossil) {
        return null;
    }
}
