package com.spectrobes.spectrobesmod.common.world.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpectrobesBiomes {

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event)
    {
//        event.getRegistry().register(new GenshiVolcanoBiome().build());
    }
}
