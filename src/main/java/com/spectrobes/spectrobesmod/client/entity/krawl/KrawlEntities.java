package com.spectrobes.spectrobesmod.client.entity.krawl;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.renderer.SwarRenderer;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityKrawl;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntitySwar;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class KrawlEntities {

    private static final Map<String, EntityType<? extends EntityKrawl>> KRAWL = new HashMap<>();

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = new DeferredRegister<>(ForgeRegistries.ENTITIES, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<EntityType<EntitySwar>> ENTITY_SWAR
            = ENTITY_TYPES.register("entity_swar",
            () -> EntityType.Builder.create(EntitySwar::new,
                    EntityClassification.CREATURE)
                    .size(0.5f, 0.75f)
                    .build(new ResourceLocation(SpectrobesInfo.MOD_ID, "swar").toString()));

    public static void init() {
        populateMap();

        RenderingRegistry.registerEntityRenderingHandler(KrawlEntities.ENTITY_SWAR.get(), manager -> new SwarRenderer(manager));
    }

    private static void populateMap() {
        KRAWL.put("swar", ENTITY_SWAR.get());
    }

    public static EntityType<? extends EntityKrawl> getByName(String name) throws ClassNotFoundException {
        EntityType<? extends EntityKrawl> krawl = KRAWL.get(name.toLowerCase());
        if(krawl != null) {
            return krawl;
        }
        throw new ClassNotFoundException("could not find the krawl's " +
                "entity registry. " +
                "is its name spelled correctly?");
    }
}
