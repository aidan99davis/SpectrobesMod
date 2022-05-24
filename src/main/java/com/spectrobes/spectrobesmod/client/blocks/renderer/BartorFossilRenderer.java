package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.blocks.model.AoiFossilModel;
import com.spectrobes.spectrobesmod.client.blocks.model.BartorFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.AoiFossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.tile.BartorFossilBlockTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class BartorFossilRenderer extends GeoBlockRenderer<BartorFossilBlockTileEntity> {

    public BartorFossilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new BartorFossilModel());
    }

    @Override
    public void render(BartorFossilBlockTileEntity tile, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(tile, partialTicks, stack, bufferIn, packedLightIn);
    }
}
