package com.spectrobes.spectrobesmod.common.world;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class SpectrobesOreGen {

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registries.CONFIGURED_FEATURE, SpectrobesInfo.MOD_ID);

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registries.PLACED_FEATURE, SpectrobesInfo.MOD_ID);

    private static final TagMatchTest STONE_ORE_REPLACEABLES =
            new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);

    private static final TagMatchTest DEEPSLATE_ORE_REPLACEABLES =
            new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

    // Configured Features
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OWERWORLD_FOSSIL_ORES =
            () -> List.of(
                    OreConfiguration.target(
                            STONE_ORE_REPLACEABLES,
                            SpectrobesBlocks.fossil_block.get().defaultBlockState()
                    ),
                    OreConfiguration.target(
                            DEEPSLATE_ORE_REPLACEABLES,
                            SpectrobesBlocks.fossil_block.get().defaultBlockState()
                    )
            ); // TODO Add Deepslate fossil block variant

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OWERWORLD_MINERAL_ORES =
            () -> List.of(
                    OreConfiguration.target(
                            STONE_ORE_REPLACEABLES,
                            SpectrobesBlocks.mineral_block.get().defaultBlockState()
                    ),
                    OreConfiguration.target(
                            DEEPSLATE_ORE_REPLACEABLES,
                            SpectrobesBlocks.mineral_block.get().defaultBlockState()
                    )
            ); // TODO Add Deepslate mineral block variant

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OWERWORLD_METALIUM_ORES =
            () -> List.of(
                    OreConfiguration.target(
                            STONE_ORE_REPLACEABLES,
                            SpectrobesBlocks.metalium_ore.get().defaultBlockState()
                    ),
                    OreConfiguration.target(
                            DEEPSLATE_ORE_REPLACEABLES,
                            SpectrobesBlocks.metalium_ore.get().defaultBlockState()
                    )
            ); // TODO Add Deepslate metalium ore block variant

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OWERWORLD_TITANIUM_ORES =
            () -> List.of(
                    OreConfiguration.target(
                            STONE_ORE_REPLACEABLES,
                            SpectrobesBlocks.titanium_ore.get().defaultBlockState()
                    ),
                    OreConfiguration.target(
                            DEEPSLATE_ORE_REPLACEABLES,
                            SpectrobesBlocks.titanium_ore.get().defaultBlockState()
                    )
            ); // TODO Add Deepslate titanium ore block variant

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OWERWORLD_MARBLE_ORES =
            () -> List.of(
                    OreConfiguration.target(
                            STONE_ORE_REPLACEABLES,
                            SpectrobesBlocks.marble_ore.get().defaultBlockState()
                    ),
                    OreConfiguration.target(
                            DEEPSLATE_ORE_REPLACEABLES,
                            SpectrobesBlocks.marble_ore.get().defaultBlockState()
                    )
            ); // TODO Add Deepslate marble ore block variant

    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> FOSSIL_ORES =
            CONFIGURED_FEATURES.register("fossil_ore",
                    () -> new ConfiguredFeature<>(
                            Feature.ORE,
                            new OreConfiguration(OWERWORLD_FOSSIL_ORES.get(), 3)
                    ));

    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> MINERAL_ORES =
            CONFIGURED_FEATURES.register("mineral_ore",
                    () -> new ConfiguredFeature<>(
                            Feature.ORE,
                            new OreConfiguration(OWERWORLD_MINERAL_ORES.get(), 12)
                    ));

    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> METALIUM_ORES =
            CONFIGURED_FEATURES.register("metalium_ore",
                    () -> new ConfiguredFeature<>(
                            Feature.ORE,
                            new OreConfiguration(OWERWORLD_METALIUM_ORES.get(), 3, 0.15F)
                    ));

    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> TITANIUM_ORES =
            CONFIGURED_FEATURES.register("titanium_ore",
                    () -> new ConfiguredFeature<>(
                            Feature.ORE,
                            new OreConfiguration(OWERWORLD_TITANIUM_ORES.get(), 3, 0.15F)
                    ));

    public static final DeferredHolder<ConfiguredFeature<?, ?>, ConfiguredFeature<?, ?>> MARBLE_ORES =
            CONFIGURED_FEATURES.register("marble_ore",
                    () -> new ConfiguredFeature<>(
                            Feature.ORE,
                            new OreConfiguration(OWERWORLD_MARBLE_ORES.get(), 3, 0.15F)
                    ));

    // Placed Features
    public static final DeferredHolder<PlacedFeature, PlacedFeature> FOSSIL_ORE_PLACED =
            PLACED_FEATURES.register("fossil_ore_placed",
                    () -> new PlacedFeature(
                            FOSSIL_ORES,
                            commonOrePlacement(
                                    8,
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-16),
                                            VerticalAnchor.absolute(112)
                                    )
                            )
                    ));

    public static final DeferredHolder<PlacedFeature, PlacedFeature> MINERAL_ORE_PLACED =
            PLACED_FEATURES.register("mineral_ore_placed",
                    () -> new PlacedFeature(
                            MINERAL_ORES,
                            commonOrePlacement(
                                    16,
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-16),
                                            VerticalAnchor.absolute(112)
                                    )
                            )
                    ));

    public static final DeferredHolder<PlacedFeature, PlacedFeature> METALIUM_ORE_PLACED =
            PLACED_FEATURES.register("metalium_ore_placed",
                    () -> new PlacedFeature(
                            METALIUM_ORES,
                            commonOrePlacement(
                                    4,
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(32)
                                    )
                            )
                    ));

    public static final DeferredHolder<PlacedFeature, PlacedFeature> TITANIUM_ORE_PLACED =
            PLACED_FEATURES.register("titanium_ore_placed",
                    () -> new PlacedFeature(
                            TITANIUM_ORES,
                            commonOrePlacement(
                                    4,
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(32)
                                    )
                            )
                    ));

    public static final DeferredHolder<PlacedFeature, PlacedFeature> MARBLE_ORE_PLACED =
            PLACED_FEATURES.register("marble_ore_placed",
                    () -> new PlacedFeature(
                            MARBLE_ORES,
                            commonOrePlacement(
                                    4,
                                    HeightRangePlacement.triangle(
                                            VerticalAnchor.absolute(-32),
                                            VerticalAnchor.absolute(32)
                                    )
                            )
                    ));

    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
        PLACED_FEATURES.register(eventBus);
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier countPlacement, PlacementModifier heightRange) {
        return List.of(
                countPlacement,
                InSquarePlacement.spread(),
                heightRange,
                BiomeFilter.biome()
        );
    }

    private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    private static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }
}