package com.spectrobes.spectrobesmod.common.entities.krawl;

import com.spectrobes.spectrobesmod.common.entities.goals.AttackSpectrobeMasterGoal;
import com.spectrobes.spectrobesmod.common.entities.goals.SpawnWaveGoal;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.registry.KrawlRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityVortex extends EntityKrawl {


    private static final DataParameter<Integer> WAVES_REMAINING =
            EntityDataManager.createKey(EntityKrawl.class,
                    DataSerializers.VARINT);

    private List<EntityKrawl> children;

    public EntityVortex(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        children = new ArrayList<>();
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AttackSpectrobeMasterGoal(this, true, true));
        this.goalSelector.addGoal(0, new SpawnWaveGoal(this));
    }

    @Override
    protected void registerData() {
        super.registerData();
        dataManager.register(WAVES_REMAINING, calculateKrawlWaves());
    }

    public List<EntityKrawl> getKrawlWave() {
        return children;
    }


    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        calculateKrawlWaves();
        setNatureByBiome();
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    private void setNatureByBiome() {
        SpectrobeProperties.Nature nature = SpectrobeProperties.Nature.OTHER;

        Biome.Category cat = world.getBiome(getPosition()).getCategory();

        switch (cat) {
            //FLASH
            case TAIGA:
            case OCEAN:
            case ICY:
            case BEACH:
            case RIVER:
            case SWAMP:
                nature = SpectrobeProperties.Nature.FLASH;
                break;
            //CORONA
            case MESA:
            case DESERT:
            case NETHER:
            case SAVANNA:
                nature = SpectrobeProperties.Nature.CORONA;
                break;
            //AURORA
            case FOREST:
            case JUNGLE:
            case PLAINS:
            case MUSHROOM:
            case EXTREME_HILLS:
                nature = SpectrobeProperties.Nature.AURORA;
                break;
            //OTHER
            case THEEND:
            case NONE:
                nature = SpectrobeProperties.Nature.OTHER;
                break;
        }

        krawlProperties.setNature(nature);
    }

    private int calculateKrawlWaves() {
        Random random = new Random();

        return random.nextInt(3) + 1;
    }

    @Override
    public AnimationFactory getFactory() {
        return animationControllers;
    }

    @Override
    public <ENTITY extends EntityKrawl> PlayState moveController(AnimationEvent<ENTITY> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.vortex.spin", true));
        return PlayState.CONTINUE;
    }

    @Override
    protected KrawlProperties GetKrawlProperties() {
        return KrawlRegistry.Vortex_Properties.copy();
    }

    @Override
    public void onDeath(DamageSource source) {
        Random random = new Random();

        int mineralCount = random.nextInt(3);

        ItemStack mineralStack = SpectrobesItems.getRandomMineral();
        mineralStack.grow(mineralCount);

        ItemEntity lvt_10_1_ = new ItemEntity(world,
                this.getPosX() + 0.5D,
                (this.getPosY() + 1),
                this.getPosZ() + 0.5D, mineralStack);
        lvt_10_1_.setDefaultPickupDelay();
        world.addEntity(lvt_10_1_);
        super.onDeath(source);
    }

    public int getWaves() {
        return dataManager.get(WAVES_REMAINING);
    }

    public void validateWave() {
        children.removeIf(entityKrawl -> entityKrawl.getHealth() <= 0);

        if(children.isEmpty()) {
            dataManager.set(WAVES_REMAINING, getWaves() - 1);
        }
    }

    public void addKrawl(EntityKrawl entityKrawl) {
        this.world.addEntity(entityKrawl);
        entityKrawl.setPositionAndUpdate(getPosX(), getPosY(), getPosZ());
        children.add(entityKrawl);
    }
}
