package com.spectrobes.spectrobesmod.common.packets.networking.packets.client;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class COpenPrizmodPacket implements CustomPacketPayload {

    public static final COpenPrizmodPacket INSTANCE = new COpenPrizmodPacket();

    public static final Type<COpenPrizmodPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "open_prizmod")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, COpenPrizmodPacket> STREAM_CODEC =
            StreamCodec.unit(INSTANCE);

    public COpenPrizmodPacket() {
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(COpenPrizmodPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> ClientPacketHandler.handlePacket(packet, context));
    }
}