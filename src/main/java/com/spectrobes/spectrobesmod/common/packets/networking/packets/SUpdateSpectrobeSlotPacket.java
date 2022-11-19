package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

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

    public void toBytes(FriendlyByteBuf buf) {
        if(spectrobeUUID != null)
            buf.writeUUID(spectrobeUUID);
        buf.writeInt(slot);
    }

    public static SUpdateSpectrobeSlotPacket fromBytes(FriendlyByteBuf buf) {
        int slot = buf.readInt();
        UUID spectrobeUUID;
        try {
            spectrobeUUID = buf.readUUID();
        } catch(Exception ex) {
            spectrobeUUID = null;
        }

        return new SUpdateSpectrobeSlotPacket(slot, spectrobeUUID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            PlayerSpectrobeMaster serverCap = (PlayerSpectrobeMaster) player
                    .getCapability(SpectrobeMaster.INSTANCE)
                    .orElseThrow(IllegalStateException::new);
            serverCap.setTeamMember(slot, spectrobeUUID);

        });
        return true;
    }
}
