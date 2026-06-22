package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.container.CyrusShopContainer;
import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Containers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS =
            DeferredRegister.create(Registries.MENU, SpectrobesInfo.MOD_ID);

    public static void init() {
        PrizmodContainer.PRIZMOD = CONTAINERS.register(
                "prizmod",
                () -> new MenuType<>(
                        (containerId, inventory) -> new PrizmodContainer(containerId, inventory.player),
                        FeatureFlags.DEFAULT_FLAGS)
        );

        SpectrobeDetailsContainer.SPECTROBE_DETAILS = CONTAINERS.register(
                "spectrobe_details",
                () -> new MenuType<>(
                        (containerId, inventory) -> new SpectrobeDetailsContainer(containerId, null),
                        FeatureFlags.DEFAULT_FLAGS
                )
        );

        HealerContainer.HEALER = CONTAINERS.register(
                "healer",
                () -> new MenuType<>(HealerContainer::new, FeatureFlags.DEFAULT_FLAGS)
        );

        CyrusShopContainer.CYRUS_SHOP = CONTAINERS.register(
                "cyrusshop",
                () -> new MenuType<>(CyrusShopContainer::new, FeatureFlags.DEFAULT_FLAGS)
        );
    }
}