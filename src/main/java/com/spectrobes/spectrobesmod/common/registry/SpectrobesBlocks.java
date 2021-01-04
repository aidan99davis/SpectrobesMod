package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.FossilBlock;
import com.spectrobes.spectrobesmod.common.blocks.GrildaFossilBlock;
import com.spectrobes.spectrobesmod.common.blocks.MineralBlock;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class SpectrobesBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<GrildaFossilBlock> grilda_fossil = BLOCKS.register("grilda_fossil", GrildaFossilBlock::new);
    public static final RegistryObject<Block> mineral_block = BLOCKS.register("mineral_block", MineralBlock::new);
    public static final RegistryObject<Block> fossil_block = BLOCKS.register("fossil_block", FossilBlock::new);
}
