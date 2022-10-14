package com.spectrobes.spectrobesmod.client.entity.attacks;

import com.spectrobes.spectrobesmod.client.entity.attacks.renderer.EnergyBoltRenderer;
import com.spectrobes.spectrobesmod.client.entity.krawl.renderer.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class AttackRendererManager {
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(AttackEntities.ENTITY_ENERGY_BOLT.get(), manager -> new EnergyBoltRenderer(manager));
    }
}
