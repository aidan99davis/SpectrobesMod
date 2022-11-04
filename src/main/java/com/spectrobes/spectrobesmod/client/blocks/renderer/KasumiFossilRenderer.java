package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.blocks.model.KasumiFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.KasumiFossilBlockTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class KasumiFossilRenderer extends GeoBlockRenderer<KasumiFossilBlockTileEntity> {

    public KasumiFossilRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new KasumiFossilModel());
    }

    @Override
    public void render(KasumiFossilBlockTileEntity tile, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(tile, partialTicks, stack, bufferIn, packedLightIn);
    }
}
