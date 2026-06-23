package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.SpectrobesEntities;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobSpawnType;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.Nullable;

public class SSpawnSpectrobePacket implements CustomPacketPayload {

    public static final Type<SSpawnSpectrobePacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "spawn_spectrobe")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SSpawnSpectrobePacket> STREAM_CODEC =
            StreamCodec.ofMember(SSpawnSpectrobePacket::write, SSpawnSpectrobePacket::new);

    @Nullable
    private final Spectrobe spectrobe;

    public SSpawnSpectrobePacket(@Nullable Spectrobe spectrobe) {
        this.spectrobe = spectrobe;
    }

    private SSpawnSpectrobePacket(RegistryFriendlyByteBuf buffer) {
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

    public static void handle(SSpawnSpectrobePacket packet, IPayloadContext context) {
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
//                EntitySpectrobe spawnedSpectrobe = SpectrobesEntities.getByName(spectrobe.name).spawn(
//                        serverLevel,
//                        spectrobe.write(),
//                        Component.literal(spectrobe.name),
//                        player,
//                        player.blockPosition(),
//                        MobSpawnType.MOB_SUMMONED,
//                        true,
//                        true
//                );

                EntitySpectrobe spawnedSpectrobe = SpectrobesEntities.getByName(spectrobe.name).spawn(
                        serverLevel,
                        player.blockPosition(),
                        MobSpawnType.MOB_SUMMONED
                );

                if (spawnedSpectrobe == null) {
                    return;
                }

                spawnedSpectrobe.setSpectrobeData(spectrobe);
                spawnedSpectrobe.setOwnerUUID(player.getUUID());
                serverCap.spawnSpectrobe(spectrobe);
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