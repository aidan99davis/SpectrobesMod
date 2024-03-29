package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.TenkroFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.TenkroFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class TenkroFossilRenderer extends GeoBlockRenderer<TenkroFossilBlockTileEntity> {

    public TenkroFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new TenkroFossilModel());
    }

    @Override
    public void render(TenkroFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
