package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.KasumiFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class KasumiFossilModel extends GeoModel<KasumiFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(KasumiFossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/kasumi.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KasumiFossilBlockTileEntity grildaFossilBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KasumiFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
