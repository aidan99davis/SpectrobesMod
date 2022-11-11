package com.spectrobes.spectrobesmod.common.items.tools;

import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SOpenSpectrobeDetailsScreenPacket;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class PrizmodItem extends Item {
    public PrizmodItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStack = new ItemStack(playerIn.getItemInHand(handIn).getItem(), 1);
        if(!playerIn.isShiftKeyDown()) {
            if(!worldIn.isClientSide()) {
                NetworkHooks.openScreen((ServerPlayer) playerIn, new SimpleMenuProvider(
                        (id, player, stack) -> new PrizmodContainer(id, playerIn),
                        Component.empty())
                );
            }
        }
        return InteractionResultHolder.success(itemStack);
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(Component.literal("Right click to open the prizmod."));
        pTooltip.add(Component.literal("Shift Right click on a spectrobe to view its stats."));
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}
