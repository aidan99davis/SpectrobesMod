package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
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
            event.getOriginal().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(oldStore -> {
                event.getPlayer().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
}
