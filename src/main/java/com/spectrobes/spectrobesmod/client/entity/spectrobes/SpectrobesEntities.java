package com.spectrobes.spectrobesmod.client.entity.spectrobes;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.spectrobes.renderer.*;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda.EntityGrilda;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda.EntityGrilden;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.harumi.EntityHarumi;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomainu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomanoto;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKubaku;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKuganon;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamukabu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamurite;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin.EntityShakin;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin.EntityShakor;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpikan;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpiko;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilamasta;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class SpectrobesEntities {

    private static final Map<String, EntityType<? extends EntitySpectrobe>> SPECTROBES = new HashMap<>();

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = new DeferredRegister<>(ForgeRegistries.ENTITIES, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<EntityType<EntityKomanoto>> ENTITY_KOMANOTO
            = ENTITY_TYPES.register("entity_komanoto",
            () -> EntityType.Builder.create(EntityKomanoto::new,
                    EntityClassification.CREATURE)
                    .size(1.5f, 1.5f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "komanoto").toString()));

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

    public static final RegistryObject<EntityType<EntityKubaku>> ENTITY_KUBAKU
            = ENTITY_TYPES.register("entity_kubaku",
            () -> EntityType.Builder.create(EntityKubaku::new,
                    EntityClassification.CREATURE)
                    .size(0.5f, 0.75f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "kubaku").toString()));

    public static final RegistryObject<EntityType<EntityKuganon>> ENTITY_KUGANON
            = ENTITY_TYPES.register("entity_kuganon",
            () -> EntityType.Builder.create(EntityKuganon::new,
                    EntityClassification.CREATURE)
                    .size(0.5f, 0.75f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "kuganon").toString()));

    public static final RegistryObject<EntityType<EntityShakin>> ENTITY_SHAKIN
            = ENTITY_TYPES.register("entity_shakin",
            () -> EntityType.Builder.create(EntityShakin::new,
                    EntityClassification.CREATURE)
                    .size(0.5f, 0.75f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "shakin").toString()));

    public static final RegistryObject<EntityType<EntityShakor>> ENTITY_SHAKOR
            = ENTITY_TYPES.register("entity_shakor",
            () -> EntityType.Builder.create(EntityShakor::new,
                    EntityClassification.CREATURE)
                    .size(0.5f, 0.75f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "shakor").toString()));

    public static final RegistryObject<EntityType<EntityVilar>> ENTITY_VILAR
            = ENTITY_TYPES.register("entity_vilar",
            () -> EntityType.Builder.create(EntityVilar::new,
                    EntityClassification.CREATURE)
                    .size(0.5f, 0.75f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "vilar").toString()));

    public static final RegistryObject<EntityType<EntityVilamasta>> ENTITY_VILAMASTA
            = ENTITY_TYPES.register("entity_vilamasta",
            () -> EntityType.Builder.create(EntityVilamasta::new,
                    EntityClassification.CREATURE)
                    .size(0.5f, 0.75f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "vilamasta").toString()));

    public static final RegistryObject<EntityType<EntitySegu>> ENTITY_SEGU
            = ENTITY_TYPES.register("entity_segu",
            () -> EntityType.Builder.create(EntitySegu::new,
                    EntityClassification.CREATURE)
                    .size(0.5f, 0.75f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "segu").toString()));

    public static final RegistryObject<EntityType<EntityHarumi>> ENTITY_HARUMI
            = ENTITY_TYPES.register("entity_harumi",
            () -> EntityType.Builder.create(EntityHarumi::new,
                    EntityClassification.CREATURE)
                    .size(0.5f, 0.75f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "harumi").toString()));

    public static final RegistryObject<EntityType<EntityGrilda>> ENTITY_GRILDA
            = ENTITY_TYPES.register("entity_grilda",
            () -> EntityType.Builder.create(EntityGrilda::new,
                    EntityClassification.CREATURE)
                    .size(1f, 1f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "grilda").toString()));

    public static final RegistryObject<EntityType<EntityGrilden>> ENTITY_GRILDEN
            = ENTITY_TYPES.register("entity_grilden",
            () -> EntityType.Builder.create(EntityGrilden::new,
                    EntityClassification.CREATURE)
                    .size(2f, 2f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "grilden").toString()));

    public static <T extends Entity> RegistryObject<EntityType<T>> BuildEntity(EntityType.IFactory<T> entity, Class<T> entityClass, float width, float height)
    {
        String name = entityClass.getSimpleName().toLowerCase();
        return ENTITY_TYPES.register(name,
                () -> EntityType.Builder.create(entity, EntityClassification.CREATURE)
                        .size(width, height).build(name));
    }

    public static void init() {
        populateMap();
    }

    private static void populateMap() {
        SPECTROBES.put("komainu", ENTITY_KOMAINU.get());
        SPECTROBES.put("komanoto", ENTITY_KOMANOTO.get());
        SPECTROBES.put("samukabu", ENTITY_SAMUKABU.get());
        SPECTROBES.put("samurite", ENTITY_SAMURITE.get());
        SPECTROBES.put("spiko", ENTITY_SPIKO.get());
        SPECTROBES.put("spikan", ENTITY_SPIKAN.get());
        SPECTROBES.put("kubaku", ENTITY_KUBAKU.get());
        SPECTROBES.put("kuganon", ENTITY_KUGANON.get());
        SPECTROBES.put("shakin", ENTITY_SHAKIN.get());
        SPECTROBES.put("shakor", ENTITY_SHAKOR.get());
        SPECTROBES.put("vilar", ENTITY_VILAR.get());
        SPECTROBES.put("vilamasta", ENTITY_VILAMASTA.get());
        SPECTROBES.put("segu", ENTITY_SEGU.get());
        SPECTROBES.put("harumi", ENTITY_HARUMI.get());
        SPECTROBES.put("grilda", ENTITY_GRILDA.get());
        SPECTROBES.put("grilden", ENTITY_GRILDEN.get());
    }

    public static EntityType<? extends EntitySpectrobe> getByName(String name) throws ClassNotFoundException {
        EntityType<? extends EntitySpectrobe> spectrobe = SPECTROBES.get(name.toLowerCase());
        if(spectrobe != null) {
            return spectrobe;
        }
        throw new ClassNotFoundException("could not find the spectrobe's " +
                "entity registry. " +
                "is its name spelled correctly?: " + name);
    }
}
