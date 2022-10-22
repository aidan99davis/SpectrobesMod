package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.KrawlVortexFormXellesGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.SpawnWaveGoal;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityVortex extends EntityKrawl {
    private static final DataParameter<Integer> WAVES_REMAINING =
            EntityDataManager.defineId(EntityKrawl.class,
                    DataSerializers.INT);

    private static final DataParameter<Integer> AGE_IN_TICKS =
            EntityDataManager.defineId(EntityKrawl.class,
                    DataSerializers.INT);

    private final List<EntityKrawl> children;

    public EntityVortex(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        children = new ArrayList<>();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AttackSpectrobeMasterGoal(this, true, true));
        this.goalSelector.addGoal(0, new AttackSpectrobeGoal(this, true, true));
        this.goalSelector.addGoal(1, new SpawnWaveGoal(this));
        this.goalSelector.addGoal(1, new KrawlVortexFormXellesGoal(this));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.5d));
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
            SpectrobesWorldSaveData worldData = (SpectrobesWorldSaveData.getWorldData((ServerWorld) level));
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

        if(this.isOnFire()) this.remove();

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
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        calculateKrawlWaves();
        setNatureByBiome();
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private void setNatureByBiome() {
        SpectrobeProperties.Nature nature = SpectrobeProperties.Nature.OTHER;

        Biome.Category cat = level.getBiome(blockPosition()).getBiomeCategory();

        switch (cat) {
            //FLASH
            case TAIGA:
            case OCEAN:
            case ICY:
            case BEACH:
            case RIVER:
            case SWAMP:
                nature = SpectrobeProperties.Nature.FLASH;
                break;
            //CORONA
            case MESA:
            case DESERT:
            case NETHER:
            case SAVANNA:
                nature = SpectrobeProperties.Nature.CORONA;
                break;
            //AURORA
            case FOREST:
            case JUNGLE:
            case PLAINS:
            case MUSHROOM:
            case EXTREME_HILLS:
                nature = SpectrobeProperties.Nature.AURORA;
                break;
            //OTHER
            case THEEND:
            case NONE:
                nature = SpectrobeProperties.Nature.OTHER;
                break;
        }

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

        ItemStack mineralStack = SpectrobesItems.getRandomMineral(rarity);
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
