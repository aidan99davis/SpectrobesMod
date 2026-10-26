package com.spectrobes.spectrobesmod.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Server config for the krawl nest infection system (spectrobesmod-server.toml, per world).
 */
public final class KrawlConfig {
    public static final ModConfigSpec SPEC;

    // Vortexes
    public static final ModConfigSpec.DoubleValue VORTEX_BIOMASS_PER_HEALTH;
    public static final ModConfigSpec.IntValue VORTEX_MERGE_THRESHOLD;
    public static final ModConfigSpec.IntValue VORTEX_MAX_AGE_TICKS;
    public static final ModConfigSpec.IntValue VORTEX_AGED_BIOMASS_VALUE;
    public static final ModConfigSpec.IntValue VORTEX_FORAGE_RANGE;
    public static final ModConfigSpec.IntValue VORTEX_STUCK_TICKS;
    public static final ModConfigSpec.IntValue VORTEX_UNSEEN_DISTANCE;

    // Nests
    public static final ModConfigSpec.IntValue NEST_FOUNDING_BIOMASS;
    public static final ModConfigSpec.IntValue NEST_RANGE;
    public static final ModConfigSpec.IntValue NEST_TICK_INTERVAL;
    public static final ModConfigSpec.IntValue NEST_BLOCKS_PER_TICK;
    public static final ModConfigSpec.DoubleValue NEST_BIOMASS_PER_CARVED_BLOCK;
    public static final ModConfigSpec.DoubleValue NEST_BIOMASS_PER_INFECTED_BLOCK;
    public static final ModConfigSpec.IntValue NEST_INFECTION_RADIUS_BASE;
    public static final ModConfigSpec.IntValue NEST_INFECTION_RADIUS_MAX;
    public static final ModConfigSpec.IntValue NEST_GRAND_CHAMBER_Y;
    public static final ModConfigSpec.IntValue NEST_TIER_SPACING;
    public static final ModConfigSpec.IntValue NEST_BOSS_BIOMASS_COST;

    // Mini xelles
    public static final ModConfigSpec.IntValue MINI_XELLES_SUMMON_COOLDOWN;
    public static final ModConfigSpec.IntValue MINI_XELLES_WAVES_PER_LIFE;
    public static final ModConfigSpec.IntValue MINI_XELLES_WAVE_RESET_TICKS;
    public static final ModConfigSpec.IntValue MINI_XELLES_REVIVE_TICKS;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("vortex");
        VORTEX_BIOMASS_PER_HEALTH = builder
                .comment("Biomass a vortex gains per point of max health of a mob it consumes.")
                .defineInRange("biomassPerHealth", 1.0D, 0.0D, 100.0D);
        VORTEX_MERGE_THRESHOLD = builder
                .comment("Biomass at which a vortex stops foraging and heads for a nest.")
                .defineInRange("mergeThreshold", 100, 1, 100000);
        VORTEX_MAX_AGE_TICKS = builder
                .comment("Age in ticks after which a vortex migrates to a nest regardless of biomass.")
                .defineInRange("maxAgeTicks", 48000, 1200, Integer.MAX_VALUE);
        VORTEX_AGED_BIOMASS_VALUE = builder
                .comment("Biomass an aged vortex is worth when it merges (used if larger than what it gathered).")
                .defineInRange("agedBiomassValue", 60, 0, 100000);
        VORTEX_FORAGE_RANGE = builder
                .comment("How far a vortex looks for mobs to consume.")
                .defineInRange("forageRange", 16, 2, 64);
        VORTEX_STUCK_TICKS = builder
                .comment("Ticks without progress toward its nest before a vortex merges remotely (when unseen).")
                .defineInRange("stuckTicks", 600, 20, 72000);
        VORTEX_UNSEEN_DISTANCE = builder
                .comment("A vortex further than this from every player, or out of their line of sight, counts as unseen.")
                .defineInRange("unseenDistance", 48, 8, 256);
        builder.pop();

        builder.push("nest");
        NEST_FOUNDING_BIOMASS = builder
                .comment("Biomass a vortex needs to found a new nest when none is in range.")
                .defineInRange("foundingBiomass", 100, 1, 100000);
        NEST_RANGE = builder
                .comment("Vortexes join any nest (active or inactive) within this range; new nests cannot form closer than this.")
                .defineInRange("range", 384, 32, 10000);
        NEST_TICK_INTERVAL = builder
                .comment("Ticks between nest updates.")
                .defineInRange("tickInterval", 40, 1, 1200);
        NEST_BLOCKS_PER_TICK = builder
                .comment("Maximum blocks a nest changes per nest update.")
                .defineInRange("blocksPerTick", 24, 1, 4096);
        NEST_BIOMASS_PER_CARVED_BLOCK = builder
                .comment("Biomass spent per block carved or lined while building tunnels and chambers.")
                .defineInRange("biomassPerCarvedBlock", 0.1D, 0.0D, 100.0D);
        NEST_BIOMASS_PER_INFECTED_BLOCK = builder
                .comment("Biomass spent per natural block converted by surface/wall infection.")
                .defineInRange("biomassPerInfectedBlock", 0.5D, 0.0D, 100.0D);
        NEST_INFECTION_RADIUS_BASE = builder
                .comment("Infection radius around nest chambers when a nest is founded.")
                .defineInRange("infectionRadiusBase", 6, 1, 256);
        NEST_INFECTION_RADIUS_MAX = builder
                .comment("Largest infection radius around nest chambers.")
                .defineInRange("infectionRadiusMax", 32, 1, 256);
        NEST_GRAND_CHAMBER_Y = builder
                .comment("Floor height of the grand xelles chamber (ancient city depth by default).")
                .defineInRange("grandChamberY", -45, -64, 300);
        NEST_TIER_SPACING = builder
                .comment("Approximate vertical distance between nest chambers.")
                .defineInRange("tierSpacing", 30, 12, 128);
        NEST_BOSS_BIOMASS_COST = builder
                .comment("Biomass a nest must spend to regrow a defeated Xelles boss after reactivating.")
                .defineInRange("bossBiomassCost", 1000, 0, 1000000);
        builder.pop();

        builder.push("mini_xelles");
        MINI_XELLES_SUMMON_COOLDOWN = builder
                .comment("Ticks between krawl waves a mini xelles summons.")
                .defineInRange("summonCooldown", 600, 20, 72000);
        MINI_XELLES_WAVES_PER_LIFE = builder
                .comment("Krawl waves that must be wiped out to kill a mini xelles.")
                .defineInRange("wavesPerLife", 3, 1, 100);
        MINI_XELLES_WAVE_RESET_TICKS = builder
                .comment("Ticks a living mini xelles must go untriggered before its lost waves are restored.")
                .defineInRange("waveResetTicks", 24000, 20, Integer.MAX_VALUE);
        MINI_XELLES_REVIVE_TICKS = builder
                .comment("Ticks a nest must stay active before a dead mini xelles in it revives.")
                .defineInRange("reviveTicks", 72000, 20, Integer.MAX_VALUE);
        builder.pop();

        SPEC = builder.build();
    }

    private KrawlConfig() {
    }
}
