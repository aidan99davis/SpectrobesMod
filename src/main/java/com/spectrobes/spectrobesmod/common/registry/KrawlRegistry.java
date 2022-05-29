package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.common.krawl.KrawlProperties;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.KrawlPropertiesBuilder;

public class KrawlRegistry {
    public static final KrawlProperties Swar_Properties
            = new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.CORONA)
            .withAtkLevel(50)
            .withAtkOffset(5)
            .withDefLevel(40)
            .withDefOffset(6)
            .withHpLevel(200)
            .withHpOffset(15)
            .withXpWorth(25)
            .withLevel(1)
            .build();

    public static final KrawlProperties Subar_Properties
            = new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.FLASH)
            .withAtkLevel(80)
            .withAtkOffset(10)
            .withDefLevel(100)
            .withDefOffset(16)
            .withHpLevel(400)
            .withHpOffset(30)
            .withXpWorth(50)
            .withGuraWorth(150)
            .withLevel(5)
            .build();

    public static final KrawlProperties Vizbar_Properties
            = new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.AURORA)
            .withAtkLevel(125)
            .withAtkOffset(15)
            .withDefLevel(200)
            .withDefOffset(18)
            .withHpLevel(700)
            .withHpOffset(30)
            .withXpWorth(100)
            .withGuraWorth(250)
            .withLevel(10)
            .build();

    public static final KrawlProperties Gris_Properties
            = new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.CORONA)
            .withAtkLevel(100)
            .withAtkOffset(15)
            .withDefLevel(125)
            .withDefOffset(12)
            .withHpLevel(200)
            .withHpOffset(10)
            .withXpWorth(50)
            .withGuraWorth(100)
            .withLevel(5)
            .build();

    public static final KrawlProperties Grisen_Properties
            = new KrawlPropertiesBuilder()
            .withNature(SpectrobeProperties.Nature.AURORA)
            .withAtkLevel(175)
            .withAtkOffset(18)
            .withDefLevel(150)
            .withDefOffset(15)
            .withHpLevel(400)
            .withHpOffset(20)
            .withXpWorth(100)
            .withGuraWorth(150)
            .withLevel(10)
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
            .withGuraWorth(0)
            .withLevel(0)
            .build();
}
