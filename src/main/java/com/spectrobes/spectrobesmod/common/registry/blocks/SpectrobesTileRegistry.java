package com.spectrobes.spectrobesmod.common.registry.blocks;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.*;
import com.spectrobes.spectrobesmod.common.blocks.machines.entity.CyrusShopBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SpectrobesTileRegistry {
        public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SpectrobesInfo.MOD_ID);

        public static final RegistryObject<BlockEntityType<GrildaFossilBlockTileEntity>> GRILDA_FOSSIL_TILE = TILES.register("grildafossiltile", () -> BlockEntityType.Builder.of(GrildaFossilBlockTileEntity::new, SpectrobesBlocks.grilda_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<HarumiFossilBlockTileEntity>> HARUMI_FOSSIL_TILE = TILES.register("harumifossiltile", () -> BlockEntityType.Builder.of(HarumiFossilBlockTileEntity::new, SpectrobesBlocks.harumi_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<KomainuFossilBlockTileEntity>> KOMAINU_FOSSIL_TILE = TILES.register("komainufossiltile", () -> BlockEntityType.Builder.of(KomainuFossilBlockTileEntity::new, SpectrobesBlocks.komainu_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<KubakuFossilBlockTileEntity>> KUBAKU_FOSSIL_TILE = TILES.register("kubakufossiltile", () -> BlockEntityType.Builder.of(KubakuFossilBlockTileEntity::new, SpectrobesBlocks.kubaku_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<NaguFossilBlockTileEntity>> NAGU_FOSSIL_TILE = TILES.register("nagufossiltile", () -> BlockEntityType.Builder.of(NaguFossilBlockTileEntity::new, SpectrobesBlocks.nagu_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<SamukabuFossilBlockTileEntity>> SAMUKABU_FOSSIL_TILE = TILES.register("samukabufossiltile", () -> BlockEntityType.Builder.of(SamukabuFossilBlockTileEntity::new, SpectrobesBlocks.samukabu_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<SeguFossilBlockTileEntity>> SEGU_FOSSIL_TILE = TILES.register("segufossiltile", () -> BlockEntityType.Builder.of(SeguFossilBlockTileEntity::new, SpectrobesBlocks.segu_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<ShakinFossilBlockTileEntity>> SHAKIN_FOSSIL_TILE = TILES.register("shakinfossiltile", () -> BlockEntityType.Builder.of(ShakinFossilBlockTileEntity::new, SpectrobesBlocks.shakin_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<SpikoFossilBlockTileEntity>> SPIKO_FOSSIL_TILE = TILES.register("spikofossiltile", () -> BlockEntityType.Builder.of(SpikoFossilBlockTileEntity::new, SpectrobesBlocks.spiko_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<VilarFossilBlockTileEntity>> VILAR_FOSSIL_TILE = TILES.register("vilarfossiltile", () -> BlockEntityType.Builder.of(VilarFossilBlockTileEntity::new, SpectrobesBlocks.vilar_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<ZozaFossilBlockTileEntity>> ZOZA_FOSSIL_TILE = TILES.register("zozafossiltile", () -> BlockEntityType.Builder.of(ZozaFossilBlockTileEntity::new, SpectrobesBlocks.zoza_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<MossariFossilBlockTileEntity>> MOSSARI_FOSSIL_TILE = TILES.register("mossarifossiltile", () -> BlockEntityType.Builder.of(MossariFossilBlockTileEntity::new, SpectrobesBlocks.mossari_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<AoiFossilBlockTileEntity>> AOI_FOSSIL_TILE = TILES.register("aoifossiltile", () -> BlockEntityType.Builder.of(AoiFossilBlockTileEntity::new, SpectrobesBlocks.aoi_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<BartorFossilBlockTileEntity>> BARTOR_FOSSIL_TILE = TILES.register("bartorfossiltile", () -> BlockEntityType.Builder.of(BartorFossilBlockTileEntity::new, SpectrobesBlocks.bartor_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<GejioFossilBlockTileEntity>> GEJIO_FOSSIL_TILE = TILES.register("gejiofossiltile", () -> BlockEntityType.Builder.of(GejioFossilBlockTileEntity::new, SpectrobesBlocks.gejio_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<MesaFossilBlockTileEntity>> MESA_FOSSIL_TILE = TILES.register("mesafossiltile", () -> BlockEntityType.Builder.of(MesaFossilBlockTileEntity::new, SpectrobesBlocks.mesa_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<DongorFossilBlockTileEntity>> DONGOR_FOSSIL_TILE = TILES.register("dongorfossiltile", () -> BlockEntityType.Builder.of(DongorFossilBlockTileEntity::new, SpectrobesBlocks.dongor_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<InkanaFossilBlockTileEntity>> INKANA_FOSSIL_TILE = TILES.register("inkanafossiltile", () -> BlockEntityType.Builder.of(InkanaFossilBlockTileEntity::new, SpectrobesBlocks.inkana_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<DanawaFossilBlockTileEntity>> DANAWA_FOSSIL_TILE = TILES.register("danawafossiltile", () -> BlockEntityType.Builder.of(DanawaFossilBlockTileEntity::new, SpectrobesBlocks.danawa_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<MasettoFossilBlockTileEntity>> MASETTO_FOSSIL_TILE = TILES.register("masettofossiltile", () -> BlockEntityType.Builder.of(MasettoFossilBlockTileEntity::new, SpectrobesBlocks.masetto_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<TenkroFossilBlockTileEntity>> TENKRO_FOSSIL_TILE = TILES.register("tenkrofossiltile", () -> BlockEntityType.Builder.of(TenkroFossilBlockTileEntity::new, SpectrobesBlocks.tenkro_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<KasumiFossilBlockTileEntity>> KASUMI_FOSSIL_TILE = TILES.register("kasumifossiltile", () -> BlockEntityType.Builder.of(KasumiFossilBlockTileEntity::new, SpectrobesBlocks.kasumi_fossil.get()).build(null));
        public static final RegistryObject<BlockEntityType<FossilBlockTileEntity>> FOSSIL_TILE = TILES.register("fossiltile", () -> BlockEntityType.Builder.of(FossilBlockTileEntity::new, SpectrobesBlocks.fossil_block.get()).build(null));
        public static final RegistryObject<BlockEntityType<HealerBlockTileEntity>> HEALER_TILE = TILES.register("healertile", () -> BlockEntityType.Builder.of(HealerBlockTileEntity::new, SpectrobesBlocks.healer_block.get()).build(null));
        public static final RegistryObject<BlockEntityType<CyrusShopBlockEntity>> CYRUS_SHOP_TILE = TILES.register("cyrusshoptile", () -> BlockEntityType.Builder.of(CyrusShopBlockEntity::new, SpectrobesBlocks.cyrus_shop_block.get()).build(null));
        public static final RegistryObject<BlockEntityType<XellesTrophyBlockTileEntity>> XELLES_TROPHY_TILE = TILES.register("xellestrophytile", () -> BlockEntityType.Builder.of(XellesTrophyBlockTileEntity::new, SpectrobesBlocks.xelles_trophy.get()).build(null));
}
