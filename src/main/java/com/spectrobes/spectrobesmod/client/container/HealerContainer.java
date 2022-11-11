package com.spectrobes.spectrobesmod.client.container;

import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesToolsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

public class HealerContainer extends AbstractContainerMenu {
    private PlayerSpectrobeMaster capability;
    private Player player;

    public static RegistryObject<MenuType<HealerContainer>> HEALER = null;

    public HealerContainer(int pContainerId, Player player) {
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
    public boolean stillValid(Player playerIn) {
        return playerIn.getInventory().contains(SpectrobesToolsRegistry.prizmod_item.get().getDefaultInstance());
    }

    public int getCurrentGuraBalance() {
        return capability.getCurrentGuraBalance();
    }

    public void healTeam() {
        capability.getCurrentTeamUuids().forEach((integer, uuid) -> {
            if(uuid != null) {
                Spectrobe spectrobe = capability.getSpectrobeByUuid(uuid);
                spectrobe.setCurrentHealth(spectrobe.stats.getHpLevel());
            }
        });
    }

    public boolean spendGura(int healCost) {
        return capability.spendGura(healCost);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }
}
