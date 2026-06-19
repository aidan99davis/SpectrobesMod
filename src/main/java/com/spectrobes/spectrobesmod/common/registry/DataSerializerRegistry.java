package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = SpectrobesInfo.MOD_ID)
public class DataSerializerRegistry {
    public static final DeferredRegister<EntityDataSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.Keys.ENTITY_DATA_SERIALIZERS, SpectrobesInfo.MOD_ID);
    public static final Supplier<EntityDataSerializer<Spectrobe>> SPECTROBE_SERIALIZER = SERIALIZERS.register("spectrobe_serializer", () -> Spectrobe.SpectrobeSerializer);
}
