package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EntitySpawningSpore extends Monster implements GeoEntity {
    private static final String TAG_BOSS_SPORE = "BOSS_SPORE";
    private static final String TAG_AGE_TICKS = "AGE_TICKS";

    private static final int SPAWN_DELAY_TICKS = 40;

    private static final RawAnimation IDLE_ANIMATION =
            RawAnimation.begin().thenLoop("animation.xelles_spore_spawn.idle");

    protected static final EntityDataAccessor<Boolean> BOSS_SPORE =
            SynchedEntityData.defineId(EntitySpawningSpore.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> AGE_TICKS =
            SynchedEntityData.defineId(EntitySpawningSpore.class, EntityDataSerializers.INT);

    private final AnimatableInstanceCache animationCache = GeckoLibUtil.createInstanceCache(this);

    public EntitySpawningSpore(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        // No FlyingMoveControl — spores should fall to the ground under gravity
        // and stay there. FlyingMoveControl disabled gravity; removing it lets
        // the standard physics tick handle descent naturally.
        this.setPathfindingMalus(PathType.OPEN, 0.0F);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        // Ground navigation — spores don't fly, they land and pulse.
        return super.createNavigation(level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(BOSS_SPORE, false);
        builder.define(AGE_TICKS, 0);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            return;
        }

        int ageTicks = this.getAgeTicks() + 1;
        this.setAgeTicks(ageTicks);

        if (ageTicks >= SPAWN_DELAY_TICKS) {
            this.spawnKrawl();
        }
    }

    public boolean isBossSpore() {
        return this.entityData.get(BOSS_SPORE);
    }

    public void setBossSpore(boolean bossSpore) {
        this.entityData.set(BOSS_SPORE, bossSpore);
    }

    public int getAgeTicks() {
        return this.entityData.get(AGE_TICKS);
    }

    private void setAgeTicks(int ageTicks) {
        this.entityData.set(AGE_TICKS, ageTicks);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean(TAG_BOSS_SPORE, this.isBossSpore());
        compound.putInt(TAG_AGE_TICKS, this.getAgeTicks());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        this.setBossSpore(compound.getBoolean(TAG_BOSS_SPORE));
        this.setAgeTicks(compound.getInt(TAG_AGE_TICKS));
    }

    private void spawnKrawl() {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        EntityType<? extends EntityKrawl> krawlType = this.isBossSpore()
                ? KrawlEntities.getBossForDimension(serverLevel)
                : KrawlEntities.getByLevel(100, serverLevel);

        if (krawlType != null) {
            krawlType.spawn(
                    serverLevel,
                    this.blockPosition(),
                    MobSpawnType.MOB_SUMMONED
            );
        }

        this.discard();
    }

    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
        // Spawning spores are intentionally damage-immune.
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.ATTACK_DAMAGE, 0.0D);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "move_controller", 10, this::moveController));
    }

    private PlayState moveController(AnimationState<EntitySpawningSpore> animationState) {
        return animationState.setAndContinue(IDLE_ANIMATION);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.animationCache;
    }

}