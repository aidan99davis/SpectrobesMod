package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AbsorbKrawlGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.HealKrawlGoal;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import com.spectrobes.spectrobesmod.common.save_data.SpectrobesWorldSaveData;
import com.spectrobes.spectrobesmod.util.KrawlPropertiesBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.spectrobes.spectrobesmod.common.entities.krawl.EntitySpawningSpore.BOSS_SPORE;

public class EntityXelles extends EntityBossKrawl {

    private static final EntityDataAccessor<Integer> STAGE =
            SynchedEntityData.defineId(EntityXelles.class,
                    DataSerializers.INT);

    private static final EntityDataAccessor<Integer> AGE_IN_TICKS =
            SynchedEntityData.defineId(EntityXelles.class,
                    DataSerializers.INT);

    private static final EntityDataAccessor<Integer> LAST_HURT_TICKS =
            SynchedEntityData.defineId(EntityXelles.class,
                    DataSerializers.INT);

    private static final EntityDataAccessor<Integer> LAST_SPAWNED_HEALING_SPORES_TICKS =
            SynchedEntityData.defineId(EntityXelles.class,
                    DataSerializers.INT);

    private static final EntityDataAccessor<Integer> LAST_SPAWNED_SUMMONING_SPORES_TICKS =
            SynchedEntityData.defineId(EntityXelles.class,
                    DataSerializers.INT);

    private static final EntityDataAccessor<Integer> LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS =
            SynchedEntityData.defineId(EntityXelles.class,
                    DataSerializers.INT);

    private static final EntityDataAccessor<Boolean> IS_SPAWNING_SPORES =
            SynchedEntityData.defineId(EntityXelles.class,
                    DataSerializers.BOOLEAN);

    public EntityXelles(EntityType<? extends Monster> type, Level worldIn) {
        super(type, worldIn);
        setPersistenceRequired();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AttackSpectrobeMasterGoal(this, true, true));
        this.goalSelector.addGoal(0, new AttackSpectrobeGoal(this, true, true));
        this.goalSelector.addGoal(1, new HealKrawlGoal(this));
        this.goalSelector.addGoal(1, new XellesSpawnKrawlGroupGoal(this));
        this.goalSelector.addGoal(1, new XellesSpawnKrawlBossGoal(this));
        this.goalSelector.addGoal(1, new AbsorbKrawlGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(AGE_IN_TICKS, 0);
        entityData.define(STAGE, 1);
        entityData.define(LAST_SPAWNED_SUMMONING_SPORES_TICKS, 0);
        entityData.define(LAST_SPAWNED_HEALING_SPORES_TICKS, 0);
        entityData.define(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS, 24000);
        entityData.define(LAST_HURT_TICKS, 1000);
        entityData.define(IS_SPAWNING_SPORES, false);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("AGE_IN_TICKS", entityData.get(AGE_IN_TICKS));
        pCompound.putInt("STAGE", entityData.get(STAGE));
        pCompound.putInt("LAST_SPAWNED_SUMMONING_SPORES_TICKS", entityData.get(LAST_SPAWNED_SUMMONING_SPORES_TICKS));
        pCompound.putInt("LAST_SPAWNED_HEALING_SPORES_TICKS", entityData.get(LAST_SPAWNED_HEALING_SPORES_TICKS));
        pCompound.putInt("LAST_SPAWNED_BOSS_SPORES_TICKS", entityData.get(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS));
        pCompound.putInt("LAST_HURT_TICKS", entityData.get(LAST_HURT_TICKS));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        entityData.set(AGE_IN_TICKS, pCompound.getInt("AGE_IN_TICKS"));
        entityData.set(STAGE, pCompound.getInt("STAGE"));
        entityData.set(LAST_SPAWNED_SUMMONING_SPORES_TICKS, pCompound.getInt("LAST_SPAWNED_SUMMONING_SPORES_TICKS"));
        entityData.set(LAST_SPAWNED_HEALING_SPORES_TICKS, pCompound.getInt("LAST_SPAWNED_HEALING_SPORES_TICKS"));
        entityData.set(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS, pCompound.getInt("LAST_SPAWNED_BOSS_SPORES_TICKS"));
        entityData.set(LAST_HURT_TICKS, pCompound.getInt("LAST_HURT_TICKS"));
    }

    @Override
    protected void actuallyHurt(DamageSource damageSrc, float damageAmount) {
        if(damageSrc != DamageSource.CRAMMING) {
            AABB bounds = getBoundingBox().inflate(40, 40, 40);
            List<EntityOtorso> otorso = level.getEntities(KrawlEntities.ENTITY_OTORSO.get(), bounds, entity -> true);
            if(otorso.size() == 0) {
                super.actuallyHurt(damageSrc, damageAmount);
            }
            entityData.set(LAST_HURT_TICKS, 0);
        }
    }

    @Override
    public Vec3 getDeltaMovement() {
        return Vec3.ZERO;
    }

    @Override
    public void tick() {
        super.tick();
        entityData.set(AGE_IN_TICKS, entityData.get(AGE_IN_TICKS) + 1);
        entityData.set(LAST_HURT_TICKS, entityData.get(LAST_HURT_TICKS) + 1);
        entityData.set(LAST_SPAWNED_SUMMONING_SPORES_TICKS, entityData.get(LAST_SPAWNED_SUMMONING_SPORES_TICKS) + 1);
        entityData.set(LAST_SPAWNED_HEALING_SPORES_TICKS, entityData.get(LAST_SPAWNED_HEALING_SPORES_TICKS) + 1);
        entityData.set(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS, entityData.get(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS) + 1);
        if(!level.isClientSide()) {

            SpectrobesWorldSaveData worldData = SpectrobesWorldSaveData.getWorldData((ServerWorld) level);
            entityData.set(STAGE, worldData.getNest(blockPosition()).stage);

            if(getStage() == 1 && (worldData.getNest(blockPosition()).vortex_absorbed > 5)) {
                worldData.getNest(blockPosition()).stage = 2;
                worldData.setDirty();
            }
            if(getAge() >= 3 && (worldData.getNest(blockPosition()).stage == 2)) {
                worldData.getNest(blockPosition()).stage = 3;
                worldData.setDirty();
            }
            if(getAge() >= 3) {
                AxisAlignedBB bound = getBoundingBox().inflate(40,40,40);
                List<EntityVortex> nearbyVortex = level.getEntities(KrawlEntities.ENTITY_VORTEX.get(), bound, entityVortex -> true);
                nearbyVortex.forEach(entityVortex -> entityVortex.remove());
            }
        }
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(super.getDisplayName().getString() + " - Stage: " + getStage());
    }

    @Override
    @ParametersAreNonnullByDefault
    public void die(DamageSource pCause) {
        super.die(pCause);
        if(!level.isClientSide()) {
            SpectrobesWorldSaveData saveData = SpectrobesWorldSaveData.getWorldData((ServerWorld) level);
            saveData.getNest(blockPosition()).setDead();
            saveData.setDirty();
        }

        if(getStage() == 3) {
            int mineralCount = random.nextInt(3) + 3;
            for (int i = 0; i < mineralCount; i++) {
                ItemStack mineralStack = SpectrobesItems.getRandomMineral(Mineral.MineralRarity.Rare);
                ItemEntity minerals = new ItemEntity(level,
                        this.getX() + 0.5D,
                        (this.getY() + 1),
                        this.getZ() + 0.5D, mineralStack);
                minerals.setDefaultPickUpDelay();
                level.addFreshEntity(minerals);
            }

            ItemEntity trophy = new ItemEntity(level,
                    this.getX() + 0.5D,
                    (this.getY() + 1),
                    this.getZ() + 0.5D, SpectrobesBlocks.xelles_trophy.get().asItem().getDefaultInstance());
            trophy.setDefaultPickUpDelay();
            level.addFreshEntity(trophy);
        }
    }

    public boolean isSpawningSpores() {
        return entityData.get(IS_SPAWNING_SPORES);
    }

    public void setIsSpawningSpores(boolean isSpawningSpores) {
        entityData.set(IS_SPAWNING_SPORES, isSpawningSpores);
    }

    public int lastHurtTicksAgo() {return entityData.get(LAST_HURT_TICKS); }

    //returns the xelles age in days.
    public int getAge() {
        return entityData.get(AGE_IN_TICKS) / 24000;
    }

    public int getStage() {
        return entityData.get(STAGE);
    }

    public boolean canSpawnHealSpores() {
        return entityData.get(LAST_SPAWNED_HEALING_SPORES_TICKS) >= 600;
    }

    public boolean canSpawnBossSpore() {
        AxisAlignedBB searchBox = getBoundingBox().inflate(50, 50, 50);
        List<EntityOrbix> nearbyBosses = level.getEntities(KrawlEntities.ENTITY_ORBIX.get(), searchBox, entityOrbix -> true);
        boolean hasSpawnedBoss = nearbyBosses.size() > 0;
        return entityData.get(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS) >= 24000
                && !hasSpawnedBoss;
    }

    private boolean canSummonWave() {
        return entityData.get(LAST_SPAWNED_SUMMONING_SPORES_TICKS) >= 1200;
    }

    private int waveSize() {
        switch (getStage()) {
            case 2:
                return 2;
            case 3:
                return 4;
            default: return 0;
        }
    }

    public void spawnHealingSpores(List<EntityKrawl> targets) {
        entityData.set(LAST_SPAWNED_HEALING_SPORES_TICKS, 0);
        setIsSpawningSpores(true);
        if(!level.isClientSide()) {
            for (EntityKrawl krawl :
                    targets) {
                //create healing spore
                EntityHealingSpore spore =
                        (EntityHealingSpore) KrawlEntities.ENTITY_HEALING_SPORES.get()
                                .spawn((ServerWorld) level,
                                        null,
                                        null,
                                        blockPosition(),
                                        SpawnReason.MOB_SUMMONED,
                                        false,
                                        false);
                if (spore != null) {
                    spore.setTarget(krawl);
                    spore.setDeltaMovement(0, 0.5, 0);
                }
            }
        }

        setIsSpawningSpores(false);
    }

    @Override
    public BossInfo.Color getBossNameColour() {
        return BossInfo.Color.PURPLE;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return new KrawlPropertiesBuilder()
                .withGuraWorth(2000)
                .withXpWorth(1000)
                .withAtkLevel(0)
                .withDefLevel(0)
                .withHpLevel(800)
                .withAtkOffset(10)
                .withDefOffset(10)
                .withHpOffset(10)
                .withLevel(30)
                .build();
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> event) {
        if(event.getAnimatable().isDeadOrDying()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.xelles.death", false));
            return PlayState.CONTINUE;
        } else if(((EntityXelles)event.getAnimatable()).isSpawningSpores()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.xelles.spawning", true));
            return PlayState.CONTINUE;
        } else if(((EntityXelles)event.getAnimatable()).lastHurtTicksAgo() == 0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.xelles.hurt", false));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.xelles.idle", true));
        return PlayState.CONTINUE;
    }

    private static class XellesSpawnKrawlGroupGoal extends Goal {

        EntityXelles mob;

        public XellesSpawnKrawlGroupGoal(EntityXelles xelles) {
            mob = xelles;
        }

        @Override
        public boolean canUse() {
            return mob.getTarget() != null && mob.canSummonWave();
        }

        @Override
        public void start() {
            super.start();

            int numToSpawn = mob.waveSize();
            mob.setIsSpawningSpores(true);

            for (int i = 0; i < numToSpawn; i++) {
                if(!mob.level.isClientSide()) {
                    EntitySpawningSpore spore = (EntitySpawningSpore) KrawlEntities.ENTITY_SPAWNING_SPORE.get()
                            .spawn((ServerWorld) mob.level,
                                    null,
                                    null,
                                    mob.blockPosition(),
                                    SpawnReason.MOB_SUMMONED,
                                    false,
                                    false);
                    if (spore != null) {
                        spore.setDeltaMovement(0, 0.5, 0);
                    }
                    mob.entityData.set(LAST_SPAWNED_SUMMONING_SPORES_TICKS, 0);
                }
            }
            mob.setIsSpawningSpores(false);

        }
    }

    private static class XellesSpawnKrawlBossGoal extends Goal {

        EntityXelles mob;

        public XellesSpawnKrawlBossGoal(EntityXelles xelles) {
            mob = xelles;
        }

        @Override
        public boolean canUse() {
            return mob.getTarget() != null && mob.canSpawnBossSpore();
        }

        @Override
        public void start() {
            super.start();

            mob.setIsSpawningSpores(true);

            if(!mob.level.isClientSide()) {
                EntitySpawningSpore spore = (EntitySpawningSpore) KrawlEntities.ENTITY_SPAWNING_SPORE.get()
                        .spawn((ServerWorld) mob.level,
                                null,
                                null,
                                mob.blockPosition(),
                                SpawnReason.MOB_SUMMONED,
                                false,
                                false);
                if (spore != null) {
                    spore.getEntityData().set(BOSS_SPORE, true);
                    spore.setDeltaMovement(0, 0.5, 0);
                }
                mob.entityData.set(LAST_SPAWNED_BOSS_SUMMONING_SPORE_TICKS, 0);
            }
            mob.setIsSpawningSpores(false);

        }
    }

}
