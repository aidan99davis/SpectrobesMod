package com.spectrobes.spectrobesmod.common.items.tools;

import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
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

import java.util.List;

public class PrizmodItem extends Item {

    public PrizmodItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            return InteractionResultHolder.pass(itemStack);
        }

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(new SimpleMenuProvider(
                    (containerId, playerInventory, menuPlayer) -> new PrizmodContainer(containerId, menuPlayer),
                    Component.empty()
            ));
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        tooltip.add(Component.literal("Right click to open the prizmod."));
        tooltip.add(Component.literal("Shift Right click on a spectrobe to view its stats."));
    }
}