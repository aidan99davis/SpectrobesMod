package com.spectrobes.spectrobesmod.common.items.minerals;

public class MineralPropertiesBuilder {
    private int hpOffset = 0;
    private int atkOffset = 0;
    private int defOffset = 0;

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

    public MineralProperties build() {
        MineralProperties properties = new MineralProperties();

        properties.setHpOffset(this.hpOffset);
        properties.setAtkOffset(this.atkOffset);
        properties.setDefOffset(this.defOffset);

        return properties;
    }
}
