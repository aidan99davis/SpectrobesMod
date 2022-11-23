package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SGiveMineralPacket {

    private final String mineral;

    public SGiveMineralPacket(String mineral) {
        this.mineral = mineral;
    }

    public void toBytes(FriendlyByteBuf buf) {
        if(mineral != null) {
            buf.writeUtf(mineral);
        }
    }

    public static SGiveMineralPacket fromBytes(FriendlyByteBuf buf) {
        String mineral = buf.readUtf();
        return new SGiveMineralPacket(mineral);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = ctx.get().getSender();
            ItemStack itemStack = SpectrobesMineralsRegistry.getMineralByRegistryName(mineral);
            assert player != null;
            player.getInventory().add(itemStack);
        });
        return true;
    }
}
