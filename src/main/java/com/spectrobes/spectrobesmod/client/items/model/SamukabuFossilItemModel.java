package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.SamukabuFossilItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SamukabuFossilItemModel extends AnimatedGeoModel<SamukabuFossilItem> {

    @Override
    public ResourceLocation getModelResource(SamukabuFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/samukabu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SamukabuFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SamukabuFossilItem grildaFossilBlock) {
        return null;
    }
}
