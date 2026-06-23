package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.BasicBlasterItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicBlasterItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BasicBlasterItemRenderer extends GeoItemRenderer<BasicBlasterItem> {

    public BasicBlasterItemRenderer() {
        super(new BasicBlasterItemModel());
    }
}
