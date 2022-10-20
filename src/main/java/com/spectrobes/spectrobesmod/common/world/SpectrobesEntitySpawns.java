package com.spectrobes.spectrobesmod.common.world;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityVortex;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Bus.FORGE)
public class SpectrobesEntitySpawns {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        List<MobSpawnInfo.Spawners> spawns =
                event.getSpawns().getSpawner(EntityClassification.MONSTER);

        spawns.add(new MobSpawnInfo.Spawners(KrawlEntities.ENTITY_VORTEX.get(), 100, 1, 3));
    }

    public static final EntitySpawnPlacementRegistry.IPlacementPredicate<EntityVortex> MONSTER = (EntityType<EntityVortex> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random rand) -> {
        if (world.getDifficulty() == Difficulty.PEACEFUL)
            return false;

        return MonsterEntity.isDarkEnoughToSpawn(world, pos, rand);
    };
}
