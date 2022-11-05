package com.spectrobes.spectrobesmod.client.entity.krawl;

import com.spectrobes.spectrobesmod.client.entity.krawl.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class KrawlRendererManager {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(KrawlEntities.ENTITY_SWAR.get(), SwarRenderer::new);
        event.registerEntityRenderer(KrawlEntities.ENTITY_SUBAR.get(), SubarRenderer::new);
        event.registerEntityRenderer(KrawlEntities.ENTITY_VIZBAR.get(), VizbarRenderer::new);
        event.registerEntityRenderer(KrawlEntities.ENTITY_GRIS.get(), GrisRenderer::new);
        event.registerEntityRenderer(KrawlEntities.ENTITY_GRISEN.get(), GrisenRenderer::new);
        event.registerEntityRenderer(KrawlEntities.ENTITY_VORTEX.get(), VortexRenderer::new);
        event.registerEntityRenderer(KrawlEntities.ENTITY_XELLES.get(), XellesRenderer::new);
        event.registerEntityRenderer(KrawlEntities.ENTITY_HEALING_SPORES.get(), XellesSporeHealRenderer::new);
        event.registerEntityRenderer(KrawlEntities.ENTITY_SPAWNING_SPORE.get(), XellesSpawningSporeRenderer::new);
        event.registerEntityRenderer(KrawlEntities.ENTITY_ORBIX.get(), OrbixRenderer::new);
        event.registerEntityRenderer(KrawlEntities.ENTITY_ORBUX.get(), OrbixRenderer::new);
        event.registerEntityRenderer(KrawlEntities.ENTITY_OTORSO.get(), OtorsoRenderer::new);
    }
}
