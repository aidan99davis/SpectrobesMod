package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;

public class DamageUtils {

    public static float getTypeBonus(int advantage, int atkPower) {
        float typeBonus;

        switch (advantage) {
            case -1:
                typeBonus = atkPower * 0.75f;
                break;
            case 1:
                typeBonus = atkPower * 1.5f;
                break;
            default:
                typeBonus = atkPower;
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
}
