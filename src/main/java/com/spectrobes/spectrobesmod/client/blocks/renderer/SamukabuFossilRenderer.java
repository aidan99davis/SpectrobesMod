package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.SamukabuFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.SamukabuFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class SamukabuFossilRenderer extends GeoBlockRenderer<SamukabuFossilBlockTileEntity> {

    public SamukabuFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new SamukabuFossilModel());
    }

    @Override
    public void render(SamukabuFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.9f, 0.9f, 0.9f);
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
