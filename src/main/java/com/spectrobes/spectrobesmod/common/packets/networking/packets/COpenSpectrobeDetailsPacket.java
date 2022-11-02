package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobePacketHandler;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class COpenSpectrobeDetailsPacket {

    @Nullable
    public Spectrobe spectrobe;

    public COpenSpectrobeDetailsPacket(Spectrobe spectrobe) {
        this.spectrobe = spectrobe;
    }

    public void toBytes(PacketBuffer buf) {
        if(spectrobe != null) {
            buf.writeNbt(spectrobe.write());
        }
    }

    public static COpenSpectrobeDetailsPacket fromBytes(PacketBuffer buf) {
        Spectrobe spectrobe = Spectrobe.read(buf.readNbt());

        return new COpenSpectrobeDetailsPacket(spectrobe);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        return SpectrobePacketHandler.handlePacket(this, ctx);
    }
}
