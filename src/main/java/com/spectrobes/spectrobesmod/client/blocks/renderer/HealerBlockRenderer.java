package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.HealerBlockModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.HealerBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class HealerBlockRenderer extends GeoBlockRenderer<HealerBlockTileEntity> {

    public HealerBlockRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new HealerBlockModel());
    }
}
