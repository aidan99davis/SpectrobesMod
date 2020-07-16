package com.spectrobes.spectrobesmod.common.spectrobes;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.annotation.Nullable;
import java.util.UUID;

public class Spectrobe {

    @Nullable
    public UUID MasterUUID;
    @Required
    private String name;
    @Required
    private SpectrobeProperties properties;
    @Required
    private SpectrobeStats stats;
    @Nullable
    private Spectrobe evolution;
}
