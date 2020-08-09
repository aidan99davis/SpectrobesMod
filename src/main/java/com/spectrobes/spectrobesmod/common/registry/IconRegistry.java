package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;

import java.util.HashMap;
import java.util.Map;

public class IconRegistry {
    private static Map<String,SpectrobeIconInfo> icons = new HashMap<>();
    private static IconRegistry instance;

    public static void init() {
        instance = new IconRegistry();
    }

    //keys should be lowercase.
    public IconRegistry() {
        icons.put("komainu", new SpectrobeIconInfo("komainu", 20, 21));
        icons.put("komanoto", new SpectrobeIconInfo("komanoto", 32, 28));
        icons.put("spiko", new SpectrobeIconInfo("spiko", 30, 32));
        icons.put("spikan", new SpectrobeIconInfo("spikan", 32, 26));
    }

    public static IconRegistry getInstance() {
        if(instance == null) {
            init();
        }
        return instance;
    }

    public SpectrobeIconInfo getByName(String name) {
        if(icons.get(name.toLowerCase()) != null) {
            return icons.get(name.toLowerCase());
        } else {

            SpectrobesInfo.LOGGER.warn("COULD NOT FIND SPECIFIED ICON " + name.toLowerCase());
            return null;
        }
    }
}
