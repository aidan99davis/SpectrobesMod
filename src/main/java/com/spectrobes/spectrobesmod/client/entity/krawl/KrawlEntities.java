package com.spectrobes.spectrobesmod.client.entity.krawl;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.*;
import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class KrawlEntities {

    private static ArrayList<EntityType<? extends EntityKrawl>> AURORA_KRAWL = new ArrayList<>();
    private static ArrayList<EntityType<? extends EntityKrawl>> FLASH_KRAWL = new ArrayList<>();
    private static ArrayList<EntityType<? extends EntityKrawl>> CORONA_KRAWL = new ArrayList<>();
    private static ArrayList<EntityType<? extends EntityKrawl>> OTHER_KRAWL = new ArrayList<>();
    private static ArrayList<EntityType<? extends EntityKrawl>> SPECIAL_KRAWL = new ArrayList<>();

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<EntityType<EntityXelles>> ENTITY_XELLES
            = ENTITY_TYPES.register("entity_xelles",
            () -> EntityType.Builder.of(EntityXelles::new,
                    EntityClassification.MONSTER)
                    .sized(3f, 5f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "xelles").toString()));

    public static final RegistryObject<EntityType<EntityHealingSpore>> ENTITY_HEALING_SPORES
            = ENTITY_TYPES.register("entity_healing_spores",
            () -> EntityType.Builder.of(EntityHealingSpore::new,
                    EntityClassification.MONSTER)
                    .sized(1f, 1f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "healing_spores").toString()));

    public static final RegistryObject<EntityType<EntitySpawningSpore>> ENTITY_SPAWNING_SPORE
            = ENTITY_TYPES.register("entity_spawning_spores",
            () -> EntityType.Builder.of(EntitySpawningSpore::new,
                    EntityClassification.MONSTER)
                    .sized(1f, 1f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "spawning_spores").toString()));

    public static final RegistryObject<EntityType<EntityOrbix>> ENTITY_ORBIX
            = ENTITY_TYPES.register("entity_orbix",
            () -> EntityType.Builder.of(EntityOrbix::new,
                    EntityClassification.MONSTER)
                    .sized(1f, 1.5f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "orbix").toString()));

    public static final RegistryObject<EntityType<EntityOrbux>> ENTITY_ORBUX
            = ENTITY_TYPES.register("entity_orbux",
            () -> EntityType.Builder.of(EntityOrbux::new,
                    EntityClassification.MONSTER)
                    .sized(1f, 1.5f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "orbux").toString()));

    public static final RegistryObject<EntityType<EntitySwar>> ENTITY_SWAR
            = ENTITY_TYPES.register("entity_swar",
            () -> EntityType.Builder.of(EntitySwar::new,
                    EntityClassification.MONSTER)
                    .sized(1f, 2f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "swar").toString()));

    public static final RegistryObject<EntityType<EntitySubar>> ENTITY_SUBAR
            = ENTITY_TYPES.register("entity_subar",
            () -> EntityType.Builder.of(EntitySubar::new,
                    EntityClassification.MONSTER)
                    .sized(1.5f, 1.25f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "subar").toString()));

    public static final RegistryObject<EntityType<EntityVizbar>> ENTITY_VIZBAR
            = ENTITY_TYPES.register("entity_vizbar",
            () -> EntityType.Builder.of(EntityVizbar::new,
                    EntityClassification.MONSTER)
                    .sized(1.5f, 1.25f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "vizbar").toString()));

    public static final RegistryObject<EntityType<EntityGris>> ENTITY_GRIS
            = ENTITY_TYPES.register("entity_gris",
            () -> EntityType.Builder.of(EntityGris::new,
                    EntityClassification.MONSTER)
                    .sized(1.5f, 1.25f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "gris").toString()));

    public static final RegistryObject<EntityType<EntityGrisen>> ENTITY_GRISEN
            = ENTITY_TYPES.register("entity_grisen",
            () -> EntityType.Builder.of(EntityGrisen::new,
                    EntityClassification.MONSTER)
                    .sized(1.5f, 1.25f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "grisen").toString()));

    public static final RegistryObject<EntityType<EntityVortex>> ENTITY_VORTEX
            = ENTITY_TYPES.register("entity_vortex",
            () -> EntityType.Builder.of(EntityVortex::new,
                    EntityClassification.MONSTER)
                    .sized(2f, 2f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "vortex").toString()));

    public static void init() {
        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(KrawlEntities.ENTITY_VORTEX.get(), EntityVortex.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(KrawlEntities.ENTITY_SWAR.get(), EntitySwar.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(KrawlEntities.ENTITY_GRIS.get(), EntityGris.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(KrawlEntities.ENTITY_GRISEN.get(), EntityGrisen.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(KrawlEntities.ENTITY_SUBAR.get(), EntitySubar.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(KrawlEntities.ENTITY_VIZBAR.get(), EntityVizbar.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(KrawlEntities.ENTITY_XELLES.get(), EntityXelles.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(KrawlEntities.ENTITY_ORBIX.get(), EntityOrbix.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(KrawlEntities.ENTITY_ORBUX.get(), EntityOrbux.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(KrawlEntities.ENTITY_HEALING_SPORES.get(), EntityHealingSpore.setCustomAttributes().build());
            GlobalEntityTypeAttributes.put(KrawlEntities.ENTITY_SPAWNING_SPORE.get(), EntitySpawningSpore.setCustomAttributes().build());
        });

        populateMaps();
    }

    private static void populateMaps() {
        SPECIAL_KRAWL.add(ENTITY_VORTEX.get());

        CORONA_KRAWL.add(ENTITY_SWAR.get());
        CORONA_KRAWL.add(ENTITY_GRIS.get());
        FLASH_KRAWL.add(ENTITY_SUBAR.get());
        AURORA_KRAWL.add(ENTITY_VIZBAR.get());
        AURORA_KRAWL.add(ENTITY_GRISEN.get());
        //Emergency backup krawl
        OTHER_KRAWL.add(ENTITY_SWAR.get());
    }

    public static EntityType<? extends EntityKrawl> getByNature(SpectrobeProperties.Nature nature) {
        EntityType<? extends EntityKrawl> toReturn = null;
        switch(nature) {
            case FLASH:
                toReturn = getFlashKrawl();
                break;
            case CORONA:
                toReturn = getCoronaKrawl();
                break;
            case AURORA:
                toReturn = getAuroraKrawl();
                break;
        }
        if(toReturn == null) {
            toReturn = OTHER_KRAWL.get(0);
        }

        return toReturn;
    }

    private static EntityType<? extends EntityKrawl> getCoronaKrawl() {
        if(CORONA_KRAWL.size() == 0) {
            return null;
        }
        int range = CORONA_KRAWL.size();
        int randomInt = new Random().nextInt(range);

        return CORONA_KRAWL.get(randomInt);
    }

    private static EntityType<? extends EntityKrawl> getAuroraKrawl() {
        if(AURORA_KRAWL.size() == 0) {
            return null;
        }
        int range = AURORA_KRAWL.size();
        int randomInt = new Random().nextInt(range);

        return AURORA_KRAWL.get(randomInt);
    }

    private static EntityType<? extends EntityKrawl> getFlashKrawl() {
        if(FLASH_KRAWL.size() == 0) {
            return null;
        }
        int range = FLASH_KRAWL.size();
        int randomInt = new Random().nextInt(range);

        return FLASH_KRAWL.get(randomInt);
    }
//
//    public static EntityType<? extends EntityKrawl> getByNatureAndLevel(SpectrobeProperties.Nature vortexNature, int level) {
//        List<EntityType<? extends EntityKrawl>> options = new ArrayList<>();
//        switch (vortexNature) {
//            case CORONA:
//                options = CORONA_KRAWL.stream().filter(filterKrawlByLevel(level)).collect(Collectors.toList());
//                break;
//            case AURORA:
//                options = AURORA_KRAWL.stream().filter(filterKrawlByLevel(level)).collect(Collectors.toList());
//                break;
//            case FLASH:
//                options = FLASH_KRAWL.stream().filter(filterKrawlByLevel(level)).collect(Collectors.toList());
//                break;
//            case OTHER:
//                options = OTHER_KRAWL.stream().filter(filterKrawlByLevel(level)).collect(Collectors.toList());
//                break;
//        }
//        return options.get(new Random().nextInt(options.size()) - 1);
//
//    }

    public static EntityType<? extends EntityKrawl> getByLevel(int level, World world) {
        List<EntityType<? extends EntityKrawl>> options = new ArrayList<>();
        options.addAll(CORONA_KRAWL.stream().filter(filterKrawlByLevel(level, world)).collect(Collectors.toList()));
        options.addAll(AURORA_KRAWL.stream().filter(filterKrawlByLevel(level, world)).collect(Collectors.toList()));
        options.addAll(FLASH_KRAWL.stream().filter(filterKrawlByLevel(level, world)).collect(Collectors.toList()));
        int rand = new Random().nextInt(options.size());
        return options.get(rand);
    }


    private static Predicate<EntityType<? extends EntityKrawl>> filterKrawlByLevel(int level, World world) {
        return entityType ->
        {
            try {
                return (entityType.create(world)).GetKrawlProperties().getLevel() <= (level == 0? 1 : level);
            } catch (Exception e) {
                SpectrobesInfo.LOGGER.error("COULDNT CALL GetKrawlProperties");
                SpectrobesInfo.LOGGER.error(e);
                return false;
            }
        };
    }

    public static EntityType<? extends EntityKrawl> getBossForDimension(World level) {
        return ENTITY_ORBIX.get();
    }
}
