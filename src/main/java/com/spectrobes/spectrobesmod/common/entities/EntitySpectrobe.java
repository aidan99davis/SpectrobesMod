package com.spectrobes.spectrobesmod.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import com.spectrobes.spectrobesmod.common.entities.SpectrobeProperties.Nature;
import com.spectrobes.spectrobesmod.common.entities.SpectrobeProperties.Stage;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import software.bernie.geckolib.animation.model.AnimationControllerCollection;
import software.bernie.geckolib.entity.IAnimatedEntity;

import javax.annotation.Nullable;


public abstract class EntitySpectrobe extends Entity implements IEntityAdditionalSpawnData, IAnimatedEntity {
    SpectrobeProperties spectrobeProperties = null;
    @Nullable
    EntitySpectrobe evolution;

    public EntitySpectrobe(EntityType<?> entityTypeIn,
                           World worldIn,
                           SpectrobeProperties spectrobeProperties) {
        super(entityTypeIn, worldIn);
        this.spectrobeProperties = spectrobeProperties;
        setInvulnerable(true);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(source.getImmediateSource() instanceof EntitySpectrobe){
            return super.attackEntityFrom(source,amount);
        }
        return super.attackEntityFrom(source, 0);
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {

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
    public void tick() {
        super.tick();

        //check if the spectrobe has an evolution
        if(this.hasEvolution()){
            //check if the spectrobe meets the evolution requirements
            if(this.canEvolve()){
                //evolve the spectrobe
                this.evolve();
            }
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

    abstract boolean canEvolve();

    private void evolve() {

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
