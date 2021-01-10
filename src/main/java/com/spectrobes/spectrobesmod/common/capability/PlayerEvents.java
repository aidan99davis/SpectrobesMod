package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SDespawnSpectrobePacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.UUID;

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
            if(!event.getPlayer().world.isRemote()) {
                event.getOriginal().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(oldStore -> {
                    event.getPlayer().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                        despawnSpectrobes(event, newStore);
                    });
                });
            }
        }
    }

    private void despawnSpectrobes(PlayerEvent.Clone event, PlayerSpectrobeMaster newStore) {
        World world = event.getOriginal().world;
        BlockPos playerPos = event.getOriginal().getPosition().toImmutable();

        MutableBoundingBox boundingBox = MutableBoundingBox.createProper(playerPos.getX(), playerPos.getY(), playerPos.getZ(), playerPos.getX(), playerPos.getY(), playerPos.getZ());
        AxisAlignedBB axisAlignedBB = AxisAlignedBB.toImmutable(boundingBox);

        List<EntitySpectrobe> spectrobes = world
                .getEntitiesWithinAABB(EntitySpectrobe.class, axisAlignedBB.grow(30, 30, 30));
        for(EntitySpectrobe spectrobe : spectrobes) {
            boolean hasOwner = spectrobe.getOwnerId() != null;
            UUID ownerUUID = spectrobe.getOwnerId();
            UUID playerUUID = event.getOriginal().getUniqueID();

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
        event.getPlayer().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(newStore -> {
            SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(newStore), (ServerPlayerEntity) event.getPlayer());
        });
    }
}
