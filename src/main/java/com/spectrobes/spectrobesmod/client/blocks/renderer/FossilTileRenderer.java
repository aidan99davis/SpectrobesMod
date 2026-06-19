package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.FossilBlockModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.FossilBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FossilTileRenderer extends GeoBlockRenderer<FossilBlockTileEntity> {

    public FossilTileRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new FossilBlockModel());
    }
}
