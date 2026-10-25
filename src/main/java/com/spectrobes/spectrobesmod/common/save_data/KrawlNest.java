package com.spectrobes.spectrobesmod.common.save_data;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.common.util.INBTSerializable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * A krawl nest tracked in world save data. The nest is the "mycelium network": it owns its mini xelles,
 * the chambers it has carved, the construction job in progress and the Xelles boss state.
 */
public class KrawlNest implements INBTSerializable<CompoundTag> {

    public enum State { ACTIVE, INACTIVE }

    public enum BossState { NONE, ALIVE, DEFEATED }

    public UUID id;
    public BlockPos heart;
    public State state;
    public double biomass;
    public double totalBiomass;
    public long createdTime;

    public final Map<BlockPos, MiniXellesRecord> miniXelles = new LinkedHashMap<>();
    public final List<Chamber> chambers = new ArrayList<>();

    /** Deepest tier whose access shaft has been finished (0 = none yet). */
    public int shaftTier;
    /** Floor centre of the chamber the most recently finished shaft leads to. */
    @Nullable
    public BlockPos nextChamberCenter;
    /** Where that shaft's corridor meets the chamber's edge. */
    @Nullable
    public BlockPos nextChamberEntry;
    @Nullable
    public NestJob job;

    public BossState bossState = BossState.NONE;
    @Nullable
    public UUID bossUuid;

    public KrawlNest() {
        this.id = UUID.randomUUID();
        this.heart = BlockPos.ZERO;
        this.state = State.INACTIVE;
    }

    public KrawlNest(BlockPos heart, long gameTime) {
        this.id = UUID.randomUUID();
        this.heart = heart.immutable();
        this.state = State.ACTIVE;
        this.createdTime = gameTime;
    }

    public boolean isActive() {
        return state == State.ACTIVE;
    }

    public void addBiomass(double amount) {
        if (amount <= 0) {
            return;
        }

        biomass += amount;
        totalBiomass += amount;
    }

    public boolean trySpend(double amount) {
        if (biomass < amount) {
            return false;
        }

        biomass -= amount;
        return true;
    }

    public int getDeepestChamberTier() {
        int deepest = 0;

        for (Chamber chamber : chambers) {
            deepest = Math.max(deepest, chamber.tier);
        }

        return deepest;
    }

    @Nullable
    public Chamber getChamber(int tier) {
        for (Chamber chamber : chambers) {
            if (chamber.tier == tier) {
                return chamber;
            }
        }

        return null;
    }

    @Nullable
    public Chamber getGrandChamber() {
        for (Chamber chamber : chambers) {
            if (chamber.grand) {
                return chamber;
            }
        }

        return null;
    }

    public boolean allMiniXellesDead() {
        for (MiniXellesRecord record : miniXelles.values()) {
            if (!record.dead) {
                return false;
            }
        }

        return true;
    }

    public int aliveMiniXelles() {
        int alive = 0;

        for (MiniXellesRecord record : miniXelles.values()) {
            if (!record.dead) {
                alive++;
            }
        }

        return alive;
    }

    /** Positions infection and protection radiate from: the heart plus every chamber centre. */
    public List<BlockPos> getAnchors() {
        List<BlockPos> anchors = new ArrayList<>();
        anchors.add(heart);

        for (Chamber chamber : chambers) {
            anchors.add(chamber.center);
        }

        return anchors;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();

        tag.putUUID("id", id);
        tag.put("heart", NbtUtils.writeBlockPos(heart));
        tag.putString("state", state.name());
        tag.putDouble("biomass", biomass);
        tag.putDouble("total_biomass", totalBiomass);
        tag.putLong("created", createdTime);
        tag.putInt("shaft_tier", shaftTier);

        if (nextChamberCenter != null) {
            tag.put("next_chamber_center", NbtUtils.writeBlockPos(nextChamberCenter));
        }

        if (nextChamberEntry != null) {
            tag.put("next_chamber_entry", NbtUtils.writeBlockPos(nextChamberEntry));
        }

        ListTag miniXellesList = new ListTag();
        for (MiniXellesRecord record : miniXelles.values()) {
            miniXellesList.add(record.save());
        }
        tag.put("mini_xelles", miniXellesList);

        ListTag chamberList = new ListTag();
        for (Chamber chamber : chambers) {
            chamberList.add(chamber.save());
        }
        tag.put("chambers", chamberList);

        if (job != null) {
            tag.put("job", job.save());
        }

        tag.putString("boss_state", bossState.name());
        if (bossUuid != null) {
            tag.putUUID("boss_uuid", bossUuid);
        }

        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        id = tag.getUUID("id");
        heart = NbtUtils.readBlockPos(tag, "heart").orElse(BlockPos.ZERO);
        state = parseEnum(State.class, tag.getString("state"), State.INACTIVE);
        biomass = tag.getDouble("biomass");
        totalBiomass = tag.getDouble("total_biomass");
        createdTime = tag.getLong("created");
        shaftTier = tag.getInt("shaft_tier");
        nextChamberCenter = NbtUtils.readBlockPos(tag, "next_chamber_center").orElse(null);
        nextChamberEntry = NbtUtils.readBlockPos(tag, "next_chamber_entry").orElse(null);

        miniXelles.clear();
        for (Tag entry : tag.getList("mini_xelles", Tag.TAG_COMPOUND)) {
            MiniXellesRecord record = MiniXellesRecord.load((CompoundTag) entry);
            miniXelles.put(record.pos, record);
        }

        chambers.clear();
        for (Tag entry : tag.getList("chambers", Tag.TAG_COMPOUND)) {
            chambers.add(Chamber.load((CompoundTag) entry));
        }

        job = tag.contains("job", Tag.TAG_COMPOUND) ? NestJob.load(tag.getCompound("job")) : null;

        bossState = parseEnum(BossState.class, tag.getString("boss_state"), BossState.NONE);
        bossUuid = tag.hasUUID("boss_uuid") ? tag.getUUID("boss_uuid") : null;
    }

    /** Nests saved by the old infection system have no id; they are dropped on load. */
    public static boolean isLegacyTag(CompoundTag tag) {
        return !tag.hasUUID("id");
    }

    private static <E extends Enum<E>> E parseEnum(Class<E> type, String name, E fallback) {
        try {
            return Enum.valueOf(type, name);
        } catch (IllegalArgumentException e) {
            return fallback;
        }
    }

    public static class MiniXellesRecord {
        public BlockPos pos;
        public int tier;
        public boolean dead;
        public long reviveAt;

        public MiniXellesRecord(BlockPos pos, int tier) {
            this.pos = pos.immutable();
            this.tier = tier;
        }

        CompoundTag save() {
            CompoundTag tag = new CompoundTag();
            tag.put("pos", NbtUtils.writeBlockPos(pos));
            tag.putInt("tier", tier);
            tag.putBoolean("dead", dead);
            tag.putLong("revive_at", reviveAt);
            return tag;
        }

        static MiniXellesRecord load(CompoundTag tag) {
            MiniXellesRecord record = new MiniXellesRecord(NbtUtils.readBlockPos(tag, "pos").orElse(BlockPos.ZERO), tag.getInt("tier"));
            record.dead = tag.getBoolean("dead");
            record.reviveAt = tag.getLong("revive_at");
            return record;
        }
    }

    /** A carved chamber. {@code center} is the centre of its floor. */
    public static class Chamber {
        public int tier;
        public BlockPos center;
        public int radius;
        public int height;
        public boolean grand;
        /** Where the corridor from the tier above enters; the shaft down leaves from the opposite side. */
        public BlockPos entry;

        public Chamber(int tier, BlockPos center, int radius, int height, boolean grand, BlockPos entry) {
            this.tier = tier;
            this.center = center.immutable();
            this.radius = radius;
            this.height = height;
            this.grand = grand;
            this.entry = entry.immutable();
        }

        CompoundTag save() {
            CompoundTag tag = new CompoundTag();
            tag.putInt("tier", tier);
            tag.put("center", NbtUtils.writeBlockPos(center));
            tag.putInt("radius", radius);
            tag.putInt("height", height);
            tag.putBoolean("grand", grand);
            tag.put("entry", NbtUtils.writeBlockPos(entry));
            return tag;
        }

        static Chamber load(CompoundTag tag) {
            return new Chamber(
                    tag.getInt("tier"),
                    NbtUtils.readBlockPos(tag, "center").orElse(BlockPos.ZERO),
                    tag.getInt("radius"),
                    tag.getInt("height"),
                    tag.getBoolean("grand"),
                    NbtUtils.readBlockPos(tag, "entry").orElse(BlockPos.ZERO));
        }
    }

    /**
     * A construction job. The block operations are regenerated deterministically from these fields, so only
     * the progress index needs saving.
     */
    public static class NestJob {
        public enum Type { SHAFT, CHAMBER }

        public Type type;
        public int tier;
        /** SHAFT: helix axis at the top floor height. CHAMBER: floor centre. */
        public BlockPos origin;
        /** SHAFT: floor height the helix ends at. CHAMBER: unused. */
        public int bottomY;
        /** SHAFT: angle (radians) of the helix's top step around the axis. */
        public float angle;
        /** SHAFT: whether a corridor leads into the top of the helix from the chamber above. */
        public boolean topCorridor;
        /** CHAMBER: radius, height and whether it is the grand chamber. SHAFT: radius of the chamber it leads to. */
        public int radius;
        public int height;
        public boolean grand;
        /** CHAMBER: where the corridor enters, so carving spreads outward from it. SHAFT: start of the top corridor. */
        public BlockPos entry;
        public int progress;

        /** Transient: generated operations. */
        @Nullable
        public transient List<NestOp> ops;

        public static NestJob shaft(int tier, BlockPos axisTop, int bottomY, float angle, int nextRadius,
                                    @Nullable BlockPos corridorStart) {
            NestJob job = new NestJob();
            job.type = Type.SHAFT;
            job.tier = tier;
            job.origin = axisTop.immutable();
            job.bottomY = bottomY;
            job.angle = angle;
            job.radius = nextRadius;
            job.topCorridor = corridorStart != null;
            job.entry = corridorStart != null ? corridorStart.immutable() : axisTop.immutable();
            return job;
        }

        public static NestJob chamber(int tier, BlockPos floorCenter, int radius, int height, boolean grand, BlockPos entry) {
            NestJob job = new NestJob();
            job.type = Type.CHAMBER;
            job.tier = tier;
            job.origin = floorCenter.immutable();
            job.radius = radius;
            job.height = height;
            job.grand = grand;
            job.entry = entry.immutable();
            return job;
        }

        CompoundTag save() {
            CompoundTag tag = new CompoundTag();
            tag.putString("type", type.name());
            tag.putInt("tier", tier);
            tag.put("origin", NbtUtils.writeBlockPos(origin));
            tag.putInt("bottom_y", bottomY);
            tag.putFloat("angle", angle);
            tag.putBoolean("top_corridor", topCorridor);
            tag.putInt("radius", radius);
            tag.putInt("height", height);
            tag.putBoolean("grand", grand);
            tag.put("entry", NbtUtils.writeBlockPos(entry));
            tag.putInt("progress", progress);
            return tag;
        }

        static NestJob load(CompoundTag tag) {
            NestJob job = new NestJob();
            job.type = parseEnum(Type.class, tag.getString("type"), Type.SHAFT);
            job.tier = tag.getInt("tier");
            job.origin = NbtUtils.readBlockPos(tag, "origin").orElse(BlockPos.ZERO);
            job.bottomY = tag.getInt("bottom_y");
            job.angle = tag.getFloat("angle");
            job.topCorridor = tag.getBoolean("top_corridor");
            job.radius = tag.getInt("radius");
            job.height = tag.getInt("height");
            job.grand = tag.getBoolean("grand");
            job.entry = NbtUtils.readBlockPos(tag, "entry").orElse(job.origin);
            job.progress = tag.getInt("progress");
            return job;
        }
    }

    /** One block change in a construction job. */
    public record NestOp(BlockPos pos, Kind kind) {
        public enum Kind { CARVE, FLOOR, WALL }
    }
}
