package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.KomainuFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.KomainuFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class KomainuFossilRenderer extends GeoBlockRenderer<KomainuFossilBlockTileEntity> {

    public KomainuFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new KomainuFossilModel());
    }

    @Override
    public void render(KomainuFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.25f, 1.25f, 1.25f);
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
