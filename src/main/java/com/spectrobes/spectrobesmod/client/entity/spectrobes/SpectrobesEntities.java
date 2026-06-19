package com.spectrobes.spectrobesmod.client.entity.spectrobes;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.*;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.aoi.EntityAoi;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.bartor.EntityBartolor;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.bartor.EntityBartor;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.danawa.EntityDanawa;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.dongor.EntityDongor;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.dongor.EntityDongora;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.gejio.EntityGejio;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda.EntityGrilda;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.grilda.EntityGrilden;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.harumi.EntityHarumi;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.harumi.EntityHarumite;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.inkana.EntityInkana;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kasumi.EntityKasumi;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomainu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.komainu.EntityKomanoto;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKubaku;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.kubaku.EntityKuganon;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.masetto.EntityMasetto;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.mesa.EntityMesa;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.mossari.EntityMossari;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.mossari.EntityMossarito;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.nagu.EntityNagu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.nagu.EntityNaguryu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamukabu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.samubaku.EntitySamurite;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegu;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.segu.EntitySegulos;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin.EntityShakin;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.shakin.EntityShakor;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpikan;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.spiko.EntitySpiko;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.tenkro.EntityTenkro;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilamasta;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.vilar.EntityVilar;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.zoza.EntityZoza;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.zoza.EntityZozane;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

public class SpectrobesEntities {

    private static final Map<String, EntityType<? extends EntitySpectrobe>> SPECTROBES = new HashMap<>();

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(Registries.ENTITY_TYPE, SpectrobesInfo.MOD_ID);

    public static final DeferredHolder<EntityType<EntityKasumi>> ENTITY_KASUMI
            = ENTITY_TYPES.register("entity_kasumi",
            () -> EntityType.Builder.of(EntityKasumi::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.5f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "kasumi").toString()));

    public static final DeferredHolder<EntityType<EntityTenkro>> ENTITY_TENKRO
            = ENTITY_TYPES.register("entity_tenkro",
            () -> EntityType.Builder.of(EntityTenkro::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.5f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "tenkro").toString()));

    public static final DeferredHolder<EntityType<EntityDanawa>> ENTITY_DANAWA
            = ENTITY_TYPES.register("entity_danawa",
            () -> EntityType.Builder.of(EntityDanawa::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.5f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "danawa").toString()));

    public static final DeferredHolder<EntityType<EntityInkana>> ENTITY_INKANA
            = ENTITY_TYPES.register("entity_inkana",
            () -> EntityType.Builder.of(EntityInkana::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.5f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "inkana").toString()));

    public static final DeferredHolder<EntityType<EntityDongor>> ENTITY_DONGOR
            = ENTITY_TYPES.register("entity_dongor",
            () -> EntityType.Builder.of(EntityDongor::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.6f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "dongor").toString()));

    public static final DeferredHolder<EntityType<EntityDongora>> ENTITY_DONGORA
            = ENTITY_TYPES.register("entity_dongora",
            () -> EntityType.Builder.of(EntityDongora::new,
                    MobCategory.MONSTER)
                    .sized(2f, 2f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "dongora").toString()));

    public static final DeferredHolder<EntityType<EntityGejio>> ENTITY_GEJIO
            = ENTITY_TYPES.register("entity_gejio",
            () -> EntityType.Builder.of(EntityGejio::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.6f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "gejio").toString()));

    public static final DeferredHolder<EntityType<EntityKomainu>> ENTITY_KOMAINU
            = ENTITY_TYPES.register("entity_komainu",
            () -> EntityType.Builder.of(EntityKomainu::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.75f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "komainu").toString()));

    public static final DeferredHolder<EntityType<EntityKomanoto>> ENTITY_KOMANOTO
            = ENTITY_TYPES.register("entity_komanoto",
            () -> EntityType.Builder.of(EntityKomanoto::new,
                    MobCategory.MONSTER)
                    .sized(1.5f, 1.5f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "komanoto").toString()));

    public static final DeferredHolder<EntityType<EntitySpiko>> ENTITY_SPIKO
            = ENTITY_TYPES.register("entity_spiko",
            () -> EntityType.Builder.of(EntitySpiko::new,
                    MobCategory.MONSTER)
                    .sized(0.75f, 0.5f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "spiko").toString()));

    public static final DeferredHolder<EntityType<EntitySpikan>> ENTITY_SPIKAN
            = ENTITY_TYPES.register("entity_spikan",
            () -> EntityType.Builder.of(EntitySpikan::new,
                    MobCategory.MONSTER)
                    .sized(1f, 2f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "spikan").toString()));

    public static final DeferredHolder<EntityType<EntitySamukabu>> ENTITY_SAMUKABU
            = ENTITY_TYPES.register("entity_samubaku",
            () -> EntityType.Builder.of(EntitySamukabu::new,
                    MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "samubaku").toString()));

    public static final DeferredHolder<EntityType<EntitySamurite>> ENTITY_SAMURITE
            = ENTITY_TYPES.register("entity_samurite",
            () -> EntityType.Builder.of(EntitySamurite::new,
                    MobCategory.MONSTER)
                    .sized(1f, 2f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "samurite").toString()));

    public static final DeferredHolder<EntityType<EntityKubaku>> ENTITY_KUBAKU
            = ENTITY_TYPES.register("entity_kubaku",
            () -> EntityType.Builder.of(EntityKubaku::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.75f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "kubaku").toString()));

    public static final DeferredHolder<EntityType<EntityKuganon>> ENTITY_KUGANON
            = ENTITY_TYPES.register("entity_kuganon",
            () -> EntityType.Builder.of(EntityKuganon::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.75f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "kuganon").toString()));

    public static final DeferredHolder<EntityType<EntityShakin>> ENTITY_SHAKIN
            = ENTITY_TYPES.register("entity_shakin",
            () -> EntityType.Builder.of(EntityShakin::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.75f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "shakin").toString()));

    public static final DeferredHolder<EntityType<EntityShakor>> ENTITY_SHAKOR
            = ENTITY_TYPES.register("entity_shakor",
            () -> EntityType.Builder.of(EntityShakor::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.75f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "shakor").toString()));

    public static final DeferredHolder<EntityType<EntityVilar>> ENTITY_VILAR
            = ENTITY_TYPES.register("entity_vilar",
            () -> EntityType.Builder.of(EntityVilar::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.75f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "vilar").toString()));

    public static final DeferredHolder<EntityType<EntityVilamasta>> ENTITY_VILAMASTA
            = ENTITY_TYPES.register("entity_vilamasta",
            () -> EntityType.Builder.of(EntityVilamasta::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.75f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "vilamasta").toString()));

    public static final DeferredHolder<EntityType<EntitySegu>> ENTITY_SEGU
            = ENTITY_TYPES.register("entity_segu",
            () -> EntityType.Builder.of(EntitySegu::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.75f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "segu").toString()));

    public static final DeferredHolder<EntityType<EntitySegulos>> ENTITY_SEGULOS
            = ENTITY_TYPES.register("entity_segulos",
            () -> EntityType.Builder.of(EntitySegulos::new,
                    MobCategory.MONSTER)
                    .sized(1.5f, 2f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "segulos").toString()));

    public static final DeferredHolder<EntityType<EntityZoza>> ENTITY_ZOZA
            = ENTITY_TYPES.register("entity_zoza",
            () -> EntityType.Builder.of(EntityZoza::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.75f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "zoza").toString()));

    public static final DeferredHolder<EntityType<EntityZozane>> ENTITY_ZOZANE
            = ENTITY_TYPES.register("entity_zozane",
            () -> EntityType.Builder.of(EntityZozane::new,
                    MobCategory.MONSTER)
                    .sized(1, 2)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "zozane").toString()));

    public static final DeferredHolder<EntityType<EntityHarumi>> ENTITY_HARUMI
            = ENTITY_TYPES.register("entity_harumi",
            () -> EntityType.Builder.of(EntityHarumi::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.75f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "harumi").toString()));

    public static final DeferredHolder<EntityType<EntityHarumite>> ENTITY_HARUMITE
            = ENTITY_TYPES.register("entity_harumite",
            () -> EntityType.Builder.of(EntityHarumite::new,
                    MobCategory.MONSTER)
                    .sized(1.5f, 1.5f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "harumite").toString()));

    public static final DeferredHolder<EntityType<EntityGrilda>> ENTITY_GRILDA
            = ENTITY_TYPES.register("entity_grilda",
            () -> EntityType.Builder.of(EntityGrilda::new,
                    MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "grilda").toString()));

    public static final DeferredHolder<EntityType<EntityGrilden>> ENTITY_GRILDEN
            = ENTITY_TYPES.register("entity_grilden",
            () -> EntityType.Builder.of(EntityGrilden::new,
                    MobCategory.MONSTER)
                    .sized(2f, 2f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "grilden").toString()));

    public static final DeferredHolder<EntityType<EntityNagu>> ENTITY_NAGU
            = ENTITY_TYPES.register("entity_nagu",
            () -> EntityType.Builder.of(EntityNagu::new,
                    MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "nagu").toString()));

    public static final DeferredHolder<EntityType<EntityNaguryu>> ENTITY_NAGURYU
            = ENTITY_TYPES.register("entity_naguryu",
            () -> EntityType.Builder.of(EntityNaguryu::new,
                    MobCategory.MONSTER)
                    .sized(2f, 2f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "naguryu").toString()));

    public static final DeferredHolder<EntityType<EntityMossari>> ENTITY_MOSSARI
            = ENTITY_TYPES.register("entity_mossari",
            () -> EntityType.Builder.of(EntityMossari::new,
                    MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "mossari").toString()));

    public static final DeferredHolder<EntityType<EntityMossarito>> ENTITY_MOSSARITO
            = ENTITY_TYPES.register("entity_mossarito",
            () -> EntityType.Builder.of(EntityMossarito::new,
                    MobCategory.MONSTER)
                    .sized(1f, 1.6f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "mossarito").toString()));

    public static final DeferredHolder<EntityType<EntityAoi>> ENTITY_AOI
            = ENTITY_TYPES.register("entity_aoi",
            () -> EntityType.Builder.of(EntityAoi::new,
                    MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "aoi").toString()));

    public static final DeferredHolder<EntityType<EntityMesa>> ENTITY_MESA
            = ENTITY_TYPES.register("entity_mesa",
            () -> EntityType.Builder.of(EntityMesa::new,
                    MobCategory.MONSTER)
                    .sized(0.5f, 0.6f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "mesa").toString()));

    public static final DeferredHolder<EntityType<EntityBartor>> ENTITY_BARTOR
            = ENTITY_TYPES.register("entity_bartor",
            () -> EntityType.Builder.of(EntityBartor::new,
                    MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "bartor").toString()));

    public static final DeferredHolder<EntityType<EntityBartolor>> ENTITY_BARTOLOR
            = ENTITY_TYPES.register("entity_bartolor",
            () -> EntityType.Builder.of(EntityBartolor::new,
                    MobCategory.MONSTER)
                    .sized(1.6f, 1.6f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "bartolor").toString()));

    public static final DeferredHolder<EntityType<EntityMasetto>> ENTITY_MASETTO
            = ENTITY_TYPES.register("entity_masetto",
            () -> EntityType.Builder.of(EntityMasetto::new,
                    MobCategory.MONSTER)
                    .sized(1f, 1f)
                    .build(ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "masetto").toString()));

//    public static <T extends Entity> DeferredHolder<EntityType<T>> BuildEntity(EntityType.IFactory<T> entity, Class<T> entityClass, float width, float height)
//    {
//        String name = entityClass.getSimpleName().toLowerCase();
//        return ENTITY_TYPES.register(name,
//                () -> EntityType.Builder.of(entity, MobCategory.CREATURE)
//                        .sized(width, height).build(name));
//    }

    @SubscribeEvent
    public static void registerEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(SpectrobesEntities.ENTITY_GRILDA.get(), EntityGrilda.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_GRILDEN.get(), EntityGrilden.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_HARUMI.get(), EntityHarumi.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_HARUMITE.get(), EntityHarumite.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_KOMAINU.get(), EntityKomainu.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_KOMANOTO.get(), EntityKomanoto.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_KUBAKU.get(), EntityKubaku.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_KUGANON.get(), EntityKuganon.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_MOSSARI.get(), EntityMossari.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_MOSSARITO.get(), EntityMossarito.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_NAGU.get(), EntityNagu.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_NAGURYU.get(), EntityNaguryu.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_SAMUKABU.get(), EntitySamukabu.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_SAMURITE.get(), EntitySamurite.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_SEGU.get(), EntitySegu.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_SEGULOS.get(), EntitySegulos.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_SHAKIN.get(), EntityShakin.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_SHAKOR.get(), EntityShakor.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_SPIKO.get(), EntitySpiko.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_SPIKAN.get(), EntitySpikan.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_VILAR.get(), EntityVilar.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_VILAMASTA.get(), EntityVilamasta.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_ZOZA.get(), EntityZoza.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_ZOZANE.get(), EntityZozane.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_AOI.get(), EntityAoi.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_BARTOR.get(), EntityBartor.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_BARTOLOR.get(), EntityBartolor.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_GEJIO.get(), EntityGejio.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_MESA.get(), EntityMesa.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_DONGOR.get(), EntityDongor.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_DONGORA.get(), EntityDongora.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_INKANA.get(), EntityInkana.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_DANAWA.get(), EntityDanawa.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_MASETTO.get(), EntityMasetto.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_TENKRO.get(), EntityTenkro.setCustomAttributes().build());
        event.put(SpectrobesEntities.ENTITY_KASUMI.get(), EntityKasumi.setCustomAttributes().build());
    }

    public static void populateMap() {
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
        SPECTROBES.put("segulos", ENTITY_SEGULOS.get());
        SPECTROBES.put("harumi", ENTITY_HARUMI.get());
        SPECTROBES.put("harumite", ENTITY_HARUMITE.get());
        SPECTROBES.put("grilda", ENTITY_GRILDA.get());
        SPECTROBES.put("grilden", ENTITY_GRILDEN.get());
        SPECTROBES.put("zoza", ENTITY_ZOZA.get());
        SPECTROBES.put("zozane", ENTITY_ZOZANE.get());
        SPECTROBES.put("nagu", ENTITY_NAGU.get());
        SPECTROBES.put("naguryu", ENTITY_NAGURYU.get());
        SPECTROBES.put("mossari", ENTITY_MOSSARI.get());
        SPECTROBES.put("mossarito", ENTITY_MOSSARITO.get());
        SPECTROBES.put("aoi", ENTITY_AOI.get());
        SPECTROBES.put("bartor", ENTITY_BARTOR.get());
        SPECTROBES.put("bartolor", ENTITY_BARTOLOR.get());
        SPECTROBES.put("gejio", ENTITY_GEJIO.get());
        SPECTROBES.put("mesa", ENTITY_MESA.get());
        SPECTROBES.put("dongor", ENTITY_DONGOR.get());
        SPECTROBES.put("dongora", ENTITY_DONGORA.get());
        SPECTROBES.put("inkana", ENTITY_INKANA.get());
        SPECTROBES.put("danawa", ENTITY_DANAWA.get());
        SPECTROBES.put("masetto", ENTITY_MASETTO.get());
        SPECTROBES.put("tenkro", ENTITY_TENKRO.get());
        SPECTROBES.put("kasumi", ENTITY_KASUMI.get());
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
