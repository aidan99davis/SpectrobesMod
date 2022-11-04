package com.spectrobes.spectrobesmod.common.items.minerals.chroma;

import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.items.minerals.SpecialMineralItem;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

import net.minecraft.item.Item.Properties;
import net.minecraft.world.item.Item;

public class ChromaMineralItem extends SpecialMineralItem {

    private int variantNumber;

    public ChromaMineralItem(int variantNumber) {
        super(new Item.Properties().tab(SpectrobesItems.SpectrobesMineralItemGroup.Instance));
        this.variantNumber = variantNumber;
    }

    @Override
    protected String getMineralEffectDescription() {
        return "\nChanges your Spectrobes variant colour to: " + this.variantNumber;
    }

    @Override
    public Spectrobe applyEffect(Spectrobe spectrobe) {
        spectrobe.setVariant(this.variantNumber);
        return spectrobe;
    }
}
