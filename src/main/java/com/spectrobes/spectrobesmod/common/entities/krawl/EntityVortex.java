package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.KrawlVortexFormXellesGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.SpawnWaveGoal;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.biome.Biomes;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityVortex extends EntityKrawl {
    private static final EntityDataAccessor<Integer> WAVES_REMAINING =
            SynchedEntityData.defineId(EntityKrawl.class,
                    EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> AGE_IN_TICKS =
            SynchedEntityData.defineId(EntityKrawl.class,
                    EntityDataSerializers.INT);

    private final List<EntityKrawl> children;

    public EntityVortex(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        children = new ArrayList<>();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AttackSpectrobeMasterGoal(this, true, true));
        this.goalSelector.addGoal(0, new AttackSpectrobeGoal(this, true, true));
        this.goalSelector.addGoal(1, new SpawnWaveGoal(this));
        this.goalSelector.addGoal(1, new KrawlVortexFormXellesGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 0.5d));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(WAVES_REMAINING, calculateKrawlWaves());
        entityData.define(AGE_IN_TICKS, 0);
    }

    @Override
    public boolean isPersistenceRequired() {
        if(!level.isClientSide()) {
            SpectrobesWorldSaveData worldData = (SpectrobesWorldSaveData.getWorldData((ServerLevel) level));
            return worldData.canSpawnNest(blockPosition());
        }
        return super.isPersistenceRequired();
    }

    @Override
    public void checkDespawn() {
        super.checkDespawn();
    }

    @Override
    public void tick() {
        super.tick();

        if(this.isOnFire()) this.remove(RemovalReason.KILLED);

        entityData.set(AGE_IN_TICKS, entityData.get(AGE_IN_TICKS) + 1);
    }

    //returns the vortex's age in days.
    public int getAge() {
        return entityData.get(AGE_IN_TICKS) / 24000;
    }

    @Override
    public boolean isVortex() {
        return true;
    }

    public List<EntityKrawl> getKrawlWave() {
        return children;
    }


    //cant be seen once its spawned krawl.
    @Override
    public boolean isInvisible() {
        return !children.isEmpty();
    }

    //can only be "killed" by defeating all waves of krawl.
    @Override
    public boolean isInvulnerable() {
        return true;
    }



    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        calculateKrawlWaves();
        setNatureByBiome();
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private void setNatureByBiome() {
        List<SpectrobeProperties.Nature> possibleNatures = new ArrayList<>();
        Biome biome = level.getBiome(blockPosition()).value();

        if(biome.getPrecipitation().equals(Biome.Precipitation.RAIN)
                || biome.getPrecipitation().equals(Biome.Precipitation.SNOW)) {
            possibleNatures.add(SpectrobeProperties.Nature.FLASH);
        }
        if(biome.getBaseTemperature() >= 0.5f
                || biome.getPrecipitation().equals(Biome.Precipitation.NONE)
                || biome.warmEnoughToRain(getOnPos())
                || biome.shouldSnowGolemBurn(getOnPos())) {
            possibleNatures.add(SpectrobeProperties.Nature.CORONA);
        }
        if(biome.getGenerationSettings().getFlowerFeatures().size() > 0) {
            possibleNatures.add(SpectrobeProperties.Nature.AURORA);
        }

        possibleNatures.add(SpectrobeProperties.Nature.OTHER);

        SpectrobeProperties.Nature nature = possibleNatures.get(random.nextInt(possibleNatures.size()));
        //TODO: MAKE SURE THIS STILL WORKS

        krawlProperties.setNature(nature);
    }

    private int calculateKrawlWaves() {
        Random random = new Random();

        return random.nextInt(3) + 1;
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.vortex.spin", true));
        return PlayState.CONTINUE;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Vortex_Properties.copy();
    }

    @Override
    public void die(DamageSource source) {
        if(source == DamageSource.MAGIC) {
            super.die(source);
        }
        Random random = new Random();
        int rarityInt = random.nextInt(10);
        Mineral.MineralRarity rarity;

        switch(rarityInt) {
            case 9:
                rarity = Mineral.MineralRarity.Rare;
                break;
            case 8:
            case 7:
            case 6:
                rarity = Mineral.MineralRarity.Uncommon;
                break;
            default:
                rarity = Mineral.MineralRarity.Common;
                break;
        }

        ItemStack mineralStack = SpectrobesMineralsRegistry.getRandomMineral(rarity);
        if(rarity != Mineral.MineralRarity.Rare) {
            int mineralCount = random.nextInt(3);
            mineralStack.grow(mineralCount);
        }

        ItemEntity lvt_10_1_ = new ItemEntity(level,
                this.getX() + 0.5D,
                (this.getY() + 1),
                this.getZ() + 0.5D, mineralStack);
        lvt_10_1_.setDefaultPickUpDelay();
        level.addFreshEntity(lvt_10_1_);
        super.die(source);
    }

    public int getWaves() {
        return entityData.get(WAVES_REMAINING);
    }

    public void validateWave() {
        children.removeIf(entityKrawl -> entityKrawl.getHealth() <= 0);

        if(children.isEmpty()) {
            entityData.set(WAVES_REMAINING, getWaves() - 1);
        }
    }

    public void addKrawl(EntityKrawl entityKrawl) {
        this.level.addFreshEntity(entityKrawl);
        entityKrawl.teleportTo(getX(), getY(), getZ());
        children.add(entityKrawl);
    }
}
