package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.SpikoFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SpikoFossilModel extends AnimatedGeoModel<SpikoFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(SpikoFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/spiko.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SpikoFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SpikoFossilBlockTileEntity fossil) {
        return null;
    }
}
