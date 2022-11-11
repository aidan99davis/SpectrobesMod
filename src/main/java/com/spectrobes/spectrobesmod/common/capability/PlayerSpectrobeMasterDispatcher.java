package com.spectrobes.spectrobesmod.common.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerSpectrobeMasterDispatcher implements ICapabilitySerializable<CompoundTag> {

    private final PlayerSpectrobeMaster playerSpectrobeMaster = new PlayerSpectrobeMaster();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == PlayerProperties.PLAYER_SPECTROBE_MASTER) {
            return LazyOptional.of(() -> (T) playerSpectrobeMaster);
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return playerSpectrobeMaster.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        playerSpectrobeMaster.deserializeNBT(nbt);
    }
}
