package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class CSyncSpectrobeMasterPacket implements CustomPacketPayload {

    public static final Type<CSyncSpectrobeMasterPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "c_sync_spectrobe_master")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, CSyncSpectrobeMasterPacket> STREAM_CODEC =
            StreamCodec.ofMember(CSyncSpectrobeMasterPacket::write, CSyncSpectrobeMasterPacket::new);

    private final PlayerSpectrobeMaster capability;

    public CSyncSpectrobeMasterPacket(PlayerSpectrobeMaster capability) {
        this.capability = capability;
    }

    private CSyncSpectrobeMasterPacket(RegistryFriendlyByteBuf buf) {
        PlayerSpectrobeMaster capability = new PlayerSpectrobeMaster();

        CompoundTag tag = buf.readNbt();

        if (tag != null) {
            capability.deserializeNBT(buf.registryAccess(), tag);
        }

        this.capability = capability;
    }

    private void write(RegistryFriendlyByteBuf buf) {
        buf.writeNbt(this.capability.serializeNBT(buf.registryAccess()));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(CSyncSpectrobeMasterPacket packet, IPayloadContext context) {
        if (!(context.player() instanceof ServerPlayer player)) {
            return;
        }

        PlayerSpectrobeMaster playerSpectrobeMaster = player.getCapability(SpectrobeMaster.INSTANCE);

        if (playerSpectrobeMaster == null) {
            return;
        }

        playerSpectrobeMaster.copyFrom(packet.capability);
    }
}