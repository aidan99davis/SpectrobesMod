package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Containers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, SpectrobesInfo.MOD_ID);

    public static void init() {
        PrizmodContainer.PRIZMOD = CONTAINERS.register("prizmod",
                () -> IForgeContainerType.create((windowId, inv, data) -> new PrizmodContainer(windowId, inv.player)));
        SpectrobeDetailsContainer.SPECTROBE_DETAILS = CONTAINERS.register("spectrobe_details",
                () -> IForgeContainerType.create((windowId, inv, data) -> new SpectrobeDetailsContainer(windowId, null)));
        HealerContainer.HEALER = CONTAINERS.register("healer",
                () -> IForgeContainerType.create((windowId, inv, data) -> new HealerContainer(windowId, inv.player)));
    }
}
