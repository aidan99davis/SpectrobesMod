package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.FossilBlockModel;
import com.spectrobes.spectrobesmod.client.blocks.model.MiniXellesBlockModel;
import com.spectrobes.spectrobesmod.common.blocks.FossilBlock;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.FossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.blocks.krawl.MiniXellesBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class FossilTileRenderer extends GeoBlockRenderer<FossilBlockTileEntity> {

    public FossilTileRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new FossilBlockModel());
    }

    @Override
    public void render(FossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
