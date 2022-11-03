package com.spectrobes.spectrobesmod.common.blocks;

import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineralBlock extends SpectrobesBlock {
    private static final Properties props = Properties.of(Material.STONE)
            .requiresCorrectToolForDrops()
            .strength(1.5f)
            .sound(SoundType.STONE)
            .harvestLevel(2);

    public MineralBlock() {
        super(props);
    }

    @Override
    @SuppressWarnings({"NullableProblems", "deprecation"})
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        Random random = new Random();

        int rarityInt = random.nextInt(10);
        Mineral.MineralRarity rarity;

        switch(rarityInt) {
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
        ItemStack mineralItem = SpectrobesItems.getRandomMineral(rarity);
        ArrayList<ItemStack> minerals = new ArrayList<>();
        minerals.add(mineralItem);

        return  minerals;
    }
}
