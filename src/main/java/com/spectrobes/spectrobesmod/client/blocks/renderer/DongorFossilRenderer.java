package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.DongorFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.DongorFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DongorFossilRenderer extends GeoBlockRenderer<DongorFossilBlockTileEntity> {

    public DongorFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new DongorFossilModel());
    }

    @Override
    public void render(DongorFossilBlockTileEntity animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.scale(0.33f, 0.33f, 0.33f);
        super.render(animatable, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
    }
}
