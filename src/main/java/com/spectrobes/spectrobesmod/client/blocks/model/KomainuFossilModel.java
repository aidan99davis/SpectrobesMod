package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.KomainuFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KomainuFossilModel extends GeoModel<KomainuFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(KomainuFossilBlockTileEntity fossil) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/komainu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KomainuFossilBlockTileEntity fossil) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KomainuFossilBlockTileEntity fossil) {
        return null;
    }
}
