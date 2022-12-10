package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.ShakinFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.ShakinFossilBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class ShakinFossilRenderer extends GeoBlockRenderer<ShakinFossilBlockTileEntity> {

    public ShakinFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new ShakinFossilModel());
    }
}
