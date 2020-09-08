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
        this.world = spectrobe.world;
    }

    public boolean shouldExecute() {
        if(entity.getStage() == SpectrobeProperties.Stage.CHILD) {
            List<ItemEntity> lvt_1_1_ = entity.world.getEntitiesWithinAABB(ItemEntity.class, this.entity.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntitySpectrobe.MINERAL_SELECTOR);
            return !lvt_1_1_.isEmpty();
        }
        return false;
    }

    public void startExecuting() {
        List<ItemEntity> lvt_1_1_ = this.entity.world.getEntitiesWithinAABB(ItemEntity.class, this.entity.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntitySpectrobe.MINERAL_SELECTOR);
        if (!lvt_1_1_.isEmpty()) {
            this.entity.getNavigator().tryMoveToEntityLiving(lvt_1_1_.get(0), 1.2000000476837158D);
        }
    }

    public void resetTask() {
        ItemStack lvt_1_1_ = this.entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (!lvt_1_1_.isEmpty()) {
            this.eatMineral(lvt_1_1_.getStack());
            this.entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
        }

    }

    public void tick() {
        List<ItemEntity> lvt_1_1_ = this.entity.world.getEntitiesWithinAABB(ItemEntity.class, this.entity.getBoundingBox().grow(2.0D, 2.0D, 2.0D), EntitySpectrobe.MINERAL_SELECTOR);
        if (!lvt_1_1_.isEmpty()) {
            this.entity.getNavigator().tryMoveToEntityLiving((Entity)lvt_1_1_.get(0), 1.2000000476837158D);
            if(this.entity.getDistance(lvt_1_1_.get(0)) < 5) {
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
