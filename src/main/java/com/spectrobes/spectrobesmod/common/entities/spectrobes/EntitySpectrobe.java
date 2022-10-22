package com.spectrobes.spectrobesmod.common.entities.spectrobes;

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
import com.spectrobes.spectrobesmod.common.packets.networking.packets.CSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.packets.networking.packets.SSyncSpectrobeMasterPacket;
import com.spectrobes.spectrobesmod.common.spectrobes.EvolutionRequirements;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import com.spectrobes.spectrobesmod.util.DamageUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
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
import java.util.function.Predicate;

public abstract class EntitySpectrobe extends TameableEntity implements IEntityAdditionalSpawnData, IAnimatable, IHasNature {
    public static final Predicate<ItemEntity> MINERAL_SELECTOR = (itemEntity) -> !itemEntity.hasPickUpDelay() && itemEntity.isAlive() && itemEntity.getItem().getItem() instanceof MineralItem;
    private static final Predicate<EntityKrawl> TARGET_KRAWL = (entity) -> !(entity).isVortex();
    private boolean recentInteract = false;
    private int ticksTillInteract = 0;

    private static final DataParameter<Integer> TICKS_TILL_MATE =
            EntityDataManager.defineId(EntitySpectrobe.class,
            DataSerializers.INT);

    private static final DataParameter<Boolean> IS_ATTACKING =
            EntityDataManager.defineId(EntitySpectrobe.class,
                    DataSerializers.BOOLEAN);

    protected static final DataParameter<Boolean> HAS_MATED =
            EntityDataManager.defineId(EntitySpectrobe.class,
                    DataSerializers.BOOLEAN);

    //State 0: following
    //State 1: Sitting
    //state 2: searching
    private static final DataParameter<Integer> STATE =
            EntityDataManager.defineId(EntitySpectrobe.class,
                    DataSerializers.INT);

    private static final DataParameter<Spectrobe> SPECTROBE_DATA =
            EntityDataManager.defineId(EntitySpectrobe.class,
                    Spectrobe.SpectrobeSerializer);

    public AnimationFactory animationControllers = new AnimationFactory(this);
    protected AnimationController moveAnimationController = new AnimationController(this, "moveAnimationController", 10F, this::moveController);


    public EntitySpectrobe(EntityType<? extends EntitySpectrobe> entityTypeIn,
                           World worldIn) {
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
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(2, new MasterHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new MasterHurtTargetGoal(this));
        this.targetSelector.addGoal(1, (new SpectrobeHurtByTargetGoal(this)));
        this.targetSelector.addGoal(3, new TargetKrawlGoal(this, EntityKrawl.class, true, TARGET_KRAWL));
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MonsterEntity.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ATTACK_SPEED, 1f)
                .add(Attributes.FOLLOW_RANGE, 10.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 5.0D);
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
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
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
            } else if(itemstack.getItem() instanceof PrizmodItem && player.isShiftKeyDown()) {
                if(player == getOwner()) {
                    despawn();
                }
            }
        }

        recentInteract = true;
        ticksTillInteract = 15;
        return super.mobInteract(player, hand);
    }

    private void cycleState(PlayerEntity player) {
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
                    player.sendMessage(new StringTextComponent("Your spectrobe is now following."),player.getUUID());
                    break;
                case 1:
                    player.sendMessage(new StringTextComponent("Your spectrobe is now sitting."), player.getUUID());
                    break;
                case 2:
                    player.sendMessage(new StringTextComponent("Your spectrobe is now searching."), player.getUUID());
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
        this.remove(false);
    }

    public void despawn(PlayerSpectrobeMaster compat) {
        this.getSpectrobeData().setInactive();
        compat.setSpectrobeInactive(this.getSpectrobeData());

        if(level.isClientSide()) {
            Minecraft.getInstance().level.addParticle(ParticleTypes.FIREWORK, getX() + 0.5D, getY() + 1.0D, getZ() + 0.5D, 0.0D, 1.0D, 0.0D);
        }
        this.remove(false);
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
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if(compound.get("SpectrobeData") != null) {
            setSpectrobeData(Spectrobe.read((CompoundNBT) compound.get("SpectrobeData")));
        }
        if(getSpectrobeData() == null)
            setSpectrobeData(GetNewSpectrobeInstance());

        entityData.set(HAS_MATED, compound.getBoolean("sterile"));

        updateEntityAttributes();
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
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
    public IPacket<?> getAddEntityPacket() {
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
    public Vector3d getDeltaMovement() {
        if(isOrderedToSit()) {
            return Vector3d.ZERO;
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
        tryEvolve();

    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        if(getSpectrobeData() != null)
            buffer.writeNbt(getSpectrobeData().write());
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
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

    protected abstract EvolutionRequirements getEvolutionRequirements();

    protected boolean canEvolve() {
        EvolutionRequirements requirements = getEvolutionRequirements();
        if(requirements == null)
            return false;

        return getSpectrobeData().canEvolve(requirements);
    }

    private void evolve() {
        Spectrobe spectrobeInstance = getSpectrobeData();
        if(!level.isClientSide()) {
            EntitySpectrobe spectrobe = getEvolutionRegistry().create(level);
            spectrobe.moveTo(getX(), getY(), getZ(), 0.0F, 0.0F);
            this.level.addFreshEntity(spectrobe);
            spectrobe.setPos(getX(), getY(), getZ());
            spectrobeInstance.evolve(spectrobe.getSpectrobeData());
            spectrobe.setSpectrobeData(spectrobeInstance);
            spectrobe.setCustomName(new StringTextComponent(spectrobeInstance.name));
            updateEntityAttributes();
            if(getOwner() != null) {
                getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.updateSpectrobe(spectrobe.getSpectrobeData());
                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm), (ServerPlayerEntity) getOwner());
                });
                spectrobe.setOwnerUUID(getOwnerUUID());
            }
        } else {
            if(getOwner() != null) {
                getOwner().getCapability(PlayerProperties.PLAYER_SPECTROBE_MASTER).ifPresent(sm -> {
                    sm.updateSpectrobe(spectrobeInstance);
                    SpectrobesNetwork.sendToServer(new CSyncSpectrobeMasterPacket(sm));
                });
            }
            level.addParticle(ParticleTypes.FLASH, getX() + 0.5D, getY() + 1.0D, getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
        }

        this.remove();
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
                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm), (ServerPlayerEntity) getOwner());
                }
            });
            despawn();
        } else {
            ItemEntity fossilItem = new ItemEntity(level,
                    this.getX() + 0.5D,
                    (this.getY() + 1),
                    this.getZ() + 0.5D, getFossil().getDefaultInstance());
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
            float typeBonus = DamageUtils.getTypeBonus(advantage, atkPower);

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
                SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm), (ServerPlayerEntity) getOwner());
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
                    sm.addGura(krawlProperties.getGuraWorth());
                    SpectrobesNetwork.sendToClient(new SSyncSpectrobeMasterPacket(sm), (ServerPlayerEntity) getOwner());
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
                Minecraft.getInstance().player.chat("This mineral is the wrong nature.");
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
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity ageable)
    {
        //gonna handle this myself
        return null;
    }

    private void printSpectrobeToChat(PlayerEntity player) {
        if(level.isClientSide()) {
            Spectrobe spectrobeInstance = getSpectrobeData();
            player.sendMessage(new StringTextComponent("Name: " + spectrobeInstance.name), player.getUUID());
            player.sendMessage(new StringTextComponent("Health: " + spectrobeInstance.currentHealth + "/" + spectrobeInstance.stats.getHpLevel()), player.getUUID());
            player.sendMessage(new StringTextComponent("Level: " + getLevel()), player.getUUID());
            player.sendMessage(new StringTextComponent(""), player.getUUID());
            player.sendMessage(new StringTextComponent("Nature: " + getNature()), player.getUUID());
            player.sendMessage(new StringTextComponent("Stage: " + getStage()), player.getUUID());
            player.sendMessage(new StringTextComponent(""), player.getUUID());
            player.sendMessage(new StringTextComponent("Stats"), player.getUUID());
            player.sendMessage(new StringTextComponent("Hp: " + spectrobeInstance.stats.getHpLevel()), player.getUUID());
            player.sendMessage(new StringTextComponent("Atk: " + spectrobeInstance.stats.getAtkLevel()), player.getUUID());
            player.sendMessage(new StringTextComponent("Def: " + spectrobeInstance.stats.getDefLevel()), player.getUUID());
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
            player.sendMessage(new StringTextComponent("Status: " + status), player.getUUID());
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
