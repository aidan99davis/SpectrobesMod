package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobePacketHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CUpdateSpectrobeSlotPacket {

    public int slot;

    public UUID spectrobeUUID;

    public CUpdateSpectrobeSlotPacket(int slot, UUID spectrobeUUID) {
        this.slot = slot;
        this.spectrobeUUID = spectrobeUUID;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeUniqueId(spectrobeUUID);
        buf.writeInt(slot);
    }

    public static CUpdateSpectrobeSlotPacket fromBytes(PacketBuffer buf) {
        int slot = buf.readInt();
        UUID spectrobeUUID = buf.readUniqueId();

        return new CUpdateSpectrobeSlotPacket(slot, spectrobeUUID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        return SpectrobePacketHandler.handlePacket(this, ctx);
    }
}
