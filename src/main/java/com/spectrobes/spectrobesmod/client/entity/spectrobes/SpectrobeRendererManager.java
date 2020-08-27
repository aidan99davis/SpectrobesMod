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
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SPIKO.get(), manager -> new SpikoRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KOMANOTO.get(), manager -> new KomanotoRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SPIKAN.get(), manager -> new SpikanRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SAMUKABU.get(), manager -> new SamukabuRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SAMURITE.get(), manager -> new SamuriteRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_SWAR.get(), manager -> new SwarRenderer(manager));


    }
}
