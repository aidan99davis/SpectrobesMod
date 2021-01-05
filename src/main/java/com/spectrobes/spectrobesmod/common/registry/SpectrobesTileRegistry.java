package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.blocks.fossils.SpikoFossilBlock;
import com.spectrobes.spectrobesmod.common.blocks.tile.*;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SpectrobesTileRegistry {
        public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, SpectrobesInfo.MOD_ID);

        public static final RegistryObject<TileEntityType<GrildaFossilBlockTileEntity>> GRILDA_FOSSIL_TILE = TILES.register("grildafossiltile", () -> TileEntityType.Builder.create(GrildaFossilBlockTileEntity::new, SpectrobesBlocks.grilda_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<HarumiFossilBlockTileEntity>> HARUMI_FOSSIL_TILE = TILES.register("harumifossiltile", () -> TileEntityType.Builder.create(HarumiFossilBlockTileEntity::new, SpectrobesBlocks.harumi_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<KomainuFossilBlockTileEntity>> KOMAINU_FOSSIL_TILE = TILES.register("komainufossiltile", () -> TileEntityType.Builder.create(KomainuFossilBlockTileEntity::new, SpectrobesBlocks.komainu_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<KubakuFossilBlockTileEntity>> KUBAKU_FOSSIL_TILE = TILES.register("kubakufossiltile", () -> TileEntityType.Builder.create(KubakuFossilBlockTileEntity::new, SpectrobesBlocks.kubaku_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<NaguFossilBlockTileEntity>> NAGU_FOSSIL_TILE = TILES.register("nagufossiltile", () -> TileEntityType.Builder.create(NaguFossilBlockTileEntity::new, SpectrobesBlocks.nagu_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<SamukabuFossilBlockTileEntity>> SAMUKABU_FOSSIL_TILE = TILES.register("samukabufossiltile", () -> TileEntityType.Builder.create(SamukabuFossilBlockTileEntity::new, SpectrobesBlocks.samukabu_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<SeguFossilBlockTileEntity>> SEGU_FOSSIL_TILE = TILES.register("segufossiltile", () -> TileEntityType.Builder.create(SeguFossilBlockTileEntity::new, SpectrobesBlocks.segu_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<ShakinFossilBlockTileEntity>> SHAKIN_FOSSIL_TILE = TILES.register("shakinfossiltile", () -> TileEntityType.Builder.create(ShakinFossilBlockTileEntity::new, SpectrobesBlocks.shakin_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<SpikoFossilBlockTileEntity>> SPIKO_FOSSIL_TILE = TILES.register("spikofossiltile", () -> TileEntityType.Builder.create(SpikoFossilBlockTileEntity::new, SpectrobesBlocks.spiko_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<VilarFossilBlockTileEntity>> VILAR_FOSSIL_TILE = TILES.register("vilarfossiltile", () -> TileEntityType.Builder.create(VilarFossilBlockTileEntity::new, SpectrobesBlocks.vilar_fossil.get()).build(null));
        public static final RegistryObject<TileEntityType<ZozaFossilBlockTileEntity>> ZOZA_FOSSIL_TILE = TILES.register("zozafossiltile", () -> TileEntityType.Builder.create(ZozaFossilBlockTileEntity::new, SpectrobesBlocks.zoza_fossil.get()).build(null));
}
