package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class SReleaseSpectrobePacket {

    @Nullable
    private Spectrobe spectrobe;

    public SReleaseSpectrobePacket(Spectrobe spectrobe) {
        this.spectrobe = spectrobe;
    }

    public void toBytes(PacketBuffer buf) {
        if(spectrobe != null) {
            buf.writeNbt(spectrobe.write());
        }
    }

    public static SReleaseSpectrobePacket fromBytes(PacketBuffer buf) {
        Spectrobe spectrobe = Spectrobe.read(buf.readNbt());

        return new SReleaseSpectrobePacket(spectrobe);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = ctx.get().getSender();

            PlayerSpectrobeMaster serverCap = player
                    .getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .orElseThrow(IllegalStateException::new);

            EntitySpectrobe spectrobe1 = null;
            try {
                spectrobe1 = SpectrobesEntities.getByName(spectrobe.name).spawn(
                        (ServerWorld) player.level,
                        spectrobe.write(),
                        new StringTextComponent(spectrobe.name),
                        player,
                        player.blockPosition(),
                        SpawnReason.MOB_SUMMONED,
                        true,true);
                spectrobe1.setSpectrobeData(spectrobe);
                serverCap.releaseSpectrobe(spectrobe);
            } catch (ClassNotFoundException e) {
                SpectrobesInfo.LOGGER.info("Couldnt find spectrobe's registry.\n" + e.getMessage());
            }


        });
        return true;
    }
}
