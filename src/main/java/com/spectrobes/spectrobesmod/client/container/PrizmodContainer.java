package com.spectrobes.spectrobesmod.client.container;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.items.tools.PrizmodItem;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.*;
import com.spectrobes.spectrobesmod.common.registry.Containers;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

import java.util.*;


public class PrizmodContainer extends Container {

    private PlayerEntity player;
    private PlayerSpectrobeMaster capability;
    private boolean needsSync = true;

    public static RegistryObject<ContainerType<PrizmodContainer>> PRIZMOD = null;

    public PrizmodContainer(int id, PlayerEntity player) {
        super(PRIZMOD.get(), id);
        this.player = player;
        capability = this.player.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public void detectAndSendChanges() {
        if(needsSync) {
            if(!player.world.isRemote()) {
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
            detectAndSendChanges();
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
        Collection<UUID> uuidSet = capability.getCurrentTeamUuids().values();
        for (UUID id : uuidSet) {
            SpectrobesInfo.LOGGER.info(id);

        }
        return capability.getCurrentTeamUuids();
    }

    public List<Spectrobe> getOwnedSpectrobes() {
        return capability.getOwnedSpectrobes();
    }

    public void spawnSpectrobe(Spectrobe spectrobe) {
        synchronized (capability) {
            capability.spawnSpectrobe(spectrobe);
            if(player.world.isRemote()) {

            }
            markDirty();
        }
    }

    public void setTeamMember(int index, UUID spectrobeUUID) {
        capability.setTeamMember(index, spectrobeUUID);
        if(player.world.isRemote()) {
            SpectrobesNetwork.sendToServer(new SUpdateSpectrobeSlotPacket(index, spectrobeUUID));
            markDirty();
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
        if(player.world.isRemote()) {
            SpectrobesNetwork.sendToServer(new SReleaseSpectrobePacket(spectrobe));
        }
            markDirty();
        }
    }
}
