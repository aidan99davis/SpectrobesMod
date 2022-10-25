package com.spectrobes.spectrobesmod.common.items.tools;

import com.spectrobes.spectrobesmod.client.container.PrizmodContainer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class PrizmodItem extends Item {
    public PrizmodItem(Properties properties) {
        super(properties);
        setRegistryName("prizmod_item");
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = new ItemStack(playerIn.getItemInHand(handIn).getItem(), 1);
        if(!playerIn.isShiftKeyDown()) {
            if(!worldIn.isClientSide()) {
                NetworkHooks.openGui((ServerPlayerEntity) playerIn, new SimpleNamedContainerProvider(
                                (id, player, stack) -> new PrizmodContainer(id, playerIn),
                                new StringTextComponent(""))
                );
            }
        }
        return new ActionResult<>(ActionResultType.SUCCESS, itemStack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(new StringTextComponent("Right click to open the prizmod."));
        pTooltip.add(new StringTextComponent("Shift Right click on a spectrobe to view its stats."));
    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }
}
