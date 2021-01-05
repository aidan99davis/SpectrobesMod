package com.spectrobes.spectrobesmod.client.blocks.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.spectrobes.spectrobesmod.client.blocks.model.SamukabuFossilModel;
import com.spectrobes.spectrobesmod.common.blocks.tile.SamukabuFossilBlockTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

@OnlyIn(Dist.CLIENT)
public class SamukabuFossilRenderer extends GeoBlockRenderer<SamukabuFossilBlockTileEntity> {

    public SamukabuFossilRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn, new SamukabuFossilModel());
    }

    @Override
    public void render(SamukabuFossilBlockTileEntity tile, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
        stack.scale(0.9f, 0.9f, 0.9f);
        super.render(tile, partialTicks, stack, bufferIn, packedLightIn);
    }
}
