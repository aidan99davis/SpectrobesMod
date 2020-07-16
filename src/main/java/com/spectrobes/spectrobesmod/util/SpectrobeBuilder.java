package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeStats;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

public class SpectrobeBuilder {
    private String name;
    private SpectrobeProperties properties;
    private SpectrobeStats initialStats;
    private Spectrobe evolution;

    public SpectrobeBuilder withName(String name) {
        this.name = name;
        return this;
    }
    public SpectrobeBuilder withProperties(SpectrobeProperties.Nature nature, SpectrobeProperties.Stage stage) {
        this.properties = new SpectrobeProperties(nature, stage);
        return this;
    }

    public SpectrobeBuilder withProperties(SpectrobeProperties props) {
        this.properties = props;
        return this;
    }
    public SpectrobeBuilder withInitialStats(int hpStat, int atkStat, int defStat) {
        this.initialStats = new SpectrobeStats(hpStat,atkStat,defStat);
        return this;
    }

    public Spectrobe build() {
        return new Spectrobe();
    }
}
