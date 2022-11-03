package com.spectrobes.spectrobesmod.common.packets.networking;

import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CUpdateSpectrobeSlotPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpectrobePacketHandler {

    public static boolean handlePacket(SSyncSpectrobeMasterPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            PlayerSpectrobeMaster clientCap = player
                    .getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .orElseThrow(IllegalStateException::new);
            clientCap.deserializeNBT(packet.capability.serializeNBT());

        });
        return true;
    }

    public static boolean handlePacket(CUpdateSpectrobeSlotPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            PlayerSpectrobeMaster clientCap = player
                    .getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .orElseThrow(IllegalStateException::new);
            clientCap.setTeamMember(packet.slot, packet.spectrobeUUID);

        });
        return true;
    }
}
