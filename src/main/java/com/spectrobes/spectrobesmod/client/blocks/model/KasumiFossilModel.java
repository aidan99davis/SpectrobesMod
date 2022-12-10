package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.KasumiFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KasumiFossilModel extends AnimatedGeoModel<KasumiFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelResource(KasumiFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/kasumi.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(KasumiFossilBlockTileEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(KasumiFossilBlockTileEntity grildaFossilBlock) {
        return null;
    }
}
