package com.spectrobes.spectrobesmod.common.spectrobes;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralProperties;
import com.spectrobes.spectrobesmod.common.registry.IconRegistry;
import com.spectrobes.spectrobesmod.util.SpectrobeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.util.UuidUtil;

import javax.annotation.Nullable;
import java.util.UUID;

public class Spectrobe {
    public static final EntityDataSerializer<Spectrobe> SpectrobeSerializer = new SpectrobeSerializer().init();
    @Nullable
    public UUID MasterUUID;

    public UUID SpectrobeUUID = UuidUtil.getTimeBasedUuid();

    @Required
    public String name;

    public String custom_name;

    @Required
    public SpectrobeProperties properties;

    @Required
    public SpectrobeStats stats;

    public int currentHealth;

    public boolean active;

    public int Variant;

    @Required
    public EvolutionRequirements evolutionRequirements;

    public Spectrobe copy(boolean copyUUID) {
        return new SpectrobeBuilder().buildFrom(this, copyUUID);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomName(String name) {
        this.custom_name = name;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        if(stats != null && this.currentHealth > stats.getHpLevel()) {
            this.currentHealth = stats.getHpLevel();
        }
    }

    public void addHealth(int toAdd) {
        this.currentHealth += toAdd;
        if(stats != null && this.currentHealth > stats.getHpLevel()) {
            this.currentHealth = stats.getHpLevel();
        }
    }

    public void setMasterUUID(UUID masterUUID) {
        this.MasterUUID = masterUUID;
    }

    public void setEvolutionRequirements(EvolutionRequirements evolutionRequirements) {
        this.evolutionRequirements = evolutionRequirements;
    }

    public void setProperties(SpectrobeProperties properties) {
        this.properties = properties;
    }

    public void setStats(SpectrobeStats stats) {
        this.stats = stats;
    }

    public void setVariant(int variant) {
        if(variant < 0 || variant > 2) {
            throw new IllegalArgumentException("Value must be between 0 and 2. was: " + variant);
        }
        this.Variant = variant;
    }

    public boolean canEvolve(EvolutionRequirements requirements) {
        return requirements.canEvolve(this);
    }

    public void evolve(Spectrobe evolution) {
        this.name = evolution.name;
        this.properties = evolution.properties;
        this.stats.setStatsOrBase(evolution.stats);
        this.stats.resetMineralsEaten();
        this.evolutionRequirements = evolution.evolutionRequirements;
        this.currentHealth = this.stats.getHpLevel();
    }

    public void applyMineral(MineralProperties properties) {
        this.stats.applyMineral(properties);
        this.setCurrentHealth(currentHealth + properties.getHpOffset());
    }

    public CompoundTag write() {
        CompoundTag compoundnbt = new CompoundTag();
        compoundnbt.putString("name", name);
        compoundnbt.putString("custom_name", custom_name);
        compoundnbt.putInt("currentHealth", currentHealth);
        compoundnbt.putUUID("SpectrobeUUID", SpectrobeUUID);
        if(MasterUUID != null) {
            compoundnbt.putUUID("MasterUUID", MasterUUID);
        }
        compoundnbt.putBoolean("active", active);
        compoundnbt.putInt("variant", Variant);

        compoundnbt.put("SpectrobeStats", stats.write());
        compoundnbt.put("SpectrobeProperties", properties.write());
        compoundnbt.put("EvolutionRequirements", evolutionRequirements.write());

        return compoundnbt;
    }
    public static Spectrobe read(CompoundTag nbtData) {
        Spectrobe s = new Spectrobe();
        try {
            s.SpectrobeUUID = nbtData.getUUID("SpectrobeUUID");
        } catch(NullPointerException ex) {
            s.SpectrobeUUID = UuidUtil.getTimeBasedUuid();
        }
        try {
            s.MasterUUID = nbtData.getUUID("MasterUUID");
        } catch(NullPointerException ex) {
            s.MasterUUID = null;
        }
        try {
            s.Variant = nbtData.getInt("variant");
        } catch(NullPointerException ex) {
            s.Variant = 0;
        }

        s.name = nbtData.get("name").getAsString();
        try {
            s.custom_name = nbtData.getString("custom_name");
        } catch(NullPointerException ex) {
            s.custom_name = s.name;
        }

        s.active = nbtData.getBoolean("active");
        s.properties = SpectrobeProperties.read(((CompoundTag) nbtData.get("SpectrobeProperties")));

        s.stats = SpectrobeStats.read((CompoundTag) nbtData.get("SpectrobeStats"));
        s.evolutionRequirements = EvolutionRequirements.read((CompoundTag) nbtData.get("EvolutionRequirements"));
        try {
            s.currentHealth = nbtData.getInt("currentHealth");
        } catch(NullPointerException ex) {
            s.currentHealth = s.stats.getHpLevel();
        }

        return s;
    }

    public SpectrobeIconInfo getIcon() {
        return IconRegistry.getInstance().getByName(name);
    }

    public void setInactive() {
        active = false;
    }

    public void setActive() {
        active = true;
    }

    public void setSpectrobeUUID(UUID spectrobeUUID) {
        this.SpectrobeUUID = spectrobeUUID;
    }

    public void update(Spectrobe spectrobeInstance) {
        setName(spectrobeInstance.name);
        setCustomName(spectrobeInstance.custom_name);
        setProperties(spectrobeInstance.properties);
        setVariant(spectrobeInstance.Variant);
        setStats(spectrobeInstance.stats);
        setMasterUUID(spectrobeInstance.MasterUUID);
        setEvolutionRequirements(spectrobeInstance.evolutionRequirements);
        setCurrentHealth(spectrobeInstance.currentHealth);
    }

    //Checks if the attacker should have the attack multiplier bonus applied.
    public static int hasTypeAdvantage(IHasNature attacker, IHasNature defender) {
        int toReturn = 0;

        if(attacker == defender)
            return toReturn;

        SpectrobeProperties.Nature attackerNature = attacker.getNature();
        SpectrobeProperties.Nature defenderNature = defender.getNature();

        switch(attackerNature){
            case FLASH:
                if(defenderNature == SpectrobeProperties.Nature.CORONA)
                    toReturn = 1;
                if(defenderNature == SpectrobeProperties.Nature.AURORA)
                    toReturn = -1;
                break;
            case AURORA:
                if(defenderNature == SpectrobeProperties.Nature.FLASH)
                    toReturn = 1;
                if(defenderNature == SpectrobeProperties.Nature.CORONA)
                    toReturn = -1;
                break;
            case CORONA:
                if(defenderNature == SpectrobeProperties.Nature.AURORA)
                    toReturn = 1;
                if(defenderNature == SpectrobeProperties.Nature.FLASH)
                    toReturn = -1;
                break;
            default:
                toReturn = 0;
                break;
        }

        return toReturn;
    }

    public void damage(int damageAmount) {
        if(currentHealth - damageAmount >= 0) {
            setCurrentHealth(currentHealth - damageAmount);
        } else {
            setCurrentHealth(0);
        }
    }

    public static class SpectrobeSerializer implements EntityDataSerializer<Spectrobe> {

        @Override
        public void write(FriendlyByteBuf buf, Spectrobe value) {
            buf.writeNbt(value.write());
        }

        @Override
        public Spectrobe read(FriendlyByteBuf buf) {
            return Spectrobe.read(buf.readNbt());
        }

        @Override
        public Spectrobe copy(Spectrobe value) {
            return value.copy(true);
        }

        public EntityDataSerializer<Spectrobe> init() {
            if(EntityDataSerializers.getSerializer(EntityDataSerializers.getSerializedId(this)) == null) {
                SpectrobesInfo.LOGGER.info("Registering serializer");
                EntityDataSerializers.registerSerializer(this);
            }
            return this;
        }
    }
}
