package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.MesaFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.MesaFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MesaFossilRenderer extends GeoBlockRenderer<MesaFossilBlockTileEntity> {

    public MesaFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new MesaFossilModel());
    }

    @Override
    public void render(MesaFossilBlockTileEntity animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.scale(0.6f, 0.6f, 0.6f);
        super.render(animatable, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
    }
}
