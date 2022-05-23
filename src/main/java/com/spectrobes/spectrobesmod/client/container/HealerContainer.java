package com.spectrobes.spectrobesmod.client.container;

import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;

public class HealerContainer extends Container {
    private PlayerSpectrobeMaster capability;
    private PlayerEntity player;

    public static RegistryObject<ContainerType<HealerContainer>> HEALER = null;

    public HealerContainer(int pContainerId, PlayerEntity player) {
        super(HEALER.get(), pContainerId);
        this.player = player;
        this.capability = this.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                .orElseThrow(IllegalStateException::new);
    }

    /**
     * Determines whether supplied player can use this container
     *
     * @param playerIn
     */
    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return playerIn.inventory.contains(SpectrobesItems.prizmod_item.getDefaultInstance());
    }

    public int getCurrentGuraBalance() {
        return capability.getCurrentGuraBalance();
    }

    public void healTeam() {
        capability.getCurrentTeamUuids().forEach((integer, uuid) -> {
            Spectrobe spectrobe = capability.getSpectrobeByUuid(uuid);
            spectrobe.setCurrentHealth(spectrobe.stats.getHpLevel());
        });
    }

    public boolean spendGura(int healCost) {
        return capability.spendGura(healCost);
    }
}
