package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.spectrobes.spectrobesmod.client.blocks.model.MossariFossilModel;
import com.spectrobes.spectrobesmod.client.blocks.model.NaguFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.MossariFossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.tile.NaguFossilBlockTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class MossariFossilRenderer extends GeoBlockRenderer<MossariFossilBlockTileEntity> {

    public MossariFossilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new MossariFossilModel());
    }
}
