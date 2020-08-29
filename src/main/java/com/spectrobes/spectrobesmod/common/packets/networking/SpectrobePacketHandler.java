package com.spectrobes.spectrobesmod.common.packets.networking;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SpectrobePacketHandler {

    public static boolean handlePacket(SSyncSpectrobeMasterPacket packet, Supplier<NetworkEvent.Context> ctx) {
        SpectrobesInfo.LOGGER.info("handling spectrobe master sync packet");
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = Minecraft.getInstance().player;
            PlayerSpectrobeMaster clientCap = player
                    .getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .orElseThrow(IllegalStateException::new);
            clientCap.deserializeNBT(packet.capability.serializeNBT());

        });
        return true;
    }
}
