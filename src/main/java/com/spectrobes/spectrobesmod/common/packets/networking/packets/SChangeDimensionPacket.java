package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.world.dimensions.SpectrobesDimensions;
import com.spectrobes.spectrobesmod.common.world.teleporters.GenshiTeleporter;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class SChangeDimensionPacket {

//    @Nullable
//    private Spectrobe spectrobe;

    public SChangeDimensionPacket() {
    }

    public void toBytes(PacketBuffer buf) {
//        if(spectrobe != null) {
//            buf.writeNbt(spectrobe.write());
//        }
    }

    public static SChangeDimensionPacket fromBytes(PacketBuffer buf) {
//        Spectrobe spectrobe = Spectrobe.read(buf.readNbt());

        return new SChangeDimensionPacket();
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = ctx.get().getSender();
            World worldIn = player.level;

            MinecraftServer server = worldIn.getServer();
            SpectrobesInfo.LOGGER.debug("doot 0");
            if (server != null) {
                SpectrobesInfo.LOGGER.debug("doot 0-1");
                if (worldIn.dimension() == SpectrobesDimensions.GENSHI_DIMENSION) {
                    SpectrobesInfo.LOGGER.debug("doot 0-2");
                    ServerWorld overWorld = server.getLevel(World.OVERWORLD);
                    if (overWorld != null) {
                        player.changeDimension(overWorld, new GenshiTeleporter(player.blockPosition(), false));
                    }
                } else {
                    ServerWorld daylightWorld = server.getLevel(SpectrobesDimensions.GENSHI_DIMENSION);
                    SpectrobesInfo.LOGGER.debug("doot 1");
                    if (daylightWorld != null) {
                        SpectrobesInfo.LOGGER.debug("doot 2");
                        player.changeDimension(daylightWorld, new GenshiTeleporter(player.blockPosition(), true));
                    }
                }
            }


        });
        return true;
    }
}
