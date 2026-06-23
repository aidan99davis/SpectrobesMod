package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.KubakuFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KubakuFossilModel extends GeoModel<KubakuFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(KubakuFossilBlockTileEntity fossil) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/kubaku.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KubakuFossilBlockTileEntity fossil) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KubakuFossilBlockTileEntity fossil) {
        return null;
    }
}
