package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobePacketHandler;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenPrizmodPacket {

    public SOpenPrizmodPacket() {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public static SOpenPrizmodPacket fromBytes(FriendlyByteBuf buf) {
        return new SOpenPrizmodPacket();
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        return SpectrobePacketHandler.handlePacket(this, ctx);

    }
}
