package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.Spectrobe;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

@EventBusSubscriber(modid = SpectrobesInfo.MOD_ID)
public final class DataSerializerRegistry {

    public static final DeferredRegister<EntityDataSerializer<?>> SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, SpectrobesInfo.MOD_ID);

    public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Spectrobe>> SPECTROBE_SERIALIZER =
            SERIALIZERS.register("spectrobe", SpectrobeSerializer::new);

    private DataSerializerRegistry() {
    }

    public static final class SpectrobeSerializer implements EntityDataSerializer<Spectrobe> {

        private static final StreamCodec<? super RegistryFriendlyByteBuf, Spectrobe> STREAM_CODEC =
                ByteBufCodecs.COMPOUND_TAG.map(
                        Spectrobe::read,
                        Spectrobe::write
                );

        @Override
        public StreamCodec<? super RegistryFriendlyByteBuf, Spectrobe> codec() {
            return STREAM_CODEC;
        }

        @Override
        public Spectrobe copy(Spectrobe value) {
            return value.copy(true);
        }
    }
}