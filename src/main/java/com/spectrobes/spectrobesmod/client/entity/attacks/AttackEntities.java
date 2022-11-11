package com.spectrobes.spectrobesmod.client.entity.attacks;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.attacks.EnergyBoltEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AttackEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<EntityType<EnergyBoltEntity>> ENTITY_ENERGY_BOLT
            = ENTITY_TYPES.register("entity_energy_bolt",
            () -> EntityType.Builder.of(EnergyBoltEntity::new,
                    MobCategory.MISC)
                    .sized(1f, 1f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "energy_bolt").toString()));
}
