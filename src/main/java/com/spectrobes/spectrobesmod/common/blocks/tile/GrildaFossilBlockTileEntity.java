package com.spectrobes.spectrobesmod.common.blocks.tile;

import net.minecraft.tileentity.TileEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class GrildaFossilBlockTileEntity extends TileEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        return PlayState.STOP;
    }

    public GrildaFossilBlockTileEntity() {
        super(SpectrobesTileRegistry.GRILDA_FOSSIL_TILE.get());
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }
    @Override
    public AnimationFactory getFactory()
    {
        return factory;
    }
}
