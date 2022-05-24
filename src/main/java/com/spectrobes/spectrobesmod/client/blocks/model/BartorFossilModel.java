package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.AoiFossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.tile.BartorFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BartorFossilModel extends AnimatedGeoModel<BartorFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(BartorFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/bartor.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(BartorFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(BartorFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
