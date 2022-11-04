package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.KomainuFossilBlockTileEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KomainuFossilModel extends AnimatedGeoModel<KomainuFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(KomainuFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/komainu.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(KomainuFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(KomainuFossilBlockTileEntity fossil) {
        return null;
    }
}
