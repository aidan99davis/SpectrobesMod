package com.spectrobes.spectrobesmod.client.items.armour.basic;

import com.spectrobes.spectrobesmod.common.items.armour.BasicNppArmourItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BasicNppArmourItemRenderer extends GeoItemRenderer<BasicNppArmourItem> {
    public BasicNppArmourItemRenderer() {
        super(new BasicNppArmourItemModel());
    }
}