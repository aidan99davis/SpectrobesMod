package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.GrildaFossilModel;
import com.spectrobes.spectrobesmod.client.blocks.model.HarumiFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.GrildaFossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.tile.HarumiFossilBlockTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class HarumiFossilRenderer extends GeoBlockRenderer<HarumiFossilBlockTileEntity> {

    public HarumiFossilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new HarumiFossilModel());
    }
}
