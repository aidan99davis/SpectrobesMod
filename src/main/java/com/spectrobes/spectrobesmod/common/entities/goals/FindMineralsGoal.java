package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IWorldReader;

import java.util.List;

public class FindMineralsGoal extends Goal {
    private IWorldReader world;
    private EntitySpectrobe entity;

    public FindMineralsGoal(EntitySpectrobe spectrobe) {
        this.entity = spectrobe;
        this.world = spectrobe.level;
    }

    public boolean canUse() {
        if(entity.getStage() == SpectrobeProperties.Stage.CHILD && (entity.isSearching() || entity.getOwner() == null)) {
            List<ItemEntity> lvt_1_1_ = entity.level.getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), EntitySpectrobe.MINERAL_SELECTOR);
            return !lvt_1_1_.isEmpty();
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        List<ItemEntity> lvt_1_1_ = entity.level.getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), EntitySpectrobe.MINERAL_SELECTOR);
        return !lvt_1_1_.isEmpty() && (entity.isSearching() || entity.getOwner() == null);
    }

    public void start() {
        List<ItemEntity> lvt_1_1_ = this.entity.level.getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), EntitySpectrobe.MINERAL_SELECTOR);
        if (!lvt_1_1_.isEmpty()) {
            this.entity.getNavigation().moveTo(lvt_1_1_.get(0), 0.8000000476837158D);
        }
    }

    public void stop() {
        ItemStack lvt_1_1_ = this.entity.getItemBySlot(EquipmentSlotType.MAINHAND);
        if (!lvt_1_1_.isEmpty()) {
            this.eatMineral(lvt_1_1_.getStack());
        }
        this.entity.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
        this.entity.getNavigation().stop();

    }

    public void tick() {
        List<ItemEntity> lvt_1_1_ = this.entity.level.getEntitiesOfClass(ItemEntity.class, this.entity.getBoundingBox().inflate(2.0D, 2.0D, 2.0D), EntitySpectrobe.MINERAL_SELECTOR);
        if (!lvt_1_1_.isEmpty()) {
            this.entity.getNavigation().moveTo((Entity)lvt_1_1_.get(0), 0.8000000476837158D);
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
