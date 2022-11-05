package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.InkanaFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.InkanaFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class InkanaFossilRenderer extends GeoBlockRenderer<InkanaFossilBlockTileEntity> {
    public InkanaFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new InkanaFossilModel());
    }

    @Override
    public void render(InkanaFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.5f, 0.5f, 0.5f);
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
