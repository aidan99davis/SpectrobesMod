package com.spectrobes.spectrobesmod.client.container;

import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesToolsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class HealerContainer extends AbstractContainerMenu {
    private final PlayerSpectrobeMaster capability;
    private final Player player;

    public static Supplier<MenuType<HealerContainer>> HEALER = null;

    public HealerContainer(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory.player);
    }

    public HealerContainer(int containerId, Player player) {
        super(HEALER.get(), containerId);

        this.player = player;
        this.capability = getSpectrobeMaster(player);
    }

    private static PlayerSpectrobeMaster getSpectrobeMaster(Player player) {
        PlayerSpectrobeMaster capability = player.getCapability(SpectrobeMaster.INSTANCE);

        if (capability == null) {
            throw new IllegalStateException("Player is missing SpectrobeMaster capability.");
        }

        return capability;
    }

    /**
     * Determines whether the supplied player can use this container.
     */
    @Override
    public boolean stillValid(Player player) {
        return player.getInventory().contains(SpectrobesToolsRegistry.prizmod_item.get().getDefaultInstance());
    }

    public int getCurrentGuraBalance() {
        return capability.getCurrentGuraBalance();
    }

    public void healTeam() {
        capability.getCurrentTeamUuids().forEach((slot, uuid) -> {
            if (uuid == null) {
                return;
            }

            Spectrobe spectrobe = capability.getSpectrobeByUuid(uuid);

            if (spectrobe != null) {
                spectrobe.setCurrentHealth(spectrobe.stats.getHpLevel());
            }
        });
    }

    public boolean spendGura(int healCost) {
        return capability.spendGura(healCost);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }
}