package com.spectrobes.spectrobesmod.client.items.machines.renderer;

import com.spectrobes.spectrobesmod.client.items.machines.model.CyrusShopBlockItemModel;
import com.spectrobes.spectrobesmod.common.items.machines.CyrusShopBlockItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class CyrusShopBlockItemRenderer extends GeoItemRenderer<CyrusShopBlockItem> {

    public CyrusShopBlockItemRenderer() {
        super(new CyrusShopBlockItemModel());
    }
}
