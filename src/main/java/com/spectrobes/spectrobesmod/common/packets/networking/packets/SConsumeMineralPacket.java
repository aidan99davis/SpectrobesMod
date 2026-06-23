package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SConsumeMineralPacket implements CustomPacketPayload {

    public static final Type<SConsumeMineralPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "consume_mineral")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SConsumeMineralPacket> STREAM_CODEC =
            StreamCodec.ofMember(SConsumeMineralPacket::write, SConsumeMineralPacket::new);

    private final String mineral;

    public SConsumeMineralPacket(String mineral) {
        this.mineral = mineral == null ? "" : mineral;
    }

    private SConsumeMineralPacket(RegistryFriendlyByteBuf buffer) {
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

    public static void handle(SConsumeMineralPacket packet, IPayloadContext context) {
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

        int itemSlot = player.getInventory().findSlotMatchingItem(itemStack);

        if (itemSlot < 0) {
            return;
        }

        player.getInventory().removeItem(itemSlot, 1);
    }
}