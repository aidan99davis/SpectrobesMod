package com.spectrobes.spectrobesmod.client.blocks;

import com.spectrobes.spectrobesmod.client.blocks.renderer.*;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesTileRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public class BlockRendererManager {
    public static void init() {
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.GRILDA_FOSSIL_TILE.get(), GrildaFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.HARUMI_FOSSIL_TILE.get(), HarumiFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.KOMAINU_FOSSIL_TILE.get(), KomainuFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.KUBAKU_FOSSIL_TILE.get(), KubakuFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.NAGU_FOSSIL_TILE.get(), NaguFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.SAMUKABU_FOSSIL_TILE.get(), SamukabuFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.SEGU_FOSSIL_TILE.get(), SeguFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.SHAKIN_FOSSIL_TILE.get(), ShakinFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.SPIKO_FOSSIL_TILE.get(), SpikoFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.VILAR_FOSSIL_TILE.get(), VilarFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.ZOZA_FOSSIL_TILE.get(), ZozaFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.MOSSARI_FOSSIL_TILE.get(), MossariFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.AOI_FOSSIL_TILE.get(), AoiFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.BARTOR_FOSSIL_TILE.get(), BartorFossilRenderer::new);
        ClientRegistry.bindTileEntityRenderer(SpectrobesTileRegistry.HEALER_TILE.get(), HealerBlockRenderer::new);
    }
}
