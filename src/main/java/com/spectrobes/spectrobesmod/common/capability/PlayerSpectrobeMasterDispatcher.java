package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerSpectrobeMasterDispatcher implements ICapabilitySerializable<CompoundNBT> {

    private PlayerSpectrobeMaster playerSpectrobeMaster = new PlayerSpectrobeMaster();

    /**
     * Retrieves the Optional handler for the capability requested on the specific side.
     * The return value <strong>CAN</strong> be the same for multiple faces.
     * Modders are encouraged to cache this value, using the listener capabilities of the Optional to
     * be notified if the requested capability get lost.
     *
     * @param cap
     * @param side
     * @return The requested an optional holding the requested capability.
     */
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == PlayerProperties.PLAYER_SPECTROBE_MASTER) {
            return LazyOptional.of(() -> (T) playerSpectrobeMaster);
        }
        return null;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return playerSpectrobeMaster.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        playerSpectrobeMaster.deserializeNBT(nbt);
        SpectrobesInfo.LOGGER.warn("player pectrobes count: " + playerSpectrobeMaster.getOwnedSpectrobesCount());
    }
}
