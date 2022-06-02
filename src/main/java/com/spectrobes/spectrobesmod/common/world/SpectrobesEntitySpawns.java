package com.spectrobes.spectrobesmod.common.world;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Bus.FORGE)
public class SpectrobesEntitySpawns {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        List<MobSpawnInfo.Spawners> spawns =
                event.getSpawns().getSpawner(EntityClassification.MONSTER);

        spawns.add(new MobSpawnInfo.Spawners(KrawlEntities.ENTITY_VORTEX.get(), 75, 1, 1));
    }
}
