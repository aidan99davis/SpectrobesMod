package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.InkanaFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.InkanaFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class InkanaFossilRenderer extends GeoBlockRenderer<InkanaFossilBlockTileEntity> {
    public InkanaFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new InkanaFossilModel());
    }

    @Override
    public void render(InkanaFossilBlockTileEntity animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.scale(0.5f, 0.5f, 0.5f);
        super.render(animatable, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
    }
}
