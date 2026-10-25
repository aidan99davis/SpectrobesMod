package com.spectrobes.spectrobesmod.common.networking.server;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class SDespawnSpectrobePacket implements CustomPacketPayload {

    public static final Type<SDespawnSpectrobePacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "despawn_spectrobe")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SDespawnSpectrobePacket> STREAM_CODEC =
            StreamCodec.ofMember(SDespawnSpectrobePacket::write, SDespawnSpectrobePacket::new);

    @Nullable
    private final BlockPos playerPos;

    public SDespawnSpectrobePacket(@Nullable BlockPos playerPos) {
        this.playerPos = playerPos;
    }

    private SDespawnSpectrobePacket(RegistryFriendlyByteBuf buffer) {
        this.playerPos = buffer.readBoolean() ? buffer.readBlockPos() : null;
    }

    private void write(RegistryFriendlyByteBuf buffer) {
        buffer.writeBoolean(this.playerPos != null);

        if (this.playerPos != null) {
            buffer.writeBlockPos(this.playerPos);
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Nullable
    public BlockPos getPlayerPos() {
        return playerPos;
    }

    public static void handle(SDespawnSpectrobePacket packet, IPayloadContext context) {
        if (!(context.player() instanceof ServerPlayer player)) {
            return;
        }

        Level level = player.level();
        AABB searchBox = player.getBoundingBox().inflate(30.0D, 30.0D, 30.0D);

        List<EntitySpectrobe> spectrobes = level.getEntitiesOfClass(
                EntitySpectrobe.class,
                searchBox
        );

        UUID playerUUID = player.getUUID();

        for (EntitySpectrobe spectrobe : spectrobes) {
            UUID ownerUUID = spectrobe.getOwnerUUID();

            if (ownerUUID != null && ownerUUID.equals(playerUUID)) {
                spectrobe.despawn();
            }
        }
    }
}