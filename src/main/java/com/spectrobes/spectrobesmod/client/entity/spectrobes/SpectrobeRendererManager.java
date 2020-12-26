package com.spectrobes.spectrobesmod.client.entity.spectrobes;

import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.client.entity.krawl.renderer.SwarRenderer;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class SpectrobeRendererManager {
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KOMAINU.get(), manager -> new KomainuRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KOMANOTO.get(), manager -> new KomanotoRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_VILAR.get(), manager -> new VilarRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_VILAMASTA.get(), manager -> new VilamastaRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SPIKO.get(), manager -> new SpikoRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SPIKAN.get(), manager -> new SpikanRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SAMUKABU.get(), manager -> new SamukabuRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SAMURITE.get(), manager -> new SamuriteRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KUBAKU.get(), manager -> new KubakuRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KUGANON.get(), manager -> new KuganonRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SHAKIN.get(), manager -> new ShakinRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SHAKOR.get(), manager -> new ShakorRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SEGU.get(), manager -> new SeguRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_HARUMI.get(), manager -> new HarumiRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_GRILDA.get(), manager -> new GrildaRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_GRILDEN.get(), manager -> new GrildenRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_ZOZA.get(), manager -> new ZozaRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_ZOZANE.get(), manager -> new ZozaneRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_SWAR.get(), manager -> new SwarRenderer(manager));
    }
}
