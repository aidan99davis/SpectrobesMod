package com.spectrobes.spectrobesmod.client.container;

import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.items.minerals.IWorthGura;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;


public class CyrusShopContainer extends AbstractContainerMenu {
    private final Player player;
    private final PlayerSpectrobeMaster capability;
    private boolean needsSync = true;

    public static RegistryObject<MenuType<CyrusShopContainer>> CYRUS_SHOP = null;

    public CyrusShopContainer(int id, Player player) {
        super(CYRUS_SHOP.get(), id);
        this.player = player;
        capability = this.player.getCapability(SpectrobeMaster.INSTANCE)
                .orElseThrow(IllegalStateException::new);
    }

    public boolean buyMineral(IWorthGura mineral) {
        if(capability.spendGura(mineral.getGuraWorth())) {
            if(player.getInventory().getFreeSlot() < 0) {
                //drop item
                if(player.level.isClientSide()) {
                    SpectrobesNetwork.sendToServer(new SSpawnDroppedMineralPacket(mineral.getName()));
                    SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(capability));
                }
            } else {
              //give item
                if(player.level.isClientSide()) {
                    SpectrobesNetwork.sendToServer(new SGiveMineralPacket(mineral.getName()));
                    SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(capability));
                }
            }
            needsSync = true;
            return true;
        }
        return false;
    }

    public boolean sellMineral(Item mineral) {
        if(player.getInventory().contains(mineral.getDefaultInstance())) {
            if(player.level.isClientSide()) {
                SpectrobesNetwork.sendToServer(new SConsumeMineralPacket(((IWorthGura)mineral).getName()));
                capability.addGura(((IWorthGura)mineral).getGuraWorth() / 3);
                SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(capability));
            }
            needsSync = true;
            return true;
        }
        return false;
    }

    @Override
    public void broadcastChanges() {
        if(player.level.isClientSide()) {
            SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(capability));

        } else {
            SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(capability),
                        (ServerPlayer) player);
        }
        needsSync = false;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    public void tick() {
        if(needsSync) {
            broadcastChanges();
        }
    }

    public int getGuraBalance() {
        return capability.getCurrentGuraBalance();
    }
}
