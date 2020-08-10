package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.util.SpectrobeBuilder;

public class SpectrobeRegistry {

    public static final Spectrobe Komanoto
            = new SpectrobeBuilder()
            .withName("Komanoto")
            .withProperties(SpectrobePropertyRegistry.KOMANOTO)
            .withStats(75,45,45)
            .build();
    public static final Spectrobe Komainu
            = new SpectrobeBuilder()
            .withName("Komainu")
            .withProperties(SpectrobePropertyRegistry.KOMAINU)
            .withStats(25,5,5)
            .build();


    public static final Spectrobe Spiko
            = new SpectrobeBuilder()
            .withName("Spiko")
            .withProperties(SpectrobePropertyRegistry.SPIKO)
            .withStats(25,5,5)
            .build();

    public static final Spectrobe Spikan
            = new SpectrobeBuilder()
            .withName("Spikan")
            .withProperties(SpectrobePropertyRegistry.SPIKAN)
            .withStats(100,45,30)
            .build();


    public static final Spectrobe Samukabu
            = new SpectrobeBuilder()
            .withName("Samukabu")
            .withProperties(SpectrobePropertyRegistry.SAMUKABU)
            .withStats(25,5,5)
            .build();

    public static final Spectrobe Samurite
            = new SpectrobeBuilder()
            .withName("Samurite")
            .withProperties(SpectrobePropertyRegistry.SAMURITE)
            .withStats(100,45,30)
            .build();
}
