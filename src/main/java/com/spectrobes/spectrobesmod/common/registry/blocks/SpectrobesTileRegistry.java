package com.spectrobes.spectrobesmod.common.registry.blocks;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.tile.*;
import com.spectrobes.spectrobesmod.common.blocks.krawl.MiniXellesBlockEntity;
import com.spectrobes.spectrobesmod.common.blocks.machines.shop.CyrusShopBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpectrobesTileRegistry {
        public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, SpectrobesInfo.MOD_ID);

        public static final Supplier<BlockEntityType<GrildaFossilBlockTileEntity>> GRILDA_FOSSIL_TILE = TILES.register("grildafossiltile", () -> BlockEntityType.Builder.of(GrildaFossilBlockTileEntity::new, SpectrobesBlocks.grilda_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<HarumiFossilBlockTileEntity>> HARUMI_FOSSIL_TILE = TILES.register("harumifossiltile", () -> BlockEntityType.Builder.of(HarumiFossilBlockTileEntity::new, SpectrobesBlocks.harumi_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<KomainuFossilBlockTileEntity>> KOMAINU_FOSSIL_TILE = TILES.register("komainufossiltile", () -> BlockEntityType.Builder.of(KomainuFossilBlockTileEntity::new, SpectrobesBlocks.komainu_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<KubakuFossilBlockTileEntity>> KUBAKU_FOSSIL_TILE = TILES.register("kubakufossiltile", () -> BlockEntityType.Builder.of(KubakuFossilBlockTileEntity::new, SpectrobesBlocks.kubaku_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<NaguFossilBlockTileEntity>> NAGU_FOSSIL_TILE = TILES.register("nagufossiltile", () -> BlockEntityType.Builder.of(NaguFossilBlockTileEntity::new, SpectrobesBlocks.nagu_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<SamukabuFossilBlockTileEntity>> SAMUKABU_FOSSIL_TILE = TILES.register("samukabufossiltile", () -> BlockEntityType.Builder.of(SamukabuFossilBlockTileEntity::new, SpectrobesBlocks.samukabu_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<SeguFossilBlockTileEntity>> SEGU_FOSSIL_TILE = TILES.register("segufossiltile", () -> BlockEntityType.Builder.of(SeguFossilBlockTileEntity::new, SpectrobesBlocks.segu_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<ShakinFossilBlockTileEntity>> SHAKIN_FOSSIL_TILE = TILES.register("shakinfossiltile", () -> BlockEntityType.Builder.of(ShakinFossilBlockTileEntity::new, SpectrobesBlocks.shakin_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<SpikoFossilBlockTileEntity>> SPIKO_FOSSIL_TILE = TILES.register("spikofossiltile", () -> BlockEntityType.Builder.of(SpikoFossilBlockTileEntity::new, SpectrobesBlocks.spiko_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<VilarFossilBlockTileEntity>> VILAR_FOSSIL_TILE = TILES.register("vilarfossiltile", () -> BlockEntityType.Builder.of(VilarFossilBlockTileEntity::new, SpectrobesBlocks.vilar_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<ZozaFossilBlockTileEntity>> ZOZA_FOSSIL_TILE = TILES.register("zozafossiltile", () -> BlockEntityType.Builder.of(ZozaFossilBlockTileEntity::new, SpectrobesBlocks.zoza_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<MossariFossilBlockTileEntity>> MOSSARI_FOSSIL_TILE = TILES.register("mossarifossiltile", () -> BlockEntityType.Builder.of(MossariFossilBlockTileEntity::new, SpectrobesBlocks.mossari_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<AoiFossilBlockTileEntity>> AOI_FOSSIL_TILE = TILES.register("aoifossiltile", () -> BlockEntityType.Builder.of(AoiFossilBlockTileEntity::new, SpectrobesBlocks.aoi_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<BartorFossilBlockTileEntity>> BARTOR_FOSSIL_TILE = TILES.register("bartorfossiltile", () -> BlockEntityType.Builder.of(BartorFossilBlockTileEntity::new, SpectrobesBlocks.bartor_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<GejioFossilBlockTileEntity>> GEJIO_FOSSIL_TILE = TILES.register("gejiofossiltile", () -> BlockEntityType.Builder.of(GejioFossilBlockTileEntity::new, SpectrobesBlocks.gejio_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<MesaFossilBlockTileEntity>> MESA_FOSSIL_TILE = TILES.register("mesafossiltile", () -> BlockEntityType.Builder.of(MesaFossilBlockTileEntity::new, SpectrobesBlocks.mesa_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<DongorFossilBlockTileEntity>> DONGOR_FOSSIL_TILE = TILES.register("dongorfossiltile", () -> BlockEntityType.Builder.of(DongorFossilBlockTileEntity::new, SpectrobesBlocks.dongor_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<InkanaFossilBlockTileEntity>> INKANA_FOSSIL_TILE = TILES.register("inkanafossiltile", () -> BlockEntityType.Builder.of(InkanaFossilBlockTileEntity::new, SpectrobesBlocks.inkana_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<DanawaFossilBlockTileEntity>> DANAWA_FOSSIL_TILE = TILES.register("danawafossiltile", () -> BlockEntityType.Builder.of(DanawaFossilBlockTileEntity::new, SpectrobesBlocks.danawa_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<MasettoFossilBlockTileEntity>> MASETTO_FOSSIL_TILE = TILES.register("masettofossiltile", () -> BlockEntityType.Builder.of(MasettoFossilBlockTileEntity::new, SpectrobesBlocks.masetto_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<TenkroFossilBlockTileEntity>> TENKRO_FOSSIL_TILE = TILES.register("tenkrofossiltile", () -> BlockEntityType.Builder.of(TenkroFossilBlockTileEntity::new, SpectrobesBlocks.tenkro_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<KasumiFossilBlockTileEntity>> KASUMI_FOSSIL_TILE = TILES.register("kasumifossiltile", () -> BlockEntityType.Builder.of(KasumiFossilBlockTileEntity::new, SpectrobesBlocks.kasumi_fossil.get()).build(null));
        public static final Supplier<BlockEntityType<FossilBlockTileEntity>> FOSSIL_TILE = TILES.register("fossiltile", () -> BlockEntityType.Builder.of(FossilBlockTileEntity::new, SpectrobesBlocks.fossil_block.get()).build(null));
        public static final Supplier<BlockEntityType<HealerBlockTileEntity>> HEALER_TILE = TILES.register("healertile", () -> BlockEntityType.Builder.of(HealerBlockTileEntity::new, SpectrobesBlocks.healer_block.get()).build(null));
        public static final Supplier<BlockEntityType<CyrusShopBlockEntity>> CYRUS_SHOP_TILE = TILES.register("cyrusshoptile", () -> BlockEntityType.Builder.of(CyrusShopBlockEntity::new, SpectrobesBlocks.cyrus_shop_block.get()).build(null));
        public static final Supplier<BlockEntityType<XellesTrophyBlockTileEntity>> XELLES_TROPHY_TILE = TILES.register("xellestrophytile", () -> BlockEntityType.Builder.of(XellesTrophyBlockTileEntity::new, SpectrobesBlocks.xelles_trophy.get()).build(null));
        public static final Supplier<BlockEntityType<MiniXellesBlockEntity>> MINI_XELLES_TILE = TILES.register("minixellestile", () -> BlockEntityType.Builder.of(MiniXellesBlockEntity::new, SpectrobesBlocks.mini_xelles_block.get()).build(null));
}
