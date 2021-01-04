package com.spectrobes.spectrobesmod.common.blocks.tile;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SpectrobesTileRegistry {
        public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, SpectrobesInfo.MOD_ID);

        public static final RegistryObject<TileEntityType<GrildaFossilBlockTileEntity>> GRILDA_FOSSIL_TILE = TILES.register("grildafossiltile", () -> TileEntityType.Builder.create(GrildaFossilBlockTileEntity::new, SpectrobesBlocks.grilda_fossil.get()).build(null));
}
