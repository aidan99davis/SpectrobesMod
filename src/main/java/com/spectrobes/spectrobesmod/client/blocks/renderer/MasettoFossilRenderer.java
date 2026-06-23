package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.MasettoFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.MasettoFossilBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MasettoFossilRenderer extends GeoBlockRenderer<MasettoFossilBlockTileEntity> {

    public MasettoFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new MasettoFossilModel());
    }
}
