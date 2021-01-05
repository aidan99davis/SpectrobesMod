package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.NaguFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NaguFossilModel extends AnimatedGeoModel<NaguFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(NaguFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/nagu.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(NaguFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(NaguFossilBlockTileEntity fossil) {
        return null;
    }
}
