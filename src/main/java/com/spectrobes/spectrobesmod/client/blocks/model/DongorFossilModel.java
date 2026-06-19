package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.DongorFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DongorFossilModel extends GeoModel<DongorFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(DongorFossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/dongor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DongorFossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DongorFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
