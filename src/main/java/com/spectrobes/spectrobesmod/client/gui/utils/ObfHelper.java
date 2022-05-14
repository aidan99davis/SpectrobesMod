package com.spectrobes.spectrobesmod.client.gui.utils;

import net.minecraftforge.fml.loading.FMLLoader;

public class ObfHelper {
    private static final String OBF_VERSION = "1.15.2";
    private static boolean devEnvironment;

    public static void detectDevEnvironment()
    {
        devEnvironment = FMLLoader.getNaming().equals("mcp");
    }
    public static boolean isDevEnvironment()
    {
        return devEnvironment;
    }

    public static final String getEntityTexture = "getTextureLocation"; //IEntityRenderer
    public static final String preRenderCallback = "scale"; //LivingRenderer
    public static final String getHurtSound = "getHurtSound"; //LivingEntity
    public static final String getDeathSound = "getDeathSound"; //LivingEntity
    public static final String getSoundVolume = "getSoundVolume"; //LivingEntity
    public static final String getSoundPitch = "getVoicePitch"; //LivingEntity
    public static final String onChangedPotionEffect = "onEffectUpdated"; //LivingEntity
}
