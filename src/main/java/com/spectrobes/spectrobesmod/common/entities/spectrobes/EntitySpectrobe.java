package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.client.container.SpectrobeDetailsContainer;
import com.spectrobes.spectrobesmod.client.gui.spectrobes_details.SpectrobeDetailsScreen;
import com.spectrobes.spectrobesmod.common.capability.PlayerProperties;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.*;
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
import com.spectrobes.spectrobesmod.util.DamageUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Nature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Stage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public abstract class EntitySpectrobe extends TamableAnimal implements IEntityAdditionalSpawnData, IAnimatable, IHasNature {
    public static final Predicate<ItemEntity> MINERAL_SELECTOR = (itemEntity) -> !itemEntity.hasPickUpDelay() && itemEntity.isAlive() && itemEntity.getItem().getItem() instanceof MineralItem;
    private static final Predicate<EntityKrawl> TARGET_KRAWL = (entity) -> !(entity).isVortex();
    private boolean recentInteract = false;
    private int ticksTillInteract = 0;

    private static final EntityDataAccessor<Integer> TICKS_TILL_MATE =
            SynchedEntityData.defineId(EntitySpectrobe.class,
                    EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> IS_ATTACKING =
            SynchedEntityData.defineId(EntitySpectrobe.class,
                    EntityDataSerializers.BOOLEAN);

    protected static final EntityDataAccessor<Boolean> HAS_MATED =
            SynchedEntityData.defineId(EntitySpectrobe.class,
                    EntityDataSerializers.BOOLEAN);

    //State 0: following
    //State 1: Sitting
    //state 2: searching
    private static final EntityDataAccessor<Integer> STATE =
            SynchedEntityData.defineId(EntitySpectrobe.class,
                    EntityDataSerializers.INT);

    private static final EntityDataAccessor<Spectrobe> SPECTROBE_DATA =
            SynchedEntityData.defineId(EntitySpectrobe.class,
                    Spectrobe.SpectrobeSerializer);

    public AnimationFactory animationControllers = new AnimationFactory(this);
    protected AnimationController moveAnimationController = new AnimationController(this, "moveAnimationController", 10F, this::moveController);


    public EntitySpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn,
                           Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(3, new FollowMasterGoal(this, 1, 2, 10, canFly()));
        this.goalSelector.addGoal(1, new AttackKrawlGoal(this, true, true));
        this.goalSelector.addGoal(1, new FindMineralsGoal(this));
        this.goalSelector.addGoal(1, new ChildFormSearchGoal(this));
        this.goalSelector.addGoal(3, new AvoidKrawlGoal(this, EntityKrawl.class, 10.0F, 0.5d, 0.75d));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4f));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5f, true));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new MasterHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new MasterHurtTargetGoal(this));
        this.targetSelector.addGoal(1, (new SpectrobeHurtByTargetGoal(this)));
        this.targetSelector.addGoal(3, new TargetKrawlGoal(this, EntityKrawl.class, true, TARGET_KRAWL));
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ATTACK_SPEED, 1f)
                .add(Attributes.FOLLOW_RANGE, 10.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0D);
    }

    @Override
    public boolean isOrderedToSit() {
        return entityData.get(STATE) == 1;
    }

    public void setState(int state) { entityData.set(STATE, state); }
    public int getState() { return entityData.get(STATE); }

    public boolean canFly() {
        return false;
    }

    @Override
    //processInteract
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(getSpectrobeData() != null) {
            if(!recentInteract && itemstack.isEmpty()) {
                if(player.getUUID().equals(getOwnerUUID()) && player.isShiftKeyDown()) {
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
            } else if(itemstack.getItem() instanceof PrizmodItem) {
                if(player == getOwner()) {
                    if(player.isShiftKeyDown()) {
                        if(player.level.isClientSide())
                            Minecraft.getInstance()
                                    .setScreen(
                                    new SpectrobeDetailsScreen(
                                            new SpectrobeDetailsContainer(
                                                    0,
                                                    getSpectrobeData()),
                                            player.getInventory(),
                                            Component.literal("")));
                    }
                }
            }
        }

        recentInteract = true;
        ticksTillInteract = 15;
        return super.mobInteract(player, hand);
    }

    private void cycleState(Player player) {
        int oldstate = entityData.get(STATE);

        int newstate = oldstate + 1;

        //first check is to prevent non child forms from searching. 2nd is to cycle fully for children
        if((getStage() != Stage.CHILD && newstate > 1) || newstate > 2) {
            newstate = 0;
        }

        entityData.set(STATE, newstate);
        if(level.isClientSide()) {
            switch(newstate) {
                case 0:
                    player.sendSystemMessage(Component.literal("Your spectrobe is now following."));
                    break;
                case 1:
                    player.sendSystemMessage(Component.literal("Your spectrobe is now sitting."));
                    break;
                case 2:
                    player.sendSystemMessage(Component.literal("Your spectrobe is now searching."));
                    break;
            }
        }
    }

    //prevent spectrobes from despawning naturally. they should only stop existing via prizmod recall feature, or death.
    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    public void despawn() {
        this.getSpectrobeData().setInactive();
        if(this.getOwner() != null) {
            this.getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> sm.setSpectrobeInactive(this.getSpectrobeData()));
        }
        if(level.isClientSide()) {
            if (Minecraft.getInstance().level != null) {
                Minecraft.getInstance().level.addParticle(ParticleTypes.FIREWORK, getX() + 0.5D, getY() + 1.0D, getZ() + 0.5D, 0.0D, 1.0D, 0.0D);
            }
        }
        this.remove(RemovalReason.DISCARDED);
    }

    public void despawn(PlayerSpectrobeMaster compat) {
        this.getSpectrobeData().setInactive();
        compat.setSpectrobeInactive(this.getSpectrobeData());

        if(level.isClientSide()) {
            Minecraft.getInstance().level.addParticle(ParticleTypes.FIREWORK, getX() + 0.5D, getY() + 1.0D, getZ() + 0.5D, 0.0D, 1.0D, 0.0D);
        }
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if(source.getDirectEntity() instanceof EntitySpectrobe
                || source.getDirectEntity() instanceof EntityKrawl){
            return false;
        }

        if(source.isFire() && getNature() == Nature.CORONA) {
            return true;
        }

        return true;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.get("SpectrobeData") != null) {
            setSpectrobeData(Spectrobe.read((CompoundTag) compound.get("SpectrobeData")));
        }
        if(getSpectrobeData() == null)
            setSpectrobeData(GetNewSpectrobeInstance());

        entityData.set(HAS_MATED, compound.getBoolean("sterile"));

        updateEntityAttributes();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.put("SpectrobeData", getSpectrobeData().write());
        compound.putBoolean("sterile", entityData.get(HAS_MATED));
    }

    public Spectrobe getSpectrobeData() {
        return entityData.get(SPECTROBE_DATA);
    }
    public void setSpectrobeData(Spectrobe spectrobe) {
        entityData.set(SPECTROBE_DATA, spectrobe);
        updateEntityAttributes();
    }

    public boolean IsAttacking() {
        return entityData.get(IS_ATTACKING);
    }
    public void setIsAttacking(boolean attacking) {
        entityData.set(IS_ATTACKING, attacking);
    }

    public int getTicksTillMate() {
        return entityData.get(TICKS_TILL_MATE);
    }
    public void setTicksTillMate(int ticksTillMate) {
        entityData.set(TICKS_TILL_MATE, ticksTillMate);
    }

    //Networking
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(SPECTROBE_DATA, GetNewSpectrobeInstance());
        entityData.define(TICKS_TILL_MATE, 400);
        entityData.define(STATE, 0);
        entityData.define(IS_ATTACKING, false);
        entityData.define(HAS_MATED, false);
    }

    public boolean isSearching() {
        return entityData.get(STATE) == 2;
    }

    @Override
    public Vec3 getDeltaMovement() {
        if(isOrderedToSit()) {
            return Vec3.ZERO;
        }
        return super.getDeltaMovement();
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void aiStep() {
        super.aiStep();
        if(!this.isOrderedToSit())  {
            //check if the spectrobe has an evolution, and meets the requirements to evolve.
            tryMate();
        }
        if(ticksTillInteract > 0)
            ticksTillInteract--;
        if(ticksTillInteract == 0)
            recentInteract = false;
        if(getOwner() == null && (getLastHurtByMobTimestamp() - this.tickCount) > 200) this.setHealth(getHealth() + (getHealth() / 100));
        tryEvolve();

    }

    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {
        if(getSpectrobeData() != null)
            buffer.writeNbt(getSpectrobeData().write());
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
        setSpectrobeData(Spectrobe.read(additionalData.readNbt()));
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
        if(getStage() != Stage.CHILD && getOwner() == null && !entityData.get(HAS_MATED)) {
            if(getTicksTillMate() == 0) {
                mate();
            } else {
                setTicksTillMate(getTicksTillMate() - 1);
            }
        }
    }

    public abstract void mate();

    private boolean hasEvolution() {
        return getEvolution() != null;
    }

    private EntityType<? extends EntitySpectrobe> getEvolution() {
        return getEvolutionRegistry();
    }

    protected EvolutionRequirements getEvolutionRequirements() {
        return getSpectrobeData().evolutionRequirements;
    }

    protected boolean canEvolve() {
        EvolutionRequirements requirements = getEvolutionRequirements();
        if(requirements == null)
            return false;

        return getSpectrobeData().canEvolve(requirements);
    }

    private void evolve() {
        Spectrobe spectrobeInstance = getSpectrobeData();
        if(!level.isClientSide()) {
            EntitySpectrobe evolution = getEvolutionRegistry().create(level);
            evolution.moveTo(getX(), getY(), getZ(), 0.0F, 0.0F);
            this.level.addFreshEntity(evolution);
            evolution.setPos(getX(), getY(), getZ());
            spectrobeInstance.evolve(evolution.getSpectrobeData());
            evolution.setSpectrobeData(spectrobeInstance);
            evolution.setCustomName(Component.literal(spectrobeInstance.name));
            updateEntityAttributes();
            if(getOwner() != null) {
                evolution.setOwnerUUID(getOwnerUUID());
                getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.updateSpectrobe(evolution.getSpectrobeData());
                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm), (ServerPlayer) getOwner());
                    evolution.despawn();
                });
            }
        } else {
            if(getOwner() != null) {
                getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.updateSpectrobe(spectrobeInstance);
//                    SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(sm));
                    getOwner().sendSystemMessage(Component.literal("Your spectrobe has evolved and returned to the prizmod."));
                });
            }
            level.addParticle(ParticleTypes.FLASH, getX() + 0.5D, getY() + 1.0D, getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
        }

        this.remove(RemovalReason.DISCARDED);
        //should store all the spectrobes data in an object, then create a
        // cocoon entity which holds this, the cocoon will "hatch"
        // after a predefined time. it will then spawn the next form of spectrobe with
        //the correct variation of skin, part and data for atk, hp and def etc.
    }

    @Override
    public void die(DamageSource cause) {
        if(getOwner() != null) {
            Spectrobe spectrobeData = getSpectrobeData().copy(true);
            spectrobeData.setCurrentHealth(0);
            setSpectrobeData(spectrobeData);
            getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                sm.updateSpectrobe(this.getSpectrobeData());
                if(!level.isClientSide()) {
                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm), (ServerPlayer) getOwner());
                }
            });
            despawn();
        } else {
            ItemEntity fossilItem = new ItemEntity(level,
                    this.getX() + 0.5D,
                    (this.getY() + 1),
                    this.getZ() + 0.5D, new ItemStack(getFossil()));
            fossilItem.setDefaultPickUpDelay();
            level.addFreshEntity(fossilItem);
            super.die(cause);
        }
    }

    @Override
    protected void actuallyHurt(DamageSource damageSrc, float damageAmount) {
        if(getOwner() != null && getSpectrobeData().properties.getStage() == Stage.CHILD) {
            super.actuallyHurt(damageSrc, 0);
            return;
        }
        if(damageSrc.getDirectEntity() instanceof EntityKrawl) {
            IHasNature attacker = (IHasNature)damageSrc.getDirectEntity();
            int advantage = Spectrobe.hasTypeAdvantage(attacker, this);
            int atkPower = ((EntityKrawl)damageSrc.getDirectEntity()).krawlProperties.getAtkLevel();
            float typeBonus = DamageUtils.getTypeBonus(advantage);

            int defPower = getSpectrobeData().stats.getDefLevel();
            int powerScale = 1;
            float scaledAmount = DamageUtils.getFinalDamageAmount(typeBonus, atkPower, powerScale, defPower);

            Spectrobe updatedSpectrobe = this.getSpectrobeData().copy(true);
            updatedSpectrobe.damage(Math.round(scaledAmount));
            setSpectrobeData(updatedSpectrobe);


            super.actuallyHurt(damageSrc, scaledAmount);
        } else {
            Spectrobe updatedSpectrobe = this.getSpectrobeData().copy(true);
            updatedSpectrobe.damage(Math.round(damageAmount));
            setSpectrobeData(updatedSpectrobe);
            super.actuallyHurt(damageSrc, damageAmount);
        }
        updateEntityAttributes();
        if(getOwner() != null) {
            getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                sm.updateSpectrobe(this.getSpectrobeData());
                SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm), (ServerPlayer) getOwner());
            });
        }
    }

    @Override
    public boolean doHurtTarget(Entity toAttack) {
        setIsAttacking(true);
        return super.doHurtTarget(toAttack);
    }

    @Override
    public void setAggressive(boolean hasAggro) {
        if(!hasAggro){
            setIsAttacking(false);
        }
        super.setAggressive(hasAggro);
    }

    public void awardKillStats(KrawlProperties krawlProperties) {
        if(!level.isClientSide()) {
            Spectrobe spectrobeInstance = getSpectrobeData();
            spectrobeInstance.stats.addStats(krawlProperties);
            updateEntityAttributes();
            if(getOwner() != null) {
                getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.updateSpectrobe(spectrobeInstance);
                    sm.addXp(krawlProperties.getXpWorth());
                    sm.addGura(krawlProperties.getGuraWorth());
                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm), (ServerPlayer) getOwner());
                });
            }
        }
    }

    public void applySpecialMineral(SpecialMineralItem mineralItem) {
        Spectrobe updatedData = mineralItem.applyEffect(this.getSpectrobeData());

        this.setSpectrobeData(updatedData);

        updateEntityAttributes();

        if(getOwner() != null) {
            getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                sm.updateSpectrobe(updatedData);
            });
        }
    }

    public void applyMineral(MineralItem mineralItem) {
        Spectrobe spectrobeInstance = getSpectrobeData();
        if(spectrobeInstance.properties.getNature()
                .equals(mineralItem.mineral.properties.getNature())
                || mineralItem.mineral.properties.getNature().equals(Nature.OTHER)) {
            spectrobeInstance.applyMineral(mineralItem.mineral.properties);

            updateEntityAttributes();

            if(getOwner() != null) {
                getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.updateSpectrobe(spectrobeInstance);
                });
            }
        } else {
            if(getOwner() != null)
                Minecraft.getInstance().player.chatSigned("This mineral is the wrong nature.", Component.empty());
        }
    }

    public void updateEntityAttributes() {
        Spectrobe spectrobeInstance = getSpectrobeData();

        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(
                spectrobeInstance.stats.getHpLevel());

        this.setHealth(spectrobeInstance.currentHealth);
    }

    public Nature getNature() {
        return getSpectrobeData().properties.getNature();
    }
    public Stage getStage() {
        return getSpectrobeData().properties.getStage();
    }

    public int getSpectrobeLevel() { return getSpectrobeData().stats.getLevel(); }

    //ageable entity stuff

    @Override
    public boolean isInLove()
    {
        //gonna handle it myself
        return entityData.get(TICKS_TILL_MATE) == 0;
    }

    @Nullable
    @Override
    //createChild
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob ageable)
    {
        //gonna handle this myself
        return null;
    }

    private void printSpectrobeToChat(Player player) {
        if(level.isClientSide()) {
            Spectrobe spectrobeInstance = getSpectrobeData();
            player.sendSystemMessage(Component.literal("Name: " + spectrobeInstance.name));
            player.sendSystemMessage(Component.literal("Health: " + spectrobeInstance.currentHealth + "/" + spectrobeInstance.stats.getHpLevel()));
            player.sendSystemMessage(Component.literal("Level: " + getLevel()));
            player.sendSystemMessage(Component.literal(""));
            player.sendSystemMessage(Component.literal("Nature: " + getNature()));
            player.sendSystemMessage(Component.literal("Stage: " + getStage()));
            player.sendSystemMessage(Component.literal(""));
            player.sendSystemMessage(Component.literal("Stats"));
            player.sendSystemMessage(Component.literal("Hp: " + spectrobeInstance.stats.getHpLevel()));
            player.sendSystemMessage(Component.literal("Atk: " + spectrobeInstance.stats.getAtkLevel()));
            player.sendSystemMessage(Component.literal("Def: " + spectrobeInstance.stats.getDefLevel()));
            String status;
            switch (entityData.get(STATE)) {
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
            player.sendSystemMessage(Component.literal("Status: " + status));
        }
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
