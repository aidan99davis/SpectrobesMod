package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.FossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.krawl.MiniXellesBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FossilBlockModel extends AnimatedGeoModel<FossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(FossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/box.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/blocks/fossil_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
