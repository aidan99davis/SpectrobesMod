package com.spectrobes.spectrobesmod.common.items.minerals.chroma;

import com.spectrobes.spectrobesmod.common.items.SpectrobesItemGroups;
import com.spectrobes.spectrobesmod.common.items.minerals.IWorthGura;
import com.spectrobes.spectrobesmod.common.items.minerals.SpecialMineralItem;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

import net.minecraft.world.item.Item;

public class ChromaMineralItem extends SpecialMineralItem implements IWorthGura {

    private final int variantNumber;

    public ChromaMineralItem(int variantNumber) {
        super(new Item.Properties().tab(SpectrobesItemGroups.SpectrobesMineralItemGroup.Instance));
        this.variantNumber = variantNumber;
    }

    public int getVariantNumber() { return variantNumber; }

    @Override
    protected String getMineralEffectDescription() {
        return "\nChanges your Spectrobes variant colour to: " + this.variantNumber;
    }

    @Override
    public Spectrobe applyEffect(Spectrobe spectrobe) {
        spectrobe.setVariant(this.variantNumber);
        return spectrobe;
    }

    @Override
    public int getGuraWorth() {
        return 50000;
    }

    @Override
    public String getName() {
        return switch (variantNumber) {
            case 1 -> "chroma_mineral_item_one";
            case 2 -> "chroma_mineral_item_two";
            default -> "chroma_mineral_item_zero";
        };
    }
}
