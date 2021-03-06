package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.KubakuFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.KubakuFossilBlockTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class KubakuFossilRenderer extends GeoBlockRenderer<KubakuFossilBlockTileEntity> {

    public KubakuFossilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new KubakuFossilModel());
    }

}
