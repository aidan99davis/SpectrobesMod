package com.spectrobes.spectrobesmod;

import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlRendererManager;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobeRendererManager;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.client.keybindings.SpectrobesKeybindings;
import com.spectrobes.spectrobesmod.client.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.common.capability.PlayerEvents;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.registry.Containers;
import com.spectrobes.spectrobesmod.common.registry.IconRegistry;
import com.spectrobes.spectrobesmod.common.registry.MineralRegistry;
import com.spectrobes.spectrobesmod.common.worldgen.SpectrobesOreGen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nullable;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SpectrobesInfo.MOD_ID)
@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Bus.MOD)
public class SpectrobesMod
{
    public static SpectrobesMod Instance;
    final IEventBus modEventBus;

    public SpectrobesMod() {
        Instance = this;
        modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //register listeners to the event bus
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::enqueueIMC);
        modEventBus.addListener(this::processIMC);
        modEventBus.addListener(this::doClientStuff);
        // Register ourselves for server and other game events we are interested in

        SpectrobesEntities.ENTITY_TYPES.register(modEventBus);
        Containers.CONTAINERS.register(modEventBus);
        Containers.init();
        KrawlEntities.ENTITY_TYPES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        SpectrobesNetwork.init();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        MinecraftForge.EVENT_BUS.register(PlayerEvents.instance);
        IconRegistry.init();
        SpectrobesEntities.init();
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
        ScreenManager.registerFactory(PrizmodContainer.PRIZMOD.get(), PrizmodScreen::new);
        SpectrobeRendererManager.init();
        KrawlEntities.init();
        KrawlRendererManager.init();
        MineralRegistry.init();
        SpectrobesKeybindings.initKeybinds();
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        //InterModComms.sendTo("examplemod", "helloworld", () -> { SpectrobesInfo.LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
//        SpectrobesInfo.LOGGER.info("Got IMC {}", event.getIMCStream().
//                map(m->m.getMessageSupplier().get()).
//                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        //SpectrobesInfo.LOGGER.info("HELLO from server starting");
    }

}
