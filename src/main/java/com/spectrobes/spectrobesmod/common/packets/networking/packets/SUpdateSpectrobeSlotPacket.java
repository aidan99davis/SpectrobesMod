package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class SUpdateSpectrobeSlotPacket implements CustomPacketPayload {

    public static final Type<SUpdateSpectrobeSlotPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "s_update_spectrobe_slot")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SUpdateSpectrobeSlotPacket> STREAM_CODEC =
            StreamCodec.ofMember(SUpdateSpectrobeSlotPacket::write, SUpdateSpectrobeSlotPacket::new);

    private final int slot;

    @Nullable
    private final UUID spectrobeUUID;

    public SUpdateSpectrobeSlotPacket(int slot, @Nullable UUID spectrobeUUID) {
        this.slot = slot;
        this.spectrobeUUID = spectrobeUUID;
    }

    private SUpdateSpectrobeSlotPacket(RegistryFriendlyByteBuf buffer) {
        this.slot = ByteBufCodecs.VAR_INT.decode(buffer);
        this.spectrobeUUID = buffer.readBoolean() ? UUIDUtil.STREAM_CODEC.decode(buffer) : null;
    }

    private void write(RegistryFriendlyByteBuf buffer) {
        ByteBufCodecs.VAR_INT.encode(buffer, this.slot);
        buffer.writeBoolean(this.spectrobeUUID != null);

        if (this.spectrobeUUID != null) {
            UUIDUtil.STREAM_CODEC.encode(buffer, this.spectrobeUUID);
        }
    }

    public int getSlot() {
        return slot;
    }

    @Nullable
    public UUID getSpectrobeUUID() {
        return spectrobeUUID;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SUpdateSpectrobeSlotPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }

            PlayerSpectrobeMaster serverCap = player.getCapability(SpectrobeMaster.INSTANCE);

            if (serverCap == null) {
                return;
            }

            serverCap.setTeamMember(packet.slot, packet.spectrobeUUID);
        });
    }
}