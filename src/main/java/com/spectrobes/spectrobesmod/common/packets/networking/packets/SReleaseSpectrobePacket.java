package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class SReleaseSpectrobePacket {

    @Nullable
    private Spectrobe spectrobe;

    public SReleaseSpectrobePacket(Spectrobe spectrobe) {
        this.spectrobe = spectrobe;
    }

    public void toBytes(FriendlyByteBuf buf) {
        if(spectrobe != null) {
            buf.writeNbt(spectrobe.write());
        }
    }

    public static SReleaseSpectrobePacket fromBytes(FriendlyByteBuf buf) {
        Spectrobe spectrobe = Spectrobe.read(buf.readNbt());

        return new SReleaseSpectrobePacket(spectrobe);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            PlayerSpectrobeMaster serverCap = player
                    .getCapability(SpectrobeMaster.INSTANCE)
                    .orElseThrow(IllegalStateException::new);

            EntitySpectrobe spectrobe1 = null;
            try {
                spectrobe1 = SpectrobesEntities.getByName(spectrobe.name).spawn(
                        (ServerLevel) player.level,
                        spectrobe.write(),
                        Component.literal(spectrobe.name),
                        player,
                        player.blockPosition(),
                        MobSpawnType.MOB_SUMMONED,
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
