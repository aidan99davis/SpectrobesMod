package com.spectrobes.spectrobesmod.common.packets.networking.packets;

import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SSpawnDroppedMineralPacket {

    private final String mineral;

    public SSpawnDroppedMineralPacket(String mineral) {
        this.mineral = mineral;
    }

    public void toBytes(FriendlyByteBuf buf) {
        if(mineral != null) {
            buf.writeUtf(mineral);
        }
    }

    public static SSpawnDroppedMineralPacket fromBytes(FriendlyByteBuf buf) {
        String mineral = buf.readUtf();

        return new SSpawnDroppedMineralPacket(mineral);
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Player player = ctx.get().getSender();

            ItemStack itemStack = SpectrobesMineralsRegistry.getMineralByRegistryName(mineral);
            assert player != null;
            ItemEntity itemEntity = new ItemEntity(player.level,
                    player.getX(),
                    (player.getY() + 1),
                    player.getZ(), itemStack);
            itemEntity.setDefaultPickUpDelay();
            player.level.addFreshEntity(itemEntity);
        });
        return true;
    }
}
