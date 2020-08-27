package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class SUpdateSpectrobeSlotPacket {

    private int slot;
    @Nullable
    private Spectrobe spectrobe;

    public SUpdateSpectrobeSlotPacket(int slot, Spectrobe spectrobe) {
        this.slot = slot;
        this.spectrobe = spectrobe;
    }

    public void toBytes(PacketBuffer buf) {
        if(spectrobe != null) {
            buf.writeCompoundTag(spectrobe.write());
        }
        buf.writeInt(slot);
    }

    public static SUpdateSpectrobeSlotPacket fromBytes(PacketBuffer buf) {
        int slot = buf.readInt();
        Spectrobe spectrobe;
        try {
            spectrobe = Spectrobe.read(buf.readCompoundTag());
        } catch(NullPointerException ex) {
            spectrobe = null;
        }

        return new SUpdateSpectrobeSlotPacket(slot, spectrobe);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();

            PlayerSpectrobeMaster serverCap = player
                    .getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .orElseThrow(IllegalStateException::new);
            serverCap.setTeamMember(slot, spectrobe);

        });
        return true;
    }
}
