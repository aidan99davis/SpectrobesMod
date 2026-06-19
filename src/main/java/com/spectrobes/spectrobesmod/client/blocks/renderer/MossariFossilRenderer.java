package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.MossariFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.MossariFossilBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MossariFossilRenderer extends GeoBlockRenderer<MossariFossilBlockTileEntity> {
    public MossariFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new MossariFossilModel());
    }
}
