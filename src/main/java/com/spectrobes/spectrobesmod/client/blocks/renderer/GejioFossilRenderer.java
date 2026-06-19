package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.GejioFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.GejioFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GejioFossilRenderer extends GeoBlockRenderer<GejioFossilBlockTileEntity> {

    public GejioFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new GejioFossilModel());
    }

    @Override
    public void render(GejioFossilBlockTileEntity animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.scale(0.5f, 0.5f, 0.5f);
        super.render(animatable, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
    }
}
