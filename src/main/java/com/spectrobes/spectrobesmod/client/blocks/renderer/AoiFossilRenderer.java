package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.AoiFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.AoiFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class AoiFossilRenderer extends GeoBlockRenderer<AoiFossilBlockTileEntity> {

    public AoiFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new AoiFossilModel());
    }

    @Override
    public void render(AoiFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
