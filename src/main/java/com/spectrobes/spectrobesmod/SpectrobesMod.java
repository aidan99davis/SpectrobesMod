package com.spectrobes.spectrobesmod;

import com.spectrobes.spectrobesmod.client.entity.attacks.AttackEntities;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.capability.PlayerEvents;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItemGroups;
import com.spectrobes.spectrobesmod.common.registry.*;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.*;
import com.spectrobes.spectrobesmod.common.world.SpectrobesOreGen;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@Mod(SpectrobesInfo.MOD_ID)
public class SpectrobesMod {

    public static SpectrobesMod Instance;

    private final IEventBus modEventBus;

    public SpectrobesMod(IEventBus modEventBus) {
        this.modEventBus = modEventBus;

        SpectrobesMineralsRegistry.init();

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::registerSpawnPlacements);
        modEventBus.addListener(this::onClientStarting);
        modEventBus.addListener(this::onLoaded);

        modEventBus.addListener(SpectrobesEntities::registerEntityAttributes);
        modEventBus.addListener(KrawlEntities::registerEntityAttributes);
        modEventBus.addListener(SpectrobeMaster::registerCapabilities);

        SpectrobeMaster.ATTACHMENT_TYPES.register(modEventBus);

        SpectrobesEntities.ENTITY_TYPES.register(modEventBus);
        AttackEntities.ENTITY_TYPES.register(modEventBus);
        KrawlEntities.ENTITY_TYPES.register(modEventBus);

        SpectrobesBlocks.BLOCKS.register(modEventBus);
        SpectrobesTileRegistry.TILES.register(modEventBus);

        SpectrobesFossilsRegistry.ITEMS.register(modEventBus);
        SpectrobesMineralsRegistry.ITEMS.register(modEventBus);
        SpectrobesToolsRegistry.ITEMS.register(modEventBus);
        SpectrobesArmourRegistry.ITEMS.register(modEventBus);
        SpectrobesMachinesRegistry.ITEMS.register(modEventBus);
        SpectrobesItemsRegistry.ITEMS.register(modEventBus);
        SpectrobesBlockItemsRegistry.ITEMS.register(modEventBus);

        SpectrobesItemGroups.register(modEventBus);

        DataSerializerRegistry.SERIALIZERS.register(modEventBus);
        Containers.CONTAINERS.register(modEventBus);
        Containers.init();

        SpectrobesOreGen.register(modEventBus);

        Instance = this;
    }

    private void setup(final FMLCommonSetupEvent event) {
        NeoForge.EVENT_BUS.register(PlayerEvents.instance);

        event.enqueueWork(() -> {
            IconRegistry.init();
        });
    }

    private void registerSpawnPlacements(final RegisterSpawnPlacementsEvent event) {
        event.register(
                KrawlEntities.ENTITY_VORTEX.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.REPLACE
        );
    }

    private void onClientStarting(final FMLClientSetupEvent event) {
    }

    private void onLoaded(final FMLLoadCompleteEvent event) {
        SpectrobesEntities.populateMap();
        KrawlEntities.populateMaps();
    }
}