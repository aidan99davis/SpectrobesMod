package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.KasumiFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.KasumiFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class KasumiFossilRenderer extends GeoBlockRenderer<KasumiFossilBlockTileEntity> {

    public KasumiFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new KasumiFossilModel());
    }

    @Override
    public void render(KasumiFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.3f, 0.3f, 0.3f);
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
