package com.spectrobes.spectrobesmod.common.spectrobes;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralProperties;
import com.spectrobes.spectrobesmod.common.registry.IconRegistry;
import com.spectrobes.spectrobesmod.util.SpectrobeBuilder;
import net.minecraft.crash.ReportedException;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.IDataSerializer;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.util.UuidUtil;

import javax.annotation.Nullable;
import java.util.UUID;

public class Spectrobe {
    public static final IDataSerializer<Spectrobe> SpectrobeSerializer = new SpectrobeSerializer().init();
    @Nullable
    public UUID MasterUUID;

    public UUID SpectrobeUUID = UuidUtil.getTimeBasedUuid();
    @Required
    public String name;
    @Required
    public SpectrobeProperties properties;

    @Required
    public SpectrobeStats stats;

    public boolean active;

    public Spectrobe copy(boolean copyUUID) {
        return new SpectrobeBuilder().buildFrom(this, copyUUID);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMasterUUID(UUID masterUUID) {
        this.MasterUUID = masterUUID;
    }

    public void setProperties(SpectrobeProperties properties) {
        this.properties = properties;
    }

    public void setStats(SpectrobeStats stats) {
        this.stats = stats;
    }

    public boolean canEvolve(EvolutionRequirements requirements) {
        return requirements.canEvolve(this);
    }

    public void evolve(Spectrobe evolution) {
        this.name = evolution.name;
        this.properties = evolution.properties;
        this.stats.addStats(evolution.stats);
    }

    public void applyMineral(MineralProperties properties) {
        this.stats.applyMineral(properties);
    }

    public CompoundNBT write() {
        CompoundNBT compoundnbt = new CompoundNBT();
        compoundnbt.putString("name", name);
        compoundnbt.putUniqueId("SpectrobeUUID", SpectrobeUUID);
        if(MasterUUID != null) {
            compoundnbt.putUniqueId("MasterUUID", MasterUUID);
        }
        compoundnbt.putBoolean("active", active);

        compoundnbt.put("SpectrobeStats", stats.write());
        compoundnbt.put("SpectrobeProperties", properties.write());

        return compoundnbt;
    }
    public static Spectrobe read(CompoundNBT nbtData) {
        Spectrobe s = new Spectrobe();
        try {
            s.SpectrobeUUID = nbtData.getUniqueId("SpectrobeUUID");
        } catch(NullPointerException ex) {
            s.SpectrobeUUID = UuidUtil.getTimeBasedUuid();
        }
        try {
            s.MasterUUID = nbtData.getUniqueId("MasterUUID");
        } catch(NullPointerException ex) {
            s.MasterUUID = null;
        }
        s.name = nbtData.get("name").getString();
//        s.SpectrobeUUID = nbtData.getUniqueId("UUID");
        s.active = nbtData.getBoolean("active");
        s.properties = SpectrobeProperties.read(((CompoundNBT) nbtData.get("SpectrobeProperties")));

        s.stats = SpectrobeStats.read((CompoundNBT) nbtData.get("SpectrobeStats"));

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
        setProperties(spectrobeInstance.properties);
        setStats(spectrobeInstance.stats);
        setMasterUUID(spectrobeInstance.MasterUUID);
    }

    public static class SpectrobeSerializer implements IDataSerializer<Spectrobe> {

        @Override
        public void write(PacketBuffer buf, Spectrobe value) {
            buf.writeCompoundTag(value.write());
        }

        @Override
        public Spectrobe read(PacketBuffer buf) {
            return Spectrobe.read(buf.readCompoundTag());
        }

        @Override
        public Spectrobe copyValue(Spectrobe value) {
            return value.copy(true);
        }

        public IDataSerializer<Spectrobe> init() {
            if(DataSerializers.getSerializer(DataSerializers.getSerializerId(this)) == null) {
                SpectrobesInfo.LOGGER.info("Registering serializer");
                DataSerializers.registerSerializer(this);
            }
            return this;
        }
    }
}
