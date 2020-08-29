package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.KrawlPropertiesBuilder;

public class KrawlRegistry {
    public static final KrawlProperties Swar_Properties
            = new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.OTHER)
            .withAtkLevel(150)
            .withAtkOffset(5)
            .withDefLevel(100)
            .withDefOffset(6)
            .withHpLevel(250)
            .withHpOffset(15)
            .withXpWorth(25)
            .build();
}
