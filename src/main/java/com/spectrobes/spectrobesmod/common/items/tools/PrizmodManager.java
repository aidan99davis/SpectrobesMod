package com.spectrobes.spectrobesmod.common.items.tools;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class PrizmodManager extends AbstractContainerMenu {

    protected PrizmodManager(@Nullable MenuType<?> type, int id) {
        super(type, id);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return playerIn.getItemInHand(playerIn.getUsedItemHand()).getItem() instanceof PrizmodItem;
    }
}
