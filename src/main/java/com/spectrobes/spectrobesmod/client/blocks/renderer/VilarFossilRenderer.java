package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.VilarFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.VilarFossilBlockTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class VilarFossilRenderer extends GeoBlockRenderer<VilarFossilBlockTileEntity> {

    public VilarFossilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new VilarFossilModel());
    }
}
