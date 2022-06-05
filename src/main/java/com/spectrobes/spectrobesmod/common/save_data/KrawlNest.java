package com.spectrobes.spectrobesmod.common.save_data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.UUID;

public class KrawlNest implements INBTSerializable {
    public BlockPos position;
    public String dimension;

    public KrawlNest() {}

    public KrawlNest(BlockPos position, String dimension) {
        this.position = position;
        this.dimension = dimension;
    }

    @Override
    public INBT serializeNBT() {
        CompoundNBT nbtObj = new CompoundNBT();
        nbtObj.putString("dimension", dimension);
        nbtObj.putString("dimension", dimension);
        nbtObj.putInt("position_x", position.getX());
        nbtObj.putInt("position_y", position.getY());
        nbtObj.putInt("position_z", position.getZ());
        return nbtObj;
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        dimension = ((CompoundNBT)nbt).getString("dimension");
        int position_x = ((CompoundNBT)nbt).getInt("position_x");
        int position_y = ((CompoundNBT)nbt).getInt("position_y");
        int position_z = ((CompoundNBT)nbt).getInt("position_z");
        position = new BlockPos(position_x, position_y, position_z);
    }
}
