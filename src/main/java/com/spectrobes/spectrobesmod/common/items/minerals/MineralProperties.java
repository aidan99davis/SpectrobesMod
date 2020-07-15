package com.spectrobes.spectrobesmod.common.items.minerals;

public class MineralProperties {
    private int hpOffset;
    private int atkOffset;
    private int defOffset;

    public void setHpOffset(int hpOffset) {
        this.hpOffset = hpOffset;
    }

    public void setAtkOffset(int atkOffset) {
        this.atkOffset = atkOffset;
    }

    public void setDefOffset(int defOffset) {
        this.defOffset = defOffset;
    }

    public int getHpOffset() {
        return this.hpOffset;
    }

    public int getAtkOffset() {
        return this.atkOffset;
    }

    public int getDefOffset() {
        return this.defOffset;
    }
}
