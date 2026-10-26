package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.LabSystemModel;
import com.spectrobes.spectrobesmod.common.blocks.machines.lab.LabSystemBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LabSystemRenderer extends GeoBlockRenderer<LabSystemBlockEntity> {

    public LabSystemRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new LabSystemModel());
    }
}
