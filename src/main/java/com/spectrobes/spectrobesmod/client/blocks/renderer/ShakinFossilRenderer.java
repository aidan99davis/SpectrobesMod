package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.ShakinFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.ShakinFossilBlockTileEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class ShakinFossilRenderer extends GeoBlockRenderer<ShakinFossilBlockTileEntity> {

    public ShakinFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new ShakinFossilModel());
    }
}
