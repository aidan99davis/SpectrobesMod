package com.spectrobes.spectrobesmod.common.save_data;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.UUID;

public class KrawlNest implements INBTSerializable<CompoundTag> {
    public BlockPos position;
    public String dimension;

    public int vortex_absorbed;

    /**
     * 0 = dormant / dead
     * 1 = young nest
     * 2 = mature nest
     * 3 = hive nest
     */
    public int stage;

    public boolean alive;

    public int ageTicks;
    public int biomass;
    public int radius;

    public int growthCooldown;
    public int spawnCooldown;

    public UUID xellesUuid;

    public KrawlNest() {
        this.position = BlockPos.ZERO;
        this.dimension = "";
        this.stage = 0;
        this.vortex_absorbed = 0;
        this.alive = false;

        this.ageTicks = 0;
        this.biomass = 0;
        this.radius = 0;
        this.growthCooldown = 0;
        this.spawnCooldown = 0;
        this.xellesUuid = null;
    }

    public KrawlNest(BlockPos position, String dimension, UUID xellesUuid) {
        this.position = position;
        this.dimension = dimension;

        this.stage = 1;
        this.vortex_absorbed = 0;
        this.alive = true;

        this.ageTicks = 0;
        this.biomass = 100;
        this.radius = 12;
        this.growthCooldown = 200;
        this.spawnCooldown = 600;
        this.xellesUuid = xellesUuid;
    }

    public void tickAge() {
        if (!alive) {
            return;
        }

        ageTicks++;
        recalculateStageAndRadius();
    }

    public void absorbVortexes(int vortexes) {
        if (vortexes <= 0 || !alive) {
            return;
        }

        this.vortex_absorbed += vortexes;
        this.biomass += vortexes * 40;

        recalculateStageAndRadius();
    }

    public void addBiomass(int amount) {
        if (amount <= 0 || !alive) {
            return;
        }

        this.biomass += amount;
        recalculateStageAndRadius();
    }

    public void removeBiomass(int amount) {
        if (amount <= 0) {
            return;
        }

        this.biomass = Math.max(0, this.biomass - amount);

        if (this.biomass <= 0) {
            setDead();
        } else {
            recalculateStageAndRadius();
        }
    }

    public int getAbsorbedVortexes() {
        return this.vortex_absorbed;
    }

    public int getRadius() {
        return radius;
    }

    public int getRadiusSqr() {
        return radius * radius;
    }

    public boolean contains(BlockPos pos) {
        if (position == null || pos == null) {
            return false;
        }

        return position.distSqr(pos) <= getRadiusSqr();
    }

    public boolean isInDimension(String dimension) {
        return this.dimension != null && this.dimension.equals(dimension);
    }

    public void setStage(int stage) {
        this.stage = clamp(stage, 0, 3);
        recalculateStageAndRadius();
    }

    public void setXellesUuid(UUID xellesUuid) {
        this.xellesUuid = xellesUuid;
    }

    public boolean hasXellesUuid() {
        return xellesUuid != null;
    }

    public boolean shouldGrowThisTick() {
        if (!alive) {
            return false;
        }

        if (growthCooldown > 0) {
            growthCooldown--;
            return false;
        }

        return true;
    }

    public void resetGrowthCooldown() {
        this.growthCooldown = switch (stage) {
            case 1 -> 240;
            case 2 -> 160;
            case 3 -> 100;
            default -> 300;
        };
    }

    public boolean shouldSpawnThisTick() {
        if (!alive) {
            return false;
        }

        if (spawnCooldown > 0) {
            spawnCooldown--;
            return false;
        }

        return true;
    }

    public void resetSpawnCooldown() {
        this.spawnCooldown = switch (stage) {
            case 1 -> 900;
            case 2 -> 600;
            case 3 -> 360;
            default -> 1200;
        };
    }

    public int getGrowthAttempts() {
        return switch (stage) {
            case 1 -> 4;
            case 2 -> 8;
            case 3 -> 14;
            default -> 0;
        };
    }

    public int getSpawnAttempts() {
        return switch (stage) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 4;
            default -> 0;
        };
    }

    public float getInfectionStrengthAt(BlockPos pos) {
        if (!alive || position == null || pos == null || radius <= 0) {
            return 0.0F;
        }

        double distance = Math.sqrt(position.distSqr(pos));

        if (distance >= radius) {
            return 0.0F;
        }

        double falloff = 1.0D - distance / radius;
        return (float) ((stage + 1) * falloff);
    }

    public void setDead() {
        this.alive = false;
        this.stage = 0;
        this.radius = 0;
        this.growthCooldown = 0;
        this.spawnCooldown = 0;
        this.xellesUuid = null;
    }

    public boolean isAlive() {
        return alive;
    }

    private void recalculateStageAndRadius() {
        if (!alive) {
            this.stage = 0;
            this.radius = 0;
            return;
        }

        int calculatedStage = 1;

        if (biomass >= 350 || vortex_absorbed >= 5 || ageTicks >= 24000 * 3) {
            calculatedStage = 2;
        }

        if (biomass >= 900 || vortex_absorbed >= 12 || ageTicks >= 24000 * 7) {
            calculatedStage = 3;
        }

        this.stage = Math.max(this.stage, calculatedStage);

        int calculatedRadius = switch (stage) {
            case 1 -> 12 + biomass / 80;
            case 2 -> 24 + biomass / 70;
            case 3 -> 40 + biomass / 60;
            default -> 0;
        };

        this.radius = clamp(calculatedRadius, 0, 96);
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbtObj = new CompoundTag();

        BlockPos safePos = position == null ? BlockPos.ZERO : position;

        nbtObj.putString("dimension", dimension == null ? "" : dimension);
        nbtObj.putInt("stage", stage);
        nbtObj.putInt("position_x", safePos.getX());
        nbtObj.putInt("position_y", safePos.getY());
        nbtObj.putInt("position_z", safePos.getZ());
        nbtObj.putInt("vortex_absorbed", vortex_absorbed);
        nbtObj.putBoolean("alive", alive);

        nbtObj.putInt("age_ticks", ageTicks);
        nbtObj.putInt("biomass", biomass);
        nbtObj.putInt("radius", radius);
        nbtObj.putInt("growth_cooldown", growthCooldown);
        nbtObj.putInt("spawn_cooldown", spawnCooldown);

        if (xellesUuid != null) {
            nbtObj.putString("xelles_uuid", xellesUuid.toString());
        }

        return nbtObj;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        dimension = nbt.getString("dimension");
        stage = nbt.getInt("stage");
        vortex_absorbed = nbt.getInt("vortex_absorbed");
        alive = nbt.getBoolean("alive");

        int position_x = nbt.getInt("position_x");
        int position_y = nbt.getInt("position_y");
        int position_z = nbt.getInt("position_z");
        position = new BlockPos(position_x, position_y, position_z);

        ageTicks = nbt.contains("age_ticks") ? nbt.getInt("age_ticks") : 0;
        biomass = nbt.contains("biomass") ? nbt.getInt("biomass") : getFallbackBiomass();
        radius = nbt.contains("radius") ? nbt.getInt("radius") : getFallbackRadius();
        growthCooldown = nbt.contains("growth_cooldown") ? nbt.getInt("growth_cooldown") : 200;
        spawnCooldown = nbt.contains("spawn_cooldown") ? nbt.getInt("spawn_cooldown") : 600;

        xellesUuid = null;

        if (nbt.contains("xelles_uuid")) {
            try {
                xellesUuid = UUID.fromString(nbt.getString("xelles_uuid"));
            } catch (IllegalArgumentException ignored) {
                xellesUuid = null;
            }
        }

        recalculateStageAndRadius();
    }

    private int getFallbackBiomass() {
        if (!alive) {
            return 0;
        }

        return 100 + vortex_absorbed * 40;
    }

    private int getFallbackRadius() {
        if (!alive) {
            return 0;
        }

        return switch (stage) {
            case 1 -> 12;
            case 2 -> 24;
            case 3 -> 40;
            default -> 0;
        };
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}