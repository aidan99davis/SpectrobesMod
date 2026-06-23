package com.spectrobes.spectrobesmod.common.packets.networking.packets.server;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.items.minerals.IWorthGura;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.client.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class SSellMineralPacket implements CustomPacketPayload {

    public static final Type<SSellMineralPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "s_sell_mineral")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SSellMineralPacket> STREAM_CODEC =
            StreamCodec.ofMember(SSellMineralPacket::write, SSellMineralPacket::new);

    private final String mineral;

    public SSellMineralPacket(String mineral) {
        this.mineral = mineral == null ? "" : mineral;
    }

    private SSellMineralPacket(RegistryFriendlyByteBuf buffer) {
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

    public static void handle(SSellMineralPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }

            if (packet.mineral.isBlank()) {
                return;
            }

            ItemStack mineral = SpectrobesMineralsRegistry.getMineralByRegistryName(packet.mineral);

            if (!player.getInventory().contains(mineral)) return;

            PlayerSpectrobeMaster spectrobeMaster = context.player().getCapability(SpectrobeMaster.INSTANCE);

            if(spectrobeMaster == null) return;
            int mineralSlot = player.getInventory().findSlotMatchingItem(mineral);
            if(mineralSlot == -1) {
                return;
            } else {
                player.getInventory().removeItem(mineralSlot, 1);
                spectrobeMaster.addGura(((IWorthGura) mineral.getItem()).getGuraWorth());
            }
            SpectrobesNetwork.sendToClient(new CSyncSpectrobeMasterPacket(spectrobeMaster), player);
        });
    }
}