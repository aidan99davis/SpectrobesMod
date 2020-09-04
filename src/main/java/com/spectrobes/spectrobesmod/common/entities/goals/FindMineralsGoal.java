package com.spectrobes.spectrobesmod.common.entities.goals;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
import com.spectrobes.spectrobesmod.common.items.minerals.Mineral;
import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
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
        SpectrobesInfo.LOGGER.info("EATING MINERAL - 0");

        if(entity.getStage() == SpectrobeProperties.Stage.CHILD) {
            List<ItemEntity> lvt_1_1_ = entity.world.getEntitiesWithinAABB(ItemEntity.class, this.entity.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntitySpectrobe.MINERAL_SELECTOR);
            return !lvt_1_1_.isEmpty() || !entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND).isEmpty();
        }
        return false;
    }

    public void startExecuting() {
        List<ItemEntity> lvt_1_1_ = this.entity.world.getEntitiesWithinAABB(ItemEntity.class, this.entity.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntitySpectrobe.MINERAL_SELECTOR);
        if (!lvt_1_1_.isEmpty()) {
            this.entity.getNavigator().tryMoveToEntityLiving((Entity)lvt_1_1_.get(0), 1.2000000476837158D);
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
        SpectrobesInfo.LOGGER.info("EATING MINERAL - 1");
        List<ItemEntity> lvt_1_1_ = this.entity.world.getEntitiesWithinAABB(ItemEntity.class, this.entity.getBoundingBox().grow(8.0D, 8.0D, 8.0D), DolphinEntity.ITEM_SELECTOR);
        ItemStack lvt_2_1_ = this.entity.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (!lvt_2_1_.isEmpty()) {
            this.entity.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
        } else if (!lvt_1_1_.isEmpty()) {
            this.eatMineral(lvt_1_1_.get(0).getItem());
            lvt_1_1_.get(0).remove();
            this.entity.getNavigator().tryMoveToEntityLiving((Entity)lvt_1_1_.get(0), 1.2000000476837158D);
        }

    }

    private void eatMineral(ItemStack itemStack) {
        SpectrobesInfo.LOGGER.info("EATING MINERAL - 2");
        if (!itemStack.isEmpty()) {
            this.entity.applyMineral((MineralItem)itemStack.getItem());
        }
    }
}
