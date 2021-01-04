package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.ZozaFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.ZozaFossilBlockTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class ZozaFossilRenderer extends GeoBlockRenderer<ZozaFossilBlockTileEntity> {

    public ZozaFossilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new ZozaFossilModel());
    }
}
