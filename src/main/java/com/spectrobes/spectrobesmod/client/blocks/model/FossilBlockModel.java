package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.FossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FossilBlockModel extends GeoModel<FossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(FossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/box.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/blocks/fossil_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
