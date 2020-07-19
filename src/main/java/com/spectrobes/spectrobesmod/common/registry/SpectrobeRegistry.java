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
            .withEvolution(Komanoto.copy())
            .withStats(25,5,5)
            .build();
}
