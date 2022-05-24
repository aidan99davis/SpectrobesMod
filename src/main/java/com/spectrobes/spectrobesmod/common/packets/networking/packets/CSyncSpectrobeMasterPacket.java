package com.spectrobes.spectrobesmod.common.packets.networking.packets;


import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CSyncSpectrobeMasterPacket {

    private UUID playerUuid;
    private PlayerSpectrobeMaster capability;

    public CSyncSpectrobeMasterPacket(PlayerSpectrobeMaster capability) {
        this.capability = capability;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeNbt(capability.serializeNBT());
    }

    public static CSyncSpectrobeMasterPacket fromBytes(PacketBuffer buf) {
        PlayerSpectrobeMaster cap = new PlayerSpectrobeMaster();
        cap.deserializeNBT(buf.readNbt());

        return new CSyncSpectrobeMasterPacket(cap);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();

            player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                    .ifPresent(playerSpectrobeMaster ->
                            playerSpectrobeMaster.deserializeNBT(capability.serializeNBT()));

        });
        ctx.get().setPacketHandled(true);
        return true;
    }
}
