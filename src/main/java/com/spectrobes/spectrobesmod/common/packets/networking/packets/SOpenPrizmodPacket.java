package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.client.SpectrobePacketHandler;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SOpenPrizmodPacket implements CustomPacketPayload {

    public static final SOpenPrizmodPacket INSTANCE = new SOpenPrizmodPacket();

    public static final Type<SOpenPrizmodPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "open_prizmod")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SOpenPrizmodPacket> STREAM_CODEC =
            StreamCodec.unit(INSTANCE);

    public SOpenPrizmodPacket() {
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SOpenPrizmodPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> SpectrobePacketHandler.handlePacket(packet, context));
    }
}