package com.spectrobes.spectrobesmod;

import com.spectrobes.spectrobesmod.client.armour.ArmourRendererRegisterer;
import com.spectrobes.spectrobesmod.client.blocks.BlockRendererManager;
import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import com.spectrobes.spectrobesmod.client.entity.attacks.AttackEntities;
import com.spectrobes.spectrobesmod.client.entity.attacks.AttackRendererManager;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlRendererManager;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobeRendererManager;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.client.gui.healer.HealerScreen;
import com.spectrobes.spectrobesmod.client.gui.spectrobes_details.SpectrobeDetailsScreen;
import com.spectrobes.spectrobesmod.client.keybindings.SpectrobesKeybindings;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.registry.*;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.capability.PlayerEvents;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.registry.items.*;
import com.spectrobes.spectrobesmod.common.world.SpectrobesEntitySpawns;
import com.spectrobes.spectrobesmod.common.world.SpectrobesOreGen;
import net.minecraft.client.gui.screens.MenuScreens;
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

        //register listeners to the event bus
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        modEventBus.addListener(SpectrobesEntities::registerEntityAttributes);
        modEventBus.addListener(KrawlEntities::registerEntityAttributes);
        modEventBus.addListener(BlockRendererManager::registerTileEntityRenderers);
        modEventBus.addListener(SpectrobeRendererManager::registerEntityRenderers);
        modEventBus.addListener(KrawlRendererManager::registerEntityRenderers);
        modEventBus.addListener(AttackRendererManager::registerEntityRenderers);
        modEventBus.addListener(ArmourRendererRegisterer::registerRenderers);
//        modEventBus.addListener(SpectrobesKeybindings.KeybindingModBusEvents::onKeyRegister);
//        modEventBus.addListener(SpectrobesKeybindings.KeybindingForgeEvents::handleKeys);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
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
        SpectrobesEntities.populateMap();
        KrawlEntities.populateMaps();

        event.enqueueWork(() -> SpawnPlacements.register(KrawlEntities.ENTITY_VORTEX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules));
    }

    @SubscribeEvent
    public void doClientStuff(final FMLClientSetupEvent event)
    {
        MenuScreens.register(PrizmodContainer.PRIZMOD.get(), PrizmodScreen::new);
        MenuScreens.register(HealerContainer.HEALER.get(), HealerScreen::new);
        MenuScreens.register(SpectrobeDetailsContainer.SPECTROBE_DETAILS.get(), SpectrobeDetailsScreen::new);
        BlockRendererManager.init();
        MineralRegistry.init();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLDedicatedServerSetupEvent event) {
        // do something when the server starts
        //SpectrobesInfo.LOGGER.info("HELLO from server starting");
    }

}
