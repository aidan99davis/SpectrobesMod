package com.spectrobes.spectrobesmod.client.entity.krawl;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.krawl.*;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Random;

public class KrawlEntities {

    private static ArrayList<EntityType<? extends EntityKrawl>> AURORA_KRAWL = new ArrayList<>();
    private static ArrayList<EntityType<? extends EntityKrawl>> FLASH_KRAWL = new ArrayList<>();
    private static ArrayList<EntityType<? extends EntityKrawl>> CORONA_KRAWL = new ArrayList<>();
    private static ArrayList<EntityType<? extends EntityKrawl>> OTHER_KRAWL = new ArrayList<>();
    private static ArrayList<EntityType<? extends EntityKrawl>> SPECIAL_KRAWL = new ArrayList<>();

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = new DeferredRegister<>(ForgeRegistries.ENTITIES, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<EntityType<EntitySwar>> ENTITY_SWAR
            = ENTITY_TYPES.register("entity_swar",
            () -> EntityType.Builder.create(EntitySwar::new,
                    EntityClassification.MONSTER)
                    .size(0.5f, 0.75f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "swar").toString()));

    public static final RegistryObject<EntityType<EntitySubar>> ENTITY_SUBAR
            = ENTITY_TYPES.register("entity_subar",
            () -> EntityType.Builder.create(EntitySubar::new,
                    EntityClassification.MONSTER)
                    .size(1.5f, 1.25f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "subar").toString()));

    public static final RegistryObject<EntityType<EntityVizbar>> ENTITY_VIZBAR
            = ENTITY_TYPES.register("entity_vizbar",
            () -> EntityType.Builder.create(EntityVizbar::new,
                    EntityClassification.MONSTER)
                    .size(1.5f, 1.25f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "vizbar").toString()));

    public static final RegistryObject<EntityType<EntityGris>> ENTITY_GRIS
            = ENTITY_TYPES.register("entity_gris",
            () -> EntityType.Builder.create(EntityGris::new,
                    EntityClassification.MONSTER)
                    .size(1.5f, 1.25f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "gris").toString()));

    public static final RegistryObject<EntityType<EntityGrisen>> ENTITY_GRISEN
            = ENTITY_TYPES.register("entity_grisen",
            () -> EntityType.Builder.create(EntityGrisen::new,
                    EntityClassification.MONSTER)
                    .size(1.5f, 1.25f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "grisen").toString()));

    public static final RegistryObject<EntityType<EntityVortex>> ENTITY_VORTEX
            = ENTITY_TYPES.register("entity_vortex",
            () -> EntityType.Builder.create(EntityVortex::new,
                    EntityClassification.MONSTER)
                    .size(2f, 2f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "vortex").toString()));

    public static void init() {
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
}
