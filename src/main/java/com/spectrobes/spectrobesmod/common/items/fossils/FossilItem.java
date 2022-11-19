package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class FossilItem extends Item {

    public FossilItem(Properties properties, String registryName) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStack = playerIn.getItemInHand(handIn);
        itemStack.shrink(1);
        if(!worldIn.isClientSide) {
            Spectrobe spectrobe = getSpectrobeInstance();
            playerIn.getCapability(SpectrobeMaster.INSTANCE).ifPresent(playerCap -> {
                playerCap.addSpectrobe(spectrobe);
                SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(playerCap),
                        (ServerPlayer) playerIn);
            });
        } else {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("A new spectrobe has been sent to your prizmod."));
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack);
    }

    public abstract Spectrobe getSpectrobeInstance();
}
