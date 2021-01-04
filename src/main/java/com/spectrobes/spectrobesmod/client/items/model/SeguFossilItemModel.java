package com.spectrobes.spectrobesmod.client.items.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.fossils.SamukabuFossilItem;
import com.spectrobes.spectrobesmod.common.items.fossils.SeguFossilItem;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SeguFossilItemModel extends AnimatedGeoModel<SeguFossilItem> {

    @Override
    public ResourceLocation getModelLocation(SeguFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "geo/blocks/segu.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SeguFossilItem grildaFossilBlock) {
        return new ResourceLocation(SpectrobesInfo.MOD_ID, "textures/models/blocks/fossil.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SeguFossilItem grildaFossilBlock) {
        return null;
    }
}
