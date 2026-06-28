package com.spectrobes.spectrobesmod.common.packets.networking.packets.server;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SGiveMineralPacket implements CustomPacketPayload {

    public static final Type<SGiveMineralPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "give_mineral")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SGiveMineralPacket> STREAM_CODEC =
            StreamCodec.ofMember(SGiveMineralPacket::write, SGiveMineralPacket::new);

    private final String mineral;

    public SGiveMineralPacket(String mineral) {
        this.mineral = mineral == null ? "" : mineral;
    }

    private SGiveMineralPacket(RegistryFriendlyByteBuf buffer) {
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

    public static void handle(SGiveMineralPacket packet, IPayloadContext context) {
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

            player.getInventory().add(itemStack.copy());
        });
    }
}