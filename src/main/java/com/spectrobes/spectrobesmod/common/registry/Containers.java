package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.container.HealerContainer;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Containers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, SpectrobesInfo.MOD_ID);

    public static void init() {
        PrizmodContainer.PRIZMOD = CONTAINERS.register("prizmod",
                () -> IForgeContainerType.create((windowId, inv, data) -> new PrizmodContainer(windowId, inv.player)));
        HealerContainer.HEALER = CONTAINERS.register("healer",
                () -> IForgeContainerType.create((windowId, inv, data) -> new HealerContainer(windowId, inv.player)));
    }
}
