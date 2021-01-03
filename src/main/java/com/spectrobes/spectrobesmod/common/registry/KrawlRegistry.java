package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.KrawlPropertiesBuilder;

public class KrawlRegistry {
    public static final KrawlProperties Swar_Properties
            = new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.CORONA)
            .withAtkLevel(150)
            .withAtkOffset(5)
            .withDefLevel(100)
            .withDefOffset(6)
            .withHpLevel(250)
            .withHpOffset(15)
            .withXpWorth(25)
            .build();

    public static final KrawlProperties Subar_Properties
            = new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.FLASH)
            .withAtkLevel(150)
            .withAtkOffset(5)
            .withDefLevel(100)
            .withDefOffset(6)
            .withHpLevel(250)
            .withHpOffset(15)
            .withXpWorth(25)
            .build();

    public static final KrawlProperties Vizbar_Properties
            = new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.AURORA)
            .withAtkLevel(150)
            .withAtkOffset(5)
            .withDefLevel(100)
            .withDefOffset(6)
            .withHpLevel(250)
            .withHpOffset(15)
            .withXpWorth(25)
            .build();

    public static final KrawlProperties Gris_Properties
            = new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.CORONA)
            .withAtkLevel(150)
            .withAtkOffset(5)
            .withDefLevel(100)
            .withDefOffset(6)
            .withHpLevel(250)
            .withHpOffset(15)
            .withXpWorth(25)
            .build();

    public static final KrawlProperties Grisen_Properties
            = new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.AURORA)
            .withAtkLevel(150)
            .withAtkOffset(5)
            .withDefLevel(100)
            .withDefOffset(6)
            .withHpLevel(250)
            .withHpOffset(15)
            .withXpWorth(25)
            .build();

    public static final KrawlProperties Vortex_Properties =
            new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.OTHER)
            .withAtkLevel(0)
            .withAtkOffset(0)
            .withDefLevel(0)
            .withDefOffset(0)
            .withHpLevel(10000000)
            .withHpOffset(0)
            .withXpWorth(0)
            .build();
}
