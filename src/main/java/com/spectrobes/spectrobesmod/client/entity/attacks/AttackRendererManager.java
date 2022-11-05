package com.spectrobes.spectrobesmod.client.entity.attacks;

import com.spectrobes.spectrobesmod.client.entity.attacks.renderer.EnergyBoltRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class AttackRendererManager {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(AttackEntities.ENTITY_ENERGY_BOLT.get(), manager -> new EnergyBoltRenderer(manager));

    }
}
