package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.KomainuFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.KomainuFossilBlockTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class KomainuFossilRenderer extends GeoBlockRenderer<KomainuFossilBlockTileEntity> {

    public KomainuFossilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new KomainuFossilModel());
    }
}
