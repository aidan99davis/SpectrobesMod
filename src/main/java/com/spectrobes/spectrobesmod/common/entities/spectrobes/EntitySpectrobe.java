package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.entities.goals.*;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.items.fossils.FossilBlockItem;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.items.minerals.SpecialMineralItem;
import com.spectrobes.spectrobesmod.common.items.tools.PrizmodItem;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
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
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Nature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Stage;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public abstract class EntitySpectrobe extends TameableEntity implements IEntityAdditionalSpawnData, IAnimatable, IHasNature {
    public static final Predicate<ItemEntity> MINERAL_SELECTOR = (itemEntity) -> !itemEntity.cannotPickup() && itemEntity.isAlive() && itemEntity.getItem().getItem() instanceof MineralItem;
    private static final Predicate<LivingEntity> TARGET_KRAWL = (entity) -> {
        EntityType<?> entitytype = entity.getType();
        return entitytype.getClass().isAssignableFrom(EntityKrawl.class)
                && !((EntityKrawl)entity).isVortex();
    };
    private boolean recentInteract = false;
    private int ticksTillInteract = 0;

    private static final DataParameter<Integer> TICKS_TILL_MATE =
            EntityDataManager.createKey(EntitySpectrobe.class,
            DataSerializers.VARINT);

    private static final DataParameter<Boolean> IS_ATTACKING =
            EntityDataManager.createKey(EntitySpectrobe.class,
                    DataSerializers.BOOLEAN);

    protected static final DataParameter<Boolean> HAS_MATED =
            EntityDataManager.createKey(EntitySpectrobe.class,
                    DataSerializers.BOOLEAN);

    //State 0: following
    //State 1: Sitting
    //state 2: searching
    private static final DataParameter<Integer> STATE =
            EntityDataManager.createKey(EntitySpectrobe.class,
                    DataSerializers.VARINT);

    private static final DataParameter<Spectrobe> SPECTROBE_DATA =
            EntityDataManager.createKey(EntitySpectrobe.class,
                    Spectrobe.SpectrobeSerializer);

    public AnimationFactory animationControllers = new AnimationFactory(this);
    protected AnimationController moveAnimationController = new AnimationController(this, "moveAnimationController", 10F, this::moveController);
    private List<? extends EntitySpectrobe> children;


    public EntitySpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn,
                           World worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(0, new AttackKrawlGoal(this, true, true));
        this.goalSelector.addGoal(1, new FindMineralsGoal(this));
        this.goalSelector.addGoal(1, new FindFossilsGoal(this));
        this.goalSelector.addGoal(1, new FindMineralOreGoal(this));
        this.goalSelector.addGoal(2, new FollowMasterGoal(this,0.3f , 1, 15, true));
        this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this,1f , true));
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, EntityKrawl.class, 10, true, false, (entity) -> true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, EntityKrawl.class, true));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this, new Class[0])).setCallsForHelp(new Class[0]));
        this.targetSelector.addGoal(5, new NonTamedTargetGoal(this, EntityKrawl.class, false, TARGET_KRAWL));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, (double)0.5F)
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D)
                .createMutableAttribute(Attributes.ATTACK_SPEED, 0.25f)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 10.0D)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 5.0D);
    }

    @Override
    public boolean isSitting() {
        return dataManager.get(STATE) == 1;
    }

    public void setState(int state) { dataManager.set(STATE, state); }

    @Override
    //processInteract
    public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if(getSpectrobeData() != null) {
            if(!recentInteract && itemstack.isEmpty()) {
                if(player.getUniqueID().equals(getOwnerId()) && player.isSneaking()) {
                    cycleState(player);
                } else {
                    printSpectrobeToChat(player);
                }

            } else if (itemstack.getItem() instanceof MineralItem){
                MineralItem mineralItem = (MineralItem)itemstack.getItem();
                applyMineral(mineralItem);
                itemstack.shrink(1);
            } else if (itemstack.getItem() instanceof SpecialMineralItem){
                SpecialMineralItem mineralItem = (SpecialMineralItem)itemstack.getItem();
                applySpecialMineral(mineralItem);
                itemstack.shrink(1);
            } else if(itemstack.getItem() instanceof PrizmodItem && player.isSneaking()) {
                if(player == getOwner()) {
                    despawn();
                }
            }
        }

        recentInteract = true;
        ticksTillInteract = 15;
        return super.func_230254_b_(player, hand);
    }

    private void cycleState(PlayerEntity player) {
        int oldstate = dataManager.get(STATE);

        int newstate = oldstate + 1;

        //first check is to prevent non child forms from searching. 2nd is to cycle fully for children
        if((getStage() != Stage.CHILD && newstate > 1) || newstate > 2) {
            newstate = 0;
        }

        dataManager.set(STATE, newstate);
        if(world.isRemote()) {
            switch(newstate) {
                case 0:
                    player.sendMessage(new StringTextComponent("Your spectrobe is now following."),player.getUniqueID());
                    break;
                case 1:
                    player.sendMessage(new StringTextComponent("Your spectrobe is now sitting."), player.getUniqueID());
                    break;
                case 2:
                    player.sendMessage(new StringTextComponent("Your spectrobe is now searching."), player.getUniqueID());
                    break;
            }
        }
    }

    //prevent spectrobes from despawning naturally. they should only stop existing via prizmod recall feature, or death.
    @Override
    public boolean preventDespawn() {
        return true;
    }

    public void despawn() {
        this.getSpectrobeData().setInactive();
        if(this.getOwnerId() != null) {
            this.getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                sm.setSpectrobeInactive(this.getSpectrobeData());
            });
        }
        if(world.isRemote()) {
            Minecraft.getInstance().world.addParticle(ParticleTypes.FIREWORK, getPosX() + 0.5D, getPosY() + 1.0D, getPosZ() + 0.5D, 0.0D, 1.0D, 0.0D);
        }
        this.remove(false);
    }

    public void despawn(PlayerSpectrobeMaster compat) {
        this.getSpectrobeData().setInactive();
        compat.setSpectrobeInactive(this.getSpectrobeData());

        if(world.isRemote()) {
            Minecraft.getInstance().world.addParticle(ParticleTypes.FIREWORK, getPosX() + 0.5D, getPosY() + 1.0D, getPosZ() + 0.5D, 0.0D, 1.0D, 0.0D);
        }
        this.remove(false);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if(source.getImmediateSource() instanceof EntitySpectrobe
                || source.getImmediateSource() instanceof EntityKrawl){
            return false;
        }

        if(source.isFireDamage() && getNature() == Nature.CORONA) {
            return true;
        }

        return true;
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if(compound.get("SpectrobeData") != null) {
            setSpectrobeData(Spectrobe.read((CompoundNBT) compound.get("SpectrobeData")));
        }
        if(getSpectrobeData() == null)
            setSpectrobeData(GetNewSpectrobeInstance());

        dataManager.set(HAS_MATED, compound.getBoolean("sterile"));

        updateEntityAttributes();
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);

        compound.put("SpectrobeData", getSpectrobeData().write());
        compound.putBoolean("sterile", dataManager.get(HAS_MATED));

    }

    public Spectrobe getSpectrobeData() {
        return dataManager.get(SPECTROBE_DATA);
    }
    public void setSpectrobeData(Spectrobe spectrobe) {
        dataManager.set(SPECTROBE_DATA, spectrobe);
    }

    public boolean IsAttacking() {
        return dataManager.get(IS_ATTACKING);
    }
    public void setIsAttacking(boolean attacking) {
        dataManager.set(IS_ATTACKING, attacking);
    }

    public int getTicksTillMate() {
        return dataManager.get(TICKS_TILL_MATE);
    }
    public void setTicksTillMate(int ticksTillMate) {
        dataManager.set(TICKS_TILL_MATE, ticksTillMate);
    }

    //Networking
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(SPECTROBE_DATA, GetNewSpectrobeInstance());
        dataManager.register(TICKS_TILL_MATE, 400);
        dataManager.register(STATE, 0);
        dataManager.register(IS_ATTACKING, false);
        dataManager.register(HAS_MATED, false);
    }

    public boolean isSearching() {
        return dataManager.get(STATE) == 2;
    }

    @Override
    public Vector3d getMotion() {
        if(isSitting()) {
            return Vector3d.ZERO;
        }
        return super.getMotion();
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void livingTick() {
        super.livingTick();
        if(!this.isSitting())  {
            //check if the spectrobe has an evolution, and meets the requirements to evolve.
            tryMate();

        }
        if(ticksTillInteract > 0)
            ticksTillInteract--;
        if(ticksTillInteract == 0)
            recentInteract = false;
        tryEvolve();

    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        if(getSpectrobeData() != null)
            buffer.writeCompoundTag(getSpectrobeData().write());
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        setSpectrobeData(Spectrobe.read(additionalData.readCompoundTag()));
        updateEntityAttributes();
    }

    //Animation

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::moveController));
    }

    public abstract <ENTITY extends EntitySpectrobe> PlayState moveController(AnimationEvent<ENTITY> entityAnimationTestEvent);

    //Spectrobe evolution

    public void tryEvolve() {
        if(hasEvolution() && canEvolve()) {
            evolve();
        }
    }

    //spectrobe special time

    public void tryMate() {
        if(getStage() != Stage.CHILD && getOwner() == null && !dataManager.get(HAS_MATED)) {
            if(getTicksTillMate() == 0) {
                mate();
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

        return getSpectrobeData().canEvolve(requirements);
    }

    private void evolve() {
        Spectrobe spectrobeInstance = getSpectrobeData();
        if(!world.isRemote()) {
            EntitySpectrobe spectrobe = getEvolutionRegistry().create(world);
            spectrobe.setLocationAndAngles(getPosX(), getPosY(), getPosZ(), 0.0F, 0.0F);
            this.world.addEntity(spectrobe);
            spectrobe.setPosition(getPosX(), getPosY(), getPosZ());
            spectrobeInstance.evolve(spectrobe.getSpectrobeData());
            spectrobe.setSpectrobeData(spectrobeInstance);
            spectrobe.setCustomName(new StringTextComponent(spectrobeInstance.name));
            if(getOwner() != null) {
                getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.updateSpectrobe(spectrobe.getSpectrobeData());
//                    SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(sm));
                });
                spectrobe.setOwnerId(getOwnerId());
            }
        } else {
            if(getOwner() != null) {
                getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.updateSpectrobe(spectrobeInstance);
                    SpectrobesNetwork.sendToServer(new SSyncSpectrobeMasterPacket(sm));
                });
            }
            world.addParticle(ParticleTypes.FLASH, getPosX() + 0.5D, getPosY() + 1.0D, getPosZ() + 0.5D, 0.0D, 0.0D, 0.0D);
        }

        this.remove();
        //should store all the spectrobes data in an object, then create a
        // cocoon entity which holds this, the cocoon will "hatch"
        // after a predefined time. it will then spawn the next form of spectrobe with
        //the correct variation of skin, part and data for atk, hp and def etc.
    }

    @Override
    public void onDeath(DamageSource cause) {
        if(getOwner() != null) {
            despawn();
        } else {
            ItemEntity lvt_10_1_ = new ItemEntity(world,
                    this.getPosX() + 0.5D,
                    (this.getPosY() + 1),
                    this.getPosZ() + 0.5D, getFossil().getDefaultInstance());
            lvt_10_1_.setDefaultPickupDelay();
            world.addEntity(lvt_10_1_);
            super.onDeath(cause);
        }
    }

    @Override
    protected void damageEntity(DamageSource damageSrc, float damageAmount) {
        if(damageSrc.getImmediateSource() instanceof IHasNature) {
            IHasNature attacker = (IHasNature)damageSrc.getImmediateSource();
            int advantage = Spectrobe.hasTypeAdvantage(attacker, this);
            float scaledAmount;

            switch (advantage) {
                case -1:
                    scaledAmount = damageAmount * 0.75f;
                    break;
                case 1:
                    scaledAmount = damageAmount * 1.25f;
                    break;
                default:
                    scaledAmount = damageAmount;
                    break;
            }
            super.damageEntity(damageSrc, scaledAmount);
        } else {
            super.damageEntity(damageSrc, damageAmount);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity toAttack) {
        setIsAttacking(true);
        return super.attackEntityAsMob(toAttack);
    }

    @Override
    public void setAggroed(boolean hasAggro) {
        if(!hasAggro){
            setIsAttacking(false);
        }
        super.setAggroed(hasAggro);
    }

    private void addStats(Spectrobe spectrobeData) {
        Spectrobe spectrobeInstance = getSpectrobeData();
        spectrobeInstance.stats.addStats(spectrobeData.stats);
    }

    public void awardKillStats(KrawlProperties krawlProperties) {
        if(world.isRemote()) {
            Spectrobe spectrobeInstance = getSpectrobeData();
            spectrobeInstance.stats.addStats(krawlProperties);
            updateEntityAttributes();
            if(getOwner() != null) {
                getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.updateSpectrobe(spectrobeInstance);
                    sm.addGura(krawlProperties.getGura_worth());

                    SpectrobesNetwork.sendToServer(new SSyncSpectrobeMasterPacket(sm));
                });
            }
        } else {
            if(getOwner() != null) {
                getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    getOwner().sendMessage(new StringTextComponent("You gained: " + krawlProperties.getGura_worth() + " gura. You now have: " + sm.getPlayerGura()), getOwner().getUniqueID());
                });
            }
        }
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
    //createChild
    public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable)
    {
        //gonna handle this myself
        return null;
    }

    private void printSpectrobeToChat(PlayerEntity player) {
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
        String status;
        switch (dataManager.get(STATE)) {
            case 0:
                status = "Following";
                break;
            case 1:
                status = "Sitting";
                break;
            case 2:
                status = "Searching";
                break;
            default:
                status = "Unknown.";
        }
        builder2.append("Status: " + status);
        if(world.isRemote()) {
            player.sendMessage(new StringTextComponent(builder3.toString()), player.getUniqueID());
            player.sendMessage(new StringTextComponent(builder1.toString()), player.getUniqueID());
            player.sendMessage(new StringTextComponent(builder2.toString()), player.getUniqueID());
        }
    }

    public void applySpecialMineral(SpecialMineralItem mineralItem) {
        Spectrobe updatedData = mineralItem.applyEffect(this.getSpectrobeData());

        this.setSpectrobeData(updatedData);

        if(getOwner() != null) {
            getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                sm.updateSpectrobe(updatedData);
            });
        }
    }

    public void applyMineral(MineralItem mineralItem) {
        Spectrobe spectrobeInstance = getSpectrobeData();
        if(spectrobeInstance.properties.getNature()
                .equals(mineralItem.mineralProperties.getNature())
                || mineralItem.mineralProperties.getNature().equals(Nature.OTHER)) {
            spectrobeInstance.applyMineral(mineralItem.mineralProperties);

            updateEntityAttributes();

            if(getOwner() != null) {
                getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.updateSpectrobe(spectrobeInstance);
                });
            }
        } else {
            if(getOwner() != null)
                Minecraft.getInstance().player.sendChatMessage("This mineral is the wrong nature.");
        }
    }

    public void updateEntityAttributes() {
        Spectrobe spectrobeInstance = getSpectrobeData();

        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(
                spectrobeInstance.stats.getHpLevel());

        this.setHealth(this.getMaxHealth());

        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(
                spectrobeInstance.stats.getAtkLevel());

        this.getAttribute(Attributes.ARMOR).setBaseValue(spectrobeInstance.stats.getDefLevel());
    }

    protected abstract FossilBlockItem getFossil();
    protected abstract EntityType<? extends EntitySpectrobe> getChildForLineage();
    public abstract Spectrobe GetNewSpectrobeInstance();
    public abstract EntityType<? extends EntitySpectrobe> getEvolutionRegistry();

    public abstract String getRegistryName();
    public abstract Class getSpectrobeClass();

    public boolean isAttacking() {
        return IS_ATTACKING.equals(true);
    }
}
