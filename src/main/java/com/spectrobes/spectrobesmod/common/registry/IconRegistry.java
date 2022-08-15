package com.spectrobes.spectrobesmod.common.registry;

import com.spectrobes.spectrobesmod.SpectrobesInfo;
import com.spectrobes.spectrobesmod.common.spectrobes.SpectrobeIconInfo;

import java.util.HashMap;
import java.util.Map;

public class IconRegistry {
    private static final Map<String,SpectrobeIconInfo> icons = new HashMap<>();
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
        icons.put("segulos", new SpectrobeIconInfo("segulos", 32, 26));
        icons.put("harumi", new SpectrobeIconInfo("harumi", 22, 21));
        icons.put("harumite", new SpectrobeIconInfo("harumite", 32, 31));
        icons.put("grilda", new SpectrobeIconInfo("grilda", 32, 29));
        icons.put("grilden", new SpectrobeIconInfo("grilden", 32, 31));
        icons.put("zoza", new SpectrobeIconInfo("zoza", 31, 20));
        icons.put("zozane", new SpectrobeIconInfo("zozane", 32, 22));
        icons.put("nagu", new SpectrobeIconInfo("nagu", 32, 28));
        icons.put("mossari", new SpectrobeIconInfo("mossari", 26, 29));
        icons.put("mossarito", new SpectrobeIconInfo("mossarito", 29, 31));
        icons.put("aoi", new SpectrobeIconInfo("aoi", 32, 19));
        icons.put("gejio", new SpectrobeIconInfo("gejio", 25, 27));
        icons.put("bartor", new SpectrobeIconInfo("bartor", 32, 29));
        icons.put("bartolor", new SpectrobeIconInfo("bartolor", 32, 27));
        icons.put("mesa", new SpectrobeIconInfo("mesa", 22, 28));
        icons.put("dongor", new SpectrobeIconInfo("dongor", 24, 23));
        icons.put("inkana", new SpectrobeIconInfo("inkana", 18, 32));
        icons.put("danawa", new SpectrobeIconInfo("danawa", 20, 25));
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
