package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;

import java.util.*;
import java.util.function.Supplier;

public class SpectrobesWorldData extends WorldSavedData implements Supplier {

    private static final SpectrobesWorldData CLIENT_DUMMY = new SpectrobesWorldData();

    private Map<UUID, Spectrobe> spectrobeIdMap = new HashMap<>();
    private static int nextEntityId;

    public static SpectrobesWorldData get(IWorld world) {
        if (!(world instanceof ServerWorld))
        {
            return CLIENT_DUMMY;
        }
        DimensionSavedDataManager storage = ((ServerWorld)world).getSavedData();
        Supplier<SpectrobesWorldData> sup = new SpectrobesWorldData();
        SpectrobesWorldData saver = storage.getOrCreate(sup, SpectrobesInfo.MOD_ID);

        if (saver == null)
        {
            saver = new SpectrobesWorldData();
        }

        storage.set(saver);
        return saver;
    }

    public SpectrobesWorldData() {
        super(SpectrobesInfo.MOD_ID);
        nextEntityId = 1;
    }

    public void removeSpectrobe(UUID spectrobeId) {
        spectrobeIdMap.remove(spectrobeId);
        this.markDirty();
    }

    /**
     * reads in data from the NBTTagCompound into this MapDataBase
     *
     * @param nbt
     */
    @Override
    public void read(CompoundNBT nbt) {
        nextEntityId = nbt.getInt("NextEntityId");

        if(nbt.contains("SpectrobesData"))
        {
            SpectrobesInfo.LOGGER.info("Found my data: " + nbt.get("SpectrobesData"));
            CompoundNBT spectrobeData = (CompoundNBT) nbt.get("SpectrobesData");

            List<UUID> spectrobeIds = new ArrayList<>();
            spectrobeData.keySet().forEach(s -> spectrobeIds.add(UUID.fromString(s)));
            for (UUID spectrobeId : spectrobeIdMap.keySet()) {
                spectrobeIdMap.put(spectrobeId, Spectrobe
                        .read((CompoundNBT) spectrobeData.get(String.valueOf(spectrobeId))));
            }
        }
    }

    public void AddSpectrobe(UUID spectrobeId, Spectrobe instance) {
        spectrobeIdMap.put(spectrobeId, instance);
        this.markDirty();
    }

    public Spectrobe GetSpectrobe(UUID spectrobeId) {
        return spectrobeIdMap.get(spectrobeId);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("NextEntityId", nextEntityId);
        CompoundNBT spectrobesData = new CompoundNBT();
        if(spectrobeIdMap.keySet().size() > 0) {
            for (UUID id : spectrobeIdMap.keySet()) {
                spectrobesData.put(String.valueOf(id), spectrobeIdMap.get(id).write());
            }
        }
        compound.put("SpectrobesData", spectrobesData);
        SpectrobesInfo.LOGGER.info("Put my data in!");
        return compound;
    }

    public int nextEntityId() {
        return ++nextEntityId;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public Object get() {
        return this;
    }

}
