package com.spectrobes.spectrobesmod.client.armour.renderer;

import com.spectrobes.spectrobesmod.client.armour.model.BasicNppArmourModel;
import com.spectrobes.spectrobesmod.common.items.armour.BasicNppArmourItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class BasicNppArmourRenderer extends GeoArmorRenderer<BasicNppArmourItem> {

    public BasicNppArmourRenderer() {
        super(new BasicNppArmourModel());
    }
}
