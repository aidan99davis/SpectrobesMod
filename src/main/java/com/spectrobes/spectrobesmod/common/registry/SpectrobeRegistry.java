package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.util.SpectrobeBuilder;

public class SpectrobeRegistry {

    public static final Spectrobe Komanoto
            = new SpectrobeBuilder()
            .withName("Komanoto")
            .withProperties(SpectrobePropertyRegistry.KOMANOTO)
            .withStats(75,45,45)
            .withIconResourceLocation("komanoto")
            .build();
    public static final Spectrobe Komainu
            = new SpectrobeBuilder()
            .withName("Komainu")
            .withProperties(SpectrobePropertyRegistry.KOMAINU)
            .withStats(25,5,5)
            .withIconResourceLocation("komainu")
            .build();


    public static final Spectrobe Spiko
            = new SpectrobeBuilder()
            .withName("Spiko")
            .withProperties(SpectrobePropertyRegistry.SPIKO)
            .withIconResourceLocation("spiko")
            .withStats(25,5,5)
            .build();

    public static final Spectrobe Spikan
            = new SpectrobeBuilder()
            .withName("Spikan")
            .withProperties(SpectrobePropertyRegistry.SPIKAN)
            .withIconResourceLocation("spikan")
            .withStats(100,45,30)
            .build();


    public static final Spectrobe Samukabu
            = new SpectrobeBuilder()
            .withName("Samukabu")
            .withProperties(SpectrobePropertyRegistry.SAMUKABU)
            .withIconResourceLocation("samukabu")
            .withStats(25,5,5)
            .build();

    public static final Spectrobe Samurite
            = new SpectrobeBuilder()
            .withName("Samurite")
            .withProperties(SpectrobePropertyRegistry.SAMURITE)
            .withIconResourceLocation("samurite")
            .withStats(100,45,30)
            .build();
}
