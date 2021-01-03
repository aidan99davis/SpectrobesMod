package com.spectrobes.spectrobesmod.client.entity.krawl;

import com.spectrobes.spectrobesmod.client.entity.krawl.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class KrawlRendererManager {
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_SWAR.get(), manager -> new SwarRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_SUBAR.get(), manager -> new SubarRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_VIZBAR.get(), manager -> new VizbarRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_GRIS.get(), manager -> new GrisRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_GRISEN.get(), manager -> new GrisenRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_VORTEX.get(), manager -> new VortexRenderer((manager)));
    }
}
