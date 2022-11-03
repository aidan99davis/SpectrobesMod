package com.spectrobes.spectrobesmod.common.packets.networking;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class SpectrobesNetwork {

    private static final String PROTOCOL_VERSION = "1";
    private static int packetId = 0;

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(SpectrobesInfo.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int nextID() {
        return packetId++;
    }

    public static void init() {
        INSTANCE.messageBuilder(SSyncSpectrobeMasterPacket.class, nextID())
                .encoder(SSyncSpectrobeMasterPacket::toBytes)
                .decoder(SSyncSpectrobeMasterPacket::fromBytes)
                .consumerMainThread(SSyncSpectrobeMasterPacket::handle)
                .add();

        INSTANCE.messageBuilder(SChangeDimensionPacket.class, nextID())
                .encoder(SChangeDimensionPacket::toBytes)
                .decoder(SChangeDimensionPacket::fromBytes)
                .consumerMainThread(SChangeDimensionPacket::handle)
                .add();

        INSTANCE.messageBuilder(CSyncSpectrobeMasterPacket.class, nextID())
                .encoder(CSyncSpectrobeMasterPacket::toBytes)
                .decoder(CSyncSpectrobeMasterPacket::fromBytes)
                .consumerMainThread(CSyncSpectrobeMasterPacket::handle)
                .add();

        INSTANCE.messageBuilder(SUpdateSpectrobeSlotPacket.class, nextID())
                .encoder(SUpdateSpectrobeSlotPacket::toBytes)
                .decoder(SUpdateSpectrobeSlotPacket::fromBytes)
                .consumerMainThread(SUpdateSpectrobeSlotPacket::handle)
                .add();

        INSTANCE.messageBuilder(CUpdateSpectrobeSlotPacket.class, nextID())
                .encoder(CUpdateSpectrobeSlotPacket::toBytes)
                .decoder(CUpdateSpectrobeSlotPacket::fromBytes)
                .consumerMainThread(CUpdateSpectrobeSlotPacket::handle)
                .add();

        INSTANCE.messageBuilder(SSpawnSpectrobePacket.class, nextID())
                .encoder(SSpawnSpectrobePacket::toBytes)
                .decoder(SSpawnSpectrobePacket::fromBytes)
                .consumerMainThread(SSpawnSpectrobePacket::handle)
                .add();

        INSTANCE.messageBuilder(SReleaseSpectrobePacket.class, nextID())
                .encoder(SReleaseSpectrobePacket::toBytes)
                .decoder(SReleaseSpectrobePacket::fromBytes)
                .consumerMainThread(SReleaseSpectrobePacket::handle)
                .add();

        INSTANCE.messageBuilder(SDespawnSpectrobePacket.class, nextID())
                .encoder(SDespawnSpectrobePacket::toBytes)
                .decoder(SDespawnSpectrobePacket::fromBytes)
                .consumerMainThread(SDespawnSpectrobePacket::handle)
                .add();

        INSTANCE.messageBuilder(CSpectrobeAttackPacket.class, nextID())
                .encoder(CSpectrobeAttackPacket::toBytes)
                .decoder(CSpectrobeAttackPacket::fromBytes)
                .consumerMainThread(CSpectrobeAttackPacket::handle)
                .add();
    }

    public static void sendToClient(Object packet, ServerPlayer player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}
