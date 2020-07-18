package com.spectrobes.spectrobesmod.common.spectrobes;

import net.minecraft.nbt.CompoundNBT;
import org.lwjgl.system.CallbackI;

public class SpectrobeProperties{

    private Nature nature;
    private Stage stage;

    public SpectrobeProperties(Nature nature, Stage stage) {
        this.nature = nature;
        this.stage = stage;
    }

    public static SpectrobeProperties read(CompoundNBT spectrobeProperties) {

        return new SpectrobeProperties(
                SpectrobeProperties.Nature.valueOf(spectrobeProperties.get("nature").getString()),
                SpectrobeProperties.Stage.valueOf(spectrobeProperties.get("stage").getString()));

    }

    public Nature getNature() {
        return nature;
    }

    public Stage getStage() {
        return stage;
    }

    public void setNature(Nature nature) {
        this.nature = nature;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public CompoundNBT write() {
        CompoundNBT compoundnbt = new CompoundNBT();
        compoundnbt.putString("nature", nature.toString());
        compoundnbt.putString("stage", stage.toString());

        return compoundnbt;
    }

    public SpectrobeProperties copy() {
        return new SpectrobeProperties(nature,stage);
    }

    public enum Nature {
        CORONA,
        AURORA,
        FLASH,
        OTHER
    }

    public enum Stage {
        CHILD,
        ADULT,
        EVOLVED,
        ULTIMATE
    }
}

