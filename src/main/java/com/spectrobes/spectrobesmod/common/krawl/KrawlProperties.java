package com.spectrobes.spectrobesmod.common.krawl;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
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

    public KrawlProperties(SpectrobeProperties.Nature nature) {
        this.nature = nature;
    }

    public SpectrobeProperties.Nature getNature() {
        return nature;
    }

    public void setNature(SpectrobeProperties.Nature nature) {
        this.nature = nature;
    }

    //serialisation

    public static KrawlProperties read(CompoundNBT spectrobeProperties) {

        return new KrawlProperties(
                SpectrobeProperties.Nature.valueOf(spectrobeProperties.get("nature").getString()));

    }

    public CompoundNBT write() {
        CompoundNBT compoundnbt = new CompoundNBT();
        compoundnbt.putString("nature", nature.toString());

        return compoundnbt;
    }

    public KrawlProperties copy() {
        return new KrawlProperties(nature);
    }

}
