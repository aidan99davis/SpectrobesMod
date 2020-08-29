package com.spectrobes.spectrobesmod.client.container;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.items.tools.PrizmodItem;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SUpdateSpectrobeSlotPacket;
import com.spectrobes.spectrobesmod.common.registry.Containers;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;

import java.util.*;


public class PrizmodContainer extends Container {

    private PlayerEntity player;
    private PlayerSpectrobeMaster capability;
    private boolean needsSync = true;

    public PrizmodContainer(int id, PlayerEntity player) {
        super(Containers.PRIZMOD.get(), id);
        this.player = player;
        capability = this.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public void detectAndSendChanges() {
        if(needsSync) {
            if(!player.world.isRemote()) {
                SpectrobesInfo.LOGGER.info("SYNCING TO CLIENT");
                SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(capability),
                        (ServerPlayerEntity) player);
            } else {
                SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(capability));
            }
            needsSync = false;
        }
//        super.detectAndSendChanges();
    }

    public void tick() {
        if(needsSync) {
//            SpectrobesInfo.LOGGER.info("DETECTED AND SENDING CHANGES");
//            detectAndSendChanges();
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
    public boolean canInteractWith(PlayerEntity playerIn) {
        return playerIn.getHeldItem(playerIn.getActiveHand()).getItem() instanceof PrizmodItem;
    }

    public void markDirty() {
        needsSync = true;
    }

    public Map<Integer, UUID> getCurrentTeamUUIDs() {
        SpectrobesInfo.LOGGER.info("getCurrentTeamUUIDs:");
        Collection<UUID> uuidSet = capability.getCurrentTeamUuids().values();
        for (UUID id : uuidSet) {
            SpectrobesInfo.LOGGER.info(id);

        }
        return capability.getCurrentTeamUuids();
    }

    public List<Spectrobe> getOwnedSpectrobes() {
        SpectrobesInfo.LOGGER.info("getOwnedSpectrobes");
        for(Spectrobe s : capability.getOwnedSpectrobes()) {
            SpectrobesInfo.LOGGER.info("Spectrobe UUID: " + s.SpectrobeUUID);
        }
        return capability.getOwnedSpectrobes();
    }

    public void spawnSpectrobe(Spectrobe spectrobe) {
        SpectrobesInfo.LOGGER.info("spawnSpectrobe");
        capability.spawnSpectrobe(spectrobe);
        if(player.world.isRemote()) {

        }
        markDirty();
    }

    public void setTeamMember(int index, UUID spectrobeUUID) {
        SpectrobesInfo.LOGGER.info("setTeamMember UUID: " + spectrobeUUID.toString());
        if(!player.world.isRemote()) {
            SpectrobesInfo.LOGGER.info("SENDING PACKET");
            SpectrobesNetwork.sendToServer(new SUpdateSpectrobeSlotPacket(index, spectrobeUUID));
        } else {
            capability.setTeamMember(index, spectrobeUUID);
        }
        markDirty();

    }

    public int getOwnedSpectrobesCount() {
        SpectrobesInfo.LOGGER.info("getOwnedSpectrobesCount: " + capability.getOwnedSpectrobesCount());
        return capability.getOwnedSpectrobesCount();
    }
}
