package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class SDespawnSpectrobePacket {

    @Nullable
    private BlockPos playerPos;

    public SDespawnSpectrobePacket(BlockPos player) {
        this.playerPos = player;
    }

    public void toBytes(PacketBuffer buf) {
        if(playerPos != null) {
            buf.writeVarInt(playerPos.getX());
            buf.writeVarInt(playerPos.getY());
            buf.writeVarInt(playerPos.getZ());
        }
    }

    public static SDespawnSpectrobePacket fromBytes(PacketBuffer buf) {
        int x = buf.readVarInt();
        int y = buf.readVarInt();
        int z = buf.readVarInt();
        return new SDespawnSpectrobePacket(new BlockPos(x, y, z));
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerWorld world = (ServerWorld) ctx.get().getSender().world;

            MutableBoundingBox boundingBox = MutableBoundingBox.createProper(playerPos.getX(), playerPos.getY(), playerPos.getZ(), playerPos.getX(), playerPos.getY(), playerPos.getZ());
            AxisAlignedBB axisAlignedBB = AxisAlignedBB.toImmutable(boundingBox);

            List<EntitySpectrobe> spectrobes = world
                    .getEntitiesWithinAABB(EntitySpectrobe.class, axisAlignedBB.grow(30, 30, 30));
            for(EntitySpectrobe spectrobe : spectrobes) {
                if(spectrobe.getOwner() != null && spectrobe.getOwnerId()
                        .equals(ctx.get().getSender().getUniqueID())) {
                    spectrobe.despawn();
                }
            }

        });
        return true;
    }
}
