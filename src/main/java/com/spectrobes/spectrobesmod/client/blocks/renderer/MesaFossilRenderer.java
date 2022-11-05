package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.spectrobes.spectrobesmod.client.blocks.model.MesaFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.MesaFossilBlockTileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class MesaFossilRenderer extends GeoBlockRenderer<MesaFossilBlockTileEntity> {

    public MesaFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new MesaFossilModel());
    }

    @Override
    public void render(MesaFossilBlockTileEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.6f, 0.6f, 0.6f);
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
