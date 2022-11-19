package com.spectrobes.spectrobesmod.events;

import com.spectrobes.spectrobesmod.client.blocks.BlockRendererManager;
import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import com.spectrobes.spectrobesmod.client.gui.healer.HealerScreen;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.client.gui.spectrobes_details.SpectrobeDetailsScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.IEventBus;

public class ClientEvents {

    public static final void registerEvents(IEventBus modEventBus) {
//        modEventBus.addListener(BlockRendererManager::registerTileEntityRenderers);
//        modEventBus.addListener(SpectrobeRendererManager::registerEntityRenderers);
//        modEventBus.addListener(KrawlRendererManager::registerEntityRenderers);
//        modEventBus.addListener(AttackRendererManager::registerEntityRenderers);
//        modEventBus.addListener(ArmourRendererRegisterer::registerRenderers);
    }

    public static final void doClientStuff()
    {
        MenuScreens.register(PrizmodContainer.PRIZMOD.get(), PrizmodScreen::new);
        MenuScreens.register(HealerContainer.HEALER.get(), HealerScreen::new);
        MenuScreens.register(SpectrobeDetailsContainer.SPECTROBE_DETAILS.get(), SpectrobeDetailsScreen::new);
        BlockRendererManager.init();
    }
}
