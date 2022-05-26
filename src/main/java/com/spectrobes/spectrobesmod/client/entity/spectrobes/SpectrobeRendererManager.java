package com.spectrobes.spectrobesmod.client.entity.spectrobes;

import com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class SpectrobeRendererManager {
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KOMAINU.get(), KomainuRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KOMANOTO.get(), KomanotoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_VILAR.get(), VilarRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_VILAMASTA.get(), VilamastaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SPIKO.get(), SpikoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SPIKAN.get(), SpikanRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SAMUKABU.get(), SamukabuRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SAMURITE.get(), SamuriteRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KUBAKU.get(), KubakuRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KUGANON.get(), KuganonRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SHAKIN.get(), ShakinRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SHAKOR.get(), ShakorRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SEGU.get(), SeguRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SEGULOS.get(), SegulosRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_HARUMI.get(), HarumiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_HARUMITE.get(), HarumiteRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_GRILDA.get(), GrildaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_GRILDEN.get(), GrildenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_ZOZA.get(), ZozaRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_ZOZANE.get(), ZozaneRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_NAGU.get(), NaguRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_MOSSARI.get(), MossariRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_MOSSARITO.get(), MossaritoRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_AOI.get(), AoiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_BARTOR.get(), BartorRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_BARTOLOR.get(), BartolorRenderer::new);
    }
}
