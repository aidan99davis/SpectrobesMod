package com.spectrobes.spectrobesmod.common.save_data;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class KrawlNest implements INBTSerializable {
    public BlockPos position;
    public String dimension;
    public int vortex_absorbed;
    public int stage;
    public boolean alive;

    public KrawlNest() {}

    public KrawlNest(BlockPos position, String dimension) {
        this.position = position;
        this.dimension = dimension;
        this.stage = 1;
        this.vortex_absorbed = 0;
        this.alive = true;
    }

    public void absorbVortexes(int vortexes) {
        this.vortex_absorbed = this.vortex_absorbed +  vortexes;
    }

    public int getAbsorbedVortexes() {
        return this.vortex_absorbed;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    @Override
    public Tag serializeNBT() {
        CompoundTag nbtObj = new CompoundTag();
        nbtObj.putString("dimension", dimension);
        nbtObj.putInt("stage", stage);
        nbtObj.putInt("position_x", position.getX());
        nbtObj.putInt("position_y", position.getY());
        nbtObj.putInt("position_z", position.getZ());
        nbtObj.putInt("vortex_absorbed", vortex_absorbed);
        nbtObj.putBoolean("alive", alive);
        return nbtObj;
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        dimension = ((CompoundTag)nbt).getString("dimension");
        stage = ((CompoundTag)nbt).getInt("stage");
        vortex_absorbed = ((CompoundTag)nbt).getInt("vortex_absorbed");
        alive = ((CompoundTag)nbt).getBoolean("alive");
        int position_x = ((CompoundTag)nbt).getInt("position_x");
        int position_y = ((CompoundTag)nbt).getInt("position_y");
        int position_z = ((CompoundTag)nbt).getInt("position_z");
        position = new BlockPos(position_x, position_y, position_z);
    }

    public void setDead() {
        this.alive = false;
        this.stage = 1;
    }

    public boolean isAlive() { return alive; }
}
