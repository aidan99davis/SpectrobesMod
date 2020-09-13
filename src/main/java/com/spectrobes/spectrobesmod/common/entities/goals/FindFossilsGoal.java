//package com.spectrobes.spectrobesmod.common.entities.goals;
//
//import com.spectrobes.spectrobesmod.common.blocks.SpectrobesBlocks;
//import com.spectrobes.spectrobesmod.common.entities.spectrobes.EntitySpectrobe;
//import com.spectrobes.spectrobesmod.common.items.minerals.MineralItem;
//import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.ai.goal.Goal;
//import net.minecraft.entity.item.ItemEntity;
//import net.minecraft.entity.passive.BeeEntity;
//import net.minecraft.inventory.EquipmentSlotType;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tags.BlockTags;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.IWorldReader;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Predicate;
//
//public class FindFossilsGoal extends Goal {
//    private final Predicate<BlockState> Fossil_Block = (blockState) -> {
//        if(blockState.getBlock().equals(SpectrobesBlocks.fossil_block)) {
//            return true;
//        }
//        return false;
//    };
//    private IWorldReader world;
//    private EntitySpectrobe entity;
//
//    public FindFossilsGoal(EntitySpectrobe spectrobe) {
//        this.entity = spectrobe;
//        this.world = spectrobe.world;
//    }
//
//    public boolean shouldExecute() {
//        if(entity.getStage() == SpectrobeProperties.Stage.CHILD) {
//            List<ItemEntity> lvt_1_1_ = entity.world.getEntitiesWithinAABB(ItemEntity.class, this.entity.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntitySpectrobe.MINERAL_SELECTOR);
//            return !lvt_1_1_.isEmpty();
//        }
//        return false;
//    }
//
//    public void startExecuting() {
//        List<Block> lvt_1_1_ = this.entity.blockworld.getEntitiesWithinAABB(ItemEntity.class, this.entity.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntitySpectrobe.MINERAL_SELECTOR);
//        if (!lvt_1_1_.isEmpty()) {
//            this.entity.getNavigator().tryMoveToXYZ();
//
//        }
//    }
//
//    public void tick() {
//    }
//
//    private Optional<BlockPos> findFossilOre(Predicate<BlockState> fossilPredicate, double distance) {
//        BlockPos blockpos = new BlockPos(this.entity);
//        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
//
//        for(int i = 0; (double)i <= distance; i = i > 0 ? -i : 1 - i) {
//            for(int j = 0; (double)j < distance; ++j) {
//                for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
//                    for(int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
//                        blockpos$mutable.setPos(blockpos).move(k, i - 1, l);
//                        if (blockpos.withinDistance(blockpos$mutable, distance) && fossilPredicate.test(this.world.getBlockState(blockpos$mutable))) {
//                            return Optional.of(blockpos$mutable);
//                        }
//                    }
//                }
//            }
//        }
//
//        return Optional.empty();
//    }
//}
