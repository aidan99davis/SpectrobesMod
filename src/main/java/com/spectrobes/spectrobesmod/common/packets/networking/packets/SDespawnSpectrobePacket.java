package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class SDespawnSpectrobePacket {

    @Nullable
    private Spectrobe spectrobe;

    public SDespawnSpectrobePacket(Spectrobe spectrobe) {
        this.spectrobe = spectrobe;
    }

    public void toBytes(PacketBuffer buf) {
        if(spectrobe != null) {
            buf.writeCompoundTag(spectrobe.write());
        }
    }

    public static SDespawnSpectrobePacket fromBytes(PacketBuffer buf) {
        Spectrobe spectrobe = Spectrobe.read(buf.readCompoundTag());

        return new SDespawnSpectrobePacket(spectrobe);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerWorld world = (ServerWorld) ctx.get().getSender().world;

            List<EntitySpectrobe> spectrobes = world
                    .getEntitiesWithinAABB(EntitySpectrobe.class, ctx.get().getSender().getBoundingBox().grow(30, 30, 30));
            for(EntitySpectrobe spectrobe : spectrobes) {
                if(spectrobe.getOwnerId().equals(ctx.get().getSender().getUniqueID())) {
                    spectrobe.despawn();
                }
            }



        });
        return true;
    }
}
