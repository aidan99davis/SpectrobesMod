package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.networking.client.CSyncSpectrobeMasterPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.List;
import java.util.UUID;

public class PlayerEvents {

    public static final PlayerEvents instance = new PlayerEvents();

    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        Player originalPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();

        if (newPlayer.level().isClientSide()) {
            return;
        }

        if (!(newPlayer instanceof ServerPlayer serverPlayer)) {
            return;
        }

        PlayerSpectrobeMaster oldStore = originalPlayer.getCapability(SpectrobeMaster.INSTANCE);
        PlayerSpectrobeMaster newStore = newPlayer.getCapability(SpectrobeMaster.INSTANCE);

        if (oldStore == null || newStore == null) {
            return;
        }

        newStore.copyFrom(oldStore);
        newStore.setCurrentHealth(newStore.getMaxHealth());

        despawnSpectrobes(originalPlayer, newStore);

        SpectrobesNetwork.sendToClient(
                new CSyncSpectrobeMasterPacket(newStore),
                serverPlayer
        );
    }

    private void despawnSpectrobes(Player originalPlayer, PlayerSpectrobeMaster newStore) {
        Level level = originalPlayer.level();
        AABB playerBounds = originalPlayer.getBoundingBox();

        List<EntitySpectrobe> spectrobes = level.getEntitiesOfClass(
                EntitySpectrobe.class,
                playerBounds.inflate(30.0D, 30.0D, 30.0D)
        );

        UUID playerUUID = originalPlayer.getUUID();

        for (EntitySpectrobe spectrobe : spectrobes) {
            UUID ownerUUID = spectrobe.getOwnerUUID();

            boolean shouldDespawn = ownerUUID != null && ownerUUID.equals(playerUUID);

            if (shouldDespawn) {
                spectrobe.despawn(newStore);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) {
            return;
        }

        PlayerSpectrobeMaster store = serverPlayer.getCapability(SpectrobeMaster.INSTANCE);

        if (store == null) {
            return;
        }

        SpectrobesNetwork.sendToClient(
                new CSyncSpectrobeMasterPacket(store),
                serverPlayer
        );
    }

    @SubscribeEvent
    public void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) {
            return;
        }

        PlayerSpectrobeMaster store = serverPlayer.getCapability(SpectrobeMaster.INSTANCE);

        if (store == null) {
            return;
        }

        SpectrobesNetwork.sendToClient(
                new CSyncSpectrobeMasterPacket(store),
                serverPlayer
        );
    }
}