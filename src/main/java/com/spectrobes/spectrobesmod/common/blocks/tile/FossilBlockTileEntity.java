package com.spectrobes.spectrobesmod.common.blocks.tile;

import com.spectrobes.spectrobesmod.common.registry.SpectrobesTileRegistry;
import net.minecraft.tileentity.TileEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class FossilBlockTileEntity extends TileEntity {
    public FossilBlockTileEntity() {
        super(SpectrobesTileRegistry.FOSSIL_TILE.get());
    }
}
