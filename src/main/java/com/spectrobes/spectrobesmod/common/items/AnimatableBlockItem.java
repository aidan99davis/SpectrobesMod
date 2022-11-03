package com.spectrobes.spectrobesmod.common.items;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;


public abstract class AnimatableBlockItem extends BlockItem implements IAnimatable {

    public AnimationFactory factory = new AnimationFactory(this);

    public AnimatableBlockItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
