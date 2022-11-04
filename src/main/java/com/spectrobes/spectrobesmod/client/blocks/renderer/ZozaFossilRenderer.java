package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.blocks.model.ZozaFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.ZozaFossilBlockTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class ZozaFossilRenderer extends GeoBlockRenderer<ZozaFossilBlockTileEntity> {

    public ZozaFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new ZozaFossilModel());
    }

    @Override
    public void render(ZozaFossilBlockTileEntity tile, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        stack.scale(0.75f, 0.75f, 0.75f);
        super.render(tile, partialTicks, stack, bufferIn, packedLightIn);
    }
}
