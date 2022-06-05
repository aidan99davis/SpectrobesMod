package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.FossilBlock;
import com.spectrobes.spectrobesmod.common.blocks.HealerBlock;
import com.spectrobes.spectrobesmod.common.blocks.fossils.*;
import com.spectrobes.spectrobesmod.common.blocks.MineralBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
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
    public static final RegistryObject<HealerBlock> healer_block = BLOCKS.register("healer_block", HealerBlock::new);

    public static final RegistryObject<Block> mineral_block = BLOCKS.register("mineral_block", MineralBlock::new);
    public static final RegistryObject<Block> fossil_block = BLOCKS.register("fossil_block", FossilBlock::new);
    
    public static final RegistryObject<Block> metalium_ore = BLOCKS.register("metalium_ore", 
    		() -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(1.5f).sound(SoundType.STONE).harvestLevel(2)));
    
    public static final RegistryObject<Block> titanium_ore = BLOCKS.register("titanium_ore", 
    		() -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(1.5f).sound(SoundType.STONE).harvestLevel(2)));
    
    public static final RegistryObject<Block> marble_ore = BLOCKS.register("marble_ore", 
    		() -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(1.5f).sound(SoundType.STONE).harvestLevel(1).requiresCorrectToolForDrops()));
}
