package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.common.blocks.FossilBlock;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import java.util.ArrayList;
import java.util.List;

public class FindFossilsGoal extends Goal {

    private IWorldReader world;
    private EntitySpectrobe entity;
    private BlockPos target;

    public FindFossilsGoal(EntitySpectrobe spectrobe) {
        this.entity = spectrobe;
        this.world = spectrobe.world;
    }

    public boolean shouldExecute() {
        if(entity.getStage() == SpectrobeProperties.Stage.CHILD && entity.isSearching()) {
            List<BlockPos> lvt_1_1_ = getFossilBlocksInArea();
            return !lvt_1_1_.isEmpty();
        }
        return false;
    }

    public void startExecuting() {
        List<BlockPos> lvt_1_1_ = getFossilBlocksInArea();
        if (!lvt_1_1_.isEmpty()) {
            target = getClosestFossil(lvt_1_1_);
            this.entity.getNavigator().setPath(this.entity.getNavigator().getPathToPos(target, 2), 2);
        }
    }

    private List<BlockPos> getFossilBlocksInArea() {
        Iterable<BlockPos> blocks = BlockPos.getAllInBoxMutable((int)entity.getPosX() - 8, (int)entity.getPosY() - 8, (int)entity.getPosZ() - 8, (int)entity.getPosX() + 8, (int)entity.getPosY() + 8, (int)entity.getPosZ() + 8);

        List<BlockPos> fossilBlocks = new ArrayList<>();

        blocks.forEach((pos) -> {
            if(entity.world.getBlockState(pos).getBlock() instanceof FossilBlock) {
                fossilBlocks.add(pos.toImmutable());
            }
        });
        return fossilBlocks;
    }

    private BlockPos getClosestFossil(List<BlockPos> lvt_1_1_) {
        BlockPos closest = lvt_1_1_.get(0).toImmutable();

        for (BlockPos pos : lvt_1_1_) {
            if(entity.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) < entity.getDistanceSq(closest.getX(), closest.getY(), closest.getZ())) {
                closest = pos.toImmutable();
            }
        }

        return closest;
    }

    @Override
    public boolean shouldContinueExecuting() {
        if(target != null) {
            if(this.entity.getDistanceSq((double)target.getX(), (double)target.getY(), (double)target.getZ()) < 2) {
                target = null;
                entity.setState(1);
                return false;
            }
            return true;
        } else {
            entity.setState(0);
            return false;
        }
    }

    public void tick() {
        if(target != null) {
            this.entity.getNavigator().setPath(this.entity.getNavigator().getPathToPos(target, 1), 1);
        }
    }
}
