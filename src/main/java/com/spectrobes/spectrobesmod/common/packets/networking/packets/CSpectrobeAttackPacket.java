package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CSpectrobeAttackPacket {

    private int spectrobeID;
    private int krawlID;

    public CSpectrobeAttackPacket(int spectrobeId, int krawlId) {
        this.spectrobeID = spectrobeId;
        this.krawlID = krawlId;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeInt(spectrobeID);
        buf.writeInt(krawlID);
    }

    public static CSpectrobeAttackPacket fromBytes(PacketBuffer buf) {
        int spectrobeId = buf.readInt();
        int krawlId = buf.readInt();

        return new CSpectrobeAttackPacket(spectrobeId, krawlId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            EntityKrawl krawl = (EntityKrawl)player.level.getEntity(krawlID);
            EntitySpectrobe spectrobe = (EntitySpectrobe) player.level.getEntity(spectrobeID);
            spectrobe.setTarget(null);
            spectrobe.setTarget(krawl);
        });
        ctx.get().setPacketHandled(true);
        return true;
    }
}
