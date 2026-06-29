package com.spectrobes.spectrobesmod.common.blocks;

import com.mojang.serialization.MapCodec;
import com.spectrobes.spectrobesmod.common.blocks.api.SpectrobesBlock;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.registry.items.SpectrobesMineralsRegistry;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

import java.util.List;

public class MineralBlock extends SpectrobesBlock {

    public static final MapCodec<MineralBlock> CODEC = MapCodec.unit(MineralBlock::new);

    private static final Properties props = Properties.of()
            .requiresCorrectToolForDrops()
            .isValidSpawn((state, level, pos, entityType) -> false)
            .strength(1.5F)
            .sound(SoundType.STONE);

    public MineralBlock() {
        super(props);
    }

    @Override
    protected MapCodec<? extends MineralBlock> codec() {
        return CODEC;
    }

    @Override
    protected List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        RandomSource random = builder.getLevel().getRandom();

        int rarityInt = random.nextInt(10);
        Mineral.MineralRarity rarity;

        switch (rarityInt) {
            case 9:
                rarity = Mineral.MineralRarity.Rare;
                break;
            case 8:
            case 7:
            case 6:
                rarity = Mineral.MineralRarity.Uncommon;
                break;
            default:
                rarity = Mineral.MineralRarity.Common;
                break;
        }

        return List.of(SpectrobesMineralsRegistry.getRandomMineral(rarity));
    }
}