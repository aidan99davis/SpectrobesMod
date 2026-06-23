package com.spectrobes.spectrobesmod.client.items.weapons.renderer;

import com.spectrobes.spectrobesmod.client.items.weapons.model.BasicGloveItemModel;
import com.spectrobes.spectrobesmod.common.items.weapons.BasicGloveItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BasicGloveItemRenderer extends GeoItemRenderer<BasicGloveItem> {

    public BasicGloveItemRenderer() {
        super(new BasicGloveItemModel());
    }
}
