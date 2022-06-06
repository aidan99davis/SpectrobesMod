package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.goals.AttackSpectrobeGoal;
import com.spectrobes.spectrobesmod.common.entities.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.util.KrawlPropertiesBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityXelles extends EntityBossKrawl {

    private static final DataParameter<Integer> AGE_IN_TICKS =
            EntityDataManager.defineId(EntityXelles.class,
                    DataSerializers.INT);

    public EntityXelles(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AttackSpectrobeMasterGoal(this, true, true));
        this.goalSelector.addGoal(0, new AttackSpectrobeGoal(this, true, true));
//        this.goalSelector.addGoal(1, new HealKrawlGoal(this));
//        this.goalSelector.addGoal(1, new SpawnKrawlGroupGoal(this, cooldown=30 sec));
//        this.goalSelector.addGoal(1, new SpawnKrawlGoal(this, minibossKrawl));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(AGE_IN_TICKS, 0);
    }

    @Override
    public Vector3d getDeltaMovement() {
        return Vector3d.ZERO;
    }

    @Override
    public void tick() {
        super.tick();
        entityData.set(AGE_IN_TICKS, entityData.get(AGE_IN_TICKS) + 1);
    }

    //returns the xelles age in days.
    public int getAge() {
        return entityData.get(AGE_IN_TICKS) / 24000;
    }

    @Override
    public KrawlProperties GetKrawlProperties() {
        return new KrawlPropertiesBuilder()
                .withGuraWorth(2000)
                .withXpWorth(1000)
                .withAtkLevel(0)
                .withDefLevel(400)
                .withHpLevel(3000)
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
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.xelles.idle", true));
        return PlayState.CONTINUE;
    }
}
