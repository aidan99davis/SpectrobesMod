package com.spectrobes.spectrobesmod.common.entities;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Nature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Stage;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib.animation.AnimationTestEvent;
import software.bernie.geckolib.animation.model.AnimationController;
import software.bernie.geckolib.animation.model.AnimationControllerCollection;
import software.bernie.geckolib.entity.IAnimatedEntity;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Nullable;

public abstract class EntitySpectrobe extends TameableEntity implements IEntityAdditionalSpawnData, IAnimatedEntity{
    private boolean recentInteract = false;
    private int ticksTillInteract = 0;

    private static final DataParameter<Integer> TICKS_TILL_MATE =
            EntityDataManager.createKey(EntitySpectrobe.class,
            DataSerializers.VARINT);

    private static final DataParameter<Spectrobe> SPECTROBE_DATA =
            EntityDataManager.createKey(EntitySpectrobe.class,
                    Spectrobe.SpectrobeSerializer);

    public AnimationControllerCollection animationControllers = new AnimationControllerCollection();
    protected AnimationController moveController = new AnimationController(this, "moveController", 10F, this::moveController);


    public EntitySpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn,
                           World worldIn) {
        super(entityTypeIn, worldIn);

        setInvulnerable(true);
        registerAnimationControllers();
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(6, new SwimGoal(this));
        this.goalSelector.addGoal(4, new BreatheAirGoal(this));
        this.goalSelector.addGoal(5, new BreedGoal(this,10));
        this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.2d));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
//        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this,0.3f , 4, 12, true));
    }

    @Override
    public boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if(getSpectrobeData() != null) {
            if(!recentInteract && itemstack.isEmpty()) {
                printSpectrobeToChat();
            } else if (itemstack.getItem() instanceof MineralItem){
                MineralItem mineralItem = (MineralItem)itemstack.getItem();
                applyMineral(mineralItem);
            }
        }
        return super.processInteract(player, hand);
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
        setSpectrobeData(Spectrobe.read((CompoundNBT) compound.get("SpectrobeData")));
        if(getSpectrobeData() == null)
            setSpectrobeData(GetNewSpectrobeInstance());
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);

        compound.put("SpectrobeData", getSpectrobeData().write());

    }

    public Spectrobe getSpectrobeData() {
        return dataManager.get(SPECTROBE_DATA);
    }
    public void setSpectrobeData(Spectrobe spectrobe) {
        dataManager.set(SPECTROBE_DATA, spectrobe);
    }

    public int getTicksTillMate() {
        return dataManager.get(TICKS_TILL_MATE);
    }
    public void setTicksTillMate(int ticksTillMate) {
        dataManager.set(TICKS_TILL_MATE, ticksTillMate);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(SPECTROBE_DATA, GetNewSpectrobeInstance());
        dataManager.register(TICKS_TILL_MATE, 15000);
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
        tryMate();
        tryEvolve();

    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        if(!world.isRemote) {
            if(getSpectrobeData() != null)
                buffer.writeCompoundTag(getSpectrobeData().write());
        }
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        if(!world.isRemote) {
            setSpectrobeData(Spectrobe.read(additionalData.readCompoundTag()));
        }
    }

    //Animation

    @Override
    public AnimationControllerCollection getAnimationControllers() {
        return animationControllers;
    }

    public void registerAnimationControllers()
    {
        if(world.isRemote)
        {
            this.animationControllers.addAnimationController(moveController);
        }
    }

    public abstract <ENTITY extends Entity> boolean moveController(AnimationTestEvent<ENTITY> entityAnimationTestEvent);

    //Spectrobe evolution

    public void tryEvolve() {
        if(hasEvolution() && canEvolve()) {
            SpectrobesInfo.LOGGER.info("HAS AN EVOLUTION AND CAN EVOLVE");
            evolve();
        }
    }

    //spectrobe special time

    public void tryMate() {
        if(getStage() != Stage.CHILD) {
            if(getTicksTillMate() == 0) {
                mate();
                setTicksTillMate(16000);
            } else {
                setTicksTillMate(getTicksTillMate() - 1);
            }
        }
    }

    public abstract void mate();

    private boolean hasEvolution() {
        return getEvolution() != null? true : false;
    }

    private EntityType<? extends EntitySpectrobe> getEvolution() {
        return getEvolutionRegistry();
    }

    protected abstract EvolutionRequirements getEvolutionRequirements();

    protected boolean canEvolve() {
        EvolutionRequirements requirements = getEvolutionRequirements();
        if(requirements == null)
            return false;

        return getSpectrobeData().canEvolve(getEvolutionRequirements());
    }

    private void evolve() {
        Minecraft MINECRAFT = Minecraft.getInstance();
        if(!world.isRemote) {
            MINECRAFT.world.addParticle(ParticleTypes.FLASH, getPosX() + 0.5D, getPosY() + 1.0D, getPosZ() + 0.5D, 0.0D, 0.0D, 0.0D);
            EntitySpectrobe spectrobe = getEvolutionRegistry().create(world);
            spectrobe.setLocationAndAngles(getPosX(), getPosY(), getPosZ(), 0.0F, 0.0F);
            this.world.addEntity(spectrobe);
            spectrobe.setPosition(getPosX(), getPosY(), getPosZ());
            spectrobe.addStats(getSpectrobeData());
            if(getOwner() != null) {
                spectrobe.setOwnerId(getOwnerId());
            }
        }

        this.remove();
        //should store all the spectrobes data in an object, then create a
        // cocoon entity which holds this, the cocoon will "hatch"
        // after a predefined time. it will then spawn the next form of spectrobe with
        //the correct variation of skin, part and data for atk, hp and def etc.
    }

    private void addStats(Spectrobe spectrobeData) {
        Spectrobe spectrobeInstance = getSpectrobeData();
        spectrobeInstance.stats.addStats(spectrobeData.stats);
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

    public Nature getNature() {
        return getSpectrobeData().properties.getNature();
    }
    public Stage getStage() {
        return getSpectrobeData().properties.getStage();
    }

    public int getLevel() { return getSpectrobeData().stats.getLevel(); }

    //ageable entity stuff

    @Override
    public boolean isInLove()
    {
        //gonna handle it myself
        return false;
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable)
    {
        //gonna handle this myself
        return null;
    }

    private void printSpectrobeToChat() {
        Spectrobe spectrobeInstance = getSpectrobeData();
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        StringBuilder builder3 = new StringBuilder();
        builder3.append("Name: " + spectrobeInstance.name + ", ");
        builder3.append("Level: " + getLevel() + ", ");
        builder1.append("Nature: " + getNature() + ", ");
        builder1.append("Stage: " + getStage());

        builder2.append("Hp: " + spectrobeInstance.stats.getHpLevel() + ", ");
        builder2.append("Atk: " + spectrobeInstance.stats.getAtkLevel() + ", ");
        builder2.append("Def: " + spectrobeInstance.stats.getDefLevel() + ", ");
        if(world.isRemote()) {
            Minecraft.getInstance().player.sendChatMessage(builder3.toString());
            Minecraft.getInstance().player.sendChatMessage(builder1.toString());
            Minecraft.getInstance().player.sendChatMessage(builder2.toString());
        }
        recentInteract = true;
        ticksTillInteract = 15;
    }

    private void applyMineral(MineralItem mineralItem) {
        Spectrobe spectrobeInstance = getSpectrobeData();
        if(spectrobeInstance.properties.getNature()
                .equals(mineralItem.mineralProperties.getNature())
                || mineralItem.mineralProperties.getNature().equals(Nature.OTHER)) {
            spectrobeInstance.applyMineral(mineralItem.mineralProperties);

            this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(
                    spectrobeInstance.stats.getHpLevel());

            this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(
                    spectrobeInstance.stats.getAtkLevel());
        } else {
            Minecraft.getInstance().player.sendChatMessage("his mineral is the wrong nature.");
        }
    }

    protected abstract AgeableEntity getChildForLineage();
    public abstract Spectrobe GetNewSpectrobeInstance();
    public abstract EntityType<? extends EntitySpectrobe> getEvolutionRegistry();

    public abstract String getRegistryName();
}
