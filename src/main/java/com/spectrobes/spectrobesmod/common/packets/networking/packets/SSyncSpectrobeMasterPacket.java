package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobePacketHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SSyncSpectrobeMasterPacket {

    public PlayerSpectrobeMaster capability;

    public SSyncSpectrobeMasterPacket(PlayerSpectrobeMaster capability) {
        this.capability = capability;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeCompoundTag(capability.serializeNBT());
    }

    public static SSyncSpectrobeMasterPacket fromBytes(PacketBuffer buf) {
        PlayerSpectrobeMaster cap = new PlayerSpectrobeMaster();
        cap.deserializeNBT(buf.readCompoundTag());

        return new SSyncSpectrobeMasterPacket(cap);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        return SpectrobePacketHandler.handlePacket(this, ctx);

    }
}
