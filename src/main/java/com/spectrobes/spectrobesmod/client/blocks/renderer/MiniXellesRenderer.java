package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.spectrobes.spectrobesmod.client.blocks.model.MiniXellesBlockModel;
import com.spectrobes.spectrobesmod.common.blocks.krawl.MiniXellesBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class MiniXellesRenderer extends GeoBlockRenderer<MiniXellesBlockEntity> {

    public MiniXellesRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new MiniXellesBlockModel());
    }

    @Override
    public void render(MiniXellesBlockEntity tile, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.scale(0.7f,0.7f, 0.7f);
        super.render(tile, partialTick, poseStack, bufferSource, packedLight);
    }
}
