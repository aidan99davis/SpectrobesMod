package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.KomainuFossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.tile.KubakuFossilBlockTileEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class KubakuFossilModel extends AnimatedGeoModel<KubakuFossilBlockTileEntity> {

    @Override
    public ResourceLocation getModelLocation(KubakuFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/kubaku.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(KubakuFossilBlockTileEntity fossil) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(KubakuFossilBlockTileEntity fossil) {
        return null;
    }
}
