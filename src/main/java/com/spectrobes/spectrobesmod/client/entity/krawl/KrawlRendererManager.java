package com.spectrobes.spectrobesmod.client.entity.krawl;

import com.spectrobes.spectrobesmod.client.entity.krawl.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class KrawlRendererManager {
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_SWAR.get(), SwarRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_SUBAR.get(), SubarRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_VIZBAR.get(), VizbarRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_GRIS.get(), GrisRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_GRISEN.get(), GrisenRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_VORTEX.get(), VortexRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_XELLES.get(), XellesRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_HEALING_SPORES.get(), XellesSporeHealRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_SPAWNING_SPORE.get(), XellesSpawningSporeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_ORBIX.get(), OrbixRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_ORBUX.get(), OrbixRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_OTORSO.get(), OtorsoRenderer::new);
    }
}
