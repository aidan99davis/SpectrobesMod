package com.spectrobes.spectrobesmod.common.blocks.machines.entity;

import com.spectrobes.spectrobesmod.common.registry.blocks.SpectrobesTileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class CyrusShopBlockEntity extends BlockEntity implements IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        return PlayState.STOP;
    }

    public CyrusShopBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(SpectrobesTileRegistry.CYRUS_SHOP_TILE.get(), pPos, pBlockState);
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
