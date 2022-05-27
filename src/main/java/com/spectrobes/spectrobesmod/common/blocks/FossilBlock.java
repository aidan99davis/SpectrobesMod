package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.registry.SpectrobesItemsRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;

import net.minecraft.loot.LootParameters;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FossilBlock extends SpectrobesBlock {
    private static Properties props = Properties.of(Material.STONE)
            .harvestTool(ToolType.PICKAXE)
            .strength(1.5f)
            .sound(SoundType.STONE)
            .harvestLevel(2);

    public FossilBlock() {
        super(props);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        Vector3d vec = builder.getParameter(LootParameters.ORIGIN);
        Biome biome = Minecraft.getInstance().level.getBiome(new BlockPos(vec.x, vec.y, vec.z));

        ItemStack fossilItem;
        switch (biome.getBiomeCategory()) {
            case OCEAN:
            case BEACH:
            case RIVER:
            case SWAMP:
            case ICY:
                fossilItem = SpectrobesItemsRegistry.getRandomFossil(SpectrobeProperties.Nature.FLASH);
                break;
            case DESERT:
            case MESA:
            case SAVANNA:
            case EXTREME_HILLS:
                fossilItem = SpectrobesItemsRegistry.getRandomFossil(SpectrobeProperties.Nature.CORONA);
                break;
            case FOREST:
            case PLAINS:
            case TAIGA:
            case MUSHROOM:
            case JUNGLE:
                fossilItem = SpectrobesItemsRegistry.getRandomFossil(SpectrobeProperties.Nature.AURORA);
                break;
            default:
                fossilItem = SpectrobesItemsRegistry.getRandomFossil();
                break;
        }

        ArrayList fossil = new ArrayList();
        fossil.add(fossilItem);

        return fossil;
    }

}
