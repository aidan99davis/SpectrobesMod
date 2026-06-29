package com.spectrobes.spectrobesmod.common.registry.blocks;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.*;
import com.spectrobes.spectrobesmod.common.blocks.XellesTrophyBlock;

import com.spectrobes.spectrobesmod.common.blocks.api.MultiTextureBlock;
import com.spectrobes.spectrobesmod.common.blocks.api.SpectrobesBlock;
import com.spectrobes.spectrobesmod.common.blocks.fossils.blocks.*;
import com.spectrobes.spectrobesmod.common.blocks.krawl.KrawlFiberBlock;
import com.spectrobes.spectrobesmod.common.blocks.krawl.MiniXellesBlock;
import com.spectrobes.spectrobesmod.common.blocks.krawl.SpreadingKrawlNestBlock;
import com.spectrobes.spectrobesmod.common.blocks.machines.HealerBlock;
import com.spectrobes.spectrobesmod.common.blocks.machines.shop.CyrusShopBlock;
import com.spectrobes.spectrobesmod.common.blocks.FossilBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpectrobesBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, SpectrobesInfo.MOD_ID);

    public static final Supplier<GrildaFossilBlock> grilda_fossil = BLOCKS.register("grilda_fossil", GrildaFossilBlock::new);
    public static final Supplier<HarumiFossilBlock> harumi_fossil = BLOCKS.register("harumi_fossil", HarumiFossilBlock::new);
    public static final Supplier<KomainuFossilBlock> komainu_fossil = BLOCKS.register("komainu_fossil", KomainuFossilBlock::new);
    public static final Supplier<KubakuFossilBlock> kubaku_fossil = BLOCKS.register("kubaku_fossil", KubakuFossilBlock::new);
    public static final Supplier<NaguFossilBlock> nagu_fossil = BLOCKS.register("nagu_fossil", NaguFossilBlock::new);
    public static final Supplier<SamukabuFossilBlock> samukabu_fossil = BLOCKS.register("samukabu_fossil", SamukabuFossilBlock::new);
    public static final Supplier<SeguFossilBlock> segu_fossil = BLOCKS.register("segu_fossil", SeguFossilBlock::new);
    public static final Supplier<ShakinFossilBlock> shakin_fossil = BLOCKS.register("shakin_fossil", ShakinFossilBlock::new);
    public static final Supplier<SpikoFossilBlock> spiko_fossil = BLOCKS.register("spiko_fossil", SpikoFossilBlock::new);
    public static final Supplier<VilarFossilBlock> vilar_fossil = BLOCKS.register("vilar_fossil", VilarFossilBlock::new);
    public static final Supplier<ZozaFossilBlock> zoza_fossil = BLOCKS.register("zoza_fossil", ZozaFossilBlock::new);
    public static final Supplier<MossariFossilBlock> mossari_fossil = BLOCKS.register("mossari_fossil", MossariFossilBlock::new);
    public static final Supplier<AoiFossilBlock> aoi_fossil = BLOCKS.register("aoi_fossil", AoiFossilBlock::new);
    public static final Supplier<BartorFossilBlock> bartor_fossil = BLOCKS.register("bartor_fossil", BartorFossilBlock::new);
    public static final Supplier<GejioFossilBlock> gejio_fossil = BLOCKS.register("gejio_fossil", GejioFossilBlock::new);
    public static final Supplier<MesaFossilBlock> mesa_fossil = BLOCKS.register("mesa_fossil", MesaFossilBlock::new);
    public static final Supplier<DongorFossilBlock> dongor_fossil = BLOCKS.register("dongor_fossil", DongorFossilBlock::new);
    public static final Supplier<InkanaFossilBlock> inkana_fossil = BLOCKS.register("inkana_fossil", InkanaFossilBlock::new);
    public static final Supplier<DanawaFossilBlock> danawa_fossil = BLOCKS.register("danawa_fossil", DanawaFossilBlock::new);
    public static final Supplier<MasettoFossilBlock> masetto_fossil = BLOCKS.register("masetto_fossil", MasettoFossilBlock::new);
    public static final Supplier<TenkroFossilBlock> tenkro_fossil = BLOCKS.register("tenkro_fossil", TenkroFossilBlock::new);
    public static final Supplier<KasumiFossilBlock> kasumi_fossil = BLOCKS.register("kasumi_fossil", KasumiFossilBlock::new);
    public static final Supplier<HealerBlock> healer_block = BLOCKS.register("healer_block", HealerBlock::new);
    public static final Supplier<CyrusShopBlock> cyrus_shop_block = BLOCKS.register("cyrus_shop", CyrusShopBlock::new);
    public static final Supplier<XellesTrophyBlock> xelles_trophy = BLOCKS.register("xelles_trophy", XellesTrophyBlock::new);
    public static final Supplier<MiniXellesBlock> mini_xelles_block = BLOCKS.register("mini_xelles", MiniXellesBlock::new);

    public static final Supplier<Block> mineral_block = BLOCKS.register("mineral_block", MineralBlock::new);
    public static final Supplier<Block> fossil_block = BLOCKS.register("fossil_block", FossilBlock::new);
    public static final Supplier<Block> metalium_ore = BLOCKS.register("metalium_ore", 
    		() -> new Block(BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.STONE)));
    public static final Supplier<Block> titanium_ore = BLOCKS.register("titanium_ore", 
    		() -> new Block(BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.STONE)));
    public static final Supplier<Block> marble_ore = BLOCKS.register("marble_ore", 
    		() -> new Block(BlockBehaviour.Properties.of().strength(1.5f).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Supplier<Block> metalium_block_horizontal = BLOCKS.register("metalium_block_horizontal", MetaliumBlock::new);
    public static final Supplier<Block> metalium_block_vertical = BLOCKS.register("metalium_block_vertical", MetaliumBlock::new);
    public static final Supplier<Block> titanium_block = BLOCKS.register("titanium_block", 
    		() -> new Block(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.METAL)));
    public static final Supplier<Block> titanium_slab = BLOCKS.register("titanium_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.METAL)));
    public static final Supplier<Block> titanium_stairs = BLOCKS.register("titanium_stairs",
            () -> new StairBlock(titanium_block.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(titanium_block.get())));
    public static final Supplier<Block> minergy_lamp = BLOCKS.register("minergy_lamp",
            () -> new Block(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.METAL)
                    .lightLevel(BlockState -> 15)));
    public static final Supplier<Block> marble_block = BLOCKS.register("marble_block", 
    		() -> new Block(BlockBehaviour.Properties.of().strength(3f).sound(SoundType.GLASS)));
    public static final Supplier<SpectrobesBlock> krawl_nest = BLOCKS.register("krawl_nest",
    		() -> new SpreadingKrawlNestBlock(BlockBehaviour.Properties.of().strength(10f).sound(SoundType.STONE)));
    public static final Supplier<SpectrobesBlock> krawl_stone = BLOCKS.register("krawl_stone",
    		() -> new MultiTextureBlock(BlockBehaviour.Properties.of().strength(10f).sound(SoundType.STONE)));
//    public static final Supplier<PlanetaryTeleporterBlock> planetary_teleporter = BLOCKS.register("planetary_teleporter",
//            PlanetaryTeleporterBlock::new);
    public static final Supplier<Block> krawl_vine = BLOCKS.register("krawl_vine",
    		() -> new VineBlock(BlockBehaviour.Properties.of().noOcclusion().strength(0.2F).sound(SoundType.VINE)));
    public static final Supplier<Block> krawl_fiber = BLOCKS.register("krawl_fiber",
            () -> new KrawlFiberBlock(BlockBehaviour.Properties.of().noOcclusion().strength(0.2F).sound(SoundType.VINE)));
    public static final Supplier<SpectrobesBlock> krawl_mycelium = BLOCKS.register("krawl_mycelium",
    		() -> new MultiTextureBlock(BlockBehaviour.Properties.of().strength(1F).sound(SoundType.WET_GRASS)));
    public static final Supplier<SpectrobesBlock> krawl_mud = BLOCKS.register("krawl_mud",
    		() -> new SpreadingKrawlNestBlock(BlockBehaviour.Properties.of().strength(1F).sound(SoundType.SOUL_SAND)));
    public static final Supplier<Block> snag_log = BLOCKS.register("snag_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y
                            ? MapColor.WOOD
                            : MapColor.COLOR_PURPLE)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()));
    public static final Supplier<SpectrobesBlock> snag_planks = BLOCKS.register("snag_planks",
            () -> new SpectrobesBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()));

    public static final Supplier<Block> krawlshroom = BLOCKS.register("krawlshroom",
            () -> new HugeMushroomBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_GREEN)
                    .strength(0.2F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()));
}
