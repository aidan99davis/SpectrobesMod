package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CSyncSpectrobeMasterPacket {

    private UUID playerUuid;
    private PlayerSpectrobeMaster capability;

    public CSyncSpectrobeMasterPacket(PlayerSpectrobeMaster capability) {
        this.capability = capability;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeNbt(capability.serializeNBT());
    }

    public static CSyncSpectrobeMasterPacket fromBytes(FriendlyByteBuf buf) {
        PlayerSpectrobeMaster cap = new PlayerSpectrobeMaster();
        cap.deserializeNBT(buf.readNbt());

        return new CSyncSpectrobeMasterPacket(cap);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            player.getCapability(SpectrobeMaster.INSTANCE)
                    .ifPresent(playerSpectrobeMaster ->
                            playerSpectrobeMaster.deserializeNBT(capability.serializeNBT()));

        });
        ctx.get().setPacketHandled(true);
        return true;
    }
}
