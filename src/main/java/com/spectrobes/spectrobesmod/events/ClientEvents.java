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
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {
    public static final ClientEvents Instance = new ClientEvents();
    public static void doClientStuff()
    {
        MenuScreens.register(PrizmodContainer.PRIZMOD.get(), PrizmodScreen::new);
        MenuScreens.register(HealerContainer.HEALER.get(), HealerScreen::new);
        MenuScreens.register(CyrusShopContainer.CYRUS_SHOP.get(), CyrusShopScreen::new);
        MenuScreens.register(SpectrobeDetailsContainer.SPECTROBE_DETAILS.get(), SpectrobeDetailsScreen::new);
    }
}
