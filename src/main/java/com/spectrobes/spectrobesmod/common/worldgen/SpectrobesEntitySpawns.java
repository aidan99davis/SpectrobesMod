package com.spectrobes.spectrobesmod.common.worldgen;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.*;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Bus.MOD)
public class SpectrobesEntitySpawns {

    @SubscribeEvent
    public static void spawnEntities(FMLLoadCompleteEvent event) {
        for(Biome biome : ForgeRegistries.BIOMES) {
            if(biome.getCategory() != Biome.Category.OCEAN) {
                biome.getSpawns(EntityClassification.MONSTER)
                        .add(new Biome.SpawnListEntry(KrawlEntities.ENTITY_SWAR.get(), 10, 1, 4));
            }
        }
    }
}
