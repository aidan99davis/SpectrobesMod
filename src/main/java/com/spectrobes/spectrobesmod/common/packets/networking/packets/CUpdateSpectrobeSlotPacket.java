package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobePacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CUpdateSpectrobeSlotPacket {

    public int slot;

    public UUID spectrobeUUID;

    public CUpdateSpectrobeSlotPacket(int slot, UUID spectrobeUUID) {
        this.slot = slot;
        this.spectrobeUUID = spectrobeUUID;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUUID(spectrobeUUID);
        buf.writeInt(slot);
    }

    public static CUpdateSpectrobeSlotPacket fromBytes(FriendlyByteBuf buf) {
        int slot = buf.readInt();
        UUID spectrobeUUID = buf.readUUID();

        return new CUpdateSpectrobeSlotPacket(slot, spectrobeUUID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        return SpectrobePacketHandler.handlePacket(this, ctx);
    }
}
