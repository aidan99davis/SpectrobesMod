package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CSpectrobeAttackPacket {

    private int spectrobeID;
    private int krawlID;

    public CSpectrobeAttackPacket(int spectrobeId, int krawlId) {
        this.spectrobeID = spectrobeId;
        this.krawlID = krawlId;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(spectrobeID);
        buf.writeInt(krawlID);
    }

    public static CSpectrobeAttackPacket fromBytes(FriendlyByteBuf buf) {
        int spectrobeId = buf.readInt();
        int krawlId = buf.readInt();

        return new CSpectrobeAttackPacket(spectrobeId, krawlId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            EntityKrawl krawl = (EntityKrawl)player.level.getEntity(krawlID);
            EntitySpectrobe spectrobe = (EntitySpectrobe) player.level.getEntity(spectrobeID);
            spectrobe.setTarget(null);
            spectrobe.setTarget(krawl);
        });
        ctx.get().setPacketHandled(true);
        return true;
    }
}
