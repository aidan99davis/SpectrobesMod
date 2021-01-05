package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SDespawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerEvents {
    public static PlayerEvents instance = new PlayerEvents();

    @SubscribeEvent
    public void onEntityConstructing(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof PlayerEntity) {
            if (!event.getObject().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).isPresent()) {
                event.addCapability(new ResourceLocation(SpectrobesInfo.MOD_ID, "spectrobesmasters"),
                        new PlayerSpectrobeMasterDispatcher());
            }
        }
    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            // We need to copyFrom the capabilities

            if(event.getPlayer().world.isRemote()) {
                event.getOriginal().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(oldStore -> {
                    SpectrobesNetwork.sendToServer(new SDespawnSpectrobePacket(event.getOriginal().getPosition()));
                    event.getPlayer().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });

            }
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if(!event.getPlayer().world.isRemote()) {
            event.getPlayer().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(newStore -> {
                SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(newStore), (ServerPlayerEntity) event.getPlayer());
            });
        }
    }
}
