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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class SpectrobesWorldSaveData extends WorldSavedData {

    public static final String name = SpectrobesInfo.MOD_ID + "_data";

    private final List<KrawlNest> nests = new ArrayList<>();

    public SpectrobesWorldSaveData(String p_i2141_1_) {
        super(p_i2141_1_);
        setDirty();
    }

    //remember to call data.setDirty() so it knows to update.
    public static SpectrobesWorldSaveData getWorldData(ServerWorld world) {
        return world.getDataStorage().computeIfAbsent(SpectrobesWorldSaveData::new, SpectrobesWorldSaveData.name);
    }

    public void addNest(KrawlNest nest) {
        nests.add(nest);
        setDirty();
    }

    public boolean canSpawnNest(BlockPos position) {
        AtomicBoolean tooClose = new AtomicBoolean(false);
        nests.forEach(krawlNest -> {
            SpectrobesInfo.LOGGER.debug("DOOT");
            if(position.closerThan(krawlNest.position, 1000)) {
                SpectrobesInfo.LOGGER.debug("EXISTING XELLE AT: " + krawlNest.position.getX() + ", " + krawlNest.position.getY() + ", " + krawlNest.position.getZ());
                tooClose.set(true);
                return;
            }
        });

        return !tooClose.get();
    }

    public List<KrawlNest> getNests() { return nests; }

    public SpectrobesWorldSaveData() { super(name); }

    @Override
    public void load(CompoundNBT nbt) {
        for (INBT inbt : ((ListNBT) Objects.requireNonNull(nbt.get("nests")))) {
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
