package com.spectrobes.spectrobesmod.common.items.minerals;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.MineralPropertiesBuilder;
import net.minecraft.item.Item;

public class MineralItem extends Item {
    public MineralProperties mineralProperties = new MineralPropertiesBuilder()
            .setAtkOffset(2)
            .setDefOffset(2)
            .setHpOffset(2)
            .setNature(SpectrobeProperties.Nature.CORONA)
            .setXpWorth(10)
            .build();

    public MineralItem(Properties properties) {
        super(properties);
        setRegistryName("mineral_item");
    }
}
