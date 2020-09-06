package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class SUpdateSpectrobeSlotPacket {

    private int slot;

    private UUID spectrobeUUID;

    public SUpdateSpectrobeSlotPacket(int slot, UUID spectrobeUUID) {
        this.slot = slot;
        this.spectrobeUUID = spectrobeUUID;
//        SpectrobesInfo.LOGGER.info("instantiated packet with SpectrobeUUID: " + spectrobeUUID.toString());
    }

    public void toBytes(PacketBuffer buf) {
        if(spectrobeUUID != null)
            buf.writeUniqueId(spectrobeUUID);
        buf.writeInt(slot);
    }

    public static SUpdateSpectrobeSlotPacket fromBytes(PacketBuffer buf) {
        int slot = buf.readInt();
        UUID spectrobeUUID;
        try {
            spectrobeUUID = buf.readUniqueId();
        } catch(Exception ex) {
            spectrobeUUID = null;
        }

        return new SUpdateSpectrobeSlotPacket(slot, spectrobeUUID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();

            PlayerSpectrobeMaster serverCap = player
                    .getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .orElseThrow(IllegalStateException::new);
            serverCap.setTeamMember(slot, spectrobeUUID);

        });
        return true;
    }
}
