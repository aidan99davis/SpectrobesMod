package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class FindMineralsGoal extends Goal {
    private final EntitySpectrobe entity;

    public FindMineralsGoal(EntitySpectrobe spectrobe) {
        this.entity = spectrobe;
    }

    public boolean canUse() {
        if(entity.getStage() == SpectrobeProperties.Stage.CHILD && entity.getOwner() == null) {
            List<ItemEntity> minerals = entity.level.getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), EntitySpectrobe.MINERAL_SELECTOR);
            return !minerals.isEmpty();
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        List<ItemEntity> lvt_1_1_ = entity.level.getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), EntitySpectrobe.MINERAL_SELECTOR);
        return !lvt_1_1_.isEmpty() && (entity.isSearching() || entity.getOwner() == null);
    }

    public void start() {
        BlockPos blockpos = null;

        List<ItemEntity> minerals = this.entity.level.getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(2.0D, 2.0D, 2.0D), EntitySpectrobe.MINERAL_SELECTOR);

        for (ItemEntity mineral :
                minerals) {
            blockpos = mineral.blockPosition();
        }

        if (blockpos != null) {
            this.entity.getMoveControl().setWantedPosition(blockpos.getX(), blockpos.getY(), blockpos.getZ(), 1.0D);
        }
    }

    public void stop() {
        ItemStack lvt_1_1_ = this.entity.getItemBySlot(EquipmentSlot.MAINHAND);
        if (!lvt_1_1_.isEmpty()) {
            this.eatMineral(lvt_1_1_);
        }
        this.entity.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        this.entity.getNavigation().stop();

    }

    public void tick() {
        List<ItemEntity> lvt_1_1_ = this.entity.level.getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(4.0D, 4.0D, 4.0D), EntitySpectrobe.MINERAL_SELECTOR);
        if (!lvt_1_1_.isEmpty()) {
            this.entity.getNavigation().moveTo(lvt_1_1_.get(0), 0.8000000476837158D);
            if(this.entity.distanceTo(lvt_1_1_.get(0)) < 5) {
                this.eatMineral(lvt_1_1_.get(0).getItem());
                lvt_1_1_.get(0).getItem().shrink(1);
            }
        }

    }

    private void eatMineral(ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            this.entity.applyMineral((MineralItem)itemStack.getItem());
        }
    }
}
