package com.spectrobes.spectrobesmod.client.container;

import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;

public class SpectrobeDetailsContainer extends Container {

    private final Spectrobe spectrobe;

    public static RegistryObject<ContainerType<SpectrobeDetailsContainer>> SPECTROBE_DETAILS = null;

    public SpectrobeDetailsContainer(int id, Spectrobe spectrobe) {
        super(SPECTROBE_DETAILS.get(), id);
        this.spectrobe = spectrobe;
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return playerIn.inventory.contains(SpectrobesItems.prizmod_item.getDefaultInstance());
    }

    public Spectrobe getSpectrobe() {
        return spectrobe;
    }
}
