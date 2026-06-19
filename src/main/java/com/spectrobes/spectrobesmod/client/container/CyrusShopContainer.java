package com.spectrobes.spectrobesmod.client.container;

import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.items.minerals.IWorthGura;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SConsumeMineralPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SGiveMineralPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSpawnDroppedMineralPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class CyrusShopContainer extends AbstractContainerMenu {
    private final Player player;
    private final PlayerSpectrobeMaster capability;
    private boolean needsSync = true;

    public static Supplier<MenuType<CyrusShopContainer>> CYRUS_SHOP = null;

    public CyrusShopContainer(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory.player);
    }

    public CyrusShopContainer(int containerId, Player player) {
        super(CYRUS_SHOP.get(), containerId);

        this.player = player;
        this.capability = getSpectrobeMaster(player);
    }

    private static PlayerSpectrobeMaster getSpectrobeMaster(Player player) {
        PlayerSpectrobeMaster capability = player.getCapability(SpectrobeMaster.INSTANCE);

        if (capability == null) {
            throw new IllegalStateException("Player is missing SpectrobeMaster capability.");
        }

        return capability;
    }

    public boolean buyMineral(IWorthGura mineral) {
        if (!capability.spendGura(mineral.getGuraWorth())) {
            return false;
        }

        if (player.level().isClientSide()) {
            if (player.getInventory().getFreeSlot() < 0) {
                SpectrobesNetwork.sendToServer(new SSpawnDroppedMineralPacket(mineral.getName()));
            } else {
                SpectrobesNetwork.sendToServer(new SGiveMineralPacket(mineral.getName()));
            }

            SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(capability));
        }

        needsSync = true;
        return true;
    }

    public boolean sellMineral(Item mineral) {
        if (!(mineral instanceof IWorthGura worthGura)) {
            return false;
        }

        if (!player.getInventory().contains(mineral.getDefaultInstance())) {
            return false;
        }

        if (player.level().isClientSide()) {
            SpectrobesNetwork.sendToServer(new SConsumeMineralPacket(worthGura.getName()));

            capability.addGura(worthGura.getGuraWorth() / 3);

            SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(capability));
        }

        needsSync = true;
        return true;
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();

        if (!needsSync) {
            return;
        }

        if (player.level().isClientSide()) {
            SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(capability));
        } else if (player instanceof ServerPlayer serverPlayer) {
            SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(capability), serverPlayer);
        }

        needsSync = false;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public void tick() {
        if (needsSync) {
            broadcastChanges();
        }
    }

    public int getGuraBalance() {
        return capability.getCurrentGuraBalance();
    }
}