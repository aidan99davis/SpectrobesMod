package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;

public class DamageUtils {

    public static float getTypeBonus(int advantage, int atkPower) {
        float typeBonus;

        switch (advantage) {
            case -1:
                typeBonus = 0.75f;
                break;
            case 1:
                typeBonus = 1.25f;
                break;
            default:
                typeBonus = 1;
                break;
        }
        return typeBonus;
    }
    public static boolean hasAdvantage(SpectrobeProperties.Nature attackerNature, SpectrobeProperties.Nature defenderNature) {
        switch(attackerNature){
            case FLASH:
                if(defenderNature == SpectrobeProperties.Nature.CORONA)
                    return true;
                if(defenderNature == SpectrobeProperties.Nature.AURORA)
                    return false;
            case AURORA:
                if(defenderNature == SpectrobeProperties.Nature.FLASH)
                    return true;
                if(defenderNature == SpectrobeProperties.Nature.CORONA)
                    return false;
            case CORONA:
                if(defenderNature == SpectrobeProperties.Nature.AURORA)
                    return true;
                if(defenderNature == SpectrobeProperties.Nature.FLASH)
                    return false;
            default:
                return false;
        }
    }
    public static boolean hasDisadvantage(SpectrobeProperties.Nature attackerNature, SpectrobeProperties.Nature defenderNature) {
        switch(attackerNature){
            case FLASH:
                if(defenderNature == SpectrobeProperties.Nature.CORONA)
                    return false;
                if(defenderNature == SpectrobeProperties.Nature.AURORA)
                    return true;
            case AURORA:
                if(defenderNature == SpectrobeProperties.Nature.FLASH)
                    return false;
                if(defenderNature == SpectrobeProperties.Nature.CORONA)
                    return true;
            case CORONA:
                if(defenderNature == SpectrobeProperties.Nature.AURORA)
                    return false;
                if(defenderNature == SpectrobeProperties.Nature.FLASH)
                    return true;
            default:
                return false;
        }
    }

    public static float getFinalDamageAmount(float typeBonus, int atkPower, int powerScale, int defPower) {
        float amount = (typeBonus * ((atkPower / 2.5f) * powerScale)) - ((defPower / 5) * typeBonus);

        if(amount < 0) amount = 0;

        return amount;
    }
}
