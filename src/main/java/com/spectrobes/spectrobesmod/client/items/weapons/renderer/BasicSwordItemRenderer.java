package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.BasicSwordItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicSwordItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BasicSwordItemRenderer extends GeoItemRenderer<BasicSwordItem> {

    public BasicSwordItemRenderer() {
        super(new BasicSwordItemModel());
    }
}
