package com.spectrobes.spectrobesmod.common.networking.server;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobSpawnType;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.Nullable;

public class SReleaseSpectrobePacket implements CustomPacketPayload {

    public static final Type<SReleaseSpectrobePacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "release_spectrobe")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SReleaseSpectrobePacket> STREAM_CODEC =
            StreamCodec.ofMember(SReleaseSpectrobePacket::write, SReleaseSpectrobePacket::new);

    @Nullable
    private final Spectrobe spectrobe;

    public SReleaseSpectrobePacket(@Nullable Spectrobe spectrobe) {
        this.spectrobe = spectrobe;
    }

    private SReleaseSpectrobePacket(RegistryFriendlyByteBuf buffer) {
        CompoundTag tag = buffer.readNbt();
        this.spectrobe = tag == null ? null : Spectrobe.read(tag);
    }

    private void write(RegistryFriendlyByteBuf buffer) {
        buffer.writeNbt(this.spectrobe == null ? null : this.spectrobe.write());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    @Nullable
    public Spectrobe getSpectrobe() {
        return spectrobe;
    }

    public static void handle(SReleaseSpectrobePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }

            if (!(player.level() instanceof ServerLevel serverLevel)) {
                return;
            }

            Spectrobe spectrobe = packet.spectrobe;

            if (spectrobe == null) {
                return;
            }

            PlayerSpectrobeMaster serverCap = player.getCapability(SpectrobeMaster.INSTANCE);

            if (serverCap == null) {
                return;
            }

            try {
                EntitySpectrobe spawnedSpectrobe = SpectrobesEntities.getByName(spectrobe.name).spawn(
                        serverLevel,
                        player.blockPosition(),
                        MobSpawnType.MOB_SUMMONED
                );

                if (spawnedSpectrobe == null) {
                    return;
                }

                spawnedSpectrobe.setSpectrobeData(spectrobe);
                serverCap.releaseSpectrobe(spectrobe);
            } catch (ClassNotFoundException exception) {
                SpectrobesInfo.LOGGER.info(
                        "Couldn't find Spectrobe registry for '{}'. {}",
                        spectrobe.name,
                        exception.getMessage()
                );
            }
        });
    }
}