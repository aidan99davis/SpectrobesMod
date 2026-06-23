package com.spectrobes.spectrobesmod.client.entity.krawl;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.renderer.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, value = Dist.CLIENT)
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
