package com.spectrobes.spectrobesmod.common.save_data;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SpectrobesWorldSaveData extends WorldSavedData {

    public static final String name = SpectrobesInfo.MOD_ID + "_data";

    private final List<KrawlNest> nests = new ArrayList<>();

    public SpectrobesWorldSaveData() { super(name); }

    //remember to call data.setDirty() so it knows to update and sync.
    public static SpectrobesWorldSaveData getWorldData(ServerWorld world) {
        return world.getDataStorage().computeIfAbsent(SpectrobesWorldSaveData::new, SpectrobesWorldSaveData.name);
    }

    public void addNest(KrawlNest nest) {
        nests.add(nest);
        setDirty();
    }

    public boolean canSpawnNest(BlockPos position) {
        for (KrawlNest krawlNest : nests) {
            if (position.closerThan(krawlNest.position, 1000)) {
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
            if (position.closerThan(krawlNest.position, 1000)) {
                return krawlNest;
            }
        }
        return null;
    }

    @Override
    public void load(CompoundNBT nbt) {
        ListNBT nbtNestList = ((ListNBT) Objects.requireNonNull(nbt.get("nests")));
        for (INBT inbt : nbtNestList) {
            KrawlNest nest = new KrawlNest();
            nest.deserializeNBT(inbt);
            nests.add(nest);
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        ListNBT nbtList = new ListNBT();
        nests.forEach(krawlNest -> nbtList.add(krawlNest.serializeNBT()));
        nbt.put("nests", nbtList);
        return nbt;
    }
}
