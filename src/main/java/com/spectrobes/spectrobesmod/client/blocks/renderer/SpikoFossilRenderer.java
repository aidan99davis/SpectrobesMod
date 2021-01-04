package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.SpikoFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.SpikoFossilBlockTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class SpikoFossilRenderer extends GeoBlockRenderer<SpikoFossilBlockTileEntity> {

    public SpikoFossilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new SpikoFossilModel());
    }
}
