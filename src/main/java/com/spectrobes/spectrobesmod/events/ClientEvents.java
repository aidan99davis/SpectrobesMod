package com.spectrobes.spectrobesmod.events;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.container.CyrusShopContainer;
import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import com.spectrobes.spectrobesmod.client.gui.cyrus_shop.CyrusShopScreen;
import com.spectrobes.spectrobesmod.client.gui.healer.HealerScreen;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.client.gui.spectrobes_details.SpectrobeDetailsScreen;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(
        modid = SpectrobesInfo.MOD_ID,
        value = net.neoforged.api.distmarker.Dist.CLIENT
)
public final class ClientEvents {

    private ClientEvents() {
    }

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(PrizmodContainer.PRIZMOD.get(), PrizmodScreen::new);
        event.register(HealerContainer.HEALER.get(), HealerScreen::new);
        event.register(CyrusShopContainer.CYRUS_SHOP.get(), CyrusShopScreen::new);
        event.register(SpectrobeDetailsContainer.SPECTROBE_DETAILS.get(), SpectrobeDetailsScreen::new);
    }
}