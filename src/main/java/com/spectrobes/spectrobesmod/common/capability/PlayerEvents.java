package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.UUID;

public class PlayerEvents {
    public static PlayerEvents instance = new PlayerEvents();

//    @SubscribeEvent
//    public void onEntityConstructing(AttachCapabilitiesEvent<Entity> event){
//        if (event.getObject() instanceof Player) {
//            if (!event.getObject().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).isPresent()) {
//                event.addCapability(new ResourceLocation(SpectrobesInfo.MOD_ID, "spectrobesmasters"),
//                        new PlayerSpectrobeMasterDispatcher());
//            }
//        }
//    }

//    @SubscribeEvent
//    public static void OnPlayerClone(PlayerEvent.Clone event) {
//        SpectrobesInfo.LOGGER.info("Clone event");
//        if (event.getEntity() instanceof ServerPlayer serverPlayerNew && event.getOriginal() instanceof ServerPlayer serverPlayerOld) {
//            SpectrobesInfo.LOGGER.info("Players are server based");
//            serverPlayerOld.reviveCaps();
//            SpectrobesInfo.LOGGER.info("capability revived");
//            serverPlayerOld.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(capOld -> {
//                SpectrobesInfo.LOGGER.info("old capability exists");
//                serverPlayerNew.getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(capNew -> {
//                    SpectrobesInfo.LOGGER.info("new capability exists");
//                    capNew.copyFrom(capOld);
//                    SpectrobesInfo.LOGGER.info("new capability copied from old one");
//                });
//            });
//            serverPlayerOld.invalidateCaps();
//            SpectrobesInfo.LOGGER.info("invalidated capability");
//        }
//    }

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        event.getOriginal().reviveCaps();
        if(!event.getOriginal().level.isClientSide()) {
            event.getOriginal().getCapability(SpectrobeMaster.INSTANCE).ifPresent(oldStore -> {
                event.getEntity().getCapability(SpectrobeMaster.INSTANCE).ifPresent(newStore -> {
                    newStore.copyFrom((PlayerSpectrobeMaster) oldStore);
                    newStore.setCurrentHealth(newStore.getMaxHealth());
                    despawnSpectrobes(event, (PlayerSpectrobeMaster) newStore);
                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(newStore), (ServerPlayer) event.getEntity());
                });
            });
        }
        event.getOriginal().invalidateCaps();
    }

    private void despawnSpectrobes(PlayerEvent.Clone event, PlayerSpectrobeMaster newStore) {
        Level world = event.getOriginal().level;
        AABB playerPos = event.getOriginal().getBoundingBox();

        List<EntitySpectrobe> spectrobes = world
                .getEntitiesOfClass(EntitySpectrobe.class, playerPos.inflate(30, 30, 30));
        for(EntitySpectrobe spectrobe : spectrobes) {
            boolean hasOwner = spectrobe.getOwnerUUID() != null;
            UUID ownerUUID = spectrobe.getOwnerUUID();
            UUID playerUUID = event.getOriginal().getUUID();

            boolean shouldDespawn = hasOwner
                    && ownerUUID
                    .equals(playerUUID);

            if(shouldDespawn) {
                spectrobe.despawn(newStore);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        event.getEntity().getCapability(SpectrobeMaster.INSTANCE).ifPresent(newStore -> {
            SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(newStore), (ServerPlayer) event.getEntity());
        });
    }
    @SubscribeEvent
    public void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        event.getEntity().getCapability(SpectrobeMaster.INSTANCE).ifPresent(newStore -> {
            SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(newStore), (ServerPlayer) event.getEntity());
        });
    }

//    @SubscribeEvent
//    public void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
//        event.register(PlayerSpectrobeMaster.class);
//    }
}
