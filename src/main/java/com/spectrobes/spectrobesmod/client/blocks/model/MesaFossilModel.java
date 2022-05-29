package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.MesaFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MesaFossilModel extends AnimatedGeoModel<MesaFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(MesaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/mesa.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MesaFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MesaFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
