package com.spectrobes.spectrobesmod.common.save_data;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpectrobesWorldSaveData extends SavedData {

    public static final String NAME = SpectrobesInfo.MOD_ID + "_data";

    // Kept for compatibility if other code still references SpectrobesWorldSaveData.name.
    public static final String name = NAME;

    private static final double NEST_MIN_DISTANCE = 2000.0D;

    private static final SavedData.Factory<SpectrobesWorldSaveData> FACTORY =
            new SavedData.Factory<>(SpectrobesWorldSaveData::new, SpectrobesWorldSaveData::load);

    private final List<KrawlNest> nests = new ArrayList<>();

    public SpectrobesWorldSaveData() {
        super();
    }

    // Remember to call setDirty() whenever this data changes.
    public static SpectrobesWorldSaveData getWorldData(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(FACTORY, NAME);
    }

    public void addNest(KrawlNest nest) {
        nests.add(nest);
        setDirty();
    }

    public boolean canSpawnNest(BlockPos position) {
        for (KrawlNest krawlNest : nests) {
            if (position.closerThan(krawlNest.position, NEST_MIN_DISTANCE)) {
                return false;
            }
        }

        return true;
    }

    public List<KrawlNest> getNests() {
        return nests;
    }

    public List<KrawlNest> getNestsAlive() {
        return nests.stream()
                .filter(KrawlNest::isAlive)
                .collect(Collectors.toList());
    }

    public List<KrawlNest> getNestsDead() {
        return nests.stream()
                .filter(krawlNest -> !krawlNest.isAlive())
                .collect(Collectors.toList());
    }

    public KrawlNest getNest(BlockPos position) {
        for (KrawlNest krawlNest : nests) {
            if (position.closerThan(krawlNest.position, NEST_MIN_DISTANCE)) {
                return krawlNest;
            }
        }

        return null;
    }

    public static SpectrobesWorldSaveData load(CompoundTag tag, HolderLookup.Provider provider) {
        SpectrobesWorldSaveData data = new SpectrobesWorldSaveData();

        ListTag nestList = tag.getList("nests", Tag.TAG_COMPOUND);

        for (Tag nestTag : nestList) {
            KrawlNest nest = new KrawlNest();
            nest.deserializeNBT(provider, nestTag);
            data.nests.add(nest);
        }

        return data;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        ListTag nestList = new ListTag();

        for (KrawlNest krawlNest : nests) {
            nestList.add(krawlNest.serializeNBT(provider));
        }

        tag.put("nests", nestList);
        return tag;
    }
}