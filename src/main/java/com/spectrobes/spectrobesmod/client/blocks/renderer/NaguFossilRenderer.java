package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.NaguFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.NaguFossilBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class NaguFossilRenderer extends GeoBlockRenderer<NaguFossilBlockTileEntity> {
    public NaguFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new NaguFossilModel());
    }
}
