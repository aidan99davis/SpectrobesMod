package com.spectrobes.spectrobesmod.common.items.minerals;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.MineralPropertiesBuilder;

public class MineralProperties {
    private int hpOffset;
    private int atkOffset;
    private int defOffset;
    private SpectrobeProperties.Nature nature;
    private int xp_worth;
    private int gura_worth;

    public void setHpOffset(int hpOffset) {
        this.hpOffset = hpOffset;
    }

    public void setAtkOffset(int atkOffset) {
        this.atkOffset = atkOffset;
    }

    public void setDefOffset(int defOffset) {
        this.defOffset = defOffset;
    }

    public void setNature(SpectrobeProperties.Nature nature) {
        this.nature = nature;
    }

    public void setXpWorth(int xp_worth) {
        this.xp_worth = xp_worth;
    }

    public void setGuraWorth(int gura_worth) {this.gura_worth = gura_worth;}

    public int getHpOffset() {
        return this.hpOffset;
    }

    public int getAtkOffset() {
        return this.atkOffset;
    }

    public int getDefOffset() {
        return this.defOffset;
    }

    public SpectrobeProperties.Nature getNature() {
        return this.nature;
    }

    public int getXpWorth() {
        return this.xp_worth;
    }

    public int getGuraWorth() {return this.gura_worth; }

    public MineralProperties copy() {
        return new MineralPropertiesBuilder().buildFrom(this);
    }
}
