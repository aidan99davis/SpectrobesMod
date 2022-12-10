package com.spectrobes.spectrobesmod.common.blocks.fossils.tile;

import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class FossilBlockTileEntity extends BlockEntity implements IAnimatable {
    public final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public FossilBlockTileEntity(BlockPos pos, BlockState state) {
        super(SpectrobesTileRegistry.FOSSIL_TILE.get(), pos, state);
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
