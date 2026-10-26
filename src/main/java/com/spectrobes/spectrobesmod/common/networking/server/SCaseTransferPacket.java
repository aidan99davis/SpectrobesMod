package com.spectrobes.spectrobesmod.common.networking.server;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.networking.client.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesToolsRegistry;
import com.spectrobes.spectrobesmod.common.storage.PrizmodCaseItems;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * Moves items between the player's inventory and their Prizmod case.
 */
public class SCaseTransferPacket implements CustomPacketPayload {

    public static final Type<SCaseTransferPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "case_transfer")
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SCaseTransferPacket> STREAM_CODEC =
            StreamCodec.ofMember(SCaseTransferPacket::write, SCaseTransferPacket::new);

    public enum Action {
        // Deposit every stack of the given item from the inventory.
        DEPOSIT,
        // Deposit every case-storable item in the inventory. The item key is ignored.
        DEPOSIT_EVERYTHING,
        // Withdraw up to the given amount of the item into the inventory.
        WITHDRAW
    }

    private final Action action;
    private final String item;
    private final int amount;

    public SCaseTransferPacket(Action action, String item, int amount) {
        this.action = action;
        this.item = item == null ? "" : item;
        this.amount = amount;
    }

    private SCaseTransferPacket(RegistryFriendlyByteBuf buffer) {
        this.action = buffer.readEnum(Action.class);
        this.item = buffer.readUtf();
        this.amount = buffer.readVarInt();
    }

    private void write(RegistryFriendlyByteBuf buffer) {
        buffer.writeEnum(this.action);
        buffer.writeUtf(this.item);
        buffer.writeVarInt(this.amount);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SCaseTransferPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) {
                return;
            }

            if (!player.getInventory().contains(SpectrobesToolsRegistry.prizmod_item.get().getDefaultInstance())) {
                return;
            }

            PlayerSpectrobeMaster capability = player.getCapability(SpectrobeMaster.INSTANCE);

            if (capability == null) {
                return;
            }

            switch (packet.action) {
                case DEPOSIT -> {
                    Item item = PrizmodCaseItems.fromKey(packet.item);
                    if (item != null) {
                        deposit(player.getInventory(), capability, item);
                    }
                }
                case DEPOSIT_EVERYTHING -> {
                    for (PrizmodCaseItems.Category category : PrizmodCaseItems.Category.values()) {
                        for (Item item : PrizmodCaseItems.getItems(category)) {
                            deposit(player.getInventory(), capability, item);
                        }
                    }
                }
                case WITHDRAW -> {
                    Item item = PrizmodCaseItems.fromKey(packet.item);
                    if (item != null && packet.amount > 0) {
                        withdraw(player, capability, item, packet.amount);
                    }
                }
            }

            player.containerMenu.broadcastChanges();
            SpectrobesNetwork.sendToClient(new CSyncSpectrobeMasterPacket(capability), player);
        });
    }

    private static void deposit(Inventory inventory, PlayerSpectrobeMaster capability, Item item) {
        long deposited = 0;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);

            if (!stack.isEmpty() && stack.is(item)) {
                deposited += stack.getCount();
                inventory.setItem(i, ItemStack.EMPTY);
            }
        }

        capability.addToCase(PrizmodCaseItems.getKey(item), deposited);
    }

    private static void withdraw(ServerPlayer player, PlayerSpectrobeMaster capability, Item item, int amount) {
        String key = PrizmodCaseItems.getKey(item);
        long remaining = capability.takeFromCase(key, amount);

        while (remaining > 0) {
            int stackSize = (int) Math.min(remaining, item.getDefaultMaxStackSize());
            ItemStack stack = new ItemStack(item, stackSize);

            player.getInventory().add(stack);
            remaining -= stackSize - stack.getCount();

            if (!stack.isEmpty()) {
                // Inventory is full, so whatever did not fit goes back into the case.
                break;
            }
        }

        capability.addToCase(key, remaining);
    }
}
