package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeStats;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;

import java.util.UUID;

public class SpectrobeBuilder {
    private UUID masterUUID;
    private String name;
    private SpectrobeProperties properties;
    private SpectrobeStats stats;
    private Spectrobe evolution;

    public SpectrobeBuilder withMasterUUID(UUID masterUUID) {
        this.masterUUID = masterUUID;
        return this;
    }

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
    public SpectrobeBuilder withStats(int hpStat, int atkStat, int defStat) {
        this.stats = new SpectrobeStats(hpStat,atkStat,defStat);
        return this;
    }

    public Spectrobe buildFrom(Spectrobe spectrobe) {
        Spectrobe newSpectrobe = new Spectrobe();
        newSpectrobe.setMasterUUID(spectrobe.MasterUUID);
        newSpectrobe.setName(spectrobe.name);
        newSpectrobe.setProperties(spectrobe.properties.copy());
        newSpectrobe.setStats(spectrobe.stats.copy());
        return newSpectrobe;
    }

    public Spectrobe build() {
        Spectrobe newSpectrobe = new Spectrobe();
        newSpectrobe.setMasterUUID(masterUUID);
        newSpectrobe.setName(name);
        newSpectrobe.setProperties(properties);
        newSpectrobe.setStats(stats);
        return newSpectrobe;
    }
}
