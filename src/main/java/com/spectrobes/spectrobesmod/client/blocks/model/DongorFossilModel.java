package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.DongorFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DongorFossilModel extends AnimatedGeoModel<DongorFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(DongorFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/dongor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DongorFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DongorFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
