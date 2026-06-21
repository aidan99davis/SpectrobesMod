package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.IPlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobePacketHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SSyncSpectrobeMasterPacket implements CustomPacketPayload {

    public static final Type<SSyncSpectrobeMasterPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "s_sync_spectrobe_master")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SSyncSpectrobeMasterPacket> STREAM_CODEC =
            StreamCodec.ofMember(SSyncSpectrobeMasterPacket::write, SSyncSpectrobeMasterPacket::new);

    public final IPlayerSpectrobeMaster capability;

    public SSyncSpectrobeMasterPacket(IPlayerSpectrobeMaster capability) {
        this.capability = capability;
    }

    private SSyncSpectrobeMasterPacket(RegistryFriendlyByteBuf buffer) {
        PlayerSpectrobeMaster capability = new PlayerSpectrobeMaster();

        CompoundTag tag = buffer.readNbt();

        if (tag != null) {
            capability.deserializeNBT(buffer.registryAccess(), tag);
        }

        this.capability = capability;
    }

    private void write(RegistryFriendlyByteBuf buffer) {
        buffer.writeNbt(this.capability.serializeNBT(buffer.registryAccess()));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SSyncSpectrobeMasterPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> SpectrobePacketHandler.handlePacket(packet, context));
    }
}