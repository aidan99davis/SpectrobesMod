package com.spectrobes.spectrobesmod.common.spectrobes;

public class SpectrobeProperties{

    private Nature nature;
    private Stage stage;

    public SpectrobeProperties(Nature nature, Stage stage) {
        this.nature = nature;
        this.stage = stage;
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

    public enum Nature {
        CORONA,
        AURORA,
        FLASH
    }

    public enum Stage {
        CHILD,
        ADULT,
        EVOLVED,
        ULTIMATE
    }
}

