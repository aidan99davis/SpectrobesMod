package com.spectrobes.spectrobesmod.common.krawl;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.KrawlPropertiesBuilder;
import net.minecraft.nbt.CompoundNBT;

public class KrawlProperties {

    private SpectrobeProperties.Nature nature;
    private int atkLevel;
    private int defLevel;
    private int hpLevel;

    private int atkOffset;
    private int defOffset;
    private int hpOffset;
    private int xp_worth;

    public KrawlProperties() {
        this.nature = SpectrobeProperties.Nature.OTHER;
    }

    public KrawlProperties(SpectrobeProperties.Nature nature) {
        this.nature = nature;
    }

    public SpectrobeProperties.Nature getNature() {
        return nature;
    }

    public void setNature(SpectrobeProperties.Nature nature) {
        this.nature = nature;
    }

    public KrawlProperties copy() {
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

    public void setAtkLevel(int atkLevel) {
        this.atkLevel = atkLevel;
    }

    public void setAtkOffset(int atkOffset) {
        this.atkOffset = atkOffset;
    }

    public void setDefLevel(int defLevel) {
        this.defLevel = defLevel;
    }

    public void setDefOffset(int defOffset) {
        this.defOffset = defOffset;
    }

    public void setHpLevel(int hpLevel) {
        this.hpLevel = hpLevel;
    }

    public void setHpOffset(int hpOffset) {
        this.hpOffset = hpOffset;
    }

    public void setXpWorth(int xp_worth) {
        this.xp_worth = xp_worth;
    }

    public int getHpOffset() {
        return hpOffset;
    }

    public int getDefOffset() {
        return defOffset;
    }

    public int getAtkOffset() {
        return atkOffset;
    }

    public int getXpWorth() {
        return xp_worth;
    }
}
