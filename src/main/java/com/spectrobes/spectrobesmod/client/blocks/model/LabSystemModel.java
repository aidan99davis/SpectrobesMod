package com.spectrobes.spectrobesmod.client.blocks.model;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.machines.lab.LabSystemBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LabSystemModel extends GeoModel<LabSystemBlockEntity> {

    @Override
    public ResourceLocation getModelResource(LabSystemBlockEntity labSystemBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "geo/blocks/lab_machine_small.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LabSystemBlockEntity labSystemBlock) {
        return ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "textures/models/blocks/lab_system.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LabSystemBlockEntity labSystemBlock) {
        return null;
    }
}
