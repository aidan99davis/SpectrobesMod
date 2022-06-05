package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.tile.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SpectrobesTileRegistry {
        public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, SpectrobesInfo.MOD_ID);

        public static final RegistryObject<TileEntityType<GrildaFossilBlockTileEntity>> GRILDA_FOSSIL_TILE = TILES.register("grildafossiltile", () -> TileEntityType.Builder.of(GrildaFossilBlockTileEntity::new, SpectrobesBlocks.grilda_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<HarumiFossilBlockTileEntity>> HARUMI_FOSSIL_TILE = TILES.register("harumifossiltile", () -> TileEntityType.Builder.of(HarumiFossilBlockTileEntity::new, SpectrobesBlocks.harumi_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<KomainuFossilBlockTileEntity>> KOMAINU_FOSSIL_TILE = TILES.register("komainufossiltile", () -> TileEntityType.Builder.of(KomainuFossilBlockTileEntity::new, SpectrobesBlocks.komainu_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<KubakuFossilBlockTileEntity>> KUBAKU_FOSSIL_TILE = TILES.register("kubakufossiltile", () -> TileEntityType.Builder.of(KubakuFossilBlockTileEntity::new, SpectrobesBlocks.kubaku_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<NaguFossilBlockTileEntity>> NAGU_FOSSIL_TILE = TILES.register("nagufossiltile", () -> TileEntityType.Builder.of(NaguFossilBlockTileEntity::new, SpectrobesBlocks.nagu_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<SamukabuFossilBlockTileEntity>> SAMUKABU_FOSSIL_TILE = TILES.register("samukabufossiltile", () -> TileEntityType.Builder.of(SamukabuFossilBlockTileEntity::new, SpectrobesBlocks.samukabu_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<SeguFossilBlockTileEntity>> SEGU_FOSSIL_TILE = TILES.register("segufossiltile", () -> TileEntityType.Builder.of(SeguFossilBlockTileEntity::new, SpectrobesBlocks.segu_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<ShakinFossilBlockTileEntity>> SHAKIN_FOSSIL_TILE = TILES.register("shakinfossiltile", () -> TileEntityType.Builder.of(ShakinFossilBlockTileEntity::new, SpectrobesBlocks.shakin_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<SpikoFossilBlockTileEntity>> SPIKO_FOSSIL_TILE = TILES.register("spikofossiltile", () -> TileEntityType.Builder.of(SpikoFossilBlockTileEntity::new, SpectrobesBlocks.spiko_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<VilarFossilBlockTileEntity>> VILAR_FOSSIL_TILE = TILES.register("vilarfossiltile", () -> TileEntityType.Builder.of(VilarFossilBlockTileEntity::new, SpectrobesBlocks.vilar_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<ZozaFossilBlockTileEntity>> ZOZA_FOSSIL_TILE = TILES.register("zozafossiltile", () -> TileEntityType.Builder.of(ZozaFossilBlockTileEntity::new, SpectrobesBlocks.zoza_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<MossariFossilBlockTileEntity>> MOSSARI_FOSSIL_TILE = TILES.register("mossarifossiltile", () -> TileEntityType.Builder.of(MossariFossilBlockTileEntity::new, SpectrobesBlocks.mossari_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<AoiFossilBlockTileEntity>> AOI_FOSSIL_TILE = TILES.register("aoifossiltile", () -> TileEntityType.Builder.of(AoiFossilBlockTileEntity::new, SpectrobesBlocks.aoi_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<BartorFossilBlockTileEntity>> BARTOR_FOSSIL_TILE = TILES.register("bartorfossiltile", () -> TileEntityType.Builder.of(BartorFossilBlockTileEntity::new, SpectrobesBlocks.bartor_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<GejioFossilBlockTileEntity>> GEJIO_FOSSIL_TILE = TILES.register("gejiofossiltile", () -> TileEntityType.Builder.of(GejioFossilBlockTileEntity::new, SpectrobesBlocks.gejio_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<MesaFossilBlockTileEntity>> MESA_FOSSIL_TILE = TILES.register("mesafossiltile", () -> TileEntityType.Builder.of(MesaFossilBlockTileEntity::new, SpectrobesBlocks.mesa_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<DongorFossilBlockTileEntity>> DONGOR_FOSSIL_TILE = TILES.register("dongorfossiltile", () -> TileEntityType.Builder.of(DongorFossilBlockTileEntity::new, SpectrobesBlocks.dongor_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<InkanaFossilBlockTileEntity>> INKANA_FOSSIL_TILE = TILES.register("inkanafossiltile", () -> TileEntityType.Builder.of(InkanaFossilBlockTileEntity::new, SpectrobesBlocks.inkana_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<HealerBlockTileEntity>> HEALER_TILE = TILES.register("healertile", () -> TileEntityType.Builder.of(HealerBlockTileEntity::new, SpectrobesBlocks.healer_block.get()).build(null));
}
