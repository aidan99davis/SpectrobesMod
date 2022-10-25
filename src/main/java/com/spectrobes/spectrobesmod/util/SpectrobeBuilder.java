package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.common.spectrobes.*;
import net.minecraft.util.ResourceLocation;

import java.util.Random;
import java.util.UUID;

public class SpectrobeBuilder {
    private UUID masterUUID;
    private String name;
    private String custom_name;
    private SpectrobeProperties properties;
    private EvolutionRequirements evolutionRequirements;
    private SpectrobeStats stats;
    private int variant = 0;
    private int currentHealth = 0;

    public SpectrobeBuilder withMasterUUID(UUID masterUUID) {
        this.masterUUID = masterUUID;
        return this;
    }

    public SpectrobeBuilder withName(String name) {
        this.name = name;
        withCustomName(name);
        return this;
    }

    public SpectrobeBuilder withEvolutionRequirements(EvolutionRequirements requirements) {
        this.evolutionRequirements = requirements;
        return this;
    }

    public SpectrobeBuilder withCustomName(String name) {
        this.name = name;
        return this;
    }

    public SpectrobeBuilder withCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        return this;
    }

    public SpectrobeBuilder withVariant(int variant) {
        this.variant = variant;
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

    public SpectrobeBuilder withStats(Stat hpStat, Stat atkStat, Stat defStat) {
        this.stats = new SpectrobeStats(hpStat,atkStat,defStat);
        this.currentHealth = hpStat.value();
        return this;
    }


    public SpectrobeBuilder withStats(SpectrobeStats stats) {
        this.stats = stats;
        this.currentHealth = stats.getHpLevel();
        return this;
    }


    public Spectrobe buildFrom(Spectrobe spectrobe, boolean copyUUID) {
        Spectrobe newSpectrobe = new Spectrobe();
        newSpectrobe.setMasterUUID(spectrobe.MasterUUID);
        if(copyUUID) {
            newSpectrobe.setSpectrobeUUID(spectrobe.SpectrobeUUID);
            newSpectrobe.setVariant(spectrobe.Variant);
        } else {
            newSpectrobe.setVariant(new Random().nextInt(3));
        }
        newSpectrobe.setCurrentHealth(spectrobe.currentHealth);
        newSpectrobe.setName(spectrobe.name);
        if(custom_name != null && custom_name.length() > 0) {
            newSpectrobe.setCustomName(spectrobe.custom_name);

        } else newSpectrobe.setCustomName(spectrobe.name);
        newSpectrobe.setProperties(spectrobe.properties.copy());
        newSpectrobe.setStats(spectrobe.stats.copy());
        newSpectrobe.setEvolutionRequirements(spectrobe.evolutionRequirements.copy());
        return newSpectrobe;
    }

    public Spectrobe build() {
        Spectrobe newSpectrobe = new Spectrobe();
        newSpectrobe.setMasterUUID(masterUUID);
        newSpectrobe.setName(name);
        newSpectrobe.setEvolutionRequirements(evolutionRequirements);
        newSpectrobe.setCustomName(custom_name);
        newSpectrobe.setVariant(variant);
        newSpectrobe.setProperties(properties);
        newSpectrobe.setStats(stats);
        newSpectrobe.setCurrentHealth(currentHealth);
        return newSpectrobe;
    }
}
