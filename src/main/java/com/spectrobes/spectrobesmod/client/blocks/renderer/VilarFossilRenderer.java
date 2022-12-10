package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.VilarFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.VilarFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class VilarFossilRenderer extends GeoBlockRenderer<VilarFossilBlockTileEntity> {

    public VilarFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new VilarFossilModel());
    }

    @Override
    public void render(VilarFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.8f, 0.8f, 0.8f);
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
