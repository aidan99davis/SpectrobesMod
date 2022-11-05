package com.spectrobes.spectrobesmod.common.items.minerals;

import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class SpecialMineralItem extends Item {

    public SpecialMineralItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flag) {

        tooltip.add(Component.literal("Effect: " + getMineralEffectDescription()));
    }

    protected abstract String getMineralEffectDescription();

    public abstract Spectrobe applyEffect(Spectrobe spectrobe);
}
