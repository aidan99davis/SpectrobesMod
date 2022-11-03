package com.spectrobes.spectrobesmod.client.container;

import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.RegistryObject;

public class SpectrobeDetailsContainer extends ChestMenu {

    private final Spectrobe spectrobe;

    public static RegistryObject<MenuType<SpectrobeDetailsContainer>> SPECTROBE_DETAILS = null;

    public SpectrobeDetailsContainer(int id, Spectrobe spectrobe) {
        super(SPECTROBE_DETAILS.get(), id, null, null,0);
        this.spectrobe = spectrobe;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return playerIn.getInventory().contains(SpectrobesItems.prizmod_item.getDefaultInstance());
    }

    public Spectrobe getSpectrobe() {
        return spectrobe;
    }
}
