package com.spectrobes.spectrobesmod.common.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerProperties {
    @CapabilityInject(PlayerSpectrobeMaster.class)
    public static Capability<PlayerSpectrobeMaster> PLAYER_SPECTROBE_MASTER;
}
