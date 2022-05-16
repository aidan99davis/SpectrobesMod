package com.spectrobes.spectrobesmod.common.items.tools;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

import javax.annotation.Nullable;

public class PrizmodManager extends Container {

    protected PrizmodManager(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return playerIn.getItemInHand(playerIn.getUsedItemHand()).getItem() instanceof PrizmodItem;
    }
}
