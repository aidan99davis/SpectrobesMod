package com.spectrobes.spectrobesmod.common.capability;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public final class SpectrobeMaster {

    private SpectrobeMaster() {
    }

    public static final EntityCapability<PlayerSpectrobeMaster, Void> INSTANCE =
            EntityCapability.createVoid(
                    ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "spectrobemasters"),
                    PlayerSpectrobeMaster.class
            );

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, SpectrobesInfo.MOD_ID);

    public static final Supplier<AttachmentType<PlayerSpectrobeMaster>> PLAYER_SPECTROBE_MASTER =
            ATTACHMENT_TYPES.register(
                    "spectrobemasters",
                    () -> AttachmentType.serializable(PlayerSpectrobeMaster::new)
                            .copyOnDeath()
                            .build()
            );

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerEntity(
                INSTANCE,
                EntityType.PLAYER,
                (player, context) -> player.getData(PLAYER_SPECTROBE_MASTER)
        );
    }
}