package com.spectrobes.spectrobesmod;

import com.spectrobes.spectrobesmod.client.entity.attacks.AttackEntities;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMasterDispatcher;
import com.spectrobes.spectrobesmod.common.registry.*;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.capability.PlayerEvents;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.registry.items.*;
import com.spectrobes.spectrobesmod.common.world.SpectrobesEntitySpawns;
import com.spectrobes.spectrobesmod.common.world.SpectrobesOreGen;
import com.spectrobes.spectrobesmod.events.ClientEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(SpectrobesInfo.MOD_ID)
@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID)
public class SpectrobesMod
{
    public static SpectrobesMod Instance;
    final IEventBus modEventBus;

    public SpectrobesMod() {
        GeckoLib.initialize();
        SpectrobesMineralsRegistry.init();

        modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        //register listeners to the event bus
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::onClientStarting);
        modEventBus.addListener(this::onLoaded);
        modEventBus.addListener(SpectrobeMaster::register);
        forgeBus.addGenericListener(Entity.class, PlayerSpectrobeMasterDispatcher::attach);
        modEventBus.addListener(SpectrobesEntities::registerEntityAttributes);
        modEventBus.addListener(KrawlEntities::registerEntityAttributes);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        MinecraftForge.EVENT_BUS.register(new SpectrobesEntitySpawns());
        SpectrobesEntities.ENTITY_TYPES.register(modEventBus);
        AttackEntities.ENTITY_TYPES.register(modEventBus);
        KrawlEntities.ENTITY_TYPES.register(modEventBus);
        SpectrobesFossilsRegistry.ITEMS.register(modEventBus);
        SpectrobesMineralsRegistry.ITEMS.register(modEventBus);
        SpectrobesToolsRegistry.ITEMS.register(modEventBus);
        SpectrobesArmourRegistry.ITEMS.register(modEventBus);
        SpectrobesMachinesRegistry.ITEMS.register(modEventBus);
        SpectrobesItemsRegistry.ITEMS.register(modEventBus);
        DataSerializerRegistry.SERIALIZERS.register(modEventBus);
        SpectrobesTileRegistry.TILES.register(modEventBus);
        SpectrobesBlocks.BLOCKS.register(modEventBus);
        SpectrobesBlockItemsRegistry.ITEMS.register(modEventBus);
        Containers.CONTAINERS.register(modEventBus);
        SpectrobesOreGen.CONFIGURED_FEATURES.register(modEventBus);
        SpectrobesOreGen.PLACED_FEATURES.register(modEventBus);
        Containers.init();

        SpectrobesNetwork.init();
        Instance = this;
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        MinecraftForge.EVENT_BUS.register(PlayerEvents.instance);
        IconRegistry.init();
        event.enqueueWork(() -> SpawnPlacements.register(KrawlEntities.ENTITY_VORTEX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules));
    }

    @SubscribeEvent
    public void onClientStarting(final FMLClientSetupEvent event)
    {
        ClientEvents.doClientStuff();
    }

    @SubscribeEvent
    public void onLoaded(final FMLLoadCompleteEvent event) {
        SpectrobesEntities.populateMap();
        KrawlEntities.populateMaps();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLDedicatedServerSetupEvent event) {
        // do something when the server starts
        //SpectrobesInfo.LOGGER.info("HELLO from server starting");
    }

}
