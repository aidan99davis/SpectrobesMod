package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.blocks.model.GejioFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.GejioFossilBlockTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class GejioFossilRenderer extends GeoBlockRenderer<GejioFossilBlockTileEntity> {

    public GejioFossilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new GejioFossilModel());
    }

    @Override
    public void render(GejioFossilBlockTileEntity tile, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        stack.scale(0.5f, 0.5f, 0.5f);
        super.render(tile, partialTicks, stack, bufferIn, packedLightIn);
    }
}
