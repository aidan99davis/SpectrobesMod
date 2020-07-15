package com.spectrobes.spectrobesmod.common.entities;

public class SpectrobeProperties{
    private Nature nature;
    private Stage stage;

    public SpectrobeProperties() {

    }

    public Nature getNature() {
        return nature;
    }

    public Stage getStage() {
        return stage;
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

