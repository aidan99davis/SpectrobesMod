package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.ZozaFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.ZozaFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class ZozaFossilRenderer extends GeoBlockRenderer<ZozaFossilBlockTileEntity> {

    public ZozaFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new ZozaFossilModel());
    }

    @Override
    public void render(ZozaFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.75f, 0.75f, 0.75f);
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
