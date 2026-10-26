package com.spectrobes.spectrobesmod.client.items.machines.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.machines.LabSystemBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LabSystemBlockItemModel extends GeoModel<LabSystemBlockItem> {

    @Override
    public ResourceLocation getModelResource(LabSystemBlockItem labSystemItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/lab_machine_small.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LabSystemBlockItem labSystemItem) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/lab_system.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LabSystemBlockItem labSystemItem) {
        return null;
    }
}
