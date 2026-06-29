package com.spectrobes.spectrobesmod.common.networking.server;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.networking.client.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SBuyMineralPacket implements CustomPacketPayload {

    public static final Type<SBuyMineralPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "s_buy_mineral")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SBuyMineralPacket> STREAM_CODEC =
            StreamCodec.ofMember(SBuyMineralPacket::write, SBuyMineralPacket::new);

    private final String mineral;
    private final int guraWorth;

    public SBuyMineralPacket(String mineral, int guraWorth) {
        this.mineral = mineral == null ? "" : mineral;
        this.guraWorth = guraWorth;
    }

    private SBuyMineralPacket(RegistryFriendlyByteBuf buffer) {
        this.mineral = buffer.readUtf();
        this.guraWorth = buffer.readInt();
    }

    private void write(RegistryFriendlyByteBuf buffer) {
        buffer.writeUtf(this.mineral);
        buffer.writeInt(this.guraWorth);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public String getMineral() {
        return mineral;
    }

    public static void handle(SBuyMineralPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }

            if (packet.mineral.isBlank()) {
                return;
            }

            PlayerSpectrobeMaster spectrobeMaster = context.player().getCapability(SpectrobeMaster.INSTANCE);

            if(spectrobeMaster == null) return;

            if (spectrobeMaster.spendGura(packet.guraWorth)) {
                ItemStack mineral = SpectrobesMineralsRegistry.getMineralByRegistryName(packet.mineral);
                int availableSlot = player.getInventory().getSlotWithRemainingSpace(mineral);
                if(availableSlot == -1) {
                    dropBoughtMineral(mineral, player);
                } else {
                    player.getInventory().add(mineral);
                }
                SpectrobesNetwork.sendToClient(new CSyncSpectrobeMasterPacket(spectrobeMaster), player);
            };
        });
    }

    private static void dropBoughtMineral(ItemStack itemStack, ServerPlayer player) {
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
    }
}