package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobePacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SOpenCyrusShopPacket {

    public SOpenCyrusShopPacket() {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public static SOpenCyrusShopPacket fromBytes(FriendlyByteBuf buf) {
        return new SOpenCyrusShopPacket();
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        return SpectrobePacketHandler.handlePacket(this, ctx);

    }
}
