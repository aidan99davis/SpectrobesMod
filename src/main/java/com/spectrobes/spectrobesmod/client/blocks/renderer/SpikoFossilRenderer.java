package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.SpikoFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.SpikoFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class SpikoFossilRenderer extends GeoBlockRenderer<SpikoFossilBlockTileEntity> {

    public SpikoFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new SpikoFossilModel());
    }

    @Override
    public void render(SpikoFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.5f, 0.5f, 0.5f);
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
