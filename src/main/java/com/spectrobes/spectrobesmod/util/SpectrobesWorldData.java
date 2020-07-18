package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.SpectrobesMod;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;

import java.util.*;
import java.util.function.Supplier;

public class SpectrobesWorldData extends WorldSavedData implements Supplier {

    public CompoundNBT data = new CompoundNBT();
    private static Map<UUID, Spectrobe> spectrobeIdMap = new HashMap<>();
    private static int nextEntityId;

    public static SpectrobesWorldData get(ServerWorld world) {
        DimensionSavedDataManager storage = world.getSavedData();
        Supplier<SpectrobesWorldData> sup = new SpectrobesWorldData();
        SpectrobesWorldData saver = storage.getOrCreate(sup, SpectrobesInfo.MOD_ID);

        if (saver == null)
        {
            saver = new SpectrobesWorldData();
            storage.set(saver);
        }
        return saver;
    }

    public SpectrobesWorldData() {
        super(SpectrobesInfo.MOD_ID);
        nextEntityId = 1;
    }

    public SpectrobesWorldData(String key) {
        this();
    }

    public static void removeSpectrobe(UUID spectrobeId) {
        spectrobeIdMap.remove(spectrobeId);
    }

    /**
     * reads in data from the NBTTagCompound into this MapDataBase
     *
     * @param nbt
     */
    @Override
    public void read(CompoundNBT nbt) {
        nextEntityId = nbt.getInt("NextEntityId");

    }

    public static void AddSpectrobe(UUID spectrobeId, Spectrobe instance) {
        spectrobeIdMap.put(spectrobeId, instance);
    }

    public static Spectrobe GetSpectrobe(UUID spectrobeId) {
        return spectrobeIdMap.get(spectrobeId);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("NextEntityId", nextEntityId);
        return compound;
    }

    public static int nextEntityId() {
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

    public static void onWorldLoaded(WorldEvent.Load event)
    {
        if (!event.getWorld().isRemote() && event.getWorld() instanceof ServerWorld)
        {
            SpectrobesWorldData saver = SpectrobesWorldData.get((ServerWorld) event.getWorld());

            if(saver.data.contains("SpectrobesData"))
            {
                SpectrobesInfo.LOGGER.info("Found my data: " + saver.data.get("SpectrobesData"));
                CompoundNBT spectrobeData = (CompoundNBT) saver.data.get("SpectrobesData");

                List<UUID> spectrobeIds = new ArrayList<>();
                spectrobeData.keySet().forEach(s -> spectrobeIds.add(UUID.fromString(s)));
                for (UUID spectrobeId : spectrobeIdMap.keySet()) {
                    spectrobeIdMap.put(spectrobeId, SpectrobeUtils
                            .readFromNbt((CompoundNBT) spectrobeData.get(String.valueOf(spectrobeId))));
                }
            }
        }
    }

    public static void onWorldSaved(WorldEvent.Save event)
    {
        if (!event.getWorld().isRemote() && event.getWorld() instanceof ServerWorld)
        {
            SpectrobesWorldData saver = SpectrobesWorldData.get((ServerWorld) event.getWorld());
            CompoundNBT myData = new CompoundNBT();
            CompoundNBT spectrobesData = new CompoundNBT();
            if(spectrobeIdMap.keySet().size() > 0) {
                for (UUID id : spectrobeIdMap.keySet()) {
                    spectrobesData.put(String.valueOf(id), spectrobeIdMap.get(id).write());
                }
            }
            myData.put("SpectrobesData", spectrobesData);
            saver.data = myData;
            saver.markDirty();
            SpectrobesInfo.LOGGER.info("Put my data in!");
        }
    }
}
