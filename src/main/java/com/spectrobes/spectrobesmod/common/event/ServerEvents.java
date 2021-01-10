package com.spectrobes.spectrobesmod.common.event;


import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {

    @SubscribeEvent
    public static void OnPlayerJoin(PlayerEvent.PlayerLoggedInEvent evt) {
        if(!evt.getPlayer().world.isRemote()) {
            evt.getPlayer().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm),
                        (ServerPlayerEntity) evt.getPlayer());
            });
        }
    }

}
