package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.blocks.model.DanawaFossilModel;
import com.spectrobes.spectrobesmod.client.blocks.model.DongorFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.DanawaFossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.tile.DongorFossilBlockTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class DanawaFossilRenderer extends GeoBlockRenderer<DanawaFossilBlockTileEntity> {

    public DanawaFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new DanawaFossilModel());
    }

    @Override
    public void render(DanawaFossilBlockTileEntity tile, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        stack.scale(0.33f, 0.33f, 0.33f);
        super.render(tile, partialTicks, stack, bufferIn, packedLightIn);
    }
}
