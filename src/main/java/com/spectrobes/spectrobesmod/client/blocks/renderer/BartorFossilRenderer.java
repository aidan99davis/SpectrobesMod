package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.BartorFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.BartorFossilBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BartorFossilRenderer extends GeoBlockRenderer<BartorFossilBlockTileEntity> {

    public BartorFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new BartorFossilModel());
    }
}
