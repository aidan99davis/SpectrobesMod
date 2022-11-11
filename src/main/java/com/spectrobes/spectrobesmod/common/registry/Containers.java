package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Containers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.MENU_TYPES, SpectrobesInfo.MOD_ID);

    public static void init() {
        PrizmodContainer.PRIZMOD = CONTAINERS.register("prizmod",
                () -> IForgeMenuType.create((windowId, inv, data) -> new PrizmodContainer(windowId, inv.player)));
        SpectrobeDetailsContainer.SPECTROBE_DETAILS = CONTAINERS.register("spectrobe_details",
                () -> IForgeMenuType.create((windowId, inv, data) -> new SpectrobeDetailsContainer(windowId, null)));
        HealerContainer.HEALER = CONTAINERS.register("healer",
                () -> IForgeMenuType.create((windowId, inv, data) -> new HealerContainer(windowId, inv.player)));
    }
}
