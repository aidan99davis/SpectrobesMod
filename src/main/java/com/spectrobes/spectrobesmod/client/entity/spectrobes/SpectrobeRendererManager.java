package com.spectrobes.spectrobesmod.client.entity.spectrobes;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SpectrobeRendererManager {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_KOMAINU.get(), KomainuRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_KOMANOTO.get(), KomanotoRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_VILAR.get(), VilarRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_VILAMASTA.get(), VilamastaRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_SPIKO.get(), SpikoRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_SPIKAN.get(), SpikanRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_SAMUKABU.get(), SamukabuRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_SAMURITE.get(), SamuriteRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_KUBAKU.get(), KubakuRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_KUGANON.get(), KuganonRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_SHAKIN.get(), ShakinRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_SHAKOR.get(), ShakorRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_SEGU.get(), SeguRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_SEGULOS.get(), SegulosRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_HARUMI.get(), HarumiRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_HARUMITE.get(), HarumiteRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_GRILDA.get(), GrildaRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_GRILDEN.get(), GrildenRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_ZOZA.get(), ZozaRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_ZOZANE.get(), ZozaneRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_NAGU.get(), NaguRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_NAGURYU.get(), NaguryuRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_MOSSARI.get(), MossariRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_MOSSARITO.get(), MossaritoRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_AOI.get(), AoiRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_BARTOR.get(), BartorRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_BARTOLOR.get(), BartolorRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_GEJIO.get(), GejioRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_MESA.get(), MesaRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_DONGOR.get(), DongorRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_DONGORA.get(), DongoraRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_INKANA.get(), InkanaRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_DANAWA.get(), DanawaRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_MASETTO.get(), MasettoRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_TENKRO.get(), TenkroRenderer::new);
        event.registerEntityRenderer(SpectrobesEntities.ENTITY_KASUMI.get(), KasumiRenderer::new);
    }
}
