package com.spectrobes.spectrobesmod.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerSpectrobeMasterDispatcher implements ICapabilitySerializable<CompoundNBT> {

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
    public CompoundNBT serializeNBT() {
        return playerSpectrobeMaster.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        playerSpectrobeMaster.deserializeNBT(nbt);
    }
}
