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
        icons.put("samukabu", new SpectrobeIconInfo("samukabu", 24, 27));
        icons.put("samurite", new SpectrobeIconInfo("samurite", 31, 26));
        icons.put("kubaku", new SpectrobeIconInfo("kubaku", 21, 22));
        icons.put("kuganon", new SpectrobeIconInfo("kuganon", 21, 22));
        icons.put("shakin", new SpectrobeIconInfo("shakin", 22, 18));
        icons.put("shakor", new SpectrobeIconInfo("shakor", 32, 29));
        icons.put("vilar", new SpectrobeIconInfo("vilar", 27, 27));
        icons.put("vilamasta", new SpectrobeIconInfo("vilamasta", 28, 32));
        icons.put("segu", new SpectrobeIconInfo("segu", 26, 25));
        icons.put("harumi", new SpectrobeIconInfo("harumi", 22, 21));
        icons.put("grilda", new SpectrobeIconInfo("grilda", 32, 29));
        icons.put("grilden", new SpectrobeIconInfo("grilden", 32, 31));
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

            SpectrobesInfo.LOGGER.error("COULD NOT FIND SPECIFIED ICON " + name.toLowerCase());
            return null;
        }
    }
}
