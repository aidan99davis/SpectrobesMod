package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.*;
import com.spectrobes.spectrobesmod.common.blocks.XellesTrophyBlock;
import com.spectrobes.spectrobesmod.common.blocks.fossils.*;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SpectrobesBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SpectrobesInfo.MOD_ID);

    public static final RegistryObject<GrildaFossilBlock> grilda_fossil = BLOCKS.register("grilda_fossil", GrildaFossilBlock::new);
    public static final RegistryObject<HarumiFossilBlock> harumi_fossil = BLOCKS.register("harumi_fossil", HarumiFossilBlock::new);
    public static final RegistryObject<KomainuFossilBlock> komainu_fossil = BLOCKS.register("komainu_fossil", KomainuFossilBlock::new);
    public static final RegistryObject<KubakuFossilBlock> kubaku_fossil = BLOCKS.register("kubaku_fossil", KubakuFossilBlock::new);
    public static final RegistryObject<NaguFossilBlock> nagu_fossil = BLOCKS.register("nagu_fossil", NaguFossilBlock::new);
    public static final RegistryObject<SamukabuFossilBlock> samukabu_fossil = BLOCKS.register("samukabu_fossil", SamukabuFossilBlock::new);
    public static final RegistryObject<SeguFossilBlock> segu_fossil = BLOCKS.register("segu_fossil", SeguFossilBlock::new);
    public static final RegistryObject<ShakinFossilBlock> shakin_fossil = BLOCKS.register("shakin_fossil", ShakinFossilBlock::new);
    public static final RegistryObject<SpikoFossilBlock> spiko_fossil = BLOCKS.register("spiko_fossil", SpikoFossilBlock::new);
    public static final RegistryObject<VilarFossilBlock> vilar_fossil = BLOCKS.register("vilar_fossil", VilarFossilBlock::new);
    public static final RegistryObject<ZozaFossilBlock> zoza_fossil = BLOCKS.register("zoza_fossil", ZozaFossilBlock::new);
    public static final RegistryObject<MossariFossilBlock> mossari_fossil = BLOCKS.register("mossari_fossil", MossariFossilBlock::new);
    public static final RegistryObject<AoiFossilBlock> aoi_fossil = BLOCKS.register("aoi_fossil", AoiFossilBlock::new);
    public static final RegistryObject<BartorFossilBlock> bartor_fossil = BLOCKS.register("bartor_fossil", BartorFossilBlock::new);
    public static final RegistryObject<GejioFossilBlock> gejio_fossil = BLOCKS.register("gejio_fossil", GejioFossilBlock::new);
    public static final RegistryObject<MesaFossilBlock> mesa_fossil = BLOCKS.register("mesa_fossil", MesaFossilBlock::new);
    public static final RegistryObject<DongorFossilBlock> dongor_fossil = BLOCKS.register("dongor_fossil", DongorFossilBlock::new);
    public static final RegistryObject<InkanaFossilBlock> inkana_fossil = BLOCKS.register("inkana_fossil", InkanaFossilBlock::new);
    public static final RegistryObject<DanawaFossilBlock> danawa_fossil = BLOCKS.register("danawa_fossil", DanawaFossilBlock::new);
    public static final RegistryObject<MasettoFossilBlock> masetto_fossil = BLOCKS.register("masetto_fossil", MasettoFossilBlock::new);
    public static final RegistryObject<TenkroFossilBlock> tenkro_fossil = BLOCKS.register("tenkro_fossil", TenkroFossilBlock::new);
    public static final RegistryObject<KasumiFossilBlock> kasumi_fossil = BLOCKS.register("kasumi_fossil", KasumiFossilBlock::new);
    public static final RegistryObject<HealerBlock> healer_block = BLOCKS.register("healer_block", HealerBlock::new);
    public static final RegistryObject<XellesTrophyBlock> xelles_trophy = BLOCKS.register("xelles_trophy", XellesTrophyBlock::new);

    public static final RegistryObject<Block> mineral_block = BLOCKS.register("mineral_block", MineralBlock::new);
    public static final RegistryObject<Block> fossil_block = BLOCKS.register("fossil_block", FossilBlock::new);
    public static final RegistryObject<Block> metalium_ore = BLOCKS.register("metalium_ore", 
    		() -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(1.5f).sound(SoundType.STONE).harvestLevel(2)));
    public static final RegistryObject<Block> titanium_ore = BLOCKS.register("titanium_ore", 
    		() -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(1.5f).sound(SoundType.STONE).harvestLevel(2)));
    public static final RegistryObject<Block> marble_ore = BLOCKS.register("marble_ore", 
    		() -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(1.5f).sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> metalium_block_horizontal = BLOCKS.register("metalium_block_horizontal", MetaliumBlock::new);
    public static final RegistryObject<Block> metalium_block_vertical = BLOCKS.register("metalium_block_vertical", MetaliumBlock::new);
    public static final RegistryObject<Block> titanium_block = BLOCKS.register("titanium_block", 
    		() -> new Block(AbstractBlock.Properties.of(Material.METAL).harvestTool(ToolType.PICKAXE).strength(3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> titanium_slab = BLOCKS.register("titanium_slab",
            () -> new SlabBlock(AbstractBlock.Properties.of(Material.METAL).harvestTool(ToolType.PICKAXE).strength(3f).sound(SoundType.METAL)));
    public static final RegistryObject<Block> titanium_stairs = BLOCKS.register("titanium_stairs",
            () -> new StairsBlock(titanium_block.get().defaultBlockState(), AbstractBlock.Properties.copy(titanium_block.get())));
    public static final RegistryObject<Block> minergy_lamp = BLOCKS.register("minergy_lamp",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL).harvestTool(ToolType.PICKAXE).strength(3f).sound(SoundType.METAL)
                    .lightLevel(BlockState -> 15)));
    public static final RegistryObject<Block> marble_block = BLOCKS.register("marble_block", 
    		() -> new Block(AbstractBlock.Properties.of(Material.GLASS).harvestTool(ToolType.PICKAXE).strength(3f).sound(SoundType.GLASS)));
    public static final RegistryObject<SpectrobesBlock> krawl_nest = BLOCKS.register("krawl_nest",
    		() -> new SpectrobesBlock(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(10f).sound(SoundType.STONE)));
    public static final RegistryObject<SpectrobesBlock> krawl_stone = BLOCKS.register("krawl_stone",
    		() -> new MultiTextureBlock(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(10f).sound(SoundType.STONE)));
    public static final RegistryObject<PlanetaryTeleporterBlock> planetary_teleporter = BLOCKS.register("planetary_teleporter",
            PlanetaryTeleporterBlock::new);
    public static final RegistryObject<Block> krawl_vine = BLOCKS.register("krawl_vine",
    		() -> new VineBlock(AbstractBlock.Properties.of(Material.REPLACEABLE_PLANT).noOcclusion().strength(0.2F).sound(SoundType.VINE)));
    public static final RegistryObject<SpectrobesBlock> krawl_mycelium = BLOCKS.register("krawl_mycelium",
    		() -> new MultiTextureBlock(AbstractBlock.Properties.of(Material.DIRT).strength(1F).sound(SoundType.WET_GRASS)));
    public static final RegistryObject<SpectrobesBlock> krawl_mud = BLOCKS.register("krawl_mud",
    		() -> new SpectrobesBlock(AbstractBlock.Properties.of(Material.SNOW).strength(1F).sound(SoundType.SOUL_SAND)));
    public static final RegistryObject<Block> snag_log = BLOCKS.register("snag_log",
    		() -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD, (p_235431_2_) -> p_235431_2_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.WOOD : MaterialColor.COLOR_PURPLE).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<SpectrobesBlock> snag_planks = BLOCKS.register("snag_planks",
    		() ->  new SpectrobesBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> krawlshroom = BLOCKS.register("krawlshroom",
    		() -> new HugeMushroomBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(0.2F).sound(SoundType.WOOD)));
}
