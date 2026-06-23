package com.spectrobes.spectrobesmod.common.entities.spectrobes;

import com.spectrobes.spectrobesmod.client.gui.SpectrobeGuiHandler;
import com.spectrobes.spectrobesmod.common.capability.PlayerSpectrobeMaster;
import com.spectrobes.spectrobesmod.common.capability.SpectrobeMaster;
import com.spectrobes.spectrobesmod.common.entities.IHasNature;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.AttackKrawlGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.AvoidKrawlGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.ChildFormSearchGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.FindMineralsGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.FollowMasterGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.MasterHurtByTargetGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.MasterHurtTargetGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.SpectrobeFollowLeaderGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.SpectrobeHurtByTargetGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.SpectrobeRandomLookAroundGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.TargetKrawlGoal;
import com.spectrobes.spectrobesmod.common.items.fossils.FossilBlockItem;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.items.minerals.SpecialMineralItem;
import com.spectrobes.spectrobesmod.common.items.tools.PrizmodItem;
import com.spectrobes.spectrobesmod.common.items.tools.healing.SpectrobeSerumHealingItem;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.packets.networking.SpectrobesNetwork;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.client.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.registry.DataSerializerRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Nature;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties.Stage;
import com.spectrobes.spectrobesmod.util.DamageUtils;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.DirectionalPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class EntitySpectrobe extends TamableAnimal implements IEntityWithComplexSpawn, GeoEntity, IHasNature {
    public static final Predicate<ItemEntity> MINERAL_SELECTOR =
            itemEntity -> !itemEntity.hasPickUpDelay()
                    && itemEntity.isAlive()
                    && itemEntity.getItem().getItem() instanceof MineralItem;

    private static final Predicate<EntityKrawl> TARGET_KRAWL = entity -> !entity.isVortex();

    private static final EntityDataAccessor<Integer> TICKS_TILL_MATE =
            SynchedEntityData.defineId(EntitySpectrobe.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Boolean> IS_ATTACKING =
            SynchedEntityData.defineId(EntitySpectrobe.class, EntityDataSerializers.BOOLEAN);

    protected static final EntityDataAccessor<Boolean> HAS_MATED =
            SynchedEntityData.defineId(EntitySpectrobe.class, EntityDataSerializers.BOOLEAN);

    // State 0: following
    // State 1: sitting
    // State 2: searching
    private static final EntityDataAccessor<Integer> STATE =
            SynchedEntityData.defineId(EntitySpectrobe.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Spectrobe> SPECTROBE_DATA =
            SynchedEntityData.defineId(EntitySpectrobe.class, DataSerializerRegistry.SPECTROBE_SERIALIZER.get());

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private boolean recentInteract = false;
    private int ticksTillInteract = 0;

    @Nullable
    private EntitySpectrobe leader;

    private int schoolSize = 1;

    public EntitySpectrobe(EntityType<? extends EntitySpectrobe> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(3, new FollowMasterGoal(this, 1, 2, 10, canFly()));
        this.goalSelector.addGoal(1, new AttackKrawlGoal(this, true, true));
        this.goalSelector.addGoal(1, new FindMineralsGoal(this));
        this.goalSelector.addGoal(1, new ChildFormSearchGoal(this));
        this.goalSelector.addGoal(3, new AvoidKrawlGoal(this, EntityKrawl.class, 10.0F, 0.5D, 0.75D));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, new SpectrobeFollowLeaderGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.5D, true));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new SpectrobeRandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new MasterHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new MasterHurtTargetGoal(this));
        this.targetSelector.addGoal(1, new SpectrobeHurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new TargetKrawlGoal(this, EntityKrawl.class, true, TARGET_KRAWL));
    }

    public static AttributeSupplier.Builder setCustomAttributes() {
        return createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D)
                .add(Attributes.FOLLOW_RANGE, 10.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0D);
    }

    @Override
    public void move(MoverType type, Vec3 pos) {
        if (!isOrderedToSit()) {
            super.move(type, pos);
        }
    }

    @Override
    public boolean isOrderedToSit() {
        return this.entityData.get(STATE) == 1;
    }

    public void setState(int state) {
        this.entityData.set(STATE, state);
    }

    public int getState() {
        return this.entityData.get(STATE);
    }

    public boolean canFly() {
        return false;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (getSpectrobeData() != null) {
            if (!recentInteract && itemStack.isEmpty()) {
                if (player.getUUID().equals(getOwnerUUID()) && player.isShiftKeyDown()) {
                    cycleState(player);
                } else {
                    printSpectrobeToChat(player);
                }
            } else if (itemStack.getItem() instanceof SpectrobeSerumHealingItem serum) {
                healSpectrobe(serum.getSpectrobeHealAmount());

                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }

                return InteractionResult.sidedSuccess(this.level().isClientSide());
            } else if (itemStack.getItem() instanceof SpecialMineralItem mineralItem) {
                applySpecialMineral(mineralItem);

                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }

                return InteractionResult.sidedSuccess(this.level().isClientSide());
            } else if (itemStack.getItem() instanceof MineralItem mineralItem) {
                applyMineral(mineralItem);

                if (!player.getAbilities().instabuild) {
                    itemStack.shrink(1);
                }

                return InteractionResult.sidedSuccess(this.level().isClientSide());
            } else if (itemStack.getItem() instanceof PrizmodItem) {
                if (player.isShiftKeyDown() && player.level().isClientSide()) {
                    SpectrobeGuiHandler.openDetails(getSpectrobeData());
                    return InteractionResult.SUCCESS;
                }
            }
        }

        recentInteract = true;
        ticksTillInteract = 15;
        return super.mobInteract(player, hand);
    }

    public void healSpectrobe(int spectrobeHealAmount) {
        LivingEntity owner = getOwner();

        if (owner != null) {
            PlayerSpectrobeMaster spectrobeMaster = owner.getCapability(SpectrobeMaster.INSTANCE);

            if (spectrobeMaster != null) {
                Spectrobe specData = getSpectrobeData();
                specData.addHealth(spectrobeHealAmount);
                spectrobeMaster.updateSpectrobe(specData);

                if (!this.level().isClientSide()) {
                    SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(spectrobeMaster));
                    owner.sendSystemMessage(Component.literal("Your spectrobe has been healed: " + spectrobeHealAmount + " HP Points."));
                }
            }
        } else if (!this.level().isClientSide()) {
            Spectrobe specData = getSpectrobeData();
            specData.addHealth(spectrobeHealAmount);
            setSpectrobeData(specData);
        }
    }

    private void cycleState(Player player) {
        int oldState = this.entityData.get(STATE);
        int newState = oldState + 1;

        // First check prevents non-child forms from searching.
        // Second check cycles fully for children.
        if ((getStage() != Stage.CHILD && newState > 1) || newState > 2) {
            newState = 0;
        }

        this.entityData.set(STATE, newState);

        if (this.level().isClientSide()) {
            switch (newState) {
                case 0 -> player.sendSystemMessage(Component.literal("Your spectrobe is now following."));
                case 1 -> player.sendSystemMessage(Component.literal("Your spectrobe is now sitting."));
                case 2 -> player.sendSystemMessage(Component.literal("Your spectrobe is now searching."));
                default -> {
                }
            }
        }
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    public void despawn() {
        Spectrobe spectrobeData = this.getSpectrobeData();

        if (spectrobeData != null) {
            spectrobeData.setInactive();
        }

        LivingEntity owner = this.getOwner();

        if (owner != null) {
            PlayerSpectrobeMaster spectrobeMaster = owner.getCapability(SpectrobeMaster.INSTANCE);

            if (spectrobeMaster != null && spectrobeData != null) {
                spectrobeMaster.setSpectrobeInactive(spectrobeData);
            }
        }

        if (this.level().isClientSide()) {
            this.level().addParticle(
                    ParticleTypes.FIREWORK,
                    getX() + 0.5D,
                    getY() + 1.0D,
                    getZ() + 0.5D,
                    0.0D,
                    1.0D,
                    0.0D
            );
        }

        this.remove(RemovalReason.DISCARDED);
    }

    public void despawn(PlayerSpectrobeMaster spectrobeMaster) {
        Spectrobe spectrobeData = this.getSpectrobeData();

        if (spectrobeData != null) {
            spectrobeData.setInactive();
            spectrobeMaster.setSpectrobeInactive(spectrobeData);
        }

        if (this.level().isClientSide()) {
            this.level().addParticle(
                    ParticleTypes.FIREWORK,
                    getX() + 0.5D,
                    getY() + 1.0D,
                    getZ() + 0.5D,
                    0.0D,
                    1.0D,
                    0.0D
            );
        }

        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (source.getDirectEntity() instanceof EntitySpectrobe || source.getDirectEntity() instanceof EntityKrawl) {
            return false;
        }

        return source.is(DamageTypeTags.IS_FIRE) && getNature() == Nature.CORONA;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("SpectrobeData")) {
            setSpectrobeData(Spectrobe.read(compound.getCompound("SpectrobeData")));
        }

        if (getSpectrobeData() == null) {
            setSpectrobeData(GetNewSpectrobeInstance());
        }

        this.entityData.set(HAS_MATED, compound.getBoolean("sterile"));
        updateEntityAttributes();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        Spectrobe spectrobeData = getSpectrobeData();

        if (spectrobeData != null) {
            compound.put("SpectrobeData", spectrobeData.write());
        }

        compound.putBoolean("sterile", this.entityData.get(HAS_MATED));
    }

    public Spectrobe getSpectrobeData() {
        return this.entityData.get(SPECTROBE_DATA);
    }

    public void setSpectrobeData(Spectrobe spectrobe) {
        this.entityData.set(SPECTROBE_DATA, spectrobe);
        updateEntityAttributes();
    }

    public boolean IsAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    public void setIsAttacking(boolean attacking) {
        this.entityData.set(IS_ATTACKING, attacking);
    }

    public int getTicksTillMate() {
        return this.entityData.get(TICKS_TILL_MATE);
    }

    public void setTicksTillMate(int ticksTillMate) {
        this.entityData.set(TICKS_TILL_MATE, ticksTillMate);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(SPECTROBE_DATA, GetNewSpectrobeInstance());
        builder.define(TICKS_TILL_MATE, 400);
        builder.define(STATE, 0);
        builder.define(IS_ATTACKING, false);
        builder.define(HAS_MATED, false);
    }

    public boolean isSearching() {
        return this.entityData.get(STATE) == 2;
    }

    @Override
    public Vec3 getDeltaMovement() {
        if (isOrderedToSit()) {
            return Vec3.ZERO;
        }

        return super.getDeltaMovement();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.isOrderedToSit()) {
            tryMate();
        }

        if (this.ticksTillInteract > 0) {
            this.ticksTillInteract--;
        }

        if (this.ticksTillInteract == 0) {
            this.recentInteract = false;
        }

        if (getOwner() == null && this.tickCount - getLastHurtByMobTimestamp() > 200) {
            this.healSpectrobe(Math.round(getHealth() + (getHealth() / 100.0F)));
        }

        tryEvolve();
    }

    @Override
    public void writeSpawnData(RegistryFriendlyByteBuf buffer) {
        Spectrobe spectrobeData = getSpectrobeData();
        buffer.writeNbt(spectrobeData != null ? spectrobeData.write() : new CompoundTag());
    }

    @Override
    public void readSpawnData(RegistryFriendlyByteBuf additionalData) {
        CompoundTag tag = additionalData.readNbt();

        if (tag != null && !tag.isEmpty()) {
            setSpectrobeData(Spectrobe.read(tag));
            updateEntityAttributes();
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::moveController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public abstract PlayState moveController(AnimationState<EntitySpectrobe> animationState);

    public void tryEvolve() {
        if (hasEvolution() && canEvolve()) {
            evolve();
        }
    }

    public void tryMate() {
        if (getStage() != Stage.CHILD && getOwner() == null && !this.entityData.get(HAS_MATED)) {
            if (getTicksTillMate() == 0) {
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

        if (requirements == null) {
            return false;
        }

        return getSpectrobeData().canEvolve(requirements);
    }

    private void evolve() {
        Spectrobe spectrobeInstance = getSpectrobeData();

        if (!this.level().isClientSide()) {
            EntitySpectrobe evolution = getEvolutionRegistry().create(this.level());

            if (evolution == null) {
                return;
            }

            evolution.moveTo(getX(), getY(), getZ(), 0.0F, 0.0F);
            this.level().addFreshEntity(evolution);
            evolution.setPos(getX(), getY(), getZ());

            spectrobeInstance.evolve(evolution.getSpectrobeData());
            evolution.setSpectrobeData(spectrobeInstance);
            evolution.setCustomName(Component.literal(spectrobeInstance.name));
            updateEntityAttributes();

            LivingEntity owner = getOwner();

            if (owner != null) {
                evolution.setOwnerUUID(getOwnerUUID());

                PlayerSpectrobeMaster spectrobeMaster = owner.getCapability(SpectrobeMaster.INSTANCE);

                if (spectrobeMaster != null) {
                    spectrobeMaster.updateSpectrobe(evolution.getSpectrobeData());

                    if (owner instanceof ServerPlayer serverPlayer) {
                        SpectrobesNetwork.sendToClient(new CSyncSpectrobeMasterPacket(spectrobeMaster), serverPlayer);
                    }

                    evolution.despawn();
                }
            }
        } else {
            LivingEntity owner = getOwner();

            if (owner != null) {
                PlayerSpectrobeMaster spectrobeMaster = owner.getCapability(SpectrobeMaster.INSTANCE);

                if (spectrobeMaster != null) {
                    spectrobeMaster.updateSpectrobe(spectrobeInstance);
                    owner.sendSystemMessage(Component.literal("Your spectrobe has evolved and returned to the prizmod."));
                }
            }

            this.level().addParticle(
                    ParticleTypes.FLASH,
                    getX() + 0.5D,
                    getY() + 1.0D,
                    getZ() + 0.5D,
                    0.0D,
                    0.0D,
                    0.0D
            );
        }

        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public void die(DamageSource cause) {
        LivingEntity owner = getOwner();

        if (owner != null) {
            Spectrobe spectrobeData = getSpectrobeData().copy(true);
            spectrobeData.setCurrentHealth(0);
            setSpectrobeData(spectrobeData);

            PlayerSpectrobeMaster spectrobeMaster = owner.getCapability(SpectrobeMaster.INSTANCE);

            if (spectrobeMaster != null) {
                spectrobeMaster.updateSpectrobe(this.getSpectrobeData());

                if (!this.level().isClientSide() && owner instanceof ServerPlayer serverPlayer) {
                    SpectrobesNetwork.sendToClient(new CSyncSpectrobeMasterPacket(spectrobeMaster), serverPlayer);
                }
            }

            despawn();
        } else {
            getFossil().place(
                    new DirectionalPlaceContext(
                            this.level(),
                            getOnPos(),
                            Direction.UP,
                            new ItemStack(getFossil()),
                            Direction.UP
                    )
            );

            super.die(cause);
        }
    }

    public void setGlowing(boolean glowing) {
        this.setGlowingTag(glowing);
    }

    @Override
    protected void actuallyHurt(DamageSource damageSource, float damageAmount) {
        if (getOwner() != null && getSpectrobeData().properties.getStage() == Stage.CHILD) {
            super.actuallyHurt(damageSource, 0.0F);
            return;
        }

        if (damageSource.getDirectEntity() instanceof EntityKrawl krawl) {
            IHasNature attacker = krawl;
            int advantage = Spectrobe.hasTypeAdvantage(attacker, this);
            int attackPower = krawl.krawlProperties.getAtkLevel();
            float typeBonus = DamageUtils.getTypeBonus(advantage);

            int defensePower = getSpectrobeData().stats.getDefLevel();
            int powerScale = 1;
            float scaledAmount = DamageUtils.getFinalDamageAmount(typeBonus, attackPower, powerScale, defensePower);

            Spectrobe updatedSpectrobe = this.getSpectrobeData().copy(true);
            updatedSpectrobe.damage(Math.round(scaledAmount));
            setSpectrobeData(updatedSpectrobe);

            super.actuallyHurt(damageSource, scaledAmount);
        } else {
            Spectrobe updatedSpectrobe = this.getSpectrobeData().copy(true);
            updatedSpectrobe.damage(Math.round(damageAmount));
            setSpectrobeData(updatedSpectrobe);

            super.actuallyHurt(damageSource, damageAmount);
        }

        updateEntityAttributes();

        LivingEntity owner = getOwner();

        if (owner != null) {
            PlayerSpectrobeMaster spectrobeMaster = owner.getCapability(SpectrobeMaster.INSTANCE);

            if (spectrobeMaster != null) {
                spectrobeMaster.updateSpectrobe(this.getSpectrobeData());

                if (owner instanceof ServerPlayer serverPlayer) {
                    SpectrobesNetwork.sendToClient(new CSyncSpectrobeMasterPacket(spectrobeMaster), serverPlayer);
                }
            }
        }
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        if (getOwner() != null && getTarget() != null) {
            if (getTarget() instanceof EntityKrawl krawl) {
                krawl.setGlowing(false);
            }

            if (getTarget() instanceof EntitySpectrobe spectrobe) {
                spectrobe.setGlowing(false);
            }
        }

        if (getOwner() != null && target != null) {
            if (target instanceof EntityKrawl krawl) {
                krawl.setGlowing(true);
            }

            if (target instanceof EntitySpectrobe spectrobe) {
                spectrobe.setGlowing(true);
            }
        }

        super.setTarget(target);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        setIsAttacking(true);
        return super.doHurtTarget(target);
    }

    @Override
    public void setAggressive(boolean aggressive) {
        if (!aggressive) {
            setIsAttacking(false);
        }

        super.setAggressive(aggressive);
    }

    public void awardKillStats(KrawlProperties krawlProperties) {
        if (this.level().isClientSide()) {
            return;
        }

        Spectrobe spectrobeInstance = getSpectrobeData();
        spectrobeInstance.stats.addStats(krawlProperties);
        updateEntityAttributes();

        LivingEntity owner = getOwner();

        if (owner != null) {
            PlayerSpectrobeMaster spectrobeMaster = owner.getCapability(SpectrobeMaster.INSTANCE);

            if (spectrobeMaster != null) {
                spectrobeMaster.updateSpectrobe(spectrobeInstance);
                spectrobeMaster.addXp(krawlProperties.getXpWorth());
                spectrobeMaster.addGura(krawlProperties.getGuraWorth());

                if (owner instanceof ServerPlayer serverPlayer) {
                    SpectrobesNetwork.sendToClient(new CSyncSpectrobeMasterPacket(spectrobeMaster), serverPlayer);
                }
            }
        }
    }

    public void applySpecialMineral(SpecialMineralItem mineralItem) {
        Spectrobe updatedData = mineralItem.applyEffect(this.getSpectrobeData());

        this.setSpectrobeData(updatedData);
        updateEntityAttributes();

        LivingEntity owner = getOwner();

        if (owner != null) {
            PlayerSpectrobeMaster spectrobeMaster = owner.getCapability(SpectrobeMaster.INSTANCE);

            if (spectrobeMaster != null) {
                spectrobeMaster.updateSpectrobe(updatedData);
            }
        }
    }

    public void applyMineral(MineralItem mineralItem) {
        Spectrobe spectrobeInstance = getSpectrobeData();

        if (spectrobeInstance.properties.getNature().equals(mineralItem.mineral.properties.getNature())
                || mineralItem.mineral.properties.getNature().equals(Nature.OTHER)) {
            spectrobeInstance.applyMineral(mineralItem.mineral.properties);

            updateEntityAttributes();

            LivingEntity owner = getOwner();

            if (owner != null) {
                PlayerSpectrobeMaster spectrobeMaster = owner.getCapability(SpectrobeMaster.INSTANCE);

                if (spectrobeMaster != null) {
                    spectrobeMaster.updateSpectrobe(spectrobeInstance);
                }
            }
        } else {
            LivingEntity owner = getOwner();

            if (owner != null) {
                owner.sendSystemMessage(Component.literal("This mineral is the wrong nature."));
            }
        }
    }

    public void updateEntityAttributes() {
        Spectrobe spectrobeInstance = getSpectrobeData();

        if (spectrobeInstance == null) {
            return;
        }

        AttributeInstance maxHealth = this.getAttribute(Attributes.MAX_HEALTH);

        if (maxHealth != null) {
            maxHealth.setBaseValue(spectrobeInstance.stats.getHpLevel());
        }

        this.setHealth(Math.min(spectrobeInstance.currentHealth, getMaxHealth()));
    }

    @Override
    public double getTick(Object object) {
        return this.tickCount;
    }

    public Nature getNature() {
        return getSpectrobeData().properties.getNature();
    }

    public Stage getStage() {
        return getSpectrobeData().properties.getStage();
    }

    public int getSpectrobeLevel() {
        return getSpectrobeData().stats.getLevel();
    }

    @Override
    public boolean isInLove() {
        return this.entityData.get(TICKS_TILL_MATE) == 0;
    }


    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob ageableMob) {
        return null;
    }

    private void printSpectrobeToChat(Player player) {
        if (!this.level().isClientSide()) {
            return;
        }

        Spectrobe spectrobeInstance = getSpectrobeData();

        player.sendSystemMessage(Component.literal("Name: " + spectrobeInstance.name));
        player.sendSystemMessage(Component.literal("Health: " + spectrobeInstance.currentHealth + "/" + spectrobeInstance.stats.getHpLevel()));
        player.sendSystemMessage(Component.literal("Level: " + getSpectrobeLevel()));
        player.sendSystemMessage(Component.literal(""));
        player.sendSystemMessage(Component.literal("Nature: " + getNature()));
        player.sendSystemMessage(Component.literal("Stage: " + getStage()));
        player.sendSystemMessage(Component.literal(""));
        player.sendSystemMessage(Component.literal("Stats"));
        player.sendSystemMessage(Component.literal("Hp: " + spectrobeInstance.stats.getHpLevel()));
        player.sendSystemMessage(Component.literal("Atk: " + spectrobeInstance.stats.getAtkLevel()));
        player.sendSystemMessage(Component.literal("Def: " + spectrobeInstance.stats.getDefLevel()));

        if (getOwner() != null) {
            String status = switch (this.entityData.get(STATE)) {
                case 0 -> "Following";
                case 1 -> "Sitting";
                case 2 -> "Searching";
                default -> "Unknown.";
            };

            player.sendSystemMessage(Component.literal("Status: " + status));
        }
    }

    public boolean hasFollowers() {
        return this.schoolSize > 1;
    }

    public boolean inRangeOfLeader() {
        return this.leader != null && this.distanceToSqr(this.leader) <= 121.0D;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            if (distanceToSqr(this.leader) > 16.0D) {
                this.getNavigation().moveTo(this.leader, 1.0D);
            } else {
                this.navigation.stop();
            }
        }
    }

    public void addFollowers(Stream<? extends EntitySpectrobe> followers) {
        followers.limit(this.getMaxSchoolSize() - this.schoolSize)
                .filter(follower -> follower != this)
                .forEach(follower -> follower.startFollowing(this));
    }

    protected abstract int getMaxSchoolSize();

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public EntitySpectrobe startFollowing(EntitySpectrobe leader) {
        this.leader = leader;
        leader.addFollower();
        return leader;
    }

    public void stopFollowing() {
        if (this.leader != null) {
            this.leader.removeFollower();
            this.leader = null;
        }
    }

    private void addFollower() {
        ++this.schoolSize;
    }

    private void removeFollower() {
        --this.schoolSize;
    }

    public boolean canBeFollowed() {
        return this.schoolSize < this.getMaxSchoolSize();
    }

    protected abstract FossilBlockItem getFossil();

    protected abstract EntityType<? extends EntitySpectrobe> getChildForLineage();

    public abstract Spectrobe GetNewSpectrobeInstance();

    public abstract EntityType<? extends EntitySpectrobe> getEvolutionRegistry();

    public abstract String getRegistryName();

    public abstract Class<? extends EntitySpectrobe> getSpectrobeClass();

    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }
}