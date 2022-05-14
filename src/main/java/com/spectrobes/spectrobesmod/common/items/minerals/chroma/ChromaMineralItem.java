package com.spectrobes.spectrobesmod.common.items.minerals.chroma;

import com.spectrobes.spectrobesmod.common.items.minerals.SpecialMineralItem;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

import net.minecraft.item.Item.Properties;

public class ChromaMineralItem extends SpecialMineralItem {

    private int variantNumber;

    public ChromaMineralItem(Properties properties, String registryName, int variantNumber) {
        super(properties, registryName);
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
