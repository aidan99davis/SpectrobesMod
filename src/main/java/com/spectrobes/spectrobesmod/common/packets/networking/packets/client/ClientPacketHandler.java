package com.spectrobes.spectrobesmod.common.packets.networking.packets.client;

import com.spectrobes.spectrobesmod.client.container.CyrusShopContainer;
import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import com.spectrobes.spectrobesmod.client.gui.cyrus_shop.CyrusShopScreen;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.client.gui.spectrobes_details.SpectrobeDetailsScreen;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPacketHandler {

    public static boolean handlePacket(COpenSpectrobeDetailsScreenPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            Minecraft.getInstance()
                    .setScreen(
                            new SpectrobeDetailsScreen(
                                    new SpectrobeDetailsContainer(
                                            0,
                                            packet.spectrobe),
                                    player.getInventory(),
                                    Component.literal("")));

        });
        return true;
    }
    public static boolean handlePacket(COpenPrizmodPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            Minecraft.getInstance()
                    .setScreen(
                            new PrizmodScreen(
                                    new PrizmodContainer(
                                            0,
                                            player),
                                    player.getInventory(),
                                    Component.literal("")));

        });
        return true;
    }
    public static boolean handlePacket(COpenCyrusShopPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            Minecraft.getInstance()
                    .setScreen(
                            new CyrusShopScreen(
                                    new CyrusShopContainer(
                                            0,
                                            player),
                                    player.getInventory(),
                                    Component.literal("")));

        });
        return true;
    }

    public static boolean handlePacket(CSyncSpectrobeMasterPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            PlayerSpectrobeMaster clientCap = player.getCapability(SpectrobeMaster.INSTANCE);
            clientCap.deserializeNBT(null, packet.capability.serializeNBT(null));

        });
        return true;
    }

    public static boolean handlePacket(CUpdateSpectrobeSlotPacket packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            PlayerSpectrobeMaster clientCap = player.getCapability(SpectrobeMaster.INSTANCE);
            clientCap.setTeamMember(packet.slot, packet.spectrobeUUID);

        });
        return true;
    }
}
