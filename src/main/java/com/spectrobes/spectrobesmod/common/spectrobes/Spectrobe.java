package com.spectrobes.spectrobesmod.common.spectrobes;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralProperties;
import com.spectrobes.spectrobesmod.util.SpectrobeBuilder;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.IDataSerializer;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.util.UuidUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    public Spectrobe copy() {
        return new SpectrobeBuilder().buildFrom(this);
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


    public void applyMineral(MineralProperties properties) {
        this.stats.applyMineral(properties);
    }

    public CompoundNBT write() {
        CompoundNBT compoundnbt = new CompoundNBT();
        compoundnbt.putString("name", name);
        if(MasterUUID != null)
            compoundnbt.putUniqueId("MasterUUID", MasterUUID);
        compoundnbt.putUniqueId("UUID", SpectrobeUUID);

        compoundnbt.put("SpectrobeStats", stats.write());
        compoundnbt.put("SpectrobeProperties", properties.write());

        return compoundnbt;
    }
    public static Spectrobe read(CompoundNBT nbtData) {
        Spectrobe s = new Spectrobe();
        s.MasterUUID = nbtData.getUniqueId("MasterUUID");
        s.name = nbtData.get("name").getString();
        s.SpectrobeUUID = nbtData.getUniqueId("UUID");
        s.properties = SpectrobeProperties.read(((CompoundNBT) nbtData.get("SpectrobeProperties")));

        s.stats = SpectrobeStats.read((CompoundNBT) nbtData.get("SpectrobeStats"));

        return s;
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
            return value.copy();
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
