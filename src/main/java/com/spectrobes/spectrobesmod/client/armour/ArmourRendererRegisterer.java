package com.spectrobes.spectrobesmod.client.armour;

import com.spectrobes.spectrobesmod.client.armour.renderer.BasicNppArmourRenderer;
import com.spectrobes.spectrobesmod.common.items.armour.BasicNppArmourItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ArmourRendererRegisterer {

    public static void registerRenderers() {
        GeoArmorRenderer.registerArmorRenderer(BasicNppArmourItem.class, new BasicNppArmourRenderer());
    }
}
