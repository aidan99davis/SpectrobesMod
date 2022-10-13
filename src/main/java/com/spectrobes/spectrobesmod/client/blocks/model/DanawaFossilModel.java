package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.DanawaFossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.tile.DongorFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DanawaFossilModel extends AnimatedGeoModel<DanawaFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(DanawaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/danawa.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(DanawaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(DanawaFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
