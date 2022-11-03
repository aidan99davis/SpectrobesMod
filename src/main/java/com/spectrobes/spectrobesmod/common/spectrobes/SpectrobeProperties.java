package com.spectrobes.spectrobesmod.common.spectrobes;

import net.minecraft.nbt.CompoundTag;

public class SpectrobeProperties{

    private Nature nature;
    private Stage stage;

    public SpectrobeProperties(Nature nature, Stage stage) {
        this.nature = nature;
        this.stage = stage;
    }

    public static SpectrobeProperties read(CompoundTag spectrobeProperties) {

        return new SpectrobeProperties(
                SpectrobeProperties.Nature.valueOf(spectrobeProperties.get("nature").getAsString()),
                SpectrobeProperties.Stage.valueOf(spectrobeProperties.get("stage").getAsString()));

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

    public CompoundTag write() {
        CompoundTag compoundnbt = new CompoundTag();
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

