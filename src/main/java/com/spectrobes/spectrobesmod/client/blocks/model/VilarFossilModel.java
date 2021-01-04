package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.VilarFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VilarFossilModel extends AnimatedGeoModel<VilarFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(VilarFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/vilar.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(VilarFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(VilarFossilBlockTileEntity fossil) {
        return null;
    }
}
