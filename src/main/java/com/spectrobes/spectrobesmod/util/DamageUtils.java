package com.spectrobes.spectrobesmod.util;

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
}
