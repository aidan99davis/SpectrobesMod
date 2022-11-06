package com.spectrobes.spectrobesmod.common.world;

import com.google.common.base.Suppliers;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = SpectrobesInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpectrobesOreGen {
    public static final DeferredRegister<ConfiguredFeature<?,?>> CONFIGURED_FEATURES
            = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, SpectrobesInfo.MOD_ID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES
            = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, SpectrobesInfo.MOD_ID);

    //Configured Features
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OWERWORLD_FOSSIL_ORES
            = Suppliers.memoize(() -> Arrays.asList(
                    OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
                            SpectrobesBlocks.fossil_block.get().defaultBlockState()),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                            SpectrobesBlocks.fossil_block.get().defaultBlockState()))); //TODO Add DeepSlate fossil block variant

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OWERWORLD_MINERAL_ORES
            = Suppliers.memoize(() -> Arrays.asList(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
                    SpectrobesBlocks.mineral_block.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    SpectrobesBlocks.mineral_block.get().defaultBlockState()))); //TODO Add DeepSlate mineral block variant

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OWERWORLD_METALIUM_ORES
            = Suppliers.memoize(() -> Arrays.asList(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
                    SpectrobesBlocks.metalium_ore.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    SpectrobesBlocks.metalium_ore.get().defaultBlockState()))); //TODO Add DeepSlate metalium ore block variant

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OWERWORLD_TITANIUM_ORES
            = Suppliers.memoize(() -> Arrays.asList(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
                    SpectrobesBlocks.titanium_ore.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    SpectrobesBlocks.titanium_ore.get().defaultBlockState()))); //TODO Add DeepSlate titanium ore block variant

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OWERWORLD_MARBLE_ORES
            = Suppliers.memoize(() -> Arrays.asList(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES,
                    SpectrobesBlocks.marble_ore.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    SpectrobesBlocks.marble_ore.get().defaultBlockState()))); //TODO Add DeepSlate marble ore block variant

    public static final RegistryObject<ConfiguredFeature<?,?>> FOSSIL_ORES = CONFIGURED_FEATURES.register("fossil_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OWERWORLD_FOSSIL_ORES.get(), 2)));

    public static final RegistryObject<ConfiguredFeature<?,?>> MINERAL_ORES = CONFIGURED_FEATURES.register("mineral_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OWERWORLD_MINERAL_ORES.get(), 4)));

    public static final RegistryObject<ConfiguredFeature<?,?>> METALIUM_ORES = CONFIGURED_FEATURES.register("metalium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OWERWORLD_METALIUM_ORES.get(), 3, 0.5f)));

    public static final RegistryObject<ConfiguredFeature<?,?>> TITANIUM_ORES = CONFIGURED_FEATURES.register("titanium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OWERWORLD_TITANIUM_ORES.get(), 3, 0.5f)));

    public static final RegistryObject<ConfiguredFeature<?,?>> MARBLE_ORES = CONFIGURED_FEATURES.register("marble_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OWERWORLD_MARBLE_ORES.get(), 3, 0.5f)));

    //Placed Features
    public static final RegistryObject<PlacedFeature> FOSSIL_ORE_PLACED
            = PLACED_FEATURES.register("fossil_ore_placed",
            () -> new PlacedFeature(FOSSIL_ORES.getHolder().get(),
                    commonOrePlacement(3, //veins per chunk
                            HeightRangePlacement
                                    .triangle(VerticalAnchor.aboveBottom(10),
                                            VerticalAnchor.belowTop(50)))));
    public static final RegistryObject<PlacedFeature> MINERAL_ORE_PLACED
            = PLACED_FEATURES.register("mineral_ore_placed",
            () -> new PlacedFeature(MINERAL_ORES.getHolder().get(),
                    commonOrePlacement(7, //veins per chunk
                            HeightRangePlacement
                                    .triangle(VerticalAnchor.aboveBottom(10),
                                            VerticalAnchor.belowTop(50)))));
    public static final RegistryObject<PlacedFeature> METALIUM_ORE_PLACED
            = PLACED_FEATURES.register("metalium_ore_placed",
            () -> new PlacedFeature(METALIUM_ORES.getHolder().get(),
                    commonOrePlacement(1, //veins per chunk
                            HeightRangePlacement
                                    .triangle(VerticalAnchor.aboveBottom(-80),
                                            VerticalAnchor.aboveBottom(30)))));
    public static final RegistryObject<PlacedFeature> TITANIUM_ORE_PLACED
            = PLACED_FEATURES.register("titanium_ore_placed",
            () -> new PlacedFeature(TITANIUM_ORES.getHolder().get(),
                    commonOrePlacement(1, //veins per chunk
                            HeightRangePlacement
                                    .triangle(VerticalAnchor.aboveBottom(-80),
                                            VerticalAnchor.aboveBottom(30)))));
    public static final RegistryObject<PlacedFeature> MARBLE_ORE_PLACED
            = PLACED_FEATURES.register("marble_ore_placed",
            () -> new PlacedFeature(MARBLE_ORES.getHolder().get(),
                    commonOrePlacement(1, //veins per chunk
                            HeightRangePlacement
                                    .triangle(VerticalAnchor.aboveBottom(-80),
                                            VerticalAnchor.aboveBottom(30)))));

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return Arrays.asList(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}
