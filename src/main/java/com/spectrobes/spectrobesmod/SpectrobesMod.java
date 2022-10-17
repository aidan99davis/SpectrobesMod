package com.spectrobes.spectrobesmod;

import com.spectrobes.spectrobesmod.client.armour.ArmourRendererRegisterer;
import com.spectrobes.spectrobesmod.client.blocks.BlockRendererManager;
import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.entity.attacks.AttackEntities;
import com.spectrobes.spectrobesmod.client.entity.attacks.AttackRendererManager;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlRendererManager;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobeRendererManager;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.client.gui.healer.HealerScreen;
import com.spectrobes.spectrobesmod.client.keybindings.SpectrobesKeybindings;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.registry.*;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.capability.PlayerEvents;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.world.SpectrobesEntitySpawns;
import com.spectrobes.spectrobesmod.common.world.SpectrobesOreGen;
import com.spectrobes.spectrobesmod.common.world.dimensions.SpectrobesDimensions;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.util.Direction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

import javax.annotation.Nullable;
import java.beans.EventHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SpectrobesInfo.MOD_ID)
@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID)
public class SpectrobesMod
{
    public static SpectrobesMod Instance;
    final IEventBus modEventBus;

    public SpectrobesMod() {
        GeckoLib.initialize();
        modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //register listeners to the event bus
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new SpectrobesOreGen());
        MinecraftForge.EVENT_BUS.register(new SpectrobesEntitySpawns());
        SpectrobesEntities.ENTITY_TYPES.register(modEventBus);
        AttackEntities.ENTITY_TYPES.register(modEventBus);
        KrawlEntities.ENTITY_TYPES.register(modEventBus);
        SpectrobesItemsRegistry.ITEMS.register(modEventBus);
        SpectrobesTileRegistry.TILES.register(modEventBus);
        SpectrobesBlocks.BLOCKS.register(modEventBus);
        Containers.CONTAINERS.register(modEventBus);
        Containers.init();
        SpectrobesNetwork.init();
        Instance = this;
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        DeferredWorkQueue.runLater(SpectrobesOreGen::registerOres);
        MinecraftForge.EVENT_BUS.register(PlayerEvents.instance);
        IconRegistry.init();
        SpectrobesEntities.init();
        KrawlEntities.init();

        CapabilityManager.INSTANCE.register(PlayerSpectrobeMaster.class, new Capability.IStorage<PlayerSpectrobeMaster>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<PlayerSpectrobeMaster> capability, PlayerSpectrobeMaster instance, Direction side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<PlayerSpectrobeMaster> capability, PlayerSpectrobeMaster instance, Direction side, INBT nbt) {
                instance.deserializeNBT((CompoundNBT) nbt);
            }
        }, () -> null);
    }

    @SubscribeEvent
    public void doClientStuff(final FMLClientSetupEvent event)
    {
        ScreenManager.register(PrizmodContainer.PRIZMOD.get(), PrizmodScreen::new);
        ScreenManager.register(HealerContainer.HEALER.get(), HealerScreen::new);
        SpectrobeRendererManager.init();
        //force load the serializer to prevent clients crashing
        IDataSerializer serializer = Spectrobe.SpectrobeSerializer;
        BlockRendererManager.init();
        KrawlRendererManager.init();
        AttackRendererManager.init();
        ArmourRendererRegisterer.registerRenderers();
        MineralRegistry.init();
        SpectrobesKeybindings.initKeybinds();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        //SpectrobesInfo.LOGGER.info("HELLO from server starting");
    }

}
