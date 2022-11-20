package com.spectrobes.spectrobesmod.client.container;

import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.*;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesToolsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.*;


public class PrizmodContainer extends AbstractContainerMenu {

    private Player player;
    private PlayerSpectrobeMaster capability;
    private boolean needsSync = true;

    public static RegistryObject<MenuType<PrizmodContainer>> PRIZMOD = null;

    public PrizmodContainer(int id, Player player) {
        super(PRIZMOD.get(), id);
        this.player = player;
        capability = (PlayerSpectrobeMaster) this.player.getCapability(SpectrobeMaster.INSTANCE)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public void broadcastChanges() {
        if(needsSync) {
            if(!player.level.isClientSide()) {
                SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(capability),
                        (ServerPlayer) player);
            } else {
                SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(capability));
            }
            needsSync = false;
        }
//        super.detectAndSendChanges();
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    public void tick() {
        if(needsSync) {
            broadcastChanges();
        }
//        capability = this.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
//                .orElseThrow(IllegalStateException::new);
    }

    /**
     * Determines whether supplied player can use this container
     *
     * @param playerIn
     */
    @Override
    public boolean stillValid(Player playerIn) {
        return playerIn.getInventory().contains(SpectrobesToolsRegistry.prizmod_item.get().getDefaultInstance());
    }

    public void markDirty() {
        needsSync = true;
    }

    public Map<Integer, UUID> getCurrentTeamUUIDs() {
        return capability.getCurrentTeamUuids();
    }

    public List<Spectrobe> getOwnedSpectrobes() {
        return capability.getOwnedSpectrobes();
    }

    public void spawnSpectrobe(Spectrobe spectrobe) {
        synchronized (capability) {
            capability.spawnSpectrobe(spectrobe);
            if(player.level.isClientSide()) {

            }
            markDirty();
        }
    }

    public void setTeamMember(int index, UUID spectrobeUUID) {
        capability.setTeamMember(index, spectrobeUUID);
        if(player.level.isClientSide()) {
            SpectrobesNetwork.sendToServer(new SUpdateSpectrobeSlotPacket(index, spectrobeUUID));
//            markDirty();
        }

    }

    public int getOwnedSpectrobesCount() {
        return capability.getOwnedSpectrobesCount();
    }

    public UUID getCurrentSelectedUUID() {
        return capability.getCurrentTeamMember() != null? capability.getCurrentTeamMember().SpectrobeUUID : null;
    }

    public void releaseSpectrobe(Spectrobe spectrobe) {
        synchronized (capability) {
            capability.releaseSpectrobe(spectrobe);
        if(player.level.isClientSide()) {
            SpectrobesNetwork.sendToServer(new SReleaseSpectrobePacket(spectrobe));
        }
            markDirty();
        }
    }
}
