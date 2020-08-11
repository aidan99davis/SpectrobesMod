package com.spectrobes.spectrobesmod.common.items.tools;

import com.spectrobes.spectrobesmod.client.gui.prizmod.PrizmodMenu;
import com.spectrobes.spectrobesmod.client.prizmod.PrizmodScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class PrizmodItem extends Item {
    public PrizmodItem(Properties properties) {
        super(properties);
        setRegistryName("prizmod_item");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = new ItemStack(playerIn.getHeldItem(handIn).getItem(), 1);
        if(playerIn.isSneaking()) {
            Minecraft.getInstance().displayGuiScreen(new PrizmodScreen(playerIn));
        } else {
            Minecraft.getInstance().displayGuiScreen(new PrizmodMenu(playerIn));
        }


        return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
    }
}
