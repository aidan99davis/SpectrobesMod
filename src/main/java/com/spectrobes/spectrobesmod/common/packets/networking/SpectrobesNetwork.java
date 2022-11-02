package com.spectrobes.spectrobesmod.common.packets.networking;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

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
                .consumer(SSyncSpectrobeMasterPacket::handle)
                .add();

        INSTANCE.messageBuilder(SChangeDimensionPacket.class, nextID())
                .encoder(SChangeDimensionPacket::toBytes)
                .decoder(SChangeDimensionPacket::fromBytes)
                .consumer(SChangeDimensionPacket::handle)
                .add();

        INSTANCE.messageBuilder(CSyncSpectrobeMasterPacket.class, nextID())
                .encoder(CSyncSpectrobeMasterPacket::toBytes)
                .decoder(CSyncSpectrobeMasterPacket::fromBytes)
                .consumer(CSyncSpectrobeMasterPacket::handle)
                .add();

        INSTANCE.messageBuilder(SUpdateSpectrobeSlotPacket.class, nextID())
                .encoder(SUpdateSpectrobeSlotPacket::toBytes)
                .decoder(SUpdateSpectrobeSlotPacket::fromBytes)
                .consumer(SUpdateSpectrobeSlotPacket::handle)
                .add();

        INSTANCE.messageBuilder(CUpdateSpectrobeSlotPacket.class, nextID())
                .encoder(CUpdateSpectrobeSlotPacket::toBytes)
                .decoder(CUpdateSpectrobeSlotPacket::fromBytes)
                .consumer(CUpdateSpectrobeSlotPacket::handle)
                .add();

        INSTANCE.messageBuilder(SSpawnSpectrobePacket.class, nextID())
                .encoder(SSpawnSpectrobePacket::toBytes)
                .decoder(SSpawnSpectrobePacket::fromBytes)
                .consumer(SSpawnSpectrobePacket::handle)
                .add();

        INSTANCE.messageBuilder(SReleaseSpectrobePacket.class, nextID())
                .encoder(SReleaseSpectrobePacket::toBytes)
                .decoder(SReleaseSpectrobePacket::fromBytes)
                .consumer(SReleaseSpectrobePacket::handle)
                .add();

        INSTANCE.messageBuilder(SDespawnSpectrobePacket.class, nextID())
                .encoder(SDespawnSpectrobePacket::toBytes)
                .decoder(SDespawnSpectrobePacket::fromBytes)
                .consumer(SDespawnSpectrobePacket::handle)
                .add();

        INSTANCE.messageBuilder(CSpectrobeAttackPacket.class, nextID())
                .encoder(CSpectrobeAttackPacket::toBytes)
                .decoder(CSpectrobeAttackPacket::fromBytes)
                .consumer(CSpectrobeAttackPacket::handle)
                .add();

        INSTANCE.messageBuilder(COpenSpectrobeDetailsPacket.class, nextID())
                .encoder(COpenSpectrobeDetailsPacket::toBytes)
                .decoder(COpenSpectrobeDetailsPacket::fromBytes)
                .consumer(COpenSpectrobeDetailsPacket::handle)
                .add();
    }

    public static void sendToClient(Object packet, ServerPlayerEntity player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void sendToServer(Object packet) {
        INSTANCE.sendToServer(packet);
    }
}
