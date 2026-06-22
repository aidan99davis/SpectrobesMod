package com.spectrobes.spectrobesmod.common.items.fossils;

import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
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
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
        }

        PlayerSpectrobeMaster playerCap = player.getCapability(SpectrobeMaster.INSTANCE);

        if (playerCap == null) {
            return InteractionResultHolder.fail(itemStack);
        }

        Spectrobe spectrobe = getSpectrobeInstance().copy(false);
        playerCap.addSpectrobe(spectrobe);

        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }

        SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(playerCap), serverPlayer);
        serverPlayer.sendSystemMessage(Component.literal("A new spectrobe has been sent to your prizmod."));

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
    }

    public abstract Spectrobe getSpectrobeInstance();
}