package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Supplier;

public class SSpawnSpectrobePacket {

    @Nullable
    private Spectrobe spectrobe;

    public SSpawnSpectrobePacket(Spectrobe spectrobe) {
        this.spectrobe = spectrobe;
    }

    public void toBytes(FriendlyByteBuf buf) {
        if(spectrobe != null) {
            buf.writeNbt(spectrobe.write());
        }
    }

    public static SSpawnSpectrobePacket fromBytes(FriendlyByteBuf buf) {
        Spectrobe spectrobe = Spectrobe.read(buf.readNbt());

        return new SSpawnSpectrobePacket(spectrobe);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = ctx.get().getSender();

            PlayerSpectrobeMaster serverCap = player
                    .getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .orElseThrow(IllegalStateException::new);

            EntitySpectrobe spectrobe1 = null;
            try {
                spectrobe1 = SpectrobesEntities.getByName(spectrobe.name).spawn(
                        (ServerLevel) player.level,
                        spectrobe.write(),
                        new StringTextComponent(spectrobe.name),
                        player,
                        player.blockPosition(),
                        SpawnReason.MOB_SUMMONED,
                        true,true);
                spectrobe1.setSpectrobeData(spectrobe);
                spectrobe1.setOwnerUUID(player.getUUID());
                serverCap.spawnSpectrobe(spectrobe);
            } catch (ClassNotFoundException e) {
                SpectrobesInfo.LOGGER.info("Couldnt find spectrobe's registry.\n" + e.getMessage());
            }


        });
        return true;
    }
}
