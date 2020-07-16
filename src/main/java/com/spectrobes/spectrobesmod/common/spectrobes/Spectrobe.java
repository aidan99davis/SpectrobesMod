package com.spectrobes.spectrobesmod.common.spectrobes;

import com.spectrobes.spectrobesmod.common.items.minerals.MineralProperties;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.MerchantOffer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.annotation.Nullable;
import java.util.UUID;

public class Spectrobe {

    @Nullable
    public UUID MasterUUID;
    @Required
    public String name;
    @Required
    public SpectrobeProperties properties;

    @Required
    public SpectrobeStats stats;

    public Spectrobe copy() {
        return null;
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
        compoundnbt.putUniqueId("masterUUID", MasterUUID);
        compoundnbt.put("SpectrobeStats", stats.write());
        compoundnbt.put("SpectrobeProperties", properties.write());

        return compoundnbt;
    }
}
