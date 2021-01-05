package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.GrildaFossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.tile.HarumiFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HarumiFossilModel extends AnimatedGeoModel<HarumiFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(HarumiFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/harumi.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(HarumiFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(HarumiFossilBlockTileEntity fossil) {
        return null;
    }
}
