package com.spectrobes.spectrobesmod.client.entity.attacks;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.attacks.renderer.EnergyBoltRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, value = Dist.CLIENT)
public class AttackRendererManager {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(AttackEntities.ENTITY_ENERGY_BOLT.get(), manager -> new EnergyBoltRenderer(manager));

    }
}
