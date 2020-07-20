package com.spectrobes.spectrobesmod.client.entity;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.renderer.KomainuRenderer;
import com.spectrobes.spectrobesmod.client.entity.renderer.KomanotoRenderer;
import com.spectrobes.spectrobesmod.common.entities.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.entities.komainu.EntityKomainu;
import com.spectrobes.spectrobesmod.common.entities.komainu.EntityKomanoto;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class SpectrobesEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = new DeferredRegister<>(ForgeRegistries.ENTITIES, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<EntityType<EntityKomanoto>> ENTITY_KOMANOTO = ENTITY_TYPES.register("entity_komanoto",
            () -> EntityType.Builder.create(EntityKomanoto::new,
                    EntityClassification.CREATURE)
                    .size(0.6f, 1f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "komanoto").toString()));
    public static final RegistryObject<EntityType<EntityKomainu>> ENTITY_KOMAINU
            = ENTITY_TYPES.register("entity_komainu",
            () -> EntityType.Builder.create(EntityKomainu::new,
                    EntityClassification.CREATURE)
                    .size(0.6f, 1f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "komainu").toString()));

    public static final RegistryObject<EntityType<?>> getRegistryByName(String registryName) {

        for(RegistryObject<EntityType<?>> ro : ENTITY_TYPES.getEntries()) {
            if(ro.get().getRegistryName().toString() == SpectrobesInfo.MOD_ID + ":" + registryName) {
                return ro;
            }
        }
        return null;
    }

    public static <T extends Entity> RegistryObject<EntityType<T>> BuildEntity(EntityType.IFactory<T> entity, Class<T> entityClass, float width, float height)
    {
        String name = entityClass.getSimpleName().toLowerCase();
        return ENTITY_TYPES.register(name,
                () -> EntityType.Builder.create(entity, EntityClassification.CREATURE)
                        .size(width, height).build(name));
    }

    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KOMAINU.get(), manager -> new KomainuRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KOMANOTO.get(), manager -> new KomanotoRenderer(manager));
    }
}
