package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;

public class KrawlPropertiesBuilder {
    private SpectrobeProperties.Nature nature;
    private int atkLevel;
    private int defLevel;
    private int hpLevel;

    private int atkOffset;
    private int defOffset;
    private int hpOffset;
    private int xp_worth;

    public KrawlPropertiesBuilder withNature(SpectrobeProperties.Nature nature) {
        this.nature = nature;
        return this;
    }

    public KrawlPropertiesBuilder withAtkLevel(int atkLevel) {
        this.atkLevel = atkLevel;
        return this;
    }

    public KrawlPropertiesBuilder withDefLevel(int defLevel) {
        this.defLevel = defLevel;
        return this;
    }

    public KrawlPropertiesBuilder withHpLevel(int hpLevel) {
        this.hpLevel = hpLevel;
        return this;
    }

    public KrawlPropertiesBuilder withAtkOffset(int atkOffset) {
        this.atkOffset = atkOffset;
        return this;
    }

    public KrawlPropertiesBuilder withDefOffset(int defOffset) {
        this.defOffset = defOffset;
        return this;
    }

    public KrawlPropertiesBuilder withHpOffset(int hpOffset) {
        this.hpOffset = hpOffset;
        return this;
    }

    public KrawlPropertiesBuilder withXpWorth(int xpWorth) {
        this.xp_worth = xpWorth;
        return this;
    }

    public KrawlProperties build() {
        KrawlProperties properties = new KrawlProperties();

        properties.setNature(this.nature);
        properties.setAtkLevel(this.atkLevel);
        properties.setAtkOffset(this.atkOffset);
        properties.setDefLevel(this.defLevel);
        properties.setDefOffset(this.defOffset);
        properties.setHpLevel(this.hpLevel);
        properties.setHpOffset(this.hpOffset);
        properties.setXpWorth(this.xp_worth);

        return properties;
    }
}
