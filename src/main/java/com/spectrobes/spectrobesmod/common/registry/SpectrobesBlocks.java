package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.FossilBlock;
import com.spectrobes.spectrobesmod.common.blocks.HealerBlock;
import com.spectrobes.spectrobesmod.common.blocks.SpectrobesBlock;
import com.spectrobes.spectrobesmod.common.blocks.fossils.*;
import com.spectrobes.spectrobesmod.common.blocks.MineralBlock;
import com.spectrobes.spectrobesmod.common.items.SpectrobesItems;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
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
    public static final RegistryObject<HealerBlock> healer_block = BLOCKS.register("healer_block", HealerBlock::new);

    public static final RegistryObject<Block> mineral_block = BLOCKS.register("mineral_block", MineralBlock::new);
    public static final RegistryObject<Block> fossil_block = BLOCKS.register("fossil_block", FossilBlock::new);
}
