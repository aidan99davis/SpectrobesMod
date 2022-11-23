package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.DongorFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.DongorFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class DongorFossilRenderer extends GeoBlockRenderer<DongorFossilBlockTileEntity> {

    public DongorFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new DongorFossilModel());
    }

    @Override
    public void render(DongorFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.33f, 0.33f, 0.33f);
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
