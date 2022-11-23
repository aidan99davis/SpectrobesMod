package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.KubakuFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.KubakuFossilBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class KubakuFossilRenderer extends GeoBlockRenderer<KubakuFossilBlockTileEntity> {
    public KubakuFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new KubakuFossilModel());
    }
}
