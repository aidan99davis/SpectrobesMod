package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.XellesTrophyBlockModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.XellesTrophyBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class XellesTrophyRenderer extends GeoBlockRenderer<XellesTrophyBlockTileEntity> {

    public XellesTrophyRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(new XellesTrophyBlockModel());
    }

    @Override
    public void render(XellesTrophyBlockTileEntity animatable, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.scale(0.5f,0.5f, 0.5f);
        super.render(animatable, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
    }
}
