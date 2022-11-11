package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobePacketHandler;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenSpectrobeDetailsScreenPacket {

    public Spectrobe spectrobe;

    public SOpenSpectrobeDetailsScreenPacket(Spectrobe spectrobe) {
        this.spectrobe = spectrobe;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(spectrobe.write());
    }

    public static SOpenSpectrobeDetailsScreenPacket fromBytes(FriendlyByteBuf buf) {
        Spectrobe spec = Spectrobe.read(buf.readNbt());

        return new SOpenSpectrobeDetailsScreenPacket(spec);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        return SpectrobePacketHandler.handlePacket(this, ctx);

    }
}
