package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.HarumiFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.HarumiFossilBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class HarumiFossilRenderer extends GeoBlockRenderer<HarumiFossilBlockTileEntity> {
    public HarumiFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new HarumiFossilModel());
    }
}
