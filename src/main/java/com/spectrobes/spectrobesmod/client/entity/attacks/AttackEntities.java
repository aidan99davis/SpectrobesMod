package com.spectrobes.spectrobesmod.client.entity.attacks;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.attacks.BasicBlasterEnergyBoltEntity;
import com.spectrobes.spectrobesmod.common.entities.attacks.EnergyBoltEntity;
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

    public static final Supplier<EntityType<BasicBlasterEnergyBoltEntity>> ENTITY_PROJECTILE_BASIC_BLASTER
            = ENTITY_TYPES.register("entity_basic_blaster_shot",
            () -> EntityType.Builder.of(BasicBlasterEnergyBoltEntity::new,
                            MobCategory.MISC)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "basic_blaster_shot").toString()));
}
