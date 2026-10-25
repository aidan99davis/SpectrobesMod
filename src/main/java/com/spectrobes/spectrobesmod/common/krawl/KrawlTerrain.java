package com.spectrobes.spectrobesmod.common.krawl;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

/**
 * Which blocks krawl nests may convert, and what they turn into.
 */
public final class KrawlTerrain {
    /** Blocks nests never touch, on top of bedrock, unbreakable blocks and block entities. */
    public static final TagKey<Block> KRAWL_IMMUNE =
            TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "krawl_immune"));

    /** Mobs vortexes will not eat. */
    public static final TagKey<EntityType<?>> KRAWL_INEDIBLE =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SpectrobesInfo.MOD_ID, "krawl_inedible"));

    private KrawlTerrain() {
    }

    public static boolean isImmune(BlockGetter level, BlockPos pos, BlockState state) {
        return state.is(Blocks.BEDROCK)
                || state.is(KRAWL_IMMUNE)
                || state.hasBlockEntity()
                || state.is(SpectrobesBlocks.mini_xelles_block.get())
                || state.getDestroySpeed(level, pos) < 0.0F;
    }

    public static boolean isKrawlBlock(BlockState state) {
        Block block = state.getBlock();

        return block == SpectrobesBlocks.krawl_crust.get()
                || block == SpectrobesBlocks.krawl_flesh.get()
                || block == SpectrobesBlocks.krawl_membrane.get()
                || block == SpectrobesBlocks.krawl_vein.get()
                || block == SpectrobesBlocks.krawl_heart.get()
                || block == SpectrobesBlocks.krawl_mud.get()
                || block == SpectrobesBlocks.krawl_mycelium.get()
                || block == SpectrobesBlocks.krawl_nest.get()
                || block == SpectrobesBlocks.krawl_stone.get()
                || block == SpectrobesBlocks.mini_xelles_block.get();
    }

    /** Small natural plants that infection eats (crops and stems are left alone). */
    public static boolean isEdiblePlant(BlockState state) {
        Block block = state.getBlock();
        return block instanceof BushBlock && !(block instanceof CropBlock) && !(block instanceof StemBlock);
    }

    /**
     * The krawl block a natural block becomes when infected, or null if it cannot be infected.
     * Only dirt, grass, stone, deepslate, sand and gravel are converted; player-made blocks never are.
     */
    @Nullable
    public static BlockState getInfectedState(BlockState state) {
        if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.PODZOL) || state.is(Blocks.MYCELIUM) || state.is(Blocks.MOSS_BLOCK)) {
            return SpectrobesBlocks.krawl_mycelium.get().defaultBlockState();
        }

        if (state.is(BlockTags.DIRT) || state.is(BlockTags.SAND) || state.is(Blocks.GRAVEL) || state.is(Blocks.MUD)) {
            return SpectrobesBlocks.krawl_mud.get().defaultBlockState();
        }

        if (state.is(BlockTags.BASE_STONE_OVERWORLD)) {
            return SpectrobesBlocks.krawl_crust.get().defaultBlockState();
        }

        return null;
    }

    public static BlockState wallBlock(BlockPos pos, boolean chamber) {
        if (hash(pos) % 11 == 0) {
            return SpectrobesBlocks.krawl_vein.get().defaultBlockState();
        }

        return chamber
                ? SpectrobesBlocks.krawl_flesh.get().defaultBlockState()
                : SpectrobesBlocks.krawl_crust.get().defaultBlockState();
    }

    public static BlockState floorBlock() {
        return SpectrobesBlocks.krawl_membrane.get().defaultBlockState();
    }

    private static int hash(BlockPos pos) {
        long h = pos.asLong() * 0x9E3779B97F4A7C15L;
        return (int) ((h >>> 33) & 0x7FFFFFFF);
    }
}
