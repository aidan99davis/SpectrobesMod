package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.items.minerals.MineralProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;

public class MineralPropertiesBuilder {
    private int hpOffset = 0;
    private int atkOffset = 0;
    private int defOffset = 0;
    private int xpWorth = 0;
    private SpectrobeProperties.Nature nature;

    public MineralPropertiesBuilder setHpOffset(int offset) {
        this.hpOffset = offset;
        return this;
    }

    public MineralPropertiesBuilder setAtkOffset(int offset) {
        this.atkOffset = offset;
        return this;
    }

    public MineralPropertiesBuilder setDefOffset(int offset) {
        this.defOffset = offset;
        return this;
    }

    public MineralPropertiesBuilder setXpWorth(int xpWorth) {
        this.xpWorth = xpWorth;
        return this;
    }

    public MineralPropertiesBuilder setNature(SpectrobeProperties.Nature nature) {
        this.nature = nature;
        return this;
    }

    public MineralProperties build() {
        MineralProperties properties = new MineralProperties();

        properties.setHpOffset(this.hpOffset);
        properties.setAtkOffset(this.atkOffset);
        properties.setDefOffset(this.defOffset);
        properties.setNature(this.nature);
        properties.setXpWorth(this.xpWorth);

        return properties;
    }
}
