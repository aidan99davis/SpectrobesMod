package com.spectrobes.spectrobesmod.util;

import com.spectrobes.spectrobesmod.SpectrobesMod;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.event.world.WorldEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SpectrobesWorldData extends WorldSavedData implements Supplier {

    public CompoundNBT data = new CompoundNBT();
    private static Map<Integer, Spectrobe> spectrobeIdMap = new HashMap<>();
    private static int nextEntityId;

    public static SpectrobesWorldData get(ServerWorld world) {
        DimensionSavedDataManager storage = world.getSavedData();
        Supplier<SpectrobesWorldData> sup = new SpectrobesWorldData();
        SpectrobesWorldData saver = storage.getOrCreate(sup, SpectrobesMod.MOD_ID);

        if (saver == null)
        {
            saver = new SpectrobesWorldData();
            storage.set(saver);
        }
        return saver;
    }

    public SpectrobesWorldData() {
        super(SpectrobesMod.MOD_ID);
        nextEntityId = 0;
    }

    public SpectrobesWorldData(String key) {
        this();
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

    public static void AddSpectrobe(int spectrobeId, Spectrobe instance) {
        spectrobeIdMap.put(spectrobeId, instance);
    }

    public static Spectrobe GetSpectrobe(int spectrobeId) {
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
                SpectrobesMod.LOGGER.debug("Found my data: " + saver.data.get("SpectrobesData"));
                ListNBT spectrobeData = (ListNBT) saver.data.get("SpectrobesData");
                int id = 0;
                for (INBT nbt : spectrobeData) {
                    id++;
                    spectrobeIdMap.put(id, SpectrobeUtils.readFromNbt((CompoundNBT) nbt));
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
            ListNBT spectrobesData = new ListNBT();
            if(spectrobeIdMap.keySet().size() > 0) {
                for (int id : spectrobeIdMap.keySet()) {
                    spectrobesData.add(id, spectrobeIdMap.get(id).write());
                }
            }
            myData.put("SpectrobesData", spectrobesData);
            saver.data = myData;
            saver.markDirty();
            SpectrobesMod.LOGGER.debug("Put my data in!");
        }
    }
}
