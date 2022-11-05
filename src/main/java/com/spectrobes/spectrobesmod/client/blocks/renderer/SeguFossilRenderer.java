package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.SeguFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.SeguFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class SeguFossilRenderer extends GeoBlockRenderer<SeguFossilBlockTileEntity> {

    public SeguFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new SeguFossilModel());
    }

    @Override
    public void render(SeguFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(1.2f, 1.2f, 1.2f);
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
