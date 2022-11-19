package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerSpectrobeMasterDispatcher implements ICapabilitySerializable<CompoundTag> {

    public static final ResourceLocation IDENTIFIER = new ResourceLocation(SpectrobesInfo.MOD_ID, "spectrobemasters");

    private final PlayerSpectrobeMaster backend = new PlayerSpectrobeMaster();
    private final LazyOptional<PlayerSpectrobeMaster> optionalData = LazyOptional.of(() -> backend);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return SpectrobeMaster.INSTANCE.orEmpty(cap, this.optionalData);
    }

    void invalidate() {
        this.optionalData.invalidate();
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.backend.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.backend.deserializeNBT(nbt);
    }

    public static void attach(final AttachCapabilitiesEvent<Player> event) {
        Entity entity = event.getObject();
        if (entity instanceof Player) {
            final PlayerSpectrobeMasterDispatcher provider = new PlayerSpectrobeMasterDispatcher();

            event.addCapability(PlayerSpectrobeMasterDispatcher.IDENTIFIER, provider);
        }
    }
}
