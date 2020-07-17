package com.spectrobes.spectrobesmod.common.spectrobes;

import com.spectrobes.spectrobesmod.common.items.minerals.MineralProperties;
import com.spectrobes.spectrobesmod.util.SpectrobeBuilder;
import net.minecraft.nbt.CompoundNBT;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.util.UuidUtil;

import javax.annotation.Nullable;
import java.util.UUID;

public class Spectrobe {
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
}
