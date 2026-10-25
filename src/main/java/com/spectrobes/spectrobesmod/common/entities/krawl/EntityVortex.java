package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.VortexForageGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.VortexMigrateGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.SpawnWaveGoal;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.data.KrawlRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import com.spectrobes.spectrobesmod.common.config.KrawlConfig;
import com.spectrobes.spectrobesmod.common.krawl.KrawlInfectionManager;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EntityVortex extends EntityKrawl {
    private static final EntityDataAccessor<Integer> WAVES_REMAINING =
            SynchedEntityData.defineId(EntityVortex.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> AGE_IN_TICKS =
            SynchedEntityData.defineId(EntityVortex.class, EntityDataSerializers.INT);

    private static final RawAnimation SPIN_ANIMATION =
            RawAnimation.begin().thenLoop("animation.vortex.spin");

    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);
    private final List<EntityKrawl> children = new ArrayList<>();
    private boolean waveInProgress;

    /** Biomass gathered by consuming mobs, carried to a nest. */
    private double biomass;

    public EntityVortex(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AttackSpectrobeMasterGoal(this, true, true));
        this.goalSelector.addGoal(0, new AttackSpectrobeGoal(this, true, true));
        this.goalSelector.addGoal(1, new SpawnWaveGoal(this));
        this.goalSelector.addGoal(2, new VortexMigrateGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new VortexForageGoal(this));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.5D));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(WAVES_REMAINING, calculateKrawlWaves());
        builder.define(AGE_IN_TICKS, 0);
    }

    /** Vortexes carrying biomass stay loaded so the biomass is not lost; empty ones despawn like other mobs. */
    @Override
    public boolean requiresCustomPersistence() {
        return this.biomass > 0 || super.requiresCustomPersistence();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isOnFire()) {
            this.remove(RemovalReason.KILLED);
            return;
        }

        this.entityData.set(AGE_IN_TICKS, this.entityData.get(AGE_IN_TICKS) + 1);
    }

    public int getAgeTicks() {
        return this.entityData.get(AGE_IN_TICKS);
    }

    /**
     * Returns the vortex age in Minecraft days.
     */
    public float getAge() {
        return getAgeTicks() / 24000.0F;
    }

    // ---------------------------------------------------------------- biomass and nests

    public double getBiomass() {
        return this.biomass;
    }

    public void addBiomass(double amount) {
        this.biomass += Math.max(0, amount);
    }

    public boolean isAged() {
        return getAgeTicks() >= KrawlConfig.VORTEX_MAX_AGE_TICKS.get();
    }

    /** Ready to head for a nest: full of biomass, or old enough. */
    public boolean isReadyToMerge() {
        return (this.biomass >= KrawlConfig.VORTEX_MERGE_THRESHOLD.get() || isAged());
    }

    /** What this vortex is worth to a nest when it merges. Aged vortexes are worth at least a fixed amount. */
    public double getMergeValue() {
        return isAged() ? Math.max(this.biomass, KrawlConfig.VORTEX_AGED_BIOMASS_VALUE.get()) : this.biomass;
    }

    /** Merges into a nest: deposits its biomass and disappears. */
    public void mergeInto(ServerLevel level, com.spectrobes.spectrobesmod.common.save_data.KrawlNest nest) {
        KrawlInfectionManager.depositBiomass(level, nest, getMergeValue());
        KrawlInfectionManager.puff(level, this);
        this.biomass = 0;
        this.discard();
    }

    @Override
    public boolean isVortex() {
        return true;
    }

    public List<EntityKrawl> getKrawlWave() {
        return this.children;
    }

    /**
     * The vortex cannot be seen once it has spawned krawl.
     */
    @Override
    public boolean isInvisible() {
        return !this.children.isEmpty();
    }

    /**
     * The vortex should only be killed by the intended magic kill path.
     */
    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (source.is(DamageTypes.MAGIC)) {
            return super.isInvulnerableTo(source);
        }

        return true;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            MobSpawnType reason,
            @Nullable SpawnGroupData spawnData
    ) {
        this.entityData.set(WAVES_REMAINING, calculateKrawlWaves());
        setNatureByBiome();

        return super.finalizeSpawn(level, difficulty, reason, spawnData);
    }

    private void setNatureByBiome() {
        List<SpectrobeProperties.Nature> possibleNatures = new ArrayList<>();
        Biome biome = this.level().getBiome(blockPosition()).value();
        Biome.Precipitation precipitation = biome.getPrecipitationAt(blockPosition());

        if (precipitation == Biome.Precipitation.RAIN || precipitation == Biome.Precipitation.SNOW) {
            possibleNatures.add(SpectrobeProperties.Nature.FLASH);
        }

        if (biome.getBaseTemperature() >= 0.5F
                || precipitation == Biome.Precipitation.NONE
                || biome.warmEnoughToRain(blockPosition())) {
            possibleNatures.add(SpectrobeProperties.Nature.CORONA);
        }

        if (!biome.getGenerationSettings().getFlowerFeatures().isEmpty()) {
            possibleNatures.add(SpectrobeProperties.Nature.AURORA);
        }

        possibleNatures.add(SpectrobeProperties.Nature.OTHER);

        SpectrobeProperties.Nature nature = possibleNatures.get(this.random.nextInt(possibleNatures.size()));
        this.krawlProperties.setNature(nature);
    }

    private int calculateKrawlWaves() {
        RandomSource random = this.level() != null ? this.level().getRandom() : RandomSource.create();
        return random.nextInt(3) + 1;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }

    @Override
    public PlayState moveController(AnimationState<EntityKrawl> event) {
        event.setAnimation(SPIN_ANIMATION);
        return PlayState.CONTINUE;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Vortex_Properties.copy();
    }

    @Override
    public void die(DamageSource source) {
        if (!source.is(DamageTypes.MAGIC)) {
            return;
        }

        if (!this.level().isClientSide()) {
            dropMineralReward();
        }

        super.die(source);
    }

    private void dropMineralReward() {
        RandomSource random = this.level().getRandom();

        int rarityInt = random.nextInt(10);
        Mineral.MineralRarity rarity;

        switch (rarityInt) {
            case 9 -> rarity = Mineral.MineralRarity.Rare;
            case 8, 7, 6 -> rarity = Mineral.MineralRarity.Uncommon;
            default -> rarity = Mineral.MineralRarity.Common;
        }

        ItemStack mineralStack = SpectrobesMineralsRegistry.getRandomMineral(rarity);

        if (rarity != Mineral.MineralRarity.Rare) {
            int mineralCount = random.nextInt(3);
            mineralStack.grow(mineralCount);
        }

        ItemEntity itemEntity = new ItemEntity(
                this.level(),
                this.getX() + 0.5D,
                this.getY() + 1.0D,
                this.getZ() + 0.5D,
                mineralStack
        );

        itemEntity.setDefaultPickUpDelay();
        this.level().addFreshEntity(itemEntity);
    }

    public int getWaves() {
        return this.entityData.get(WAVES_REMAINING);
    }

    public boolean isWaveInProgress() {
        return this.waveInProgress;
    }

    public void setWaveInProgress(boolean waveInProgress) {
        this.waveInProgress = waveInProgress;
    }

    /**
     * Drops dead krawl from the current wave. When a wave in progress is wiped out, counts it as beaten
     * and returns true.
     */
    public boolean validateWave() {
        this.children.removeIf(entityKrawl -> !entityKrawl.isAlive() || entityKrawl.getHealth() <= 0.0F);

        if (this.waveInProgress && this.children.isEmpty()) {
            this.waveInProgress = false;
            this.entityData.set(WAVES_REMAINING, getWaves() - 1);
            return true;
        }

        return false;
    }

    /** All waves beaten: the vortex breaks apart and drops its reward. */
    public void onWavesDefeated() {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        dropMineralReward();

        KrawlInfectionManager.puff(serverLevel, this);
        this.biomass = 0;
        this.remove(RemovalReason.KILLED);
    }

    public void addKrawl(EntityKrawl entityKrawl) {
        this.children.add(entityKrawl);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putInt("waves_remaining", getWaves());
        compound.putInt("age_ticks", getAgeTicks());
        compound.putDouble("biomass", this.biomass);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if(compound.contains("waves_remaining")) {
            this.entityData.set(WAVES_REMAINING, compound.getInt("waves_remaining"));
        }

        this.entityData.set(AGE_IN_TICKS, compound.getInt("age_ticks"));
        this.biomass = compound.getDouble("biomass");
    }
}