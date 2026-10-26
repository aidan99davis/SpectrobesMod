package com.spectrobes.spectrobesmod.client.container;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

/**
 * Container for the Lab System block. Shares the Prizmod's spectrobe data access,
 * but does not require the player to carry a Prizmod.
 */
public class LabSystemContainer extends PrizmodContainer {

    public static Supplier<MenuType<PrizmodContainer>> LAB_SYSTEM = null;

    public LabSystemContainer(int id, Player player) {
        super(LAB_SYSTEM.get(), id, player);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return true;
    }
}
