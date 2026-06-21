package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobePacketHandler;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SOpenSpectrobeDetailsScreenPacket implements CustomPacketPayload {

    public static final Type<SOpenSpectrobeDetailsScreenPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "open_spectrobe_details_screen")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SOpenSpectrobeDetailsScreenPacket> STREAM_CODEC =
            StreamCodec.ofMember(SOpenSpectrobeDetailsScreenPacket::write, SOpenSpectrobeDetailsScreenPacket::new);

    public final Spectrobe spectrobe;

    public SOpenSpectrobeDetailsScreenPacket(Spectrobe spectrobe) {
        this.spectrobe = spectrobe;
    }

    private SOpenSpectrobeDetailsScreenPacket(RegistryFriendlyByteBuf buffer) {
        CompoundTag tag = buffer.readNbt();

        if (tag == null) {
            throw new IllegalArgumentException("SOpenSpectrobeDetailsScreenPacket received null Spectrobe NBT");
        }

        this.spectrobe = Spectrobe.read(tag);
    }

    private void write(RegistryFriendlyByteBuf buffer) {
        buffer.writeNbt(this.spectrobe.write());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SOpenSpectrobeDetailsScreenPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> SpectrobePacketHandler.handlePacket(packet, context));
    }
}