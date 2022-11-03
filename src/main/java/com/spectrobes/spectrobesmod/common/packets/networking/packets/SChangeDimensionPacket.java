package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.world.dimensions.SpectrobesDimensions;
import com.spectrobes.spectrobesmod.common.world.teleporters.GenshiTeleporter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SChangeDimensionPacket {

    public SChangeDimensionPacket() {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public static SChangeDimensionPacket fromBytes(FriendlyByteBuf buf) {
        return new SChangeDimensionPacket();
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = ctx.get().getSender();
            Level worldIn = player.level;

            MinecraftServer server = worldIn.getServer();
            if (server != null) {
                if (worldIn.dimension() == SpectrobesDimensions.GENSHI_DIMENSION) {
                    ServerLevel overWorld = server.getLevel(Level.OVERWORLD);
                    if (overWorld != null) {
                        player.changeDimension(overWorld, new GenshiTeleporter(player.blockPosition(), false));
                    }
                } else {
                    ServerLevel daylightWorld = server.getLevel(SpectrobesDimensions.GENSHI_DIMENSION);
                    if (daylightWorld != null) {
                        player.changeDimension(daylightWorld, new GenshiTeleporter(player.blockPosition(), true));
                    }
                }
            }
            player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .ifPresent(sm -> SpectrobesNetwork.sendToClient(
                            new SSyncSpectrobeMasterPacket(sm),
                                (ServerPlayer) player));

        });
        return true;
    }
}
