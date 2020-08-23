package com.spectrobes.spectrobesmod.client.entity.spectrobes;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer.*;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomainu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomanoto;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamukabu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamurite;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpikan;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpiko;
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

    private static final Map<String, EntityType<? extends EntitySpectrobe>> SPECTROBES = new HashMap<>();

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = new DeferredRegister<>(ForgeRegistries.ENTITIES, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<EntityType<EntityKomanoto>> ENTITY_KOMANOTO = ENTITY_TYPES.register("entity_komanoto",
            () -> EntityType.Builder.create(EntityKomanoto::new,
                    EntityClassification.CREATURE)
                    .size(1.5f, 1.5f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "entity_komanoto").toString()));

    public static final RegistryObject<EntityType<EntityKomainu>> ENTITY_KOMAINU
            = ENTITY_TYPES.register("entity_komainu",
            () -> EntityType.Builder.create(EntityKomainu::new,
                    EntityClassification.CREATURE)
                    .size(0.5f, 0.75f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "komainu").toString()));

    public static final RegistryObject<EntityType<EntitySpiko>> ENTITY_SPIKO
            = ENTITY_TYPES.register("entity_spiko",
            () -> EntityType.Builder.create(EntitySpiko::new,
                    EntityClassification.CREATURE)
                    .size(0.75f, 0.5f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "spiko").toString()));

    public static final RegistryObject<EntityType<EntitySpikan>> ENTITY_SPIKAN
            = ENTITY_TYPES.register("entity_spikan",
            () -> EntityType.Builder.create(EntitySpikan::new,
                    EntityClassification.CREATURE)
                    .size(1f, 2f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "spikan").toString()));

    public static final RegistryObject<EntityType<EntitySamukabu>> ENTITY_SAMUKABU
            = ENTITY_TYPES.register("entity_samubaku",
            () -> EntityType.Builder.create(EntitySamukabu::new,
                    EntityClassification.CREATURE)
                    .size(1f, 1f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "samubaku").toString()));

    public static final RegistryObject<EntityType<EntitySamurite>> ENTITY_SAMURITE
            = ENTITY_TYPES.register("entity_samurite",
            () -> EntityType.Builder.create(EntitySamurite::new,
                    EntityClassification.CREATURE)
                    .size(1f, 2f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "samurite").toString()));

    public static <T extends Entity> RegistryObject<EntityType<T>> BuildEntity(EntityType.IFactory<T> entity, Class<T> entityClass, float width, float height)
    {
        String name = entityClass.getSimpleName().toLowerCase();
        return ENTITY_TYPES.register(name,
                () -> EntityType.Builder.create(entity, EntityClassification.CREATURE)
                        .size(width, height).build(name));
    }

    public static void init() {
        populateMap();

        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KOMAINU.get(), manager -> new KomainuRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SPIKO.get(), manager -> new SpikoRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KOMANOTO.get(), manager -> new KomanotoRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SPIKAN.get(), manager -> new SpikanRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SAMUKABU.get(), manager -> new SamukabuRenderer(manager));
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_SAMURITE.get(), manager -> new SamuriteRenderer(manager));
    }

    private static void populateMap() {
        SPECTROBES.put("komainu", ENTITY_KOMAINU.get());
        SPECTROBES.put("komanoto", ENTITY_KOMANOTO.get());
        SPECTROBES.put("samukabu", ENTITY_SAMUKABU.get());
        SPECTROBES.put("samurite", ENTITY_SAMURITE.get());
        SPECTROBES.put("spiko", ENTITY_SPIKO.get());
        SPECTROBES.put("spikan", ENTITY_SPIKAN.get());
    }

    public static EntityType<? extends EntitySpectrobe> getByName(String name) throws ClassNotFoundException {
        EntityType<? extends EntitySpectrobe> spectrobe = SPECTROBES.get(name.toLowerCase());
        if(spectrobe != null) {
            return spectrobe;
        }
        throw new ClassNotFoundException("could not find the spectrobe's " +
                "entity registry. " +
                "is its name spelled correctly?");
    }
}
