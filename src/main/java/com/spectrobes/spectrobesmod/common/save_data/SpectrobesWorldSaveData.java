package com.spectrobes.spectrobesmod.common.save_data;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SpectrobesWorldSaveData extends SavedData {

    public static final String name = SpectrobesInfo.MOD_ID + "_data";

    private final List<KrawlNest> nests = new ArrayList<>();

    public SpectrobesWorldSaveData() { super(); }

    //remember to call data.setDirty() so it knows to update and sync.
    public static SpectrobesWorldSaveData getWorldData(ServerLevel world) {
        return world.getDataStorage().computeIfAbsent(SpectrobesWorldSaveData::load, SpectrobesWorldSaveData::new, SpectrobesWorldSaveData.name);
    }

    public void addNest(KrawlNest nest) {
        nests.add(nest);
        setDirty();
    }

    public boolean canSpawnNest(BlockPos position) {
        for (KrawlNest krawlNest : nests) {
            if (position.closerThan(krawlNest.position, 2000)) {
                return false;
            }
        }
        return true;
    }

    public List<KrawlNest> getNests() { return nests; }
    public List<KrawlNest> getNestsAlive() { return nests.stream().filter(KrawlNest::isAlive).collect(Collectors.toList()); }
    public List<KrawlNest> getNestsDead() { return nests.stream().filter(krawlNest -> !krawlNest.isAlive()).collect(Collectors.toList()); }

    public KrawlNest getNest(BlockPos position) {
        for (KrawlNest krawlNest : nests) {
            if (position.closerThan(krawlNest.position, 2000)) {
                return krawlNest;
            }
        }
        return null;
    }

    public static SpectrobesWorldSaveData load(CompoundTag nbt) {
        SpectrobesWorldSaveData data = new SpectrobesWorldSaveData();
        ListTag nbtNestList = ((ListTag) Objects.requireNonNull(nbt.get("nests")));
        for (Tag inbt : nbtNestList) {
            KrawlNest nest = new KrawlNest();
            nest.deserializeNBT(inbt);
            data.addNest(nest);
        }
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag nbt) {
        ListTag nbtList = new ListTag();
        nests.forEach(krawlNest -> nbtList.add(krawlNest.serializeNBT()));
        nbt.put("nests", nbtList);
        return nbt;
    }
}
