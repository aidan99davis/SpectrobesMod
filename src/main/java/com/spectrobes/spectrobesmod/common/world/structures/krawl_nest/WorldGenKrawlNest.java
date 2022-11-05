package com.spectrobes.spectrobesmod.common.world.structures.krawl_nest;

import com.mojang.serialization.Codec;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityXelles;
import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import static com.spectrobes.spectrobesmod.common.world.WorldGenUtils.*;

public class WorldGenKrawlNest extends Feature<NoneFeatureConfiguration> {
    private static final BlockState KRAWL_NEST_BLOCK = SpectrobesBlocks.krawl_nest.get().defaultBlockState();
    private static final BlockState KRAWL_NEST_BLOCK_2 = SpectrobesBlocks.krawl_mud.get().defaultBlockState();

    public WorldGenKrawlNest(Codec<NoneFeatureConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        return place(pContext.config(), pContext.level(), pContext.chunkGenerator(), pContext.random(), pContext.origin());
    }

    public void placeSmallGen(WorldGenLevel worldIn, RandomSource rand, BlockPos pos) {
        generateNestRoom(worldIn, rand, pos);
    }

    @Override
    public boolean place(NoneFeatureConfiguration pConfig, WorldGenLevel pLevel, ChunkGenerator pChunkGenerator, RandomSource pRandom, BlockPos pOrigin) {
        generateNestRoom(pLevel, pRandom, pOrigin);

        return super.place(pConfig, pLevel, pChunkGenerator, pRandom, pOrigin);
    }

    public void generateNestRoom(WorldGenLevel world, RandomSource rand, BlockPos position) {
        generateSphere(world, rand, position, 25, 10, KRAWL_NEST_BLOCK, KRAWL_NEST_BLOCK_2);
        generateSphere(world, rand, position, 22, 7, Blocks.AIR.defaultBlockState());
        Direction direction = Direction.getRandom(rand);
        while(direction == Direction.DOWN || direction == Direction.UP) {direction = Direction.getRandom(rand);}
        generateEntrance(world, rand, position.relative(direction, 17).below(4), 4, 4, direction);
        generateCircle(world, position.below(8), 20, 0, SpectrobesBlocks.krawl_stone.get().defaultBlockState());
        world.setBlock(new BlockPos(position.below(6).getX(), position.below(6).getY(), position.below(6).getZ()), SpectrobesBlocks.krawl_stone.get().defaultBlockState(), 11);
        if(!world.isClientSide()) {
            EntityXelles xelles = (EntityXelles) KrawlEntities.ENTITY_XELLES.get()
                    .spawn((ServerLevel) world, null, null, position.below(5), MobSpawnType.MOB_SUMMONED, false, false);
        }

    }


    private void generateEntrance(WorldGenLevel world, RandomSource rand, BlockPos position, int size, int height, Direction direction) {
        BlockPos up = position.above();
        while (up.getY() < world.getHeight(Heightmap.Types.WORLD_SURFACE, up.getX(), up.getZ())) {
            generateCircleRespectSky(world, rand, up, size, height, direction, KRAWL_NEST_BLOCK, KRAWL_NEST_BLOCK_2);
            up = up.above().offset(direction.getNormal());
        }

        generateSphereRespectAir(world, rand, up, size + 4, height + 2, KRAWL_NEST_BLOCK, KRAWL_NEST_BLOCK_2);
        generateSphere(world, rand, up.above(), size + 1, height, Blocks.AIR.defaultBlockState());
    }

}
