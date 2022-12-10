package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CSpectrobeAttackPacket {

    private final int spectrobeID;
    private final int krawlID;

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
            LivingEntity krawl = (LivingEntity) player.level.getEntity(krawlID);
            EntitySpectrobe spectrobe = (EntitySpectrobe) player.level.getEntity(spectrobeID);
            if(spectrobe.getTarget() != null) {
                if(spectrobe.getTarget() instanceof EntityKrawl krawl1) krawl1.setGlowing(false);
                if(spectrobe.getTarget() instanceof EntitySpectrobe spec1) spec1.setGlowing(false);
                spectrobe.setTarget(null);
            }
            spectrobe.setTarget(krawl);
            spectrobe.getNavigation().moveTo(krawl, 1);
        });
        ctx.get().setPacketHandled(true);
        return true;
    }
}
