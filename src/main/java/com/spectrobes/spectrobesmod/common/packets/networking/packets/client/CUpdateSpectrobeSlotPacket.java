package com.spectrobes.spectrobesmod.common.packets.networking.packets.client;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

public class CUpdateSpectrobeSlotPacket implements CustomPacketPayload {

    public static final Type<CUpdateSpectrobeSlotPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "c_update_spectrobe_slot")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, CUpdateSpectrobeSlotPacket> STREAM_CODEC =
            StreamCodec.ofMember(CUpdateSpectrobeSlotPacket::write, CUpdateSpectrobeSlotPacket::new);

    public final int slot;
    public final UUID spectrobeUUID;

    public CUpdateSpectrobeSlotPacket(int slot, UUID spectrobeUUID) {
        this.slot = slot;
        this.spectrobeUUID = spectrobeUUID;
    }

    private CUpdateSpectrobeSlotPacket(RegistryFriendlyByteBuf buffer) {
        this.spectrobeUUID = UUIDUtil.STREAM_CODEC.decode(buffer);
        this.slot = ByteBufCodecs.VAR_INT.decode(buffer);
    }

    private void write(RegistryFriendlyByteBuf buffer) {
        UUIDUtil.STREAM_CODEC.encode(buffer, this.spectrobeUUID);
        ByteBufCodecs.VAR_INT.encode(buffer, this.slot);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(CUpdateSpectrobeSlotPacket packet, IPayloadContext context) {
        ClientPacketHandler.handlePacket(packet, context);
    }
}