package com.spectrobes.spectrobesmod.common.spectrobes;

public class SpectrobeStats {
    private int atkLevel;
    private int defLevel;
    private int hpLevel;

    private int level;
    private int xp_required;
    private int xp;

    public SpectrobeStats(int hpStat, int atkStat, int defStat) {
        this.hpLevel = hpStat;
        this.atkLevel = atkStat;
        this.defLevel = defStat;
        xp_required = 100;
        xp = 0;
        level = 1;
    }

    //returns true if levelled up.
    public boolean addXp(int xp_to_add) {
        xp += xp_to_add;
        if(xp > xp_required) {
            xp -= xp_required;
            xp_required *= 1.2;
            level++;
            return true;
        }
        return false;
    }
}
