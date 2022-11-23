package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.machines.entity.CyrusShopBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CyrusShopModel extends AnimatedGeoModel<CyrusShopBlockEntity> {

    @Override
    public ResourceLocation getModelResource(CyrusShopBlockEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/lab_machine_small.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CyrusShopBlockEntity grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/cyrus_shop.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CyrusShopBlockEntity grildaFossilBlock) {
        return null;
    }
}
