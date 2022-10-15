package com.spectrobes.spectrobesmod.common.worldgen.structures.krawl_nest;

import com.mojang.serialization.Codec;
import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.client.entity.krawl.KrawlEntities;
import com.spectrobes.spectrobesmod.common.entities.krawl.EntityXelles;
import com.spectrobes.spectrobesmod.common.registry.SpectrobesBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

import static com.spectrobes.spectrobesmod.common.worldgen.WorldGenUtils.*;

public class WorldGenKrawlNest extends Feature<NoFeatureConfig> {
    private static final BlockState KRAWL_NEST_BLOCK = SpectrobesBlocks.krawl_nest.get().defaultBlockState();
    private static final BlockState KRAWL_NEST_BLOCK_2 = SpectrobesBlocks.krawl_mud.get().defaultBlockState();

    public WorldGenKrawlNest(Codec<NoFeatureConfig> pCodec) {
        super(pCodec);
    }

    public void placeSmallGen(ISeedReader worldIn, Random rand, BlockPos pos) {
        generateNestRoom(worldIn, rand, pos);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator p_230362_3_, Random rand, BlockPos position, NoFeatureConfig p_230362_6_) {
        generateNestRoom(world, rand, position);
        return false;
    }

    public void generateNestRoom(ISeedReader world, Random rand, BlockPos position) {
        generateSphere(world, rand, position, 25, 10, KRAWL_NEST_BLOCK, KRAWL_NEST_BLOCK_2);
        generateSphere(world, rand, position, 22, 7, Blocks.AIR.defaultBlockState());
        Direction direction = Direction.getRandom(rand);
        while(direction == Direction.DOWN || direction == Direction.UP) {direction = Direction.getRandom(rand);}
        generateEntrance(world, rand, position.relative(direction, 17).below(4), 4, 4, direction);
        generateCircle(world, position.below(8), 20, 0, SpectrobesBlocks.krawl_stone.get().defaultBlockState());
        if(!world.isClientSide()) {
            EntityXelles xelles = (EntityXelles) KrawlEntities.ENTITY_XELLES.get()
                    .spawn((ServerWorld)world, null, null, position.below(5), SpawnReason.MOB_SUMMONED, false, false);
        }

    }


    private void generateEntrance(IWorld world, Random rand, BlockPos position, int size, int height, Direction direction) {
        BlockPos up = position.above();
        while (up.getY() < world.getHeight(Heightmap.Type.WORLD_SURFACE, up.getX(), up.getZ())) {
            generateCircleRespectSky(world, rand, up, size, height, direction, KRAWL_NEST_BLOCK, KRAWL_NEST_BLOCK_2);
            up = up.above().offset(direction.getNormal());
        }

        generateSphereRespectAir(world, rand, up, size + 4, height + 2, KRAWL_NEST_BLOCK, KRAWL_NEST_BLOCK_2);
        generateSphere(world, rand, up.above(), size + 1, height, Blocks.AIR.defaultBlockState());
    }

}
