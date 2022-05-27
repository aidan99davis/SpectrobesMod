package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;

public class MineralBuilder {
    private MineralPropertiesBuilder mineralPropertiesBuilder = null;
    private String name;
    private Mineral.MineralRarity rarity = Mineral.MineralRarity.Common;

    public MineralBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MineralBuilder withRarity(Mineral.MineralRarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public MineralBuilder withMineralProperties(MineralPropertiesBuilder mineralPropertiesBuilder) {
        this.mineralPropertiesBuilder = mineralPropertiesBuilder;
        return this;
    }

    public Mineral build() {
        Mineral mineral = new Mineral();

        mineral.name = this.name;
        mineral.rarity = this.rarity;
        mineral.properties = this.mineralPropertiesBuilder.build();

        return mineral;
    }
}
