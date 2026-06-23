package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.TenkroFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.TenkroFossilBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class TenkroFossilRenderer extends GeoBlockRenderer<TenkroFossilBlockTileEntity> {

    public TenkroFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new TenkroFossilModel());
    }
}
