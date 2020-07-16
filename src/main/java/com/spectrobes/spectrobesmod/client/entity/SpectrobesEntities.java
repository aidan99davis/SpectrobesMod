package com.spectrobes.spectrobesmod.client.entity;

import com.spectrobes.spectrobesmod.SpectrobesMod;
import com.spectrobes.spectrobesmod.client.entity.renderer.KomainuRenderer;
import com.spectrobes.spectrobesmod.common.entities.komainu.EntityKomainu;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SpectrobesEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES
            = new DeferredRegister<>(ForgeRegistries.ENTITIES, SpectrobesMod.MOD_ID);

    public static final RegistryObject<EntityType<EntityKomainu>> ENTITY_KOMAINU
            = ENTITY_TYPES.register("entity_komainu",
            () -> EntityType.Builder.create(EntityKomainu::new,
                    EntityClassification.MISC)
                    .size(0.6f, 1f)
                    .build(new ResourceLocation(SpectrobesMod.MOD_ID, "komainu").toString()));

    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(SpectrobesEntities.ENTITY_KOMAINU.get(), manager -> new KomainuRenderer(manager));
    }
}
