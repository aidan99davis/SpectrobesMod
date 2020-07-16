package com.spectrobes.spectrobesmod.common.entities;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Nature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Stage;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import software.bernie.geckolib.animation.AnimationBuilder;
import software.bernie.geckolib.animation.AnimationTestEvent;
import software.bernie.geckolib.animation.model.AnimationController;
import software.bernie.geckolib.animation.model.AnimationControllerCollection;
import software.bernie.geckolib.entity.IAnimatedEntity;

import javax.annotation.Nullable;


public abstract class EntitySpectrobe extends AnimalEntity implements IEntityAdditionalSpawnData, IAnimatedEntity {
    SpectrobeProperties spectrobeProperties = null;
    @Nullable
    EntitySpectrobe evolution;

    private BreedGoal BreedGoal = new BreedGoal(this, 100);

    public AnimationControllerCollection animationControllers = new AnimationControllerCollection();
    private AnimationController moveController = new AnimationController(this, "moveController", 10F, this::moveController);


    public EntitySpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn,
                           World worldIn,
                           SpectrobeProperties spectrobeProperties) {
        super(entityTypeIn, worldIn);
        this.spectrobeProperties = spectrobeProperties;
        setInvulnerable(true);
        registerAnimationControllers();
    }

    public void registerAnimationControllers()
    {
        if(world.isRemote)
        {
            this.animationControllers.addAnimationController(moveController);
        }
    }

    private <ENTITY extends Entity> boolean moveController(AnimationTestEvent<ENTITY> entityAnimationTestEvent)
    {
        moveController.transitionLength = 10;
        if(entityAnimationTestEvent.getEntity().getMotion() != Vec3d.ZERO)
        {
            moveController.setAnimation(new AnimationBuilder().addAnimation("komainu.jump", true));
        }
        else {
            return false;
        }
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(source.getImmediateSource() instanceof EntitySpectrobe){
            return super.attackEntityFrom(source,amount);
        }
        return super.attackEntityFrom(source, 0);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {

    }

    @Override
    public void writeAdditional(CompoundNBT compound) {

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return null;
    }

    public Nature getNature() {
        return spectrobeProperties.getNature();
    }
    public Stage getStage() {
        return spectrobeProperties.getStage();
    }

    public void setEvolution(EntitySpectrobe evolution) {
        this.evolution = evolution;
    }

    @Override
    protected void registerData() {

    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void livingTick() {
        super.livingTick();

        //check if the spectrobe has an evolution, and meets the requirements to evolve.
        if(this.hasEvolution() && this.canEvolve()){
            //evolve the spectrobe
            this.evolve();
        }
    }

    public void tryEvolve() {
        if(canEvolve()) {
            evolve();
        }
    }

    private boolean hasEvolution() {
        return getEvolution() != null? true : false;
    }

    private EntitySpectrobe getEvolution() {
        return evolution;
    }

    protected abstract boolean canEvolve();

    private void evolve() {
        //at the moment just evolve directly into the next level
        evolution.onInitialSpawn(
                this.world,
                this.world.getDifficultyForLocation(new BlockPos(this)),
                SpawnReason.MOB_SUMMONED,
                (ILivingEntityData)null,
                (CompoundNBT)null);
        //should store all the spectrobes data in an object, then create a
        // cocoon entity which holds this, the cocoon will "hatch"
        // after a predefined time. it will then spawn the next form of spectrobe with
        //the correct variation of skin, part and data for atk, hp and def etc.
    }

    //Checks if the attacker should have the attack multiplier bonus applied.
    private int hasTypeAdvantage(EntitySpectrobe attacker, EntitySpectrobe defender) {
        int toReturn = 0;

        if(attacker == defender)
            return toReturn;

        Nature attackerNature = attacker.getNature();
        Nature defenderNature = defender.getNature();

        switch(attackerNature){
            case FLASH:
                if(defenderNature == Nature.CORONA)
                    toReturn = 1;
                if(defenderNature == Nature.AURORA)
                    toReturn = -1;
                break;
            case AURORA:
                if(defenderNature == Nature.FLASH)
                    toReturn = 1;
                if(defenderNature == Nature.CORONA)
                    toReturn = -1;
                break;
            case CORONA:
                if(defenderNature == Nature.AURORA)
                    toReturn = 1;
                if(defenderNature == Nature.FLASH)
                    toReturn = -1;
                break;
            default:
                toReturn = 0;
                break;
        }

        return toReturn;
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {

    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {

    }

    @Override
    public AnimationControllerCollection getAnimationControllers() {
        return null;
    }

}
