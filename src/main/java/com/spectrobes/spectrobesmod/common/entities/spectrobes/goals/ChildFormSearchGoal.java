package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class ChildFormSearchGoal extends Goal {

    private EntitySpectrobe entity;
    private BlockPos target;

    public ChildFormSearchGoal(EntitySpectrobe spectrobe) {
        this.entity = spectrobe;
    }

    public boolean canUse() {
        if(entity.getStage() == SpectrobeProperties.Stage.CHILD && entity.isSearching()) {
            List<BlockPos> blocks = getMineralBlocksInArea();
            return !blocks.isEmpty();
        }
        return false;
    }

    public void start() {
        List<BlockPos> blocks = getMineralBlocksInArea();
        if (!blocks.isEmpty()) {
            target = getClosestMineral(blocks);
            this.entity.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.0D);
        }
    }

    private List<BlockPos> getMineralBlocksInArea() {
        Iterable<BlockPos> blocks = BlockPos.betweenClosed((int)entity.getX() - 8, (int)entity.getY() - 8, (int)entity.getZ() - 8, (int)entity.getX() + 8, (int)entity.getY() + 8, (int)entity.getZ() + 8);

        List<BlockPos> mineralBlocks = new ArrayList<>();

        blocks.forEach((pos) -> {
            Block block = entity.level.getBlockState(pos).getBlock();
            if(block.getRegistryName().toString().contains("mineral_block")
                    || block.getRegistryName().toString().contains("fossil_block")
                    || block.getRegistryName().toString().contains("marble_ore")
                    || block.getRegistryName().toString().contains("metalium_ore")
                    || block.getRegistryName().toString().contains("titanium_ore")) {
                mineralBlocks.add(pos.immutable());
            }
        });
        return mineralBlocks;
    }

    private BlockPos getClosestMineral(List<BlockPos> lvt_1_1_) {
        BlockPos closest = lvt_1_1_.get(0).immutable();

        for (BlockPos pos : lvt_1_1_) {
            if(entity.distanceToSqr(pos.getX(), pos.getY(), pos.getZ()) < entity.distanceToSqr(closest.getX(), closest.getY(), closest.getZ())) {
                closest = pos.immutable();
            }
        }

        return closest;
    }

    @Override
    public boolean canContinueToUse() {
        if(target != null) {
            if(this.entity.distanceToSqr((double)target.getX(), (double)target.getY(), (double)target.getZ()) < 2) {
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
            this.entity.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 1.0D);
        }
    }
}
