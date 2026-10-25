package com.spectrobes.spectrobesmod.client.entity.attacks;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.attacks.BasicEnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.entities.attacks.EnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.entities.attacks.HomingEnergyBoltEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;



public class AttackEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(Registries.ENTITY_TYPE, SpectrobesInfo.MOD_ID);

    public static final Supplier<EntityType<EnergyBoltEntity>> ENTITY_ENERGY_BOLT
            = ENTITY_TYPES.register("entity_energy_bolt",
            () -> EntityType.Builder.of(EnergyBoltEntity::new,
                            MobCategory.MISC)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "energy_bolt").toString()));

    // = = = TESTING = = =
    public static final Supplier<EntityType<BasicEnergyBoltEntity>> ENTITY_PROJECTILE_BASIC
            = ENTITY_TYPES.register("entity_basic_blaster_shot",
            () -> EntityType.Builder.of(BasicEnergyBoltEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "basic_blaster_shot").toString()));

    public static final Supplier<EntityType<HomingEnergyBoltEntity>> ENTITY_PROJECTILE_HOMING
            = ENTITY_TYPES.register("entity_homing_shot",
            () -> EntityType.Builder.of(HomingEnergyBoltEntity::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "homing_shot").toString()));
}
