package com.spectrobes.spectrobesmod.common.entities;

import com.spectrobes.spectrobesmod.SpectrobesMod;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.SpectrobeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Nature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Stage;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib.animation.AnimationBuilder;
import software.bernie.geckolib.animation.AnimationTestEvent;
import software.bernie.geckolib.animation.model.AnimationController;
import software.bernie.geckolib.animation.model.AnimationControllerCollection;
import software.bernie.geckolib.entity.IAnimatedEntity;

import javax.annotation.Nullable;
import java.util.UUID;


public abstract class EntitySpectrobe extends TameableEntity implements IEntityAdditionalSpawnData, IAnimatedEntity{
    SpectrobeProperties spectrobeProperties = null;
    @Nullable
    public Spectrobe evolution;
    private boolean recentInteract = false;
    private int ticksTillInteract = 0;

    Spectrobe spectrobeInstance;

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

    public void setSpectrobeInstance(Spectrobe spectrobe) {
        this.spectrobeInstance = spectrobe;
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(2, new EatGrassGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this,10));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.2d));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if(!recentInteract) {
            StringBuilder builder = new StringBuilder();
            builder.append("Nature: " + spectrobeProperties.getNature() + " \n");
            builder.append("Stage: " + spectrobeProperties.getStage() + " \n");
            if(world.isRemote()) {
                Minecraft.getInstance().player.sendChatMessage(builder.toString());
            }
            recentInteract = true;
            ticksTillInteract = 1500;
        }

        return super.processInteract(player, hand);
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
        moveController.transitionLength = 2;
        if(!(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F))
        {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.komainu.jump", true));
            return true;
        }
        else if(this.isSitting()) {
            moveController.setAnimation(new AnimationBuilder().addAnimation("animation.komainu.sit", false));
            return true;
        }
        return false;
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
        super.readAdditional(compound);
        String s;
        if (!compound.getString("OwnerUUID").isEmpty())
        {
            s = compound.getString("OwnerUUID");
        }
        else
        {
            String s1 = compound.getString("Owner");
            s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
        }

        if (!s.isEmpty())
        {
            try
            {
                this.setOwnerId(UUID.fromString(s));
                this.setTamed(true);
            }
            catch (Throwable var4)
            {
                this.setTamed(false);
            }
        }

        spectrobeInstance = SpectrobeUtils.readFromNbt((CompoundNBT) compound.get("SpectrobeData"));
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.put("SpectrobeData", spectrobeInstance.write());

    }



    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public Nature getNature() {
        return spectrobeProperties.getNature();
    }
    public Stage getStage() {
        return spectrobeProperties.getStage();
    }

    public void setEvolution(Spectrobe evolution) {
        this.evolution = evolution;
    }

    @Override
    protected void registerData() {
        super.registerData();

/*        dataManager.register(SYNC_ID,
                world.isRemote ? -1 : AvatarWorldData.getDataFromWorld(world).nextEntityId());
        dataManager.register(SYNC_OWNER, Optional.absent());
        dataManager.register(SYNC_ABILITY, "earth_control");*/
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void livingTick() {
        super.livingTick();
        if(ticksTillInteract > 0)
            ticksTillInteract--;
        if(ticksTillInteract == 0)
            recentInteract = false;
        //check if the spectrobe has an evolution, and meets the requirements to evolve.
        if(this.hasEvolution() && this.canEvolve()){
            //evolve the spectrobe
            this.evolve();
        }
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

    //Animation

    @Override
    public AnimationControllerCollection getAnimationControllers() {
        return animationControllers;
    }

    //Spectrobe evolution

    public void tryEvolve() {
        if(canEvolve()) {
            evolve();
        }
    }

    private boolean hasEvolution() {
        return getEvolution() != null? true : false;
    }

    private Spectrobe getEvolution() {
        return evolution;
    }

    protected abstract boolean canEvolve();

    private void evolve() {
        //at the moment just evolve directly into the next level

        getEvolutionRegistry().onInitialSpawn(
                this.world,
                this.world.getDifficultyForLocation(new BlockPos(this)),
                SpawnReason.MOB_SUMMONED,
                (ILivingEntityData)null,
                spectrobeInstance.write());
        //should store all the spectrobes data in an object, then create a
        // cocoon entity which holds this, the cocoon will "hatch"
        // after a predefined time. it will then spawn the next form of spectrobe with
        //the correct variation of skin, part and data for atk, hp and def etc.
    }

    public abstract EntitySpectrobe getEvolutionRegistry();


    @Override
    public boolean isInLove() {
        if(getStage() == Stage.CHILD)
            return false;

        return true;
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        //children cant have children, duh.
        if(getStage() == Stage.CHILD)
            return null;
        else {
            return this.getChildForLineage();
        }
    }

    protected abstract AgeableEntity getChildForLineage();

}
