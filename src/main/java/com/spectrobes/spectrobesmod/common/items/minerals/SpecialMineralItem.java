package com.spectrobes.spectrobesmod.common.items.minerals;

import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

import net.minecraft.item.Item.Properties;
import net.minecraft.world.item.Item;

public abstract class SpecialMineralItem extends Item {

    public SpecialMineralItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flag) {

        tooltip.add(new StringTextComponent("Effect: " + getMineralEffectDescription()));
    }

    protected abstract String getMineralEffectDescription();

    public abstract Spectrobe applyEffect(Spectrobe spectrobe);
}
