package com.spectrobes.spectrobesmod.client.container;

import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesToolsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

public class SpectrobeDetailsContainer extends AbstractContainerMenu {

    private final Spectrobe spectrobe;

    public static RegistryObject<MenuType<SpectrobeDetailsContainer>> SPECTROBE_DETAILS = null;

    public SpectrobeDetailsContainer(int id, Spectrobe spectrobe) {
        super(SPECTROBE_DETAILS.get(), id);
        this.spectrobe = spectrobe;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return playerIn.getInventory().contains(SpectrobesToolsRegistry.prizmod_item.get().getDefaultInstance());
    }

    public Spectrobe getSpectrobe() {
        return spectrobe;
    }
}
