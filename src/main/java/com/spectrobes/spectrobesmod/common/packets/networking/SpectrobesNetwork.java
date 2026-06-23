package com.spectrobes.spectrobesmod.common.packets.networking;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.server.SSellMineralPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.server.SSpectrobeAttackPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.client.CUpdateSpectrobeSlotPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SConsumeMineralPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SDespawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SGiveMineralPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.client.COpenCyrusShopPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SOpenPrizmodPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SOpenSpectrobeDetailsScreenPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SReleaseSpectrobePacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.server.SBuyMineralPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSpawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.client.CSyncSpectrobeMasterPacket;
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
                CSyncSpectrobeMasterPacket.TYPE,
                CSyncSpectrobeMasterPacket.STREAM_CODEC,
                CSyncSpectrobeMasterPacket::handle
        );

        registrar.playToServer(
                SUpdateSpectrobeSlotPacket.TYPE,
                SUpdateSpectrobeSlotPacket.STREAM_CODEC,
                SUpdateSpectrobeSlotPacket::handle
        );

        registrar.playToClient(
                CUpdateSpectrobeSlotPacket.TYPE,
                CUpdateSpectrobeSlotPacket.STREAM_CODEC,
                CUpdateSpectrobeSlotPacket::handle
        );

        registrar.playToServer(
                SSpawnSpectrobePacket.TYPE,
                SSpawnSpectrobePacket.STREAM_CODEC,
                SSpawnSpectrobePacket::handle
        );

        registrar.playToServer(
                SReleaseSpectrobePacket.TYPE,
                SReleaseSpectrobePacket.STREAM_CODEC,
                SReleaseSpectrobePacket::handle
        );

        registrar.playToServer(
                SDespawnSpectrobePacket.TYPE,
                SDespawnSpectrobePacket.STREAM_CODEC,
                SDespawnSpectrobePacket::handle
        );

        registrar.playToServer(
                SSpectrobeAttackPacket.TYPE,
                SSpectrobeAttackPacket.STREAM_CODEC,
                SSpectrobeAttackPacket::handle
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

        registrar.playToServer(
                SBuyMineralPacket.TYPE,
                SBuyMineralPacket.STREAM_CODEC,
                SBuyMineralPacket::handle
        );

        registrar.playToServer(
                SSellMineralPacket.TYPE,
                SSellMineralPacket.STREAM_CODEC,
                SSellMineralPacket::handle
        );

        registrar.playToClient(
                SConsumeMineralPacket.TYPE,
                SConsumeMineralPacket.STREAM_CODEC,
                SConsumeMineralPacket::handle
        );

        registrar.playToClient(
                COpenCyrusShopPacket.TYPE,
                COpenCyrusShopPacket.STREAM_CODEC,
                COpenCyrusShopPacket::handle
        );
    }

    public static void sendToClient(CustomPacketPayload packet, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, packet);
    }

    public static void sendToServer(CustomPacketPayload packet) {
        PacketDistributor.sendToServer(packet);
    }
}