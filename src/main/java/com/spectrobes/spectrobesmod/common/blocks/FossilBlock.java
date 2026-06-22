package com.spectrobes.spectrobesmod.common.blocks;

import com.mojang.serialization.MapCodec;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.FossilBlockTileEntity;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class FossilBlock extends SpectrobesTileEntityBlock {

    public static final MapCodec<FossilBlock> CODEC = simpleCodec(FossilBlock::new);

    private static final BlockBehaviour.Properties PROPS = BlockBehaviour.Properties.of()
            .mapColor(MapColor.STONE)
            .noOcclusion()
            .requiresCorrectToolForDrops()
            .strength(1.5F)
            .sound(SoundType.STONE);

    public FossilBlock() {
        this(PROPS);
    }

    private FossilBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends FossilBlock> codec() {
        return CODEC;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        Vec3 origin = builder.getParameter(LootContextParams.ORIGIN);
        Level level = builder.getLevel();

        BlockPos blockPos = BlockPos.containing(origin.x, origin.y, origin.z);
        Biome biome = level.getBiome(blockPos).value();

        List<SpectrobeProperties.Nature> possibleNatures = new ArrayList<>();

        Biome.Precipitation precipitation = biome.getPrecipitationAt(blockPos);

        if (precipitation == Biome.Precipitation.RAIN || precipitation == Biome.Precipitation.SNOW) {
            possibleNatures.add(SpectrobeProperties.Nature.FLASH);
        }

        if (biome.getBaseTemperature() >= 0.5F
                || precipitation == Biome.Precipitation.NONE
                || biome.warmEnoughToRain(blockPos)) {
            possibleNatures.add(SpectrobeProperties.Nature.CORONA);
        }

        if (!biome.getGenerationSettings().getFlowerFeatures().isEmpty()) {
            possibleNatures.add(SpectrobeProperties.Nature.AURORA);
        }

        possibleNatures.add(SpectrobeProperties.Nature.OTHER);

        SpectrobeProperties.Nature nature = possibleNatures.get(
                level.getRandom().nextInt(possibleNatures.size())
        );

        ItemStack fossilItem = SpectrobesFossilsRegistry.getRandomFossil(nature);

        List<ItemStack> drops = new ArrayList<>();
        drops.add(fossilItem);

        return drops;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new FossilBlockTileEntity(pos, state);
    }
}