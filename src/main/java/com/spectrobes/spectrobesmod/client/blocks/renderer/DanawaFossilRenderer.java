package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.DanawaFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.DanawaFossilBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DanawaFossilRenderer extends GeoBlockRenderer<DanawaFossilBlockTileEntity> {

    public DanawaFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new DanawaFossilModel());
    }
}
