package com.spectrobes.spectrobesmod.common.packets.networking.packets.client;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobePacketHandler;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class COpenCyrusShopPacket implements CustomPacketPayload {

    public static final COpenCyrusShopPacket INSTANCE = new COpenCyrusShopPacket();

    public static final Type<COpenCyrusShopPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "open_cyrus_shop")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, COpenCyrusShopPacket> STREAM_CODEC =
            StreamCodec.unit(INSTANCE);

    public COpenCyrusShopPacket() {
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(COpenCyrusShopPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> SpectrobePacketHandler.handlePacket(packet, context));
    }
}