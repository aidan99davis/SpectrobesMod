package com.spectrobes.spectrobesmod.common.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class PlayerProperties {
    public static Capability<PlayerSpectrobeMaster> PLAYER_SPECTROBE_MASTER = CapabilityManager.get(new CapabilityToken<>(){});
//    @CapabilityInject(PlayerSpectrobeMaster.class)
//    public static Capability<PlayerSpectrobeMaster> PLAYER_SPECTROBE_MASTER;
}
