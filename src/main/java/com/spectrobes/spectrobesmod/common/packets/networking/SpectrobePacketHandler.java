package com.spectrobes.spectrobesmod.common.packets.networking;

import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodScreen;
import com.spectrobes.spectrobesmod.client.gui.spectrobes_details.SpectrobeDetailsScreen;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CUpdateSpectrobeSlotPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SOpenPrizmodPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SOpenSpectrobeDetailsScreenPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpectrobePacketHandler {

    public static boolean handlePacket(SOpenSpectrobeDetailsScreenPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
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
    public static boolean handlePacket(SOpenPrizmodPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
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

    public static boolean handlePacket(SSyncSpectrobeMasterPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            PlayerSpectrobeMaster clientCap = (PlayerSpectrobeMaster) player
                    .getCapability(SpectrobeMaster.INSTANCE)
                    .orElseThrow(IllegalStateException::new);
            clientCap.deserializeNBT(packet.capability.serializeNBT());

        });
        return true;
    }

    public static boolean handlePacket(CUpdateSpectrobeSlotPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = Minecraft.getInstance().player;
            PlayerSpectrobeMaster clientCap = (PlayerSpectrobeMaster) player
                    .getCapability(SpectrobeMaster.INSTANCE)
                    .orElseThrow(IllegalStateException::new);
            clientCap.setTeamMember(packet.slot, packet.spectrobeUUID);

        });
        return true;
    }
}
