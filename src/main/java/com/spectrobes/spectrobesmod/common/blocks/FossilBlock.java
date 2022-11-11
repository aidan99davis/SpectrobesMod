package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FossilBlock extends SpectrobesBlock {
    private static final Properties props = Properties.of(Material.STONE)
            .requiresCorrectToolForDrops()
            .strength(1.5f)
            .sound(SoundType.STONE);

    public FossilBlock() {
        super(props);
    }

    @SuppressWarnings({"deprecation"})
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        Vec3 vec = builder.getParameter(LootContextParams.ORIGIN);
        Level level = builder.getParameter(LootContextParams.THIS_ENTITY).getLevel();

        assert level != null;
        BlockPos blockPos = new BlockPos(vec.x, vec.y, vec.z);
        Biome biome = level.getBiome(blockPos).get();

        List<SpectrobeProperties.Nature> possibleNatures = new ArrayList<>();
        if(biome.getPrecipitation().equals(Biome.Precipitation.RAIN)
                || biome.getPrecipitation().equals(Biome.Precipitation.SNOW)) {
            possibleNatures.add(SpectrobeProperties.Nature.FLASH);
        }
        if(biome.getBaseTemperature() >= 0.5f
                || biome.getPrecipitation().equals(Biome.Precipitation.NONE)
                || biome.warmEnoughToRain(blockPos)
                || biome.shouldSnowGolemBurn(blockPos)) {
            possibleNatures.add(SpectrobeProperties.Nature.CORONA);
        }
        if(biome.getGenerationSettings().getFlowerFeatures().size() > 0) {
            possibleNatures.add(SpectrobeProperties.Nature.AURORA);
        }

        possibleNatures.add(SpectrobeProperties.Nature.OTHER);

        SpectrobeProperties.Nature nature = possibleNatures.get(new Random().nextInt(possibleNatures.size()));

        ItemStack fossilItem = SpectrobesFossilsRegistry.getRandomFossil(nature);
        ArrayList<ItemStack> fossil = new ArrayList<>();
        fossil.add(fossilItem);

        return fossil;
    }

}
