package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesFossilsRegistry;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesItemsRegistry;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FossilBlock extends SpectrobesBlock {
    private static final Properties props = Properties.of(Material.STONE)
            .strength(1.5f)
            .sound(SoundType.STONE);

    public FossilBlock() {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return SpectrobesTileRegistry.FOSSIL_TILE.get().create(pos, state);
    }

    @SuppressWarnings({"deprecation"})
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        Vec3 vec = builder.getParameter(LootParameters.ORIGIN);
        Level level = builder.getParameter(LootParameters.BLOCK_ENTITY).getLevel();

        assert level != null;
        Biome biome = level.getBiome(new BlockPos(vec.x, vec.y, vec.z));

        ItemStack fossilItem;
        switch (biome.getBiomeCategory()) {
            case OCEAN:
            case BEACH:
            case RIVER:
            case SWAMP:
            case ICY:
                fossilItem = SpectrobesFossilsRegistry.getRandomFossil(SpectrobeProperties.Nature.FLASH);
                break;
            case DESERT:
            case MESA:
            case SAVANNA:
            case EXTREME_HILLS:
                fossilItem = SpectrobesFossilsRegistry.getRandomFossil(SpectrobeProperties.Nature.CORONA);
                break;
            case FOREST:
            case PLAINS:
            case TAIGA:
            case MUSHROOM:
            case JUNGLE:
                fossilItem = SpectrobesFossilsRegistry.getRandomFossil(SpectrobeProperties.Nature.AURORA);
                break;
            default:
                fossilItem = SpectrobesFossilsRegistry.getRandomFossil();
                break;
        }

        ArrayList<ItemStack> fossil = new ArrayList<>();
        fossil.add(fossilItem);

        return fossil;
    }

}
