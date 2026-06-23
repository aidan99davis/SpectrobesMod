package com.spectrobes.spectrobesmod.common.entities.spectrobes.goals.child;

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

    @Override
    public boolean canUse() {
        if (entity.getOwner() != null || entity.getStage() != SpectrobeProperties.Stage.CHILD) {
            return false;
        }
        List<ItemEntity> minerals = entity.level().getEntitiesOfClass(
                ItemEntity.class,
                this.entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D),
                EntitySpectrobe.MINERAL_SELECTOR);
        return !minerals.isEmpty();
    }

    @Override
    public boolean canContinueToUse() {
        List<ItemEntity> minerals = entity.level().getEntitiesOfClass(
                ItemEntity.class,
                this.entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D),
                EntitySpectrobe.MINERAL_SELECTOR);
        return !minerals.isEmpty();
    }

    @Override
    public void start() {
        BlockPos blockpos = null;

        List<ItemEntity> minerals = this.entity.level().getEntitiesOfClass(
                ItemEntity.class,
                this.entity.getBoundingBox().inflate(2.0D, 2.0D, 2.0D),
                EntitySpectrobe.MINERAL_SELECTOR);

        for (ItemEntity mineral : minerals) {
            blockpos = mineral.blockPosition();
        }

        if (blockpos != null) {
            this.entity.getMoveControl().setWantedPosition(
                    blockpos.getX(), blockpos.getY(), blockpos.getZ(), 1.0D);
        }
    }

    @Override
    public void stop() {
        ItemStack held = this.entity.getItemBySlot(EquipmentSlot.MAINHAND);
        if (!held.isEmpty()) {
            this.eatMineral(held);
        }
        this.entity.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        this.entity.getNavigation().stop();
    }

    @Override
    public void tick() {
        List<ItemEntity> minerals = this.entity.level().getEntitiesOfClass(
                ItemEntity.class,
                this.entity.getBoundingBox().inflate(4.0D, 4.0D, 4.0D),
                EntitySpectrobe.MINERAL_SELECTOR);

        if (!minerals.isEmpty()) {
            this.entity.getNavigation().moveTo(minerals.get(0), 0.8D);
            if (this.entity.distanceTo(minerals.get(0)) < 5) {
                this.eatMineral(minerals.get(0).getItem());
                minerals.get(0).getItem().shrink(1);
            }
        }
    }

    private void eatMineral(ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            this.entity.applyMineral((MineralItem) itemStack.getItem());
        }
    }
}