package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SSpawnDroppedMineralPacket implements CustomPacketPayload {

    public static final Type<SSpawnDroppedMineralPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "spawn_dropped_mineral")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SSpawnDroppedMineralPacket> STREAM_CODEC =
            StreamCodec.ofMember(SSpawnDroppedMineralPacket::write, SSpawnDroppedMineralPacket::new);

    private final String mineral;

    public SSpawnDroppedMineralPacket(String mineral) {
        this.mineral = mineral == null ? "" : mineral;
    }

    private SSpawnDroppedMineralPacket(RegistryFriendlyByteBuf buffer) {
        this.mineral = buffer.readUtf();
    }

    private void write(RegistryFriendlyByteBuf buffer) {
        buffer.writeUtf(this.mineral);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public String getMineral() {
        return mineral;
    }

    public static void handle(SSpawnDroppedMineralPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }

            if (packet.mineral.isBlank()) {
                return;
            }

            ItemStack itemStack = SpectrobesMineralsRegistry.getMineralByRegistryName(packet.mineral);

            if (itemStack == null || itemStack.isEmpty()) {
                return;
            }

            ItemEntity itemEntity = new ItemEntity(
                    player.level(),
                    player.getX(),
                    player.getY() + 1.0D,
                    player.getZ(),
                    itemStack.copy()
            );

            itemEntity.setDefaultPickUpDelay();
            player.level().addFreshEntity(itemEntity);
        });
    }
}