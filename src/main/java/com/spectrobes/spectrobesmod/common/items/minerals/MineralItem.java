package com.spectrobes.spectrobesmod.common.items.minerals;

import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeProperties;
import com.spectrobes.spectrobesmod.util.MineralPropertiesBuilder;
import net.minecraft.item.Item;

public class MineralItem extends Item {
    public MineralProperties mineralProperties = null;

    public MineralItem(Properties properties, String registryName, MineralProperties mineralProperties) {
        super(properties);
        setRegistryName(registryName);
        this.mineralProperties = mineralProperties;
    }
}
