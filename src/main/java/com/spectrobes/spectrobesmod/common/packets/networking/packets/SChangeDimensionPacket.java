package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.world.dimensions.SpectrobesDimensions;
import com.spectrobes.spectrobesmod.common.world.teleporters.GenshiTeleporter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SChangeDimensionPacket {

    public SChangeDimensionPacket() {
    }

    public void toBytes(PacketBuffer buf) {
    }

    public static SChangeDimensionPacket fromBytes(PacketBuffer buf) {
        return new SChangeDimensionPacket();
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = ctx.get().getSender();
            World worldIn = player.level;

            MinecraftServer server = worldIn.getServer();
            if (server != null) {
                if (worldIn.dimension() == SpectrobesDimensions.GENSHI_DIMENSION) {
                    ServerWorld overWorld = server.getLevel(World.OVERWORLD);
                    if (overWorld != null) {
                        player.changeDimension(overWorld, new GenshiTeleporter(player.blockPosition(), false));
                    }
                } else {
                    ServerWorld daylightWorld = server.getLevel(SpectrobesDimensions.GENSHI_DIMENSION);
                    if (daylightWorld != null) {
                        player.changeDimension(daylightWorld, new GenshiTeleporter(player.blockPosition(), true));
                    }
                }
            }
            player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .ifPresent(sm -> SpectrobesNetwork.sendToClient(
                            new SSyncSpectrobeMasterPacket(sm),
                                (ServerPlayerEntity) player));

        });
        return true;
    }
}
