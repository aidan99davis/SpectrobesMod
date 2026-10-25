package com.spectrobes.spectrobesmod.common.save_data;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Per-dimension save data. Each level has its own instance, so nests are implicitly per dimension.
 */
public class SpectrobesWorldSaveData extends SavedData {

    public static final String NAME = SpectrobesInfo.MOD_ID + "_data";

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

    public List<KrawlNest> getNests() {
        return nests;
    }

    @Nullable
    public KrawlNest getNest(@Nullable UUID id) {
        if (id == null) {
            return null;
        }

        for (KrawlNest nest : nests) {
            if (nest.id.equals(id)) {
                return nest;
            }
        }

        return null;
    }

    /** Nearest nest whose heart is within {@code range} blocks of {@code pos}. */
    @Nullable
    public KrawlNest findNearest(BlockPos pos, double range, Predicate<KrawlNest> filter) {
        KrawlNest nearest = null;
        double nearestDist = range * range;

        for (KrawlNest nest : nests) {
            if (!filter.test(nest)) {
                continue;
            }

            double dist = nest.heart.distSqr(pos);

            if (dist <= nearestDist) {
                nearest = nest;
                nearestDist = dist;
            }
        }

        return nearest;
    }

    public static SpectrobesWorldSaveData load(CompoundTag tag, HolderLookup.Provider provider) {
        SpectrobesWorldSaveData data = new SpectrobesWorldSaveData();

        ListTag nestList = tag.getList("nests", Tag.TAG_COMPOUND);
        int legacy = 0;

        for (Tag nestTag : nestList) {
            CompoundTag compound = (CompoundTag) nestTag;

            if (KrawlNest.isLegacyTag(compound)) {
                legacy++;
                continue;
            }

            KrawlNest nest = new KrawlNest();
            nest.deserializeNBT(provider, compound);
            data.nests.add(nest);
        }

        if (legacy > 0) {
            SpectrobesInfo.LOGGER.info("Discarded {} krawl nest(s) from the old infection system", legacy);
            data.setDirty();
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
