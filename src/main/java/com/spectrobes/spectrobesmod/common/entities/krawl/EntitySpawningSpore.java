package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.goals.MoveToTargetGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.Random;

public class EntitySpawningSpore extends MonsterEntity implements IAnimatable, IFlyingAnimal {
    private static final DataParameter<Boolean> BOSS_SPORE =
            EntityDataManager.defineId(EntitySpawningSpore.class,
                    DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> AGE_TICKS =
            EntityDataManager.defineId(EntitySpawningSpore.class,
                    DataSerializers.INT);

    public AnimationFactory animationControllers = new AnimationFactory(this);
    protected AnimationController moveController = new AnimationController(this, "moveAnimationController", 10F, this::moveController);

    public EntitySpawningSpore(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.navigation.setCanFloat(true);
        this.moveControl = new FlyingMovementController(this, 10, false);
        this.setPathfindingMalus(PathNodeType.OPEN, 0.0F);
    }

    @Override
    public void tick() {
        super.tick();

        entityData.set(AGE_TICKS, entityData.get(AGE_TICKS) + 1);

        if(entityData.get(AGE_TICKS) >= 40 && !level.isClientSide()) {
            spawnKrawl();
        }
    }

    public boolean isBossSpore() {
        return entityData.get(BOSS_SPORE);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(BOSS_SPORE, false);
        entityData.define(AGE_TICKS, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("BOSS_SPORE", entityData.get(BOSS_SPORE));
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT pCompound) {
        super.readAdditionalSaveData(pCompound);
        entityData.set(BOSS_SPORE, pCompound.getBoolean("BOSS_SPORE"));
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    private void spawnKrawl() {
        if(entityData.get(BOSS_SPORE)) {
//            EntityKrawl bossKrawl = (EntityKrawl) KrawlEntities.getBossForDimension(level)
//                    .spawn((ServerWorld) level,
//                            null,
//                            null,
//                            blockPosition(),
//                            SpawnReason.MOB_SUMMONED,
//                            false,
//                            false);
//            this.remove();

        } else {
            EntityKrawl krawl = (EntityKrawl) KrawlEntities.getByLevel(100, level)
                    .spawn((ServerWorld) level,
                            null,
                            null,
                            blockPosition(),
                            SpawnReason.MOB_SUMMONED,
                            false,
                            false);
            this.remove();
        }
    }

    @Override
    protected void actuallyHurt(DamageSource damageSrc, float damageAmount) {
        super.actuallyHurt(damageSrc, 0);
    }

    @Override
    protected void registerGoals()
    {
//        this.goalSelector.addGoal(0, new SpawnKrawlGoal(this));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MonsterEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FLYING_SPEED, 0.5);
    }

    //Networking
    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    //Animation
    public <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.xelles_spore_spawn.idle", true));
        return PlayState.CONTINUE;
    }
    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::moveController));
    }
}
