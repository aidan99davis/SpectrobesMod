package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.CyrusShopModel;
import com.spectrobes.spectrobesmod.common.blocks.machines.entity.CyrusShopBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CyrusShopRenderer extends GeoBlockRenderer<CyrusShopBlockEntity> {

    public CyrusShopRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new CyrusShopModel());
    }
}
