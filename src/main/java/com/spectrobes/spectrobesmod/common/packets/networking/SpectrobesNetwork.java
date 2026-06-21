package com.spectrobes.spectrobesmod.common.packets.networking;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CSpectrobeAttackPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CUpdateSpectrobeSlotPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SConsumeMineralPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SDespawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SGiveMineralPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SOpenCyrusShopPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SOpenPrizmodPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SOpenSpectrobeDetailsScreenPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SReleaseSpectrobePacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSpawnDroppedMineralPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSpawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SUpdateSpectrobeSlotPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = SpectrobesInfo.MOD_ID)
public final class SpectrobesNetwork {

    private static final String PROTOCOL_VERSION = "1";

    private SpectrobesNetwork() {
    }

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(PROTOCOL_VERSION);

        registrar.playToClient(
                SSyncSpectrobeMasterPacket.TYPE,
                SSyncSpectrobeMasterPacket.STREAM_CODEC,
                SSyncSpectrobeMasterPacket::handle
        );

        registrar.playToServer(
                CSyncSpectrobeMasterPacket.TYPE,
                CSyncSpectrobeMasterPacket.STREAM_CODEC,
                CSyncSpectrobeMasterPacket::handle
        );

        registrar.playToClient(
                SUpdateSpectrobeSlotPacket.TYPE,
                SUpdateSpectrobeSlotPacket.STREAM_CODEC,
                SUpdateSpectrobeSlotPacket::handle
        );

        registrar.playToServer(
                CUpdateSpectrobeSlotPacket.TYPE,
                CUpdateSpectrobeSlotPacket.STREAM_CODEC,
                CUpdateSpectrobeSlotPacket::handle
        );

        registrar.playToClient(
                SSpawnSpectrobePacket.TYPE,
                SSpawnSpectrobePacket.STREAM_CODEC,
                SSpawnSpectrobePacket::handle
        );

        registrar.playToClient(
                SReleaseSpectrobePacket.TYPE,
                SReleaseSpectrobePacket.STREAM_CODEC,
                SReleaseSpectrobePacket::handle
        );

        registrar.playToClient(
                SDespawnSpectrobePacket.TYPE,
                SDespawnSpectrobePacket.STREAM_CODEC,
                SDespawnSpectrobePacket::handle
        );

        registrar.playToServer(
                CSpectrobeAttackPacket.TYPE,
                CSpectrobeAttackPacket.STREAM_CODEC,
                CSpectrobeAttackPacket::handle
        );

        registrar.playToClient(
                SOpenPrizmodPacket.TYPE,
                SOpenPrizmodPacket.STREAM_CODEC,
                SOpenPrizmodPacket::handle
        );

        registrar.playToClient(
                SOpenSpectrobeDetailsScreenPacket.TYPE,
                SOpenSpectrobeDetailsScreenPacket.STREAM_CODEC,
                SOpenSpectrobeDetailsScreenPacket::handle
        );

        registrar.playToClient(
                SGiveMineralPacket.TYPE,
                SGiveMineralPacket.STREAM_CODEC,
                SGiveMineralPacket::handle
        );

        registrar.playToClient(
                SSpawnDroppedMineralPacket.TYPE,
                SSpawnDroppedMineralPacket.STREAM_CODEC,
                SSpawnDroppedMineralPacket::handle
        );

        registrar.playToClient(
                SConsumeMineralPacket.TYPE,
                SConsumeMineralPacket.STREAM_CODEC,
                SConsumeMineralPacket::handle
        );

        registrar.playToClient(
                SOpenCyrusShopPacket.TYPE,
                SOpenCyrusShopPacket.STREAM_CODEC,
                SOpenCyrusShopPacket::handle
        );
    }

    public static void sendToClient(CustomPacketPayload packet, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, packet);
    }

    public static void sendToServer(CustomPacketPayload packet) {
        PacketDistributor.sendToServer(packet);
    }
}