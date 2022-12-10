package com.spectrobes.spectrobesmod.client.blocks;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.blocks.renderer.*;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BlockRendererManager {
    @SubscribeEvent
    public static void registerTileEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.GRILDA_FOSSIL_TILE.get(), GrildaFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.HARUMI_FOSSIL_TILE.get(), HarumiFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.KOMAINU_FOSSIL_TILE.get(), KomainuFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.KUBAKU_FOSSIL_TILE.get(), KubakuFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.NAGU_FOSSIL_TILE.get(), NaguFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.SAMUKABU_FOSSIL_TILE.get(), SamukabuFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.SEGU_FOSSIL_TILE.get(), SeguFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.SHAKIN_FOSSIL_TILE.get(), ShakinFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.SPIKO_FOSSIL_TILE.get(), SpikoFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.VILAR_FOSSIL_TILE.get(), VilarFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.ZOZA_FOSSIL_TILE.get(), ZozaFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.MOSSARI_FOSSIL_TILE.get(), MossariFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.AOI_FOSSIL_TILE.get(), AoiFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.BARTOR_FOSSIL_TILE.get(), BartorFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.GEJIO_FOSSIL_TILE.get(), GejioFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.MESA_FOSSIL_TILE.get(), MesaFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.DONGOR_FOSSIL_TILE.get(), DongorFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.INKANA_FOSSIL_TILE.get(), InkanaFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.DANAWA_FOSSIL_TILE.get(), DanawaFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.MASETTO_FOSSIL_TILE.get(), MasettoFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.TENKRO_FOSSIL_TILE.get(), TenkroFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.KASUMI_FOSSIL_TILE.get(), KasumiFossilRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.HEALER_TILE.get(), HealerBlockRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.CYRUS_SHOP_TILE.get(), CyrusShopRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.XELLES_TROPHY_TILE.get(), XellesTrophyRenderer::new);
        event.registerBlockEntityRenderer(SpectrobesTileRegistry.MINI_XELLES_TILE.get(), MiniXellesRenderer::new);
//        event.registerBlockEntityRenderer(SpectrobesTileRegistry.FOSSIL_TILE.get(), FossilTileRenderer::new);
    }
}
