package com.spectrobes.spectrobesmod.common.packets.networking.packets.server;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SSpectrobeAttackPacket implements CustomPacketPayload {

    public static final Type<SSpectrobeAttackPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "spectrobe_attack")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SSpectrobeAttackPacket> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.VAR_INT,
                    SSpectrobeAttackPacket::getSpectrobeID,
                    ByteBufCodecs.VAR_INT,
                    SSpectrobeAttackPacket::getKrawlID,
                    SSpectrobeAttackPacket::new
            );

    private final int spectrobeID;
    private final int krawlID;

    public SSpectrobeAttackPacket(int spectrobeID, int krawlID) {
        this.spectrobeID = spectrobeID;
        this.krawlID = krawlID;
    }

    public int getSpectrobeID() {
        return spectrobeID;
    }

    public int getKrawlID() {
        return krawlID;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SSpectrobeAttackPacket packet, IPayloadContext context) {
        if (!(context.player() instanceof ServerPlayer player)) {
            return;
        }

        Entity spectrobeEntity = player.level().getEntity(packet.spectrobeID);
        Entity krawlEntity = player.level().getEntity(packet.krawlID);

        if (!(spectrobeEntity instanceof EntitySpectrobe spectrobe)) {
            return;
        }

        if (!(krawlEntity instanceof LivingEntity target)) {
            return;
        }

        LivingEntity currentTarget = spectrobe.getTarget();

        if (currentTarget instanceof EntityKrawl krawl) {
            krawl.setGlowing(false);
        } else if (currentTarget instanceof EntitySpectrobe otherSpectrobe) {
            otherSpectrobe.setGlowing(false);
        }

        spectrobe.setTarget(null);
        spectrobe.setTarget(target);
        spectrobe.getNavigation().moveTo(target, 1.0D);
    }
}